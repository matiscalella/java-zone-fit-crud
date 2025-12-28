package fit_zone.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the connection to the MySQL database.
 */
public class DatabaseConnection {

    private static final String DATABASE = "fit_zone_db";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Returns an active connection to the database.
     *
     * @return Connection to MySQL
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}