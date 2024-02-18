/**
 * This class represents an iterator over a list of CharData objects.
 * it can be used to traverse the list and access its elements.
 */

public class ListIterator {
    Node current; // cursor on current position in the list

    /** Constructs a list iterator, starting at the given node. */
    public ListIterator(Node node) {
        current = node; // Sets the cursor of this iterator to the given node
    }

    /** Iterator that checks if has more nodes to process */
    public boolean hasNext() {
        return (current != null); // if the cursor is not null returns true
    }

    /**
     * Returns the CharData object of the current element in this iteration,
     * and advances the cursor to the next element.
     * Should be called only if hasNext() is true.
     */
    public CharData next() {
        CharData cd = current.cd;
        current = current.next;
        return cd;
    }
}