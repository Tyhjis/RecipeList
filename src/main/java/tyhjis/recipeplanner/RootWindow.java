package tyhjis.recipeplanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import tyhjis.recipeplanner.categories.CategoryController;

import java.io.IOException;

public class RootWindow extends VBox {
    @FXML
    private Tab recipeSelector, categorySelector, ingredientSelector;

    public RootWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/tyhjis/recipeplanner/root_window.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
