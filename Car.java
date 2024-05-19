package carDealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * Represents a car in a car dealership.
 */
public class Car {

    private final String carNumber;
    private final int year;
    private final String manufacturer;
    private double mileage;
    private double price;

    /**
     * Constructs a new Car object with the specified details.
     *
     * @param carNumber    the car number
     * @param year         the manufacturing year of the car
     * @param manufacturer the manufacturer of the car
     * @param mileage      the mileage of the car
     * @param price        the price of the car
     * @throws IllegalArgumentException if the year is less than 2017,
     *                                  the price is not positive,
     *                                  or the mileage is negative
     */
    public Car(String carNumber, int year, String manufacturer, double mileage, double price) throws IllegalArgumentException {
        if (year < 2017) {
            throw new IllegalArgumentException("The year should be 2017 or more.");
        } else {
            this.year = year;
        }
        if (price <= 0) {
            throw new IllegalArgumentException("The price should be positive.");
        } else {
            this.price = price;
        }
        if (mileage < 0) {
            throw new IllegalArgumentException("The mileage should be positive.");
        } else {
            this.mileage = mileage;
        }
        this.carNumber = carNumber;
        this.manufacturer = manufacturer;
    }

    /**
     * Decreases the price of the car by the specified percentage.
     *
     * @param percentage the percentage by which to decrease the price
     * @throws IllegalArgumentException if the percentage is negative
     *                                  or the price decrease exceeds the maximum allowed limit
     */
    public void decreasePrice(double percentage) {
        if (percentage < 0) {
            throw new IllegalArgumentException("Percentage must be a non-negative value.");
        }
        double decreaseAmount = price * (percentage / 100);
        if (decreaseAmount > 5000) {
            throw new IllegalArgumentException("Price decrease exceeds the maximum allowed limit.");
        }
        price = price - decreaseAmount;
    }

    /**
     * Writes the car details to a file for car sales.
     * The details include the car number, year, manufacturer, mileage, and price.
     */
    public void sellingCar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("car_sales.txt", true))) {
            Car newCar = new Car(carNumber, year, manufacturer, mileage, price);
            String carDetails = newCar.toString();
            writer.write(carDetails);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    /**
     * Returns the string representation of the car.
     *
     * @return the car details as a string
     */
    @Override
    public String toString() {
        return carNumber + " " + year + " " + manufacturer + " " + mileage + " " + price;
    }

    /**
     * Returns the car number.
     *
     * @return the car number
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * Returns the manufacturing year of the car.
     *
     * @return the manufacturing year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the manufacturer of the car.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Returns the mileage of the car.
     *
     * @return the mileage
     */
    public double getMileage() {
        return mileage;
    }

    /**
     * Returns the price of the car.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }
}
