package tyhjis.recipeplanner.common;

public class DatabaseServiceImpl implements ApplicationService {

    @Override
    public void insert(DatabaseObject created) {
        created.save();
    }

    @Override
    public void delete(DatabaseObject deleted) {
        deleted.delete();
    }

    @Override
    public void update(DatabaseObject updated) {
        updated.update();
    }
}
