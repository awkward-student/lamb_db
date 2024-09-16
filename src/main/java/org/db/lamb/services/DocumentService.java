package org.db.lamb.services;

import org.db.lamb.entities.Document;
import org.db.lamb.system.SystemVariables;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentService implements Administrable{
    // document service manager
    public void documentManager(String[] queries){
        // query args check
        if(queries.length != 3){
            System.out.println("Query typo : Input can not be resolved, invalid query found.");
            return;
        }
        else queries[2] = queries[2].substring(0,queries[2].length()-1);

        switch(queries[1]){
            case "createcollection" : {
                if(SystemVariables.currentDB != null){
                    if(create(queries[2])) {
                        SystemVariables.currentDB.setDocuments(new DocumentService().list());
                        System.out.println("Query Success!");
                    }
                    else System.out.println("Error creating collection " + queries[2] + ".");
                }
                else System.out.println("No database selected! Please select a database first.\nCreate collection failed.");
            }
            break;
            case "dropcollection" : {
                if(SystemVariables.currentDB == null){
                    System.out.println("No database selected! Please select a database first.");
                }
                else{
                    boolean dropColStatus = false;
                    try {
                        File document = new File(SystemVariables.currentDB.getDbAdd() + "/" + queries[2] + ".lds");
                        if(!document.isFile()) System.out.println("Collection " + queries[2] + " does not exists.");
                        else {
                            List<String> docs = list();
                            for(int i=0; i<docs.size(); i++){
                                if(docs.get(i).equals(document.getName())){
                                    docs.remove(i);
                                    break;
                                }
                            }
                            dropColStatus = delete(document);
                            if(dropColStatus){
                                String docConfigAdd = SystemVariables.currentDB.getDbAdd()+"/dbcolconfig.lds";
                                File docConfig = new File(SystemVariables.currentDB.getDocConfig().getPath());
                                if(docConfig.exists() && docConfig.delete()){
                                    File reInit = new File(docConfigAdd);
                                    if(reInit.createNewFile()){
                                        FileWriter writer = new FileWriter(docConfigAdd);
                                        for(String doc : docs){
                                            if(doc.isEmpty()) continue;
                                            writer.write("\n" + doc);
                                        }
                                        writer.close();
                                    }
                                }
                                System.out.println("Collection " + queries[2] + " delete success!");
                            }
                        }
                    } catch(Exception ex) {
                        System.out.println("Error occurred: " + ex.getMessage());
                    }
                }
            }
            break;
            case "fetch" : {
                if(SystemVariables.currentDB == null) System.out.println("No database selected! Please select a database first.");
                else {
                    if(SystemVariables.currentDoc != null) SystemVariables.currentDoc = null;
                    if(use(queries[2])) System.out.println("Collection changed! Now using " + queries[2] + ".");
                }
            }
            break;
            case "collection" : {
                if(SystemVariables.currentDB == null) System.out.println("No database selected! Please select a database first.");
                else {
                    if(queries[2].equals("list")){
                        for(String doc: list()){
                            if(doc.isEmpty()) continue;
                            System.out.println(doc.substring(0, doc.length()-4));
                        }
                    }
                    else System.out.println("Query typo : Input can not be resolved, invalid query found.");
                }
            }
            break;
            default : System.out.println("Query typo : Input can not be resolved, invalid query found.");
        }
    }

    @Override
    public boolean create(String name) {
        try {
            File initDoc = new File(SystemVariables.currentDB.getDbAdd() + "/" + name + ".lds");
            if(initDoc.createNewFile()){
                FileWriter fw = new FileWriter(SystemVariables.currentDB.getDocConfig().getPath(), true);
                fw.write("\n"+name+".lds");
                System.out.println("Collection created : " + name);
                fw.close();
                return true;
            }
            else {
                System.out.println("Collection already exists");
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Error creating collection: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(File file) {
        return file.delete();
    }

    @Override
    public boolean use(String name) {
        for(String doc: SystemVariables.currentDB.getDocuments()){
            if(doc.equals(name+".lds")){
                try {
                    Document document = new Document();

                    document.setDocName(name);
                    document.setDocAdd(SystemVariables.currentDB.getDbAdd() + "/" + name + ".lds");
                    document.setDb(SystemVariables.currentDB);
                    document.setCollection(new File(SystemVariables.currentDB.getDbAdd()+ "/" + name + ".lds"));
                    document.setEntries(null);
                    SystemVariables.currentDoc = document;
                    document.setEntries(new EntryService().readEntries(SystemVariables.currentDoc.getDocAdd()));
                    SystemVariables.entries = document.getEntries();
                    SystemVariables.currentDoc = document;
                    return true;
                } catch (Exception ex){
                    System.out.println("Collection change failed : " + ex.getMessage());
                    return false;
                }
            }
        }
        System.out.println("Can not use collection '" + name +"' : Collection does not exist.");
        return false;
    }

    @Override
    public List<String> list() {
        List<String> collections = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SystemVariables.currentDB.getDocConfig()));
            String doc;
            while((doc = reader.readLine()) != null) collections.add(doc);
            reader.close();
        } catch (IOException ex) {
            System.out.println("Error in listing collections: " + ex.getMessage());
        }
        return collections;
    }
}
