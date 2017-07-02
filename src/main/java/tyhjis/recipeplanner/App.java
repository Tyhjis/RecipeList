package tyhjis.recipeplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tyhjis.recipeplanner.common.DefaultUncaughtExceptionHandler;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler());
        Parent root = FXMLLoader.load(getClass().getResource("/tyhjis/recipeplanner/categories.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Recipe planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
