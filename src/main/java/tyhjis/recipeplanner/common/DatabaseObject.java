package tyhjis.recipeplanner.common;

import java.util.List;

public interface DatabaseObject {
    void save();
    void delete();
    void update();
    DatabaseObject find(long id);
    List<DatabaseObject> selectAll();
}
