package org.db.lamb.system;
import org.db.lamb.services.DatabaseService;
import org.db.lamb.services.DocumentService;
import org.db.lamb.services.EntryService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class SystemMethods {
    // console input handler
    public static boolean handleInput(String input) {

        // no input
        if(input.isEmpty()) {
            System.out.println("Query typo : Input can not be resolved, empty query found.");
            return true; // no more checks needed down.
        }

        // syntax check
        else if(input.charAt(input.length()-1) != ';') {
            System.out.println("Missing ';' : Check syntax at " + "'" + input + "'" + "^");
            return true; // no more checks needed down.
        }

        // exit the system
        else if(input.equals("exit;")) {
            System.out.println("Good Bye!");
            return false; // no more checks needed down.
        }

        // show help text
        else if(input.equals("help;")) {
            showHelp();
            return true; // no more checks needed down.
        }

        else if(input.equals("dump;")) {
            SystemVariables.currentDB = null;
            SystemVariables.dbConfig = null;
            SystemVariables.currentDoc = null;
            SystemVariables.entries = null;
            System.out.println("System dump success!");
        }

        else {
            // generating query tokens
            String[] query = input.split("[.]");


            switch (query[0]) {
                // handling database level queries
                case "system" -> {
                    new DatabaseService().databaseManager(query);
                    break;
                }
                // handling document level queries
                case "db" -> {
                    new DocumentService().documentManager(query);
                    break;
                }
                // handling entry level queries
                case "doc" -> {
                    new EntryService().entryManager(query);
                    break;
                }

                default -> System.out.println("There is a typo in your query .^.");
            }
        }
        return true;
    }

    // connection id generator
    public static String getConnectionId() {
        return String.valueOf((new Random().nextInt(100 - 1) + 1));
    }

    // cli help text printer
    public static void showHelp() {
        System.out.println(SystemVariables.HELP);
    }

    public static List<String> setAvailableDBs(){
        // fetching the db list using database service.
        File dataStore = new File(SystemVariables.dataStoreAdd.substring(0, SystemVariables.dataStoreAdd.length()-1));
        if(!dataStore.exists()){
            if(dataStore.mkdir()){
                try {
                    boolean created = new File(SystemVariables.dataStoreAdd + "systemdbconfig.lds").createNewFile();
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
        return new DatabaseService().list();
    }
}
