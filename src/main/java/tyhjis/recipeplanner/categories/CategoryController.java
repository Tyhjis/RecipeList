package tyhjis.recipeplanner.categories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tyhjis.recipeplanner.databaseconnection.SQLiteConnector;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private ListView<String> categoryListBox;

    @FXML
    private TextField categoryName;

    @FXML
    private Button categoryButton;

    CategoryService service;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> categoryList = FXCollections.observableArrayList();
        Connection connection = SQLiteConnector.getConnection("recipes.db");
        service = new CategoryService(connection);
        List<Category> categories = service.selectAll();
        for (Category c : categories) {
            categoryList.add(c.toString());
        }
        categoryListBox.setItems(categoryList);
    }
}
