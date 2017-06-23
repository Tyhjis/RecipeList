package tyhjis.recipeplanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/tyhjis/recipeplanner/app.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Recipe planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
