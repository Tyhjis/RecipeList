package tyhjis.recipeplanner;

/**
 * Created by khansson on 17/05/2017.
 */
public abstract class DBObject {
    protected String table;

    public DBObject(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public abstract String getKeys();

    public abstract String getValues();
}
