package edu.mansurov.classinstantiating.service.factory.impl;

import edu.mansurov.classinstantiating.service.factory.JavaFileManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.*;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

public class JavaFileManagerFactoryImpl implements JavaFileManagerFactory {
    private final Logger logger = LoggerFactory.getLogger(JavaFileManagerFactoryImpl.class);

    public JavaFileManager createJavaFileManager(Map<String, JavaFileObject> pool) {
        return new ForwardingJavaFileManager(
                ToolProvider
                        .getSystemJavaCompiler()
                        .getStandardFileManager(
                                (DiagnosticListener) diagnostic -> logger.error(
                                "Error on line {} in {}",
                                diagnostic.getLineNumber(),
                                diagnostic.getSource()),
                                Locale.getDefault(),
                                Charset.defaultCharset())) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
                logger.info("pool.get(className):" + className);
                return pool.get(className);
            }
        };
    }
}
