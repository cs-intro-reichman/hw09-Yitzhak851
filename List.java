
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
        if (size == 0) {
            addFirst(chr);
            return;
        }
        Node current = first;
        while (current != null) {
            if (current.cd.equals(chr)) {
                current.cd.count++;
                break;
            }
            current = current.next;
        }
        if (current == null){
            addFirst(chr);
        }
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
        if (size == 0) {
            return false;
        }
        if (first.cd.equals(chr)) {
            first = first.next;
            size--;
            return true;
        }
        Node current = first;
        while (current.next != null) {
            if (current.next.cd.equals(chr)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        updateProbabilities();
        return false;
    }

    /**
     * Returns the CharData object at the specified index in this list.
     * If the index is negative or is greater than the size of this list,
     * throws an IndexOutOfBoundsException.
     */
    public CharData get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.cd;
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
        if (size == 0)
            return null;
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return new ListIterator(current);
    }

    /** GIVE Textual representation of this list. */
    public String toString() {
        String str = "(";
        Node current = first;
        while (current != null) {
            str += current.cd.toString();
            current = current.next;
            if (current != null)
                str += " ";
        }
        str += ")";
        return str;
    }

}
