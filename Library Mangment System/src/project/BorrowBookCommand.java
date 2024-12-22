/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author peter_adel
 */

public class BorrowBookCommand implements Command {
    private final String bookID;
    private final String studentID;
    private final String borrowDate;
    private final String dueDate;
    private final Logger logger;

    public BorrowBookCommand(String bookID, String studentID, String borrowDate, String dueDate) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.logger = Logger.getInstance();
    }

    @Override
    public void execute() {
        try {
            Connection con = ConnectionProvider.getInstance().getConnection();
            Statement st = con.createStatement();

            // Check if the book already exists
            ResultSet rs = st.executeQuery("SELECT * FROM borrow WHERE bookID='" + bookID + "'");
            if (rs.next()) {
                // Book exists, check its return status
                if (rs.getString("returnBook").equals("NO")) {
                    JOptionPane.showMessageDialog(null, "This book is currently borrowed and not returned yet.");
                    logger.logInfo("This book is currently borrowed and not returned yet: Book ID " + bookID);
                } else {
                    // Update the record to indicate the book is borrowed again
                    st.executeUpdate("UPDATE borrow SET studentID='" + studentID + "', borrowDate='" + borrowDate +
                            "', dueDate='" + dueDate + "', returnBook='NO' WHERE bookID='" + bookID + "'");
                    JOptionPane.showMessageDialog(null, "Book Successfully Borrowed Again!");
                    logger.logInfo("Book successfully borrowed again: Book ID " + bookID);
                }
            } else {
                // Book does not exist, insert a new record
                st.executeUpdate("INSERT INTO borrow (bookID, studentID, borrowDate, dueDate, returnBook) VALUES ('"
                        + bookID + "', '" + studentID + "', '" + borrowDate + "', '" + dueDate + "', 'NO')");
                JOptionPane.showMessageDialog(null, "Book Successfully Borrowed!");
                logger.logInfo("New borrow record created: Book ID " + bookID);
            }
        } catch (Exception e) {
            logger.logError("Connection Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Connection Error: " + e.getMessage());
        }
    }
}