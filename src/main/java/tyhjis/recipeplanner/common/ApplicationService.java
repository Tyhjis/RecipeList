package tyhjis.recipeplanner.common;

public interface ApplicationService {
    void insert(DatabaseObject created);
    void delete(DatabaseObject deleted);
    void update(DatabaseObject updated);
}
