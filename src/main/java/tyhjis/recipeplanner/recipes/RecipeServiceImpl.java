package tyhjis.recipeplanner.recipes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeServiceImpl implements RecipeService {

    Connection connection;

    public RecipeServiceImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Recipe> selectAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM recipes");
            return createRecipeList(statement.executeQuery());
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Recipe getById(int id) {
        return null;
    }

    public void insert(Recipe recipe) {
        try {
            String sql = "INSERT INTO recipes (name, instructions) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getInstructions());
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Recipe deleted) {

    }

    @Override
    public void update(Recipe updated) {

    }

    @Override
    public Recipe find(int id) {
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
}
