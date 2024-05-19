package doublyLinkedList;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * Represents a node in a doubly linked list.
 *
 * @param <T> the type of value stored in the node
 */
public class Node<T> {

    private T value;
    private Node<T> previous;
    private Node<T> next;

    /**
     * Constructs a new node with the specified value.
     *
     * @param value the value to be stored in the node
     */
    public Node(T value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    /**
     * Sets the value of the node.
     *
     * @param value the new value to be set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Sets the previous node of the current node.
     *
     * @param previous the previous node
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    /**
     * Sets the next node of the current node.
     *
     * @param next the next node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Returns the value stored in the node.
     *
     * @return the value of the node
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns the next node of the current node.
     *
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Returns the previous node of the current node.
     *
     * @return the previous node
     */
    public Node<T> getPrevious() {
        return previous;
    }
}
