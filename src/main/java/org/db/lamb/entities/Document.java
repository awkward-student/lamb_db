package org.db.lamb.entities;

import java.io.File;
import java.util.List;

public class Document {
    private String docName;
    private String docAdd;
    private Database db;
    private File collection;
    private List<Entry> entries;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocAdd() {
        return docAdd;
    }

    public void setDocAdd(String docAdd) {
        this.docAdd = docAdd;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    public File getCollection() {
        return collection;
    }

    public void setCollection(File collection) {
        this.collection = collection;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Document() {
    }
}
