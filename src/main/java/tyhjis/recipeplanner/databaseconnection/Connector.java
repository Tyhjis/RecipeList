package tyhjis.recipeplanner.databaseconnection;

import java.sql.Connection;

public interface Connector {
    Connection getConnection(String path);
}
