/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

/**
 *
 * @author peter_adel
 */
public class BookCategoryFactory {
    public static BookCategory getBookCategory(String categoryType) {
        if (categoryType == null) {
            return null;
        }
        if (categoryType.equalsIgnoreCase("SoftwareEngineering")) {
            return new SoftwareEngineeringBook();
        } else if (categoryType.equalsIgnoreCase("Management")) {
            return new ManagementBook();
        } else if (categoryType.equalsIgnoreCase("AI")) {
            return new AIBook();
        }
        return null;
    }
}