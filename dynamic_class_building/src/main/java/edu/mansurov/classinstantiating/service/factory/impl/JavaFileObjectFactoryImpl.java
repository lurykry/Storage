package edu.mansurov.classinstantiating.service.factory.impl;

import edu.mansurov.classinstantiating.service.factory.JavaFileObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.OutputStream;
import java.net.URI;

import static javax.tools.JavaFileObject.Kind.SOURCE;

public class JavaFileObjectFactoryImpl implements JavaFileObjectFactory {
    private final Logger logger = LoggerFactory.getLogger(JavaFileObjectFactoryImpl.class);

    public JavaFileObject createJavaFileObject(String source, OutputStream outputStream, String fullClassName) {
        return  new SimpleJavaFileObject(URI.create(fullClassName + ".java"),
                SOURCE) {

            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }

            @Override
            public OutputStream openOutputStream() {
                return outputStream;
            }
        };
    }
}
