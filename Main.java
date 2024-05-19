package doublyLinkedList;

import java.util.Scanner;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * A class that provides a simple user interface for interacting with the DoublyLinkedList.
 */
public class Main {

    /**
     * The main method that runs the program and handles user input.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        DoublyLinkedList<String> personList = new DoublyLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        Menu choice = null;

        while (choice != Menu.EXIT) {
            printMenu();
            System.out.println("Choose an action: ");
            int pick = scanner.nextInt();

            switch (pick) {
                case 1:
                    System.out.println("Enter the name of the person: ");
                    String name = scanner.next();
                    choice = Menu.ADD_TO_BEGINNING;
                    personList.addFirst(name);
                    System.out.println(name + " has been added successfully to the list.");
                    break;

                case 2:
                    System.out.println("Enter the name of the person: ");
                    name = scanner.next();
                    choice = Menu.ADD_TO_END;
                    personList.addLast(name);
                    System.out.println(name + " has been added successfully to the list.");
                    break;

                case 3:
                    System.out.println("Enter the name of the person to search for: ");
                    name = scanner.next();
                    choice = Menu.SEARCH;
                    System.out.println("Person found: " + personList.contains(name));
                    break;

                case 4:
                    System.out.println("Enter the name of the person to remove: ");
                    name = scanner.next();
                    choice = Menu.REMOVE;
                    personList.remove(name);
                    break;

                case 5:
                    choice = Menu.CHECK_EMPTY;
                    System.out.println("Is the list empty? " + personList.isEmpty());
                    break;

                case 6:
                    choice = Menu.GET_SIZE;
                    System.out.println("Size of the list: " + personList.size());
                    break;

                case 7:
                    choice = Menu.PRINT_FORWARD;
                    System.out.println("List of persons from the beginning:");
                    personList.printForward();
                    break;

                case 8:
                    choice = Menu.PRINT_BACKWARD;
                    System.out.println("List of persons from the end:");
                    personList.printBackward();
                    break;

                case 9:
                    choice = Menu.CLEAR;
                    personList.clear();
                    System.out.println("The list has been cleared.");
                    break;

                case 10:
                    choice = Menu.EXIT;
                    System.out.println("Exiting the program...");
                    break;

                default:
                    System.out.println("Invalid input. Please choose an action: ");
            }
        }
    }

    /**
     * Prints the menu options for the user.
     */
    public static void printMenu() {
        System.out.println("""
                1 - Add a person to the beginning of the list
                2 - Add a person to the end of the list
                3 - Search for a person in the list
                4 - Remove a person from the list
                5 - Check if the list is empty
                6 - Get the size of the list
                7 - Print the list of persons from the beginning
                8 - Print the list of persons from the end
                9 - Clear the entire list
                10 - Exit the program""");

    }

}
