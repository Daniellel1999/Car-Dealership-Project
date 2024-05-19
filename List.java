package doublyLinkedList;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * An interface that represents a list data structure with basic operations.
 *
 * @param <T> the type of elements stored in the list
 */
public interface List<T> {

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list
     */
    int size();

    /**
     * Adds an element to the beginning of the list.
     *
     * @param data the element to be added
     */
    void addFirst(T data);

    /**
     * Adds an element to the end of the list.
     *
     * @param data the element to be added
     */
    void addLast(T data);

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param data the element to be removed
     * @return the node containing the removed element, or null if the element was not found
     */
    Node<T> remove(T data);

    /**
     * Removes all elements from the list, making it empty.
     */
    void clear();

    /**
     * Checks if the list contains the specified element.
     *
     * @param data the element to be checked
     * @return true if the element is found in the list, false otherwise
     */
    boolean contains(T data);

    /**
     * Prints the elements of the list in forward order.
     */
    void printForward();

    /**
     * Prints the elements of the list in backward order.
     */
    void printBackward();
}
