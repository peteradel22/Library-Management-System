/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author peter_adel
 */


public class BookRegistry implements Subject {
    private static BookRegistry instance = new BookRegistry();
    private List<Observer> observers = new ArrayList<>();

    private BookRegistry() {}

    public static BookRegistry getInstance() {
        return instance;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void addBook(String bookDetails) {
    System.out.println("Notifying observers: New book added - " + bookDetails); // Debugging log
    notifyObservers("New book added: " + bookDetails);
}

}