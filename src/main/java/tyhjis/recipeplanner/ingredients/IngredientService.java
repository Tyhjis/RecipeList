package tyhjis.recipeplanner.ingredients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientService {

    Connection connection;

    public IngredientService(Connection connection) {
        this.connection = connection;
    }

    public List<Ingredient> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ingredients");
            return createIngredientList(statement.executeQuery());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void insertIngredient() {

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
