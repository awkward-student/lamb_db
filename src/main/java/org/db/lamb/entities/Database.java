package org.db.lamb.entities;
import java.io.File;
import java.util.List;

public class Database{
    private String dbName;
    private String dbAdd;
    private File docConfig;
    private List<String> documents;

    public Database() {
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbAdd() {
        return dbAdd;
    }

    public void setDbAdd(String dbAdd) {
        this.dbAdd = dbAdd;
    }

    public File getDocConfig() {
        return docConfig;
    }

    public void setDocConfig(File docConfig) {
        this.docConfig = docConfig;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }
}
