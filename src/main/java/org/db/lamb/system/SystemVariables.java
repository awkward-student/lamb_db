package org.db.lamb.system;

import org.db.lamb.entities.Database;
import org.db.lamb.entities.Document;
import org.db.lamb.entities.Entry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SystemVariables {
    // data store address
//    public static String dataStoreAdd = "src/main/java/org/db/lamb/datastore/";
    // prod add
    public static String dataStoreAdd = "datastore/";
    // current database
    public static Database currentDB = null;
    // db config file
    public static File dbConfig = new File(dataStoreAdd + "systemdbconfig.lds");
    // current document
    public static Document currentDoc = null;
    // list of currently available databases
    public static List<String> availableDBs;
    // List of Entries
    public  static List<Entry> entries;
    // console intro text
    public static final String INTRO = "\n" +
            "Hello World!" +
            "\n" +
            "\n" +
            "Welcome to the Lamb CLI." +
            "\n" +
            "Commands end with ';'" +
            "\n" +
            "Your Lamb connection id is " +
            SystemMethods.getConnectionId()+
            "\n" +
            "Server version: 1.0.dev beta Lamb Community Server - MIT License.\n" +
            "\n" +
            "Copyright (c) 2023 - 24, Vishwas Karode / awkward-student.\n" +
            "\n" +
            "Type 'help;' for help.";
    // console help text
    public static final String HELP = """

            Welcome to Lamb CLI.

            What is Lamb DB?
            LAMB DB is an acronym for 'Layered, Atomic, Map-Based Database.
            Lamb is built on top of JAVA and File I/O.
            Lamb is a {key, value} based NoSQL Database.

            List of all Lamb DB commands:
            Note that all text commands must be first on line and end with ';'
            db.createcollection.<col_name>   :    Create a new collection (if not exist) in current / selected database. Takes collection name as argument.
            db.fetch.<col_name>              :    Use / Load a collection for operations. Takes collection name as argument.
            db.collection.list               :    List / Print the names of all the collections in the current / selected database.
            db.dropcollection.<col_name>     :    Deletes a collection. Takes collection name as argument.
            doc.delete.<key>                 :    Delete an Entry / Record in the current collection. Takes <key> as argument.
            doc.insert.<key : val>           :    Create a new Entry / Record in the current collection. Takes <key, val> pair as argument. If key exists, update value.
            doc.update.<key : val>           :    Update an Entry / Record in the current collection. Takes <key, val> pair as argument.
            doc.save                         :    Save the changes made using 'delete', 'insert' and 'update' commands. If not used no changes reflect in the collection / document.
            doc.select                       :    List / Print all the Entries / Records of the current collection.
            dump                             :    Dumps the system information such as selected {database, document}.
            exit                             :    Exit / Quit Lamb DB.
            help                             :    Display this help.
            system.createdb.<db_name>        :    Create a new database (if not exist). Takes database name as argument
            system.db.list                   :    List / Print the names of all the available databases.
            system.dropdb.<db_name>          :    Deletes a database. Takes database name as argument.
            system.use.<db_name>             :    Use a database. Takes database name as argument."""
    ;
}
