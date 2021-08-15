package edu.mansurov.classinstantiating.service.impl;

import edu.mansurov.classinstantiating.classloader.DynamicClassLoader;
import edu.mansurov.classinstantiating.domain.JavaSourceCode;
import edu.mansurov.classinstantiating.service.JsonParser;
import edu.mansurov.classinstantiating.service.JvmContextManager;
import edu.mansurov.classinstantiating.service.factory.JavaFileManagerFactory;
import edu.mansurov.classinstantiating.service.factory.JavaFileObjectFactory;
import edu.mansurov.classinstantiating.service.factory.impl.JavaFileManagerFactoryImpl;
import edu.mansurov.classinstantiating.service.factory.impl.JavaFileObjectFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.tools.JavaFileObject.Kind.SOURCE;

public class JvmContextManagerImpl implements JvmContextManager {
    private final Logger logger = LoggerFactory.getLogger(JvmContextManagerImpl.class);
    private final static String USER_DIR = System.getProperty("user.dir");

    private final JsonParser jsonParser = new JsonParserImpl();
    private final Map<String, Class<?>> classPool = new HashMap<>();

    private final JavaFileManagerFactory javaFileManagerFactory =  new JavaFileManagerFactoryImpl();
    private final JavaFileObjectFactory javaFileObjectFactory = new JavaFileObjectFactoryImpl();

    private final Map<String, JavaFileObject> pool = new HashMap<>();
    private final  ClassLoader dynamicClassLoader = new DynamicClassLoader(classPool);

    private final Object oLock = new Object();
    private final JavaFileManager javaFileManager;

    public JvmContextManagerImpl() {
        javaFileManager = new JavaFileManagerFactoryImpl().createJavaFileManager(pool);
    }

    public Object addClassToRuntime(String cls) {
        JavaSourceCode javaSourceCode = jsonParser.toJavaSourceCodeParsing(cls);

        try {
            return instantiateDynamicClassesMap(javaSourceCode);
        } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object instantiateDynamicClassesMap(JavaSourceCode javaSourceCode) throws IllegalAccessException, InstantiationException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        return createDynamicClass(javaSourceCode.getSource());
    }

    public Object createDynamicClass(String source) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        String packageName = getStringInBetween(source, "package ", ";");
        String className = getStringInBetween(source, "public class ", " implements");
        String fullClassName = (packageName + "." + className).replace('.', '/');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        JavaFileObject javaFileObject = javaFileObjectFactory.createJavaFileObject(source, outputStream, fullClassName);

        pool.put((packageName + "." + className), javaFileObject);

//		// requires adding an interface beforehand
//		// https://stackoverflow.com/a/21544850/413032
//        List<String> optionList = new ArrayList<String>();
//        optionList.add("-classpath");
//        optionList.add(System.getProperty("java.class.path") + File.pathSeparator + "dist/DynamicClasses.jar");

        JavaCompiler.CompilationTask inMemoryCompilationTask = ToolProvider
                .getSystemJavaCompiler()
                .getTask(null, javaFileManager, null, null, null, pool.values());

        List<String> options = Arrays.asList("-d", USER_DIR);

        JavaCompiler.CompilationTask fileWriterCompilationTask = ToolProvider
                .getSystemJavaCompiler()
                .getTask(null, null, null, options, null, pool.values());

        if (!inMemoryCompilationTask.call()) logger.error("inMemoryCompilationTask Task Error!");
        if (!fileWriterCompilationTask.call()) logger.error("fileWriterCompilationTask Task Err!");


        addClassIntoDynamicJarPackage(fullClassName);

        byte[] bytes = outputStream.toByteArray();
        if (bytes.length == 0) {
            logger.error("bytes zero.");
            return null;
        }

//use the unsafe class to load in the class bytes
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // unsafe.defineAnonymousClass(Class.class, bytes, null);
        Class aClass = unsafe.defineClass(fullClassName, bytes, 0, bytes.length, dynamicClassLoader, new ProtectionDomain(null, null));

        Object o = aClass.newInstance();
        classPool.put(fullClassName.replace('/', '.'), o.getClass());

// Class thisClass = Class.forName(fullClassName.replace('/', '.'));
        logger.info(o.getClass().getName() + " class generated.");

        return o;
    }

    private void addClassIntoDynamicJarPackage(String fullClassName) {
        checkDynamicsJar();
        execShellCommand("jar -ufv dynamics.jar " + fullClassName + ".class");
    }

    private void checkDynamicsJar() {
        File dynamicsJar = new File(USER_DIR + "/dynamics.jar");

        if (!dynamicsJar.exists()) {
            execShellCommand("echo SLM > empty.txt");
            execShellCommand("jar -cfv dynamics.jar empty.txt");
        }
    }

    private void execShellCommand(String command) {
        logger.info("Running> " + command);

        try { getProcessAndReadOutput(command); }
        catch (IOException e) { logger.error("error while executing shell command [ {} ] dtl: " + e.getMessage(), command); }
    }

    private void getProcessAndReadOutput(String command) throws IOException {
        Process process = getProcess(command);
        readProcessOutput(process);
    }

    private Process getProcess(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

        return isWindows
                ? runtime.exec(String.format("cmd.exe /c %s", command))
                : runtime.exec(String.format("sh -c %s", command));
    }

    private void readProcessOutput(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null)
            logger.info(line);

        reader.close();
    }

    private String getStringInBetween(String exp, String before, String after) {
        Pattern ptrnBtw = Pattern.compile(Pattern.quote(before) + "(.+?)" + Pattern.quote(after));
        Matcher matcher = ptrnBtw.matcher(exp);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}
