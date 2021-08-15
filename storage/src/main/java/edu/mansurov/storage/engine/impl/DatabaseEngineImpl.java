package edu.mansurov.storage.engine.impl;

import edu.mansurov.storage.engine.DatabaseEngine;

public class DatabaseEngineImpl implements DatabaseEngine {

    public void takeData(byte[] data) {
        System.out.println("data taken..");
        System.out.println(new String(data));
    }

    public void takeData(String query) {

    }
}
