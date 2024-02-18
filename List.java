
public class List {
    public Node first; // Points to the first node in this list (CharData object)
    public int size; // The number of elements in this list

    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
        return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cd;
    }

    /** Returns index of the first CharData object in this list */
    // that has the same chr value as the given char,
    // * or -1 if there is no such object in this list.
    // */
    public int indexOf(char chr) {
        Node current = first;
        int i = 0;
        while (current != null) {
            if (current.cd.equals(chr))
                return i;
            current = current.next;
            i++;
        }
        return -1;
    }

    /** addFirst CharData object with the given character to beginning list. */
    public void addFirst(char chr) {
        first = new Node(new CharData(chr), first);
        size++;
    }

    /** this method is a equals method between list's */
    public boolean equals(List list) {
        if (size != list.size)
            return false;
        Node current = first;
        Node other = list.first;
        while (current != null) {
            if (!current.cd.equals(other.cd))
                return false;
            current = current.next;
            other = other.next;
        }
        return true;
    }

    /**
     * If the given character exists in one of the CharData objects in this list,
     * increments its counter. Otherwise, adds a new CharData object with the
     * given chr to the beginning of this list.
     */
    public void update(char chr) {
        // if the list is empty, adds a new CharData object with the given chr to the
        // beginning of this list
        if (size == 0) {
            addFirst(chr);
            return;
        }
        // if the given character exists in one of the CharData objects in this list,
        // increments its counter
        Node current = first;
        while (current != null) {
            // if the current node contains the given character, increments its counter and
            // returns
            if (current.cd.equals(chr)) {
                // increments the counter of the current node
                current.cd.count++;
                // returns
                return;
            }
            // moves to the next node
            current = current.next;
        }
        // if the given character does not exist in one of the CharData objects in this
        // list, adds a new CharData object with the given chr to the beginning of this
        // list
        addFirst(chr);
        // updates the probability fields of all the CharData objects in this list
        updateProbabilities();

    }

    // updates the probability fields of all the CharData objects in this list
    public void updateProbabilities() {
        Node current = first;
        int total = 0;
        while (current != null) {
            total += current.cd.count;
            current = current.next;
        }
        current = first;
        while (current != null) {
            current.cd.p = (double) current.cd.count / total;
            current = current.next;
        }
        current = first;
        double cp = 0;
        while (current != null) {
            cp += current.cd.p;
            current.cd.cp = cp;
            current = current.next;
        }
    }

    /**
     * GIVE If the given character exists in one of the CharData objects
     * in this list, removes this CharData object from the lis Otherwise,dont do
     * nothing.
     */
    public boolean remove(char chr) {
        // if the list is empty, there is nothing to remove
        if (size == 0) {
            // returns
            return false;
        }
        // if the given character exists in one of the CharData objects in this list,
        // removes this CharData object from the list
        if (first.cd.equals(chr)) {
            first = first.next;
            size--;
            // returns
            return true;
        }
        // if the given character does not exist in one of the CharData objects in this
        // list, there is nothing to remove
        Node current = first;
        while (current.next != null) {
            // if the next node contains the given character, removes it from the list
            if (current.next.cd.equals(chr)) {
                // removes the next node from the list
                current.next = current.next.next;
                // updates the size of this list
                size--;
                // returns
                return true;
            }
            // moves to the next node
            current = current.next;
        }
        // updates the probability fields of all the CharData objects in this list
        updateProbabilities();
        // returns
        return false;
    }

    /**
     * Returns the CharData object at the specified index in this list.
     * If the index is negative or is greater than the size of this list,
     * throws an IndexOutOfBoundsException.
     */
    public CharData get(int index) {
        // Your code goes here
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.cd;
        // return null;
    }

    /**
     * Returns an array of CharData objects, containing all the CharData objects in
     * this list.
     */
    public CharData[] toArray() {
        CharData[] arr = new CharData[size];
        Node current = first;
        int i = 0;
        while (current != null) {
            arr[i++] = current.cd;
            current = current.next;
        }
        return arr;
    }

    /**
     * Returns an iterator over the elements in this list, starting at the given
     * index.
     */
    public ListIterator listIterator(int index) {
        // If the list is empty, there is nothing to iterate
        if (size == 0)
            return null;
        // Gets the element in position index of this list
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
        return new ListIterator(current);
    }

    /** GIVE Textual representation of this list. */
    public String toString() {
        return "List: {first: " + first + ", size: " + size + '}';
    }

}
