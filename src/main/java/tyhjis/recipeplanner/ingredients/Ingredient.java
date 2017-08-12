package tyhjis.recipeplanner.ingredients;

import tyhjis.recipeplanner.common.databaseconnection.ConnectionWrapper;
import tyhjis.recipeplanner.common.DatabaseObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ingredient implements DatabaseObject {
    private int id;
    private String name;
    private static ConnectionWrapper connectionWrapper;

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
            PreparedStatement statement = connectionWrapper.getConnection()
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
            PreparedStatement statement = connectionWrapper.getConnection()
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
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("UPDATE ingredients SET name = ? WHERE id = ?");
            statement.setString(1, this.name);
            statement.setInt(2, this.id);
            statement.execute();
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Ingredient find(long id) {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("SELECT * from ingredients WHERE id = ?");
            ResultSet rs = statement.executeQuery();
            Ingredient ingredient = new Ingredient();
            ingredient.setName(rs.getString("name"));
            ingredient.setId(rs.getInt("id"));
            return ingredient;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Ingredient> selectAll() {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("SELECT * FROM ingredients");
            ResultSet rs = statement.executeQuery();
            return createIngredientList(rs);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Ingredient> createIngredientList(ResultSet rs) throws SQLException {
        List<Ingredient> ingredientList = new ArrayList<>();
        while(rs.next()) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(rs.getInt("id"));
            ingredient.setName(rs.getString("name"));
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }
}
