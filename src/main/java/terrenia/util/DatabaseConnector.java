package terrenia.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:sqlserver://JF\\SQLEXPRESS:49670;databaseName=terrenia;encrypt=false;";
    private static final String USER = "root";
    private static final String PASS = "1234";
    private static DatabaseConnector instance;

    private DatabaseConnector() {
        // Load the SQL Server JDBC driver
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("SQL Server JDBC Driver not found!", e);
        }
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
