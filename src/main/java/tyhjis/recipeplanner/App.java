package tyhjis.recipeplanner;

import tyhjis.recipeplanner.databaseconnection.Connector;
import tyhjis.recipeplanner.databaseconnection.SQLiteConnector;

import java.sql.Connection;

/**
 * Created by khansson on 17/05/2017.
 */
public class App {
    public static void main(String[] args) {
        Connector connector = new SQLiteConnector();
        Connection conn = connector.getConnection("recipes.db");
    }
}
