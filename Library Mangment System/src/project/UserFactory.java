/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author peter_adel
 */
public class UserFactory {
    public static User createUser(String role) {
        if (role.equalsIgnoreCase("admin")) {
            return new AdminUser();
        } else if (role.equalsIgnoreCase("regular")) {
            return new RegularUser();
        }
        throw new IllegalArgumentException("Invalid user role: " + role);
    }
}
