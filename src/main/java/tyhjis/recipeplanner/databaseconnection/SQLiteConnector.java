package tyhjis.recipeplanner.databaseconnection;

import org.sqlite.SQLiteConnection;

import java.sql.*;

public class SQLiteConnector {

    private static Connection connection;

    public static Connection getConnection(String path) {
        if(connection == null) {
            createConnection("jdbc:sqlite:" + path);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void createConnection(String url) {
        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement pragmaStatement = connection.prepareStatement("PRAGMA foreign_keys=ON");
            pragmaStatement.execute();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
