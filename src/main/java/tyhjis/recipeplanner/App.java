package tyhjis.recipeplanner;

import javafx.application.Application;
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
        primaryStage.setScene(new Scene(new RootWindow()));
        primaryStage.setTitle("Recipe planner");
        primaryStage.show();
    }
}
