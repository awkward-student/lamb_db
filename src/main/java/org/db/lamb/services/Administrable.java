package org.db.lamb.services;

import java.io.File;
import java.util.List;

public interface Administrable {
    boolean create(String name);
    boolean delete(File file);
    boolean use(String name);
    List<String> list();
}
