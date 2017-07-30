package tyhjis.recipeplanner.ingredients;

import tyhjis.recipeplanner.common.ConnectionWrapper;
import tyhjis.recipeplanner.common.DatabaseObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Ingredient implements DatabaseObject {
    private int id;
    private String name;
    private static ConnectionWrapper connection;

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

    @Override
    public void save() {
        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("INSERT INTO ingredients (name) VALUES (?)");
            statement.setString(1, this.name);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("DELETE FROM ingredients WHERE id = ?");
            statement.setInt(1, this.id);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("UPDATE ingredients SET name = ? WHERE id = ?");
            statement.setString(1, this.name);
            statement.setInt(2, this.id);
            statement.execute();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public DatabaseObject find(long id) {
        try {
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("SELECT * from ingredients WHERE id = ?");
            ResultSet rs = statement.executeQuery();
            this.setName(rs.getString("name"));
            this.setId(rs.getInt("id"));
            return this;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DatabaseObject> selectAll() {
        return null;
    }

    private List<Ingredient> createIngredientList() {

    }
}
