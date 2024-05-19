package carDealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * Represents an employee in a car dealership.
 */
public class Employee implements Comparable<Employee> {
    private final String name;
    private final String id;
    private int salesNumber;

    /**
     * Constructs an employee object with the specified name, ID, and sales number.
     *
     * @param name        the name of the employee
     * @param id          the ID of the employee
     * @param salesNumber the number of sales made by the employee
     * @throws IllegalArgumentException if the name contains characters other than letters,
     *                                  if the ID number is not exactly 9 digits long,
     *                                  or if the sales number is negative
     */
    public Employee(String name, String id, int salesNumber) {
        for (int i = 0; i < name.length(); i++) {
            if ((name.charAt(i)) > 'z' || name.charAt(i) < 'A') {
                throw new IllegalArgumentException("The employee name must contain letters only.");
            } else if (name.charAt(i) < 'a' && name.charAt(i) > 'Z') {
                throw new IllegalArgumentException("The employee name must contain letters only.");
            }
        }
        if (id.length() != 9) {
            throw new IllegalArgumentException("The ID number must be exactly 9 digits long.");
        }
        if (salesNumber < 0) {
            throw new IllegalArgumentException("The sales number must be a non-negative number (greater than or equal to 0)");
        }
        this.name = name;
        this.id = id;
        this.salesNumber = salesNumber;
    }

    /**
     * Records the sale of a car by the employee and updates the sales number.
     * The employee details are written to a file.
     *
     * @param car the car being sold
     */
    public void sellCar(Car car) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("car_sales.txt", true))) {
            String employeeDetails = name + " " + id + " " + salesNumber;
            writer.write(employeeDetails);
            writer.newLine();
            salesNumber++;
            car.sellingCar();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Calculates the salary of the employee based on the base salary and sales number.
     *
     * @return the calculated salary
     */
    public int calculateSalary() {
        int baseSalary = 6000;
        return (baseSalary + (salesNumber * 100));
    }

    /**
     * Returns a string representation of the employee.
     *
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        return "Employee: " + name + ", ID: " + id + ", Sales Number: " + salesNumber + ", Salary: " + calculateSalary();
    }

    /**
     * Compares this employee with another employee based on their sales numbers.
     *
     * @param otherEmployee the other employee to compare
     * @return a negative integer if this employee's sales number is less than the other employee's,
     * a positive integer if this employee's sales number is greater than the other employee's,
     * or 0 if both employees have the same sales number
     */
    @Override
    public int compareTo(Employee otherEmployee) {
        if (this.salesNumber < otherEmployee.salesNumber) {
            return -1;
        } else if (this.salesNumber > otherEmployee.salesNumber) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Returns the name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the employee.
     *
     * @return the ID of the employee
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the number of sales made by the employee.
     *
     * @return the number of sales made by the employee
     */
    public int getSalesNumber() {
        return salesNumber;
    }
}
