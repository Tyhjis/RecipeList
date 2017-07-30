package tyhjis.recipeplanner.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper {
    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:recipes.db");
            PreparedStatement pragmaStatement = connection.prepareStatement("PRAGMA foreign_keys=ON");
            pragmaStatement.execute();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}