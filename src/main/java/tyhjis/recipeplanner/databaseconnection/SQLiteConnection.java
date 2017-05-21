package tyhjis.recipeplanner.databaseconnection;

import tyhjis.recipeplanner.DBObject;
import tyhjis.recipeplanner.common.DatabaseException;

import java.sql.*;

public class SQLiteConnection implements ConnectionInterface {

    private static Connection connection;
    private static String path;

    public SQLiteConnection(String path) {
        this.path = path;
        connect(path);
    }

    @Override
    public ResultSet query(String query) {
        connect(path);
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ResultSet findByParameter(String table, String column, String value) {
        connect(path);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(prepareFindByQueryString(table, column));
            preparedStatement.setString(1, value);
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ResultSet findByParameter(String table, String column, int value) {
        connect(path);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(prepareFindByQueryString(table, column));
            preparedStatement.setInt(1, value);
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ResultSet findByParameter(String table, String column, long value) {
        connect(path);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(prepareFindByQueryString(table, column));
            preparedStatement.setLong(1, value);
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ResultSet findByParameter(String table, String column, float value) {
        connect(path);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(prepareFindByQueryString(table, column));
            preparedStatement.setFloat(1, value);
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public ResultSet findByParameter(String table, String column, double value) {
        connect(path);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(prepareFindByQueryString(table, column));
            preparedStatement.setDouble(1, value);
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void insert(String table, DBObject record) {

    }

    private String prepareFindByQueryString(String table, String column) {
        return "SELECT * FROM " + table + " WHERE " + column + " = ?";
    }

    private synchronized static Connection connect(String path) {
        String driver = "jdbc:sqlite:";
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(driver + path);
            } catch (SQLException e) {
                throw new DatabaseException(e);
            }
        }
        return connection;
    }


}
