package tyhjis.recipeplanner.databasecontrollers;

import tyhjis.recipeplanner.common.DatabaseException;
import tyhjis.recipeplanner.Recipe;
import tyhjis.recipeplanner.databaseconnection.ConnectionInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeController implements DatabaseController<Recipe> {

    private ConnectionInterface connection;
    private final String tableName = "recipes";

    public RecipeController(ConnectionInterface connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<Recipe> selectAll() {
        ResultSet rs = connection.query("SELECT * FROM recipes");
        return buildListOfRecipesFromResultSet(rs);
    }

    @Override
    public ArrayList<Recipe> findBy(String column, String value) {
        ResultSet rs = connection.findByParameter(tableName, column, value);
        return buildListOfRecipesFromResultSet(rs);
    }

    @Override
    public ArrayList<Recipe> findBy(String column, int value) {
        ResultSet rs = connection.findByParameter(tableName, column, value);
        return buildListOfRecipesFromResultSet(rs);
    }

    @Override
    public ArrayList<Recipe> findBy(String column, long value) {
        ResultSet rs = connection.findByParameter(tableName, column, value);
        return buildListOfRecipesFromResultSet(rs);
    }

    @Override
    public ArrayList<Recipe> findBy(String column, float value) {
        ResultSet rs = connection.findByParameter(tableName, column, value);
        return buildListOfRecipesFromResultSet(rs);
    }

    @Override
    public ArrayList<Recipe> findBy(String column, double value) {
        ResultSet rs = connection.findByParameter(tableName, column, value);
        return buildListOfRecipesFromResultSet(rs);
    }

    public ResultSet insertOne() {
        return connection.query("INSERT INTO recipes(url, category_id) values ('http://www.kotiruoka.fi', 5)");
    }

    private ArrayList<Recipe> buildListOfRecipesFromResultSet(ResultSet rs) {
        ArrayList<Recipe> result = new ArrayList<>();
        try {
            iteRateOverResultSet(result, rs);
            return result;
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    private void iteRateOverResultSet(ArrayList<Recipe> result, ResultSet rs) throws SQLException {
        while(rs.next()) {
            Recipe recipe = buildRecipeFromResultSet(rs);
            if(recipe != null)
                result.add(recipe);
        }
    }

    private Recipe buildRecipeFromResultSet(ResultSet rs) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getLong("id"));
        recipe.setCategoryId(rs.getLong("category_id"));
        recipe.setUrl(rs.getString("url"));
        return recipe;
    }
}
