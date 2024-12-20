package project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author peter_adel
 */
public class ConnectionProvider {
    // Static instance to hold the single instance of the class
    private static ConnectionProvider instance;
    // Database connection object
    private Connection connection;

    // Private constructor to restrict instantiation
    private ConnectionProvider() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "11111111");
        } catch (Exception e) {
            System.out.println("Database connection creation failed: " + e);
        }
    }

    // Public method to provide global access to the instance
    public static synchronized ConnectionProvider getInstance() {
        if (instance == null) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    // Method to provide access to the database connection
    public Connection getConnection() {
        return connection;
    }
}
