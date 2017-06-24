package tyhjis.recipeplanner.categories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.sqlite.util.StringUtils;
import tyhjis.recipeplanner.databaseconnection.SQLiteConnector;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private ListView<Category> categoryListBox;

    @FXML
    private TextField categoryName;

    @FXML
    private Button categoryButton;

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
    }

    private void gatherCategoryList() {
        categoryList.addAll(service.selectAll());
    }

    private void emptyCategoryList() {
        categoryList.removeAll(categoryList);
    }

    @FXML
    private void addCategory() {
        String name = categoryName.getText();
        if(org.apache.commons.lang3.StringUtils.isNotBlank(name)) {
            try {
                service.insertCategory(name);
                emptyCategoryList();
                gatherCategoryList();
            } catch(SQLException e) {
                displayAlert("Error inserting category.", e.getMessage());
            }
        }
    }

    private void displayAlert(String title, String content) {
        Alert insertAlert = new Alert(Alert.AlertType.ERROR);
        insertAlert.setTitle(title);
        insertAlert.setContentText(content);
        insertAlert.show();
    }
}
