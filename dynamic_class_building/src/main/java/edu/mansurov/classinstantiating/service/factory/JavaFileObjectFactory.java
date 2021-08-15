package edu.mansurov.classinstantiating.service.factory;

import javax.tools.JavaFileObject;
import java.io.OutputStream;

public interface JavaFileObjectFactory {
    JavaFileObject createJavaFileObject(String source, OutputStream outputStream, String fullClassName);
}
