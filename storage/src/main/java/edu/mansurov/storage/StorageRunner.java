package edu.mansurov.storage;

import edu.mansurov.storage.engine.DatabaseEngine;
import edu.mansurov.storage.engine.impl.DatabaseEngineImpl;

import java.util.Scanner;

public class StorageRunner {
    public static void main(String[] args) {
        DatabaseEngine databaseEngine = new DatabaseEngineImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Yo, ща будем данные хранить..");

        String input = scanner.nextLine();

        while(!input.equals("quit")) {
            input = scanner.nextLine();
            databaseEngine.takeData(input.getBytes());
        }
    }
}
