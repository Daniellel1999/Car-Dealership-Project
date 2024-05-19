package carDealership;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * Represents a car dealership application.
 */
public class CarDealership {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * The main entry point of the car dealership application.
     *
     * @throws IOException if an I/O error occurs while reading or writing files
     */
    public static void main(String[] args) throws IOException {
        checkFileExist(getSoldCarsPath());
        ArrayList<Car> availableCars = readCarsFromFile();
        ArrayList<Employee> employees = readEmployeesFromFile();
        Scanner scan = new Scanner(System.in);
        MenuOptions choice = null;


        while (choice != MenuOptions.EXIT) {
            System.out.println("Menu:");
            for (MenuOptions option : MenuOptions.values()) {
                System.out.println(option);
            }
            int userInput = scan.nextInt();

            switch (userInput) {
                case 1 -> {
                    choice = MenuOptions.DISPLAY_EMPLOYEES;
                    sortArrayList(employees);
                    for (Employee e : employees) {
                        System.out.println(e);
                    }
                }
                case 2 -> {
                    choice = MenuOptions.DISPLAY_UNSOLD_CARS;
                    for (Car c : availableCars) {
                        System.out.println(c);
                    }
                }
                case 3 -> {
                    choice = MenuOptions.SELL_CAR;
                    if (availableCars.size() != 0) {
                        printEmployees(employees);
                        String ID = selectEmployeeID(scanner, employees);
                        printCars(availableCars);
                        String carNumber = selectCarNumber(scanner, availableCars);
                        processCarSale(ID, employees, carNumber, availableCars);
                    } else {
                        System.out.println("No cars available for sale!");
                    }
                }
                case 4 -> {
                    choice = MenuOptions.ADD_CAR;
                    Car car = getCarDetails(scanner);
                    try {
                        Car newCar = new Car(car.getCarNumber(), car.getYear(), car.getManufacturer(), car.getMileage(), car.getPrice());
                        availableCars.add(newCar);

                    } catch (Exception e) {
                        System.out.println("Warning Invalid data, failed to add car to the dealership");
                    }
                }
                case 5 -> {
                    System.out.println("Cars: " + availableCars);
                    checkFileExist(getAvailableCarsPath());
                    deleteFile(getAvailableCarsPath());
                    Files.createFile(getAvailableCarsPath());
                    writeCarToFile(getAvailableCarsPath(), availableCars);
                    System.out.println("Employees: " + employees);
                    checkFileExist(getEmployeesPath());
                    deleteFile(getEmployeesPath());
                    Files.createFile(getEmployeesPath());
                    writeEmployeeToFile(getEmployeesPath(), employees);
                    choice = MenuOptions.EXIT;
                }
            }
        }
    }

    /**
     * Reads the car data from a file and returns a list of available cars.
     *
     * @return the list of available cars
     */
    private static ArrayList<Car> readCarsFromFile() {
        ArrayList<Car> availableCars = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(getAvailableCarsPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                if (words.length == 5) {
                    try {
                        Car car = new Car(words[0], Integer.parseInt(words[1]), words[2], Double.parseDouble(words[3]), Double.parseDouble(words[4]));
                        availableCars.add(car);
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: Invalid data format for car, skipping the entry.");
                    }
                } else {
                    System.out.println("Warning: Invalid data format for car, skipping the entry.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading car data file: " + e.getMessage());
        }

        return availableCars;
    }

    /**
     * Reads the employee data from a file and returns a list of employees.
     *
     * @return the list of employees
     */
    private static ArrayList<Employee> readEmployeesFromFile() {
        ArrayList<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(getEmployeesPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                if (words.length == 3) {
                    try {
                        Employee employee = new Employee(words[0], words[1], Integer.parseInt(words[2]));
                        employees.add(employee);
                    } catch (NumberFormatException e) {
                        System.out.println("Warning: Invalid data format for employee, skipping the entry.");
                    }
                } else {
                    System.out.println("Warning: Invalid data format for employee, skipping the entry.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading employee data file: " + e.getMessage());
        }

        return employees;
    }

    /**
     * Returns the path of the file storing the employee data.
     *
     * @return the path of the employee data file
     */
    private static Path getEmployeesPath() {
        return Paths.get("src/carDealership/Employee.txt");
    }

    /**
     * Returns the path of the file storing the available cars' data.
     *
     * @return the path of the available cars data file
     */
    private static Path getAvailableCarsPath() {
        return Paths.get("src/carDealership/CarDealership.txt");
    }

    /**
     * Returns the path of the file storing the sold cars' data.
     *
     * @return the path of the sold cars data file
     */
    private static Path getSoldCarsPath() {
        return Paths.get("src/carDealership/Sold.txt");
    }

    /**
     * Deletes the specified file.
     *
     * @param path the path of the file to be deleted
     * @throws IOException if an I/O error occurs while deleting the file
     */
    private static void deleteFile(Path path) throws IOException {
        Files.delete(path);
    }

    /**
     * Checks if the specified file exists and creates it if it doesn't exist.
     *
     * @param path the path of the file to be checked and created if necessary
     * @throws IOException if an I/O error occurs while checking or creating the file
     */
    private static void checkFileExist(Path path) throws IOException {
        boolean fileExists = Files.exists(path);
        if (!fileExists) {
            Files.createFile(path);
        }
    }

    /**
     * Writes the car data to a file.
     *
     * @param path the path of the file to write the car data
     * @param cars the list of cars to be written to the file
     */
    private static void writeCarToFile(Path path, ArrayList<Car> cars) {
        List<String> lines = new ArrayList<>();

        for (Car car : cars) {
            lines.add(car.toString());
        }

        try {
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error writing car data to file: " + e.getMessage());
        }
    }

    /**
     * Writes the employee data to a file.
     *
     * @param path      the path of the file to write the employee data
     * @param employees the list of employees to be written to the file
     */
    private static void writeEmployeeToFile(Path path, ArrayList<Employee> employees) {
        List<String> lines = new ArrayList<>();

        for (Employee emp : employees) {
            lines.add(emp.toString());
        }

        try {
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error writing employee data to file: " + e.getMessage());
        }
    }

    /**
     * Prints the details of employees to the console.
     *
     * @param employees the list of employees to be printed
     */
    private static void printEmployees(ArrayList<Employee> employees) {
        for (Employee emp : employees) {
            System.out.println(emp.getName() + " " + emp.getId());
        }
    }

    /**
     * Prints the details of cars to the console.
     *
     * @param cars the list of cars to be printed
     */
    private static void printCars(ArrayList<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }

    /**
     * Prompts the user to enter an employee ID and returns it.
     *
     * @param scan      the scanner object used for user input
     * @param employees the list of employees to search for the ID
     * @return the selected employee ID
     */
    private static String selectEmployeeID(Scanner scan, ArrayList<Employee> employees) {
        while (true) {
            System.out.print("Enter the employee ID: ");
            String ID = scan.next();
            System.out.println(ID);

            boolean found = false;
            for (Employee emp : employees) {
                if (emp.getId().equals(ID)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                return ID;
            } else {
                System.out.println("Employee ID not found. Please enter a valid employee ID.");
            }
        }
    }

    /**
     * Prompts the user to enter a car number and returns it.
     *
     * @param scan the scanner object used for user input
     * @param cars the list of cars to search for the car number
     * @return the selected car number
     */
    private static String selectCarNumber(Scanner scan, ArrayList<Car> cars) {
        while (true) {
            System.out.print("Enter the car number: ");
            String carNum = scan.next();

            boolean found = false;
            for (Car car : cars) {
                if (car.getCarNumber().equals(carNum)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                return carNum;
            } else {
                System.out.println("Car number not found. Please enter a valid car number.");
            }
        }
    }

    /**
     * Processes the sale of a car by an employee.
     *
     * @param employeeID the ID of the employee making the sale
     * @param employees  the list of employees
     * @param carNumber  the car number of the car being sold
     * @param cars       the list of available cars
     */
    private static void processCarSale(String employeeID, ArrayList<Employee> employees, String carNumber, ArrayList<Car> cars) {
        Employee foundEmployee = null;

        for (Employee emp : employees) {
            if (emp.getId().equals(employeeID)) {
                foundEmployee = emp;
                break;
            }
        }

        if (foundEmployee != null) {
            Car foundCar = null;
            for (Car car : cars) {
                if (car.getCarNumber().equals(carNumber)) {
                    foundCar = car;
                    break;
                }
            }

            if (foundCar != null) {
                foundEmployee.sellCar(foundCar);
                cars.remove(foundCar);
                System.out.println("Car sold successfully!");
            } else {
                System.out.println("Car not found!");
            }
        } else {
            System.out.println("Employee not found!");
        }
    }

    /**
     * Prompts the user to enter the details of a car and returns it.
     *
     * @param scan the scanner object used for user input
     * @return the created car object
     */
    private static Car getCarDetails(Scanner scan) {

        System.out.println("Enter car number: ");
        String carNumber = scan.next();

        System.out.println("Enter  year: ");
        int manufactureYear = scan.nextInt();

        System.out.println("Enter manufacturer: ");
        String manufacturer = scan.next();

        System.out.println("Enter car's mileage: ");
        double mileage = scan.nextDouble();

        System.out.println("Enter car price: ");
        double price = scan.nextDouble();

        return new Car(carNumber, manufactureYear, manufacturer, mileage, price);
    }

    /**
     * Sorts an ArrayList in descending order.
     *
     * @param arr the ArrayList to be sorted
     * @param <T> the type of elements in the ArrayList
     */
    public static <T extends Comparable<T>> void sortArrayList(ArrayList<T> arr) {
        T tmp;
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i).compareTo(arr.get(j)) < 0) {
                    tmp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, tmp);
                }
            }
        }
    }

}


