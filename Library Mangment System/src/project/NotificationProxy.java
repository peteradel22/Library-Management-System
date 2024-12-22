/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author peter_adel
 */
public class NotificationProxy {
    private NotificationSystem notificationSystem;
    private boolean isAdmin;

    public NotificationProxy(boolean isAdmin) {
        this.isAdmin = isAdmin;
        this.notificationSystem = new NotificationSystem(); // Initialize the real notification system
    }

    public void update(String message) {
        if (isAdmin) {
            notificationSystem.update(message);
        } else {
            System.out.println("Access Denied: Only admins can send notifications.");
        }
    }
}
