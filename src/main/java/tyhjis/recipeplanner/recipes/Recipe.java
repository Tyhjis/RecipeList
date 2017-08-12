package tyhjis.recipeplanner.recipes;

import tyhjis.recipeplanner.categories.Category;
import tyhjis.recipeplanner.common.databaseconnection.ConnectionWrapper;
import tyhjis.recipeplanner.common.DatabaseObject;
import tyhjis.recipeplanner.ingredients.Ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Recipe implements DatabaseObject {
    private int id;
    private String name;
    private String instructions;
    private List<Ingredient> ingredients;
    private List<Category> categories;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void save() {
        try {
            String sql = "INSERT INTO recipes (name, instructions) values (?, ?)";
            PreparedStatement statement = connectionWrapper.getConnection().prepareStatement(sql);
            statement.setString(1, this.name);
            statement.setString(2, this.instructions);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        try {
            PreparedStatement statement = connectionWrapper.getConnection()
                    .prepareStatement("DELETE FROM recipes where id = ?");
            statement.setInt(1, this.id);
            statement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {

    }

    public DatabaseObject find(long id) {
        return null;
    }

    public static List<Recipe> selectAll() {

        return null;
    }
}
