package edu.mansurov.classinstantiating.service;

public interface JvmContextManager {

    /**
     * Dynamically loads class to runtime
     * @param cls Json representation of java class
     */
    Object addClassToRuntime(String cls);
}
