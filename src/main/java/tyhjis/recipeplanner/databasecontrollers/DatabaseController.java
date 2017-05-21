package tyhjis.recipeplanner.databasecontrollers;

import tyhjis.recipeplanner.DBObject;

import java.util.ArrayList;

public interface DatabaseController<T extends DBObject> {

    ArrayList<T> selectAll();
    ArrayList<T> findBy(String column, String value);
    ArrayList<T> findBy(String column, int value);
    ArrayList<T> findBy(String column, long value);
    ArrayList<T> findBy(String column, float value);
    ArrayList<T> findBy(String column, double value);
}
