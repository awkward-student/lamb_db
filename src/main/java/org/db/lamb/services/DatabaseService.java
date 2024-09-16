package org.db.lamb.services;
import org.db.lamb.entities.Database;
import org.db.lamb.system.SystemVariables;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService implements Administrable {
    // database manager service
    public void databaseManager(String[] queries){
        // query args check
        if(queries.length != 3){
            System.out.println("Query typo : Input can not be resolved, invalid query found.");
            return;
        }
        // removing ';' from the main query
        else queries[2] = queries[2].substring(0,queries[2].length()-1);
        // switching queries to their specific tasks
        switch(queries[1]){
            case "createdb" -> {
                if(create(queries[2])) {
                    SystemVariables.availableDBs.add(queries[2]);
                    System.out.println("Query Success!");
                }
                else System.out.println("Error creating database " + queries[2] + ".");
            }
            case "dropdb" -> {
                boolean dropDbStatus = false;
                try {
                    File directory = new File(SystemVariables.dataStoreAdd+queries[2]);
                    if(!directory.isDirectory() && !directory.isFile()){
                        System.out.println("Database " + queries[2] + " does not exists.");
                    }
                    else {
                        List<String> dbs = list();
                        for(int i=0; i< dbs.size(); i++){
                            if(dbs.get(i).equals(directory.getName())){
                                dbs.remove(i);
                                break;
                            }
                        }
                        dropDbStatus = delete(directory);
                        if(dropDbStatus){
                            String dbConfigAdd = SystemVariables.dataStoreAdd+"systemdbconfig.lds";
                            File dbconfig = new File(dbConfigAdd);
                            if(dbconfig.exists() && dbconfig.delete()){
                                File reInit = new File(dbConfigAdd);
                                if(reInit.createNewFile()){
                                    FileWriter writer = new FileWriter(dbConfigAdd);
                                    for(String db_s : dbs){
                                        if(db_s.isEmpty()) continue;
                                        writer.write("\n" + db_s);
                                    }
                                    writer.close();
                                }
                            }
                            System.out.println("Database " + queries[2] + " delete success!");
                        }
                        else {
                            System.out.println("Can not delete database " + queries[2] + ".");
                        }
                    }
                } catch(Exception ex) {
                    System.out.println("Error occurred: " + ex.getMessage());
                }
            }
            case "use" -> {
                if(use(queries[2])) System.out.println("Database changed! Now using " + queries[2] + ".");
            }
            case "db" -> {
                if(queries[2].equals("list")){
                    for(String db: list()){
                        System.out.println(db);
                    }
                }
                else System.out.println("Query typo : Input can not be resolved, invalid query found.");
            }
            default -> System.out.println("Query typo : Input can not be resolved, invalid query found.");
        }

    }

    // creates a database
    @Override
    public boolean create(String dbName) {
        try {
            File directory = new File(SystemVariables.dataStoreAdd+dbName);
            File initDB = new File(SystemVariables.dataStoreAdd+dbName+"/dbcolconfig.lds");
            if(directory.mkdir()){
                if(initDB.createNewFile()){
                    FileWriter fw = new FileWriter(SystemVariables.dbConfig.getPath(), true);
                    fw.write("\n"+dbName);
                    System.out.println("Database created : " + dbName);
                    fw.close();
                    return true;
                }
            }
            else {
                System.out.println("Database already exists");
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Error occurred" + ex.getMessage());
        } catch (SecurityException ex) {
            System.out.println("Security exception: " + ex.getMessage());
        }
        return false;
    }

    // deletes a database
    @Override
    public boolean delete(File file) {
        try {
            if(file.isDirectory()) {
                File[] children = file.listFiles();
                if(children != null){
                    for(File child : children) delete(child);
                }
            }
            if(!file.delete()){
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error deleting Database : " + ex.getMessage());
        }

        return true;
    }

    // using / changing a database
    @Override
    public boolean use(String dbName) {
        // check for db in systemdbconfig.lds
        for(String name: SystemVariables.availableDBs){
            if(name.equals(dbName)){
                // only if the db is available
                try {
                    Database database = new Database();
                    // setting up the new database object
                    database.setDbName(dbName);
                    database.setDbAdd(SystemVariables.dataStoreAdd + dbName);
                    database.setDocConfig(new File(SystemVariables.dataStoreAdd + dbName + "/dbcolconfig.lds"));
                    database.setDocuments(null);
                    // making system change db
                    SystemVariables.currentDB = database;
                    database.setDocuments(new DocumentService().list());
                    SystemVariables.currentDB = database;
                    SystemVariables.currentDoc = null;
                    // using database
                    return true;
                } catch (Exception ex) {
                    System.out.println("Database Change failed : " + ex.getMessage());
                    return false;
                }
            }
        }
        System.out.println("Can not use database '" + dbName +"' : Database does not exist.");
        return false;
    }

    // listing all the available databases on console
    @Override
    public List<String> list() {
        List<String> dbs = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SystemVariables.dbConfig));
            String db;
            while((db = reader.readLine()) != null) dbs.add(db);
            reader.close();
        } catch (IOException ex) {
            System.out.println("Error in listing databases: " + ex.getMessage());
        }
        return dbs;
    }
}
