package tyhjis.recipeplanner.ingredients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    Connection connection;

    public IngredientServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Ingredient created) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ingredients (name) VALUES (?)");
            statement.setString(1, created.getName());
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Ingredient deleted) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ingredients WHERE id = ?");
            statement.setInt(1, deleted.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ingredient updated) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ingredients SET name = ? WHERE id = ?");
            statement.setString(1, updated.getName());
            statement.setInt(2, updated.getId());
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ingredient find(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Ingredient result = new Ingredient();
            result.setName(rs.getString("name"));
            result.setId(rs.getInt("id"));
            return result;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ingredient> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients");
            return createIngredientList(statement.executeQuery());
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Ingredient> createIngredientList(ResultSet rs) throws SQLException {
        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient;
        while(rs.next()) {
            ingredient = new Ingredient();
            ingredient.setId(rs.getInt("id"));
            ingredient.setName(rs.getString("name"));
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }
}
