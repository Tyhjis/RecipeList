package tyhjis.recipeplanner.categories;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tyhjis.recipeplanner.databaseconnection.SQLiteConnector;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private ListView<Category> categoryListBox;

    @FXML
    private TextField categoryName;

    @FXML
    private TextField selectedCategory;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private Button updateCategoryButton;

    private CategoryService service;

    private final ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(location.toString());
        Connection connection = SQLiteConnector.getConnection("recipes.db");
        service = new CategoryService(connection);
        configureListView();
        categoryListBox.setItems(categoryList);
        gatherCategoryList();
    }

    @FXML
    private void addCategory() {
        Category category = new Category();
        category.setName(categoryName.getText());
        try {
            service.insertCategory(category);
            emptyCategoryList();
            gatherCategoryList();
            scrollToBottom();
            categoryName.setText("");
        } catch(Exception e) {
            displayAlert("Error inserting a new category.", e.getMessage());
        }
    }

    @FXML
    private void deleteCategory() {
        try {
            service.deleteCategory(categoryListBox.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            displayAlert("Error occurred while deleting a category.", e.getMessage());
        } finally {
            emptyCategoryList();
            gatherCategoryList();
            setUpdateAndDeleteControls(true, "");
        }
    }

    @FXML
    private void updateCategory() {
        Category category = categoryListBox.getSelectionModel().getSelectedItem();
        category.setName(selectedCategory.getText());
        try {
            service.updateCategory(category);
        } catch(Exception e) {
            displayAlert("Error occurred while updating a category.", e.getMessage());
        } finally {
            emptyCategoryList();
            gatherCategoryList();
            setUpdateAndDeleteControls(true, "");
        }
    }

    private void configureListView() {
        categoryListBox.setCellFactory(param -> new ListCell<Category>() {
            @Override
            protected void updateItem(Category item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        categoryListBox.setOnMouseClicked(event -> setUpdateAndDeleteControls(false, categoryListBox.getSelectionModel().getSelectedItem().getName()));
    }

    private void setUpdateAndDeleteControls(boolean disabled, String selectedText) {
        selectedCategory.setText(selectedText);
        selectedCategory.setDisable(disabled);
        deleteCategoryButton.setDisable(disabled);
        updateCategoryButton.setDisable(disabled);
    }

    private void scrollToBottom() {
        Platform.runLater(() -> categoryListBox.scrollTo(categoryList.size() - 1));
    }

    private void displayAlert(String title, String content) {
        Alert insertAlert = new Alert(Alert.AlertType.ERROR);
        insertAlert.setTitle(title);
        insertAlert.setContentText(content);
        insertAlert.show();
    }

    private void gatherCategoryList() {
        try {
            categoryList.addAll(service.selectAll());
        } catch(Exception e) {
            displayAlert("Error occurred while fetching categories.", e.getMessage());
        }
    }

    private void emptyCategoryList() {
        categoryList.removeAll(categoryList);
    }
}
