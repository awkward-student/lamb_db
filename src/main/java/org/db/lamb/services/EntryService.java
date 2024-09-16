package org.db.lamb.services;

import org.db.lamb.entities.Entry;
import org.db.lamb.system.SystemVariables;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EntryService {
    public void entryManager(String[] queries){
        if(queries.length != 3){
            if(queries.length != 2) {
                System.out.println("Query typo : Input can not be resolved, invalid query found.");
            }
            else {
                if(queries[1].equals("save;")){
                    boolean x = saveEntries(SystemVariables.entries);
                } else if (queries[1].equals("select;")) {
                    List<Entry> entries = readEntries(SystemVariables.currentDoc.getDocAdd());
                    entries.forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
                } else System.out.println("Query typo : Input can not be resolved, invalid query found.");
            }
            return;
        } else queries[2] = queries[2].substring(0,queries[2].length()-1);

        switch (queries[1]){
            case "update" :
            case "insert" : {
                String args = queries[2];
                String[] pair = args.split(":");
                Entry entry = new Entry(pair[0], pair[1]);
                SystemVariables.entries.add(entry);
                System.out.println("Query Ok!");
            }
            break;
            case "delete" : {
                String args = queries[2];
                String[] pair = args.split(":");
                SystemVariables.entries.forEach((entry) -> {
                    if(entry.getKey().equals(pair[0])) SystemVariables.entries.remove(entry);
                });
                System.out.println("Query Ok!");
            }
            break;
            default : System.out.println("Query typo : Input can not be resolved, invalid query found.");
        }
    }

    public List<Entry> readEntries(String docAdd) {
        List<Entry> entries = new ArrayList<>();
        try {
            FileReader reader = new FileReader(docAdd);
            JSONParser parser = new JSONParser();
            JSONObject fetchedEntries = (JSONObject) parser.parse(reader);
            org.json.JSONObject fex = new org.json.JSONObject(fetchedEntries);
            Map<String, Object> mapper = fex.toMap();
            for(Map.Entry<String, Object> map: mapper.entrySet()){
                entries.add(new Entry(map.getKey(), map.getValue().toString()));
            }
            reader.close();
        } catch (IOException | ParseException ex){
            System.out.println(ex.getMessage());
        }
        return entries;
    }

    public boolean saveEntries(List<Entry> entries) {
        Map<String, String> mapper = new HashMap<>();
        entries.forEach(entry -> {mapper.put(entry.getKey(), entry.getValue());});
        JSONObject docEntry = new JSONObject(mapper);
//        for(Map.Entry<String, String> map : mapper.entrySet()){
//            docEntry.put(map.getKey(), map.getValue());
//        }
        try {
            FileWriter writer = new FileWriter(SystemVariables.currentDoc.getDocAdd());
            writer.write(docEntry.toString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("Can not save changes to the collection : " + ex.getMessage());
        }
        return true;
    }
}
