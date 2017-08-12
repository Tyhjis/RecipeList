package tyhjis.recipeplanner.ingredients;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import tyhjis.recipeplanner.common.ApplicationService;
import tyhjis.recipeplanner.common.DatabaseServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IngredientController extends GridPane implements Initializable {

    @FXML
    private Button ingredientButton, deleteIngredientButton, updateIngredientButton;

    @FXML
    private TextField ingredientName, selectedIngredient;

    @FXML
    private ListView<Ingredient> ingredientListBox;

    private ApplicationService service;

    private ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();;

    public IngredientController() {
        service = new DatabaseServiceImpl();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tyhjis/recipeplanner/ingredients.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            ingredientButton.setOnAction(event -> addIngredient());
            deleteIngredientButton.setOnAction(event -> deleteIngredient());
            updateIngredientButton.setOnAction(event -> updateIngredient());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureListView();
        ingredientListBox.setItems(ingredientList);
        gatherIngredientList();
    }

    private void addIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientName.getText());
        try {
            service.insert(ingredient);
            emptyIngredientList();
            gatherIngredientList();
            scrollToBottom();
        } catch(Exception e) {
            displayAlert("Error inserting a new ingredient.", e.getMessage());
        } finally {
            ingredientName.setText("");
        }
    }

    private void deleteIngredient() {
        try {
            service.delete(ingredientListBox.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            displayAlert("Error occurred while deleting an ingredient.", e.getMessage());
        } finally {
            emptyIngredientList();
            gatherIngredientList();
            setUpdateAndDeleteControls(true, "");
        }
    }

    private void updateIngredient() {
        Ingredient ingredient = ingredientListBox.getSelectionModel().getSelectedItem();
        ingredient.setName(selectedIngredient.getText());
        try {
            service.update(ingredient);
        } catch(Exception e) {
            displayAlert("Error occurred while updating an ingredient.", e.getMessage());
        } finally {
            emptyIngredientList();
            gatherIngredientList();
            setUpdateAndDeleteControls(true, "");
        }
    }

    private void emptyIngredientList() {
        ingredientList.removeAll(ingredientList);
    }

    private void scrollToBottom() {
        Platform.runLater(() -> ingredientListBox.scrollTo(ingredientList.size() - 1));
    }

    private void displayAlert(String title, String content) {
        Alert insertAlert = new Alert(Alert.AlertType.ERROR);
        insertAlert.setTitle(title);
        insertAlert.setContentText(content);
        insertAlert.show();
    }

    private void configureListView() {
        ingredientListBox.setCellFactory(param -> new ListCell<Ingredient>() {
            @Override
            protected void updateItem(Ingredient item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        ingredientListBox.setOnMouseClicked(event -> setUpdateAndDeleteControls(
                false, ingredientListBox.getSelectionModel().getSelectedItem().getName()));
    }

    private void setUpdateAndDeleteControls(boolean disabled, String selectedText) {
        selectedIngredient.setText(selectedText);
        selectedIngredient.setDisable(disabled);
        deleteIngredientButton.setDisable(disabled);
        updateIngredientButton.setDisable(disabled);
    }

    private void gatherIngredientList() {
        try {
            ingredientList.addAll(Ingredient.selectAll());
        } catch(Exception e) {
            displayAlert("Error occurred while fetching categories.", e.getMessage());
        }
    }
}
