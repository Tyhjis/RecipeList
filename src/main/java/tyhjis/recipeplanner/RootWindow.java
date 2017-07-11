package tyhjis.recipeplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import tyhjis.recipeplanner.categories.CategoryController;

import java.io.IOException;

public class RootWindow extends VBox {
    @FXML
    private Button recipeSelector, categorySelector, ingredientSelector;

    @FXML
    private CategoryController categoryController;

    public String selectedPane;

    @FXML
    private boolean categoriesSelected;


    public RootWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/tyhjis/recipeplanner/root_window.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            recipeSelector.setOnAction(event -> selectView("Recipes"));
            categorySelector.setOnAction(event -> selectView("Categories"));
            ingredientSelector.setOnAction(event -> selectView("Ingredients"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void selectView(String selection) {
        switch(selection) {
            case "Categories":
                categoriesSelected = true;
                break;
            default:
                categoriesSelected = false;
        }
    }
}
