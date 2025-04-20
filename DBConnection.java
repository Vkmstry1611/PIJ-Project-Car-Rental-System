import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection handles establishing a connection with the MySQL database.
 */
public class DBConnection {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/carrentalsystem"; // Your database URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "dani2004"; // Your MySQL password

    /**
     * Establishes and returns a connection to the MySQL database.
     * @return Connection object if successful
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Please check the .jar file.");
            e.printStackTrace();
        }
        // Return the database connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection:");
                e.printStackTrace();
            }
        }
    }
}
