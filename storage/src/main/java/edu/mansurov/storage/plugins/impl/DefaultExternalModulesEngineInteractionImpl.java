package edu.mansurov.storage.plugins.impl;

import edu.mansurov.storage.engine.DatabaseEngine;
import edu.mansurov.storage.engine.impl.DatabaseEngineImpl;
import edu.mansurov.storage.plugins.ExternalModulesEngineInteraction;

public class DefaultExternalModulesEngineInteractionImpl implements ExternalModulesEngineInteraction {
    private final DatabaseEngine databaseEngine = new DatabaseEngineImpl();

    public void transferData(byte[] data) {
        databaseEngine.takeData(data);
    }
}
