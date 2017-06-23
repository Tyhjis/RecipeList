package tyhjis.recipeplanner.databaseconnection;

import java.sql.*;

public class SQLiteConnector implements Connector {

    @Override
    public Connection getConnection(String path) {
        return createConnection("jdbc:sqlite:" + path);
    }

    private Connection createConnection(String url) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }
}
