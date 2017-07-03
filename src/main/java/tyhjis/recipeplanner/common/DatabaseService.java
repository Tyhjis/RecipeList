package tyhjis.recipeplanner.common;

import java.util.List;

public interface DatabaseService<T extends DatabaseObject> {
    void insert(T created);
    void delete(T deleted);
    void update(T updated);
    T find(int id);
    List<T> selectAll();
}
