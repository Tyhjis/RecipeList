package tyhjis.recipeplanner.databaseconnection;

import tyhjis.recipeplanner.DBObject;

import java.sql.ResultSet;

public interface ConnectionInterface {
    ResultSet query(String query);
    ResultSet findByParameter(String table, String column, String value);
    ResultSet findByParameter(String table, String column, int value);
    ResultSet findByParameter(String table, String column, long value);
    ResultSet findByParameter(String table, String column, float value);
    ResultSet findByParameter(String table, String column, double value);
    void insert(String table, DBObject record);
}
