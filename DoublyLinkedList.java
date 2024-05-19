package doublyLinkedList;

// Assignment: 5
// Author: Danielle Elnekave, ID: 208267096

/**
 * A doubly linked list implementation that allows adding, removing, and searching for elements in both forward and backward directions.
 *
 * @param <T> the type of elements stored in the linked list
 */
public class DoublyLinkedList<T> implements List<T> {

    private Node<T> first;
    private Node<T> last;

    /**
     * Constructs an empty doubly linked list.
     */
    public DoublyLinkedList() {
        first = null;
        last = null;
    }

    /**
     * Checks if the doubly linked list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null && last == null;
    }

    /**
     * Returns the number of elements in the doubly linked list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        int count = 0;
        Node<T> current = first;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /**
     * Adds an element to the beginning of the doubly linked list.
     *
     * @param data the element to be added
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first.setPrevious(newNode);
            first = newNode;
        }
    }

    /**
     * Adds an element to the end of the doubly linked list.
     *
     * @param data the element to be added
     */
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
        }
        last = newNode;
    }

    /**
     * Removes the first occurrence of the specified element from the doubly linked list.
     *
     * @param data the element to be removed
     * @return the node containing the removed element, or null if the element was not found
     */
    public Node<T> remove(T data) {
        Node<T> current = first;

        while (current != null) {
            if (current.getValue().equals(data)) {
                Node<T> prev = current.getPrevious();
                Node<T> next = current.getNext();

                if (prev == null) {
                    first = next;
                } else {
                    prev.setNext(next);
                }

                if (next == null) {
                    last = prev;
                } else {
                    next.setPrevious(prev);
                }

                System.out.println(current.getValue() + " has been removed");
                return current;
            }

            current = current.getNext();
        }

        System.out.println(data + " doesn't exist in the list");
        return null;
    }

    /**
     * Removes all elements from the doubly linked list, making it empty.
     */
    public void clear() {
        first = null;
        last = null;
    }

    /**
     * Checks if the doubly linked list contains the specified element.
     *
     * @param data the element to be checked
     * @return true if the element is found in the list, false otherwise
     */
    public boolean contains(T data) {
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Prints the elements of the doubly linked list in forward order.
     * If the list is empty, it prints a corresponding message.
     */
    public void printForward() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        for (Node<T> current = first; current != null; current = current.getNext()) {
            System.out.print(current.getValue() + " ");
        }

        System.out.println();
    }

    /**
     * Prints the elements of the doubly linked list in backward order.
     * If the list is empty, it prints a corresponding message.
     */
    public void printBackward() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        Node<T> current = last;

        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getPrevious();
        }
        System.out.println();
    }
}
