package tyhjis.recipeplanner;

import tyhjis.recipeplanner.databaseconnection.SQLiteConnection;
import tyhjis.recipeplanner.databasecontrollers.RecipeController;

import java.util.ArrayList;

/**
 * Created by khansson on 17/05/2017.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        RecipeController controller = new RecipeController(new SQLiteConnection("testdb.db"));
        controller.insertOne();
        ArrayList<Recipe> list = controller.selectAll();
        list.stream().forEach(recipe -> {
            System.out.println(recipe);
        });
    }
}
