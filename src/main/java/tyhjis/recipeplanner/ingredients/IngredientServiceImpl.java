package tyhjis.recipeplanner.ingredients;

import tyhjis.recipeplanner.common.DatabaseObject;

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

    @Override
    public DatabaseObject find(int id) {
        return new Ingredient().find(id);
    }

    @Override
    public List<DatabaseObject> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients");
            return createIngredientList(statement.executeQuery());
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<DatabaseObject> createIngredientList(ResultSet rs) throws SQLException {
        List<DatabaseObject> ingredientList = new ArrayList<>();
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
