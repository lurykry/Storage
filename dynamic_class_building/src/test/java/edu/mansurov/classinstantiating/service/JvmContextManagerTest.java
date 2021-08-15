package edu.mansurov.classinstantiating.service;

import edu.mansurov.classinstantiating.service.impl.JvmContextManagerImpl;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class JvmContextManagerTest {
    private static final String cls = "{" +
            "\"className\": \"edu.mansurov.Person\"," +
            "\"id\": \"1\"," +
            "\"name\": \"Jack\"" +
            "}";

    JvmContextManager jvmContextManager = new JvmContextManagerImpl();

    @Test
    public void test () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object o = jvmContextManager.addClassToRuntime(cls);
        Class<?> aClass = o.getClass();
        Object result = aClass.getMethod("getName").invoke(o);
        System.out.println(result.toString());
    }
}
