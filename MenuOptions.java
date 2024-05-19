package carDealership;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * Represents the available menu options for a car dealership program.
 */
public enum MenuOptions {
    DISPLAY_EMPLOYEES(1, "Display list of employees"),
    DISPLAY_UNSOLD_CARS(2, "Display unsold cars"),
    SELL_CAR(3, "Sell a car"),
    ADD_CAR(4, "Add a car"),
    EXIT(5, "Exit program");

    private final int option;
    private final String description;

    /**
     * Constructs a menu option with the specified option number and description.
     *
     * @param option      the option number
     * @param description the description of the option
     */
    MenuOptions(int option, String description) {
        this.option = option;
        this.description = description;
    }

    /**
     * Returns the option number.
     *
     * @return the option number
     */
    public int getOption() {
        return option;
    }

    /**
     * Returns the description of the option.
     *
     * @return the description of the option
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the menu option.
     *
     * @return a string representation of the menu option
     */
    @Override
    public String toString() {
        return option + ". " + description;
    }
}
