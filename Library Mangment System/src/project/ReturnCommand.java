/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;
import java.sql.*;
import javax.swing.JOptionPane;
import project.ConnectionProvider;
import project.Logger;

/**
 *
 * @author peter_adel
 */

public class ReturnCommand implements Command {
    private String bookID;
    private String studentID;
    private static final Logger logger = Logger.getInstance();

    public ReturnCommand(String bookID, String studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
    }

    @Override
    public void execute() {
        try {
            // Log attempt to return book
            logger.logInfo("Attempting to return book with ID: " + bookID + " by studentID: " + studentID);
            
            // Access the Singleton instance and get the connection
            Connection con = ConnectionProvider.getInstance().getConnection();
            Statement st = con.createStatement();

            // Check if the book is currently borrowed by the student
            ResultSet rs = st.executeQuery("SELECT * FROM borrow WHERE bookID='" + bookID + "' AND studentID='" + studentID + "' AND returnBook='NO'");
            if (rs.next()) {
                // Update return status
                st.executeUpdate("UPDATE borrow SET returnBook='YES' WHERE studentID='" + studentID + "' AND bookID='" + bookID + "'");
                JOptionPane.showMessageDialog(null, "Book Successfully Returned");
                logger.logInfo("Book returned successfully for studentID: " + studentID);
            } else {
                JOptionPane.showMessageDialog(null, "This book is not currently borrowed or already returned.");
                logger.logInfo("Book not currently borrowed or already returned for studentID: " + studentID);
            }
        } catch (Exception e) {
            logger.logError("Error occurred during book return: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Connection Error: " + e.getMessage());
        }
    }
}