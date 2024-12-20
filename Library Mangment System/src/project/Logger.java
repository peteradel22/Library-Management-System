package project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    // The single instance of the Logger class
    private static Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {
    }

    // Method to get the singleton instance of the Logger
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Method to log info messages
    public void logInfo(String message) {
        log("INFO", message);
    }

    // Method to log error messages
    public void logError(String message) {
        log("ERROR", message);
    }

    // Method to log messages of different types
    private void log(String logType, String message) {
        // Get the current timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        
        // Create the log message
        String logMessage = timestamp + " [" + logType + "] " + message;

        // Write the log message to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("application.log", true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
