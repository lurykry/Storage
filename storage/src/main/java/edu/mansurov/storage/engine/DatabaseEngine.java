package edu.mansurov.storage.engine;

public interface DatabaseEngine {
    void takeData(byte[] data);
    void takeData(String query);
}
