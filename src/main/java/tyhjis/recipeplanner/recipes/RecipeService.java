package tyhjis.recipeplanner.recipes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeService {

    Connection connection;

    public RecipeService(Connection connection) {
        this.connection = connection;
    }

    public List<Recipe> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM recipes");
            return createRecipeList(statement.executeQuery());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private List<Recipe> createRecipeList(ResultSet rs) throws SQLException {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe;
        while(rs.next()){
            recipe = new Recipe();
            recipe.setId(rs.getInt("id"));
            recipe.setName(rs.getString("name"));
            recipeList.add(recipe);
        }
        return recipeList;
    }

    public Recipe getById(int id) {
        return null;
    }

    public void insert(Recipe recipe) {

    }

    private void addCategories(Recipe recipe) {

    }

    private void addIngredients(Recipe recipe) {

    }
}
