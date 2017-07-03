package tyhjis.recipeplanner.recipes;

import tyhjis.recipeplanner.categories.Category;
import tyhjis.recipeplanner.common.DatabaseObject;
import tyhjis.recipeplanner.ingredients.Ingredient;

import java.util.List;

public class Recipe extends DatabaseObject {
    private int id;
    private String name;
    private String instructions;
    private List<Ingredient> ingredients;
    private List<Category> categories;

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
}
