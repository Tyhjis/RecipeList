package tyhjis.recipeplanner.common;

import java.util.List;

public interface ApplicationService {
    void insert(DatabaseObject created);
    void delete(DatabaseObject deleted);
    void update(DatabaseObject updated);
    DatabaseObject find(int id);
    List<DatabaseObject> selectAll();
}
