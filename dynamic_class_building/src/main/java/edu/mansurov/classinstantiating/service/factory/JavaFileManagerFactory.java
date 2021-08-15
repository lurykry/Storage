package edu.mansurov.classinstantiating.service.factory;

import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.util.Map;

public interface JavaFileManagerFactory {
    JavaFileManager createJavaFileManager(Map<String, JavaFileObject> pool);
}
