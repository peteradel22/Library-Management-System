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

public class SearchCommand implements Command {
    private String bookID;
    private String studentID;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private static final Logger logger = Logger.getInstance();

    public SearchCommand(String bookID, String studentID, javax.swing.JTextField jTextField1,
                         javax.swing.JTextField jTextField2, javax.swing.JTextField jTextField3, javax.swing.JTextField jTextField4) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.jTextField1 = jTextField1;
        this.jTextField2 = jTextField2;
        this.jTextField3 = jTextField3;
        this.jTextField4 = jTextField4;
    }

    @Override
    public void execute() {
        try {
            // Log attempt to search
            logger.logInfo("Searching for book with ID: " + bookID + " and student ID: " + studentID);
            
            // Access the Singleton instance and get the connection
            Connection con = ConnectionProvider.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM borrow WHERE bookID='" + bookID + "' AND studentID='" + studentID + "'");
            if (rs.next()) {
                jTextField3.setText(rs.getString(3));
                jTextField4.setText(rs.getString(4));
                jTextField1.setEditable(false);
                jTextField2.setEditable(false);
                logger.logInfo("Book found and information displayed for bookID: " + bookID);
            } else {
                JOptionPane.showMessageDialog(null, "Book is Not Borrowed To This Student");
                logger.logInfo("Book not found for studentID: " + studentID);
            }
        } catch (Exception e) {
            logger.logError("Error occurred during search: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Connection Error");
        }
    }
}