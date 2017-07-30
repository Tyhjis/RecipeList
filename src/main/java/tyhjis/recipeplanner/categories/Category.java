package tyhjis.recipeplanner.categories;

import tyhjis.recipeplanner.common.ConnectionWrapper;
import tyhjis.recipeplanner.common.DatabaseObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Category implements DatabaseObject {

    private int id;

    private String name;

    private ConnectionWrapper connectionWrapper;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return id + " " + name;
    }

    @Override
    public void save() {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("INSERT INTO categories (name) VALUES (?)");
            statement.setString(1, this.name);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public DatabaseObject find(long id) {
        return null;
    }

    @Override
    public List<DatabaseObject> selectAll() {
        return null;
    }
}
