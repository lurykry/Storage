package edu.mansurov.classinstantiating.classloader;

import java.util.Map;

public class DynamicClassLoader extends ClassLoader {
    private final Map<String, Class<?>> classPool;

    public DynamicClassLoader(Map<String, Class<?>> classPool) {
        this.classPool=classPool;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        @SuppressWarnings("rawtypes")
        Class clazz = classPool.get(name);

        if(clazz != null) return clazz;
        else return super.findClass(name);
    }
}
