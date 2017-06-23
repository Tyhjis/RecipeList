package tyhjis.recipeplanner.databaseconnection;

import java.sql.*;

public class SQLiteConnector {

    private static Connection connection;

    private SQLiteConnector() {

    }

    public static Connection getConnection(String path) {
        if(connection == null) {
            createConnection("jdbc:sqlite:" + path);
        }
        return connection;
    }

    private static void createConnection(String url) {
        try {
            connection = DriverManager.getConnection(url);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
