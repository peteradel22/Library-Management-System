/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author peter_adel
 */
public class NotificationSystem implements Observer {
    private static final Logger logger = Logger.getInstance();

    public NotificationSystem() {
    BookRegistry.getInstance().attach(this);
    System.out.println("NotificationSystem observer registered.");
}


    @Override
public void update(String message) {
    System.out.println("Notification: " + message);  // This should print the notification to the console.
    logger.logInfo("Received notification: " + message);
}

}