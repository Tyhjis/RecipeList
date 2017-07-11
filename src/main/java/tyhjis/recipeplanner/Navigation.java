package tyhjis.recipeplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Navigation extends HBox {
    @FXML
    private Button recipeSelector, categorySelector, ingredientSelector;

    public Navigation() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/tyhjis/recipeplanner/navigation.fxml"));
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
        System.out.println(selection);
    }
}
