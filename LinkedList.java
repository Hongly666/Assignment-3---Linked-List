import java.util.Objects;

/**
 * LinkedList
 * Generic, singly linked, double-ended list (keeps both a head and a
 * tail pointer). The tail pointer makes insertLast O(1), but because
 * each node only points forward, deleteLast still has to walk the list
 * to find the node before the tail, so it is O(n).
 *
 * A size counter is kept up to date so size() and bounds checks are O(1).
 */
public class LinkedList<T> {

    private Link<T> head;  // first node (null if empty)
    private Link<T> tail;  // last node (null if empty)
    private int size;      // number of nodes currently in the list

    // Time complexity: O(1)
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Time complexity: O(1) - only the head pointer changes
    public void insertFirst(T newItem) {
        Link<T> newLink = new Link<>(newItem);
        if (isEmpty()) {
            tail = newLink;          // first node is also the last node
        } else {
            newLink.next = head;
        }
        head = newLink;
        size++;
    }

    // Time complexity: O(1) - the tail pointer gives direct access to the end
    public void insertLast(T newItem) {
        Link<T> newLink = new Link<>(newItem);
        if (isEmpty()) {
            head = newLink;
        } else {
            tail.next = newLink;
        }
        tail = newLink;
        size++;
    }

    // Time complexity: O(n) - worst case walks to the node before 'index'
    // (O(1) when index is 0 or size, since those reuse the first/last inserts)
    // Valid indexes are 0 to size (size means insert at the end).
    // Throws IndexOutOfBoundsException for any other index.
    public void insertAt(int index, T newItem) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for list of size " + size);
        }
        if (index == 0) {
            insertFirst(newItem);
            return;
        }
        if (index == size) {
            insertLast(newItem);
            return;
        }
        Link<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }
        Link<T> newLink = new Link<>(newItem);
        newLink.next = previous.next;
        previous.next = newLink;
        size++;
    }

    // Time complexity: O(1) - only the head pointer changes
    public boolean deleteFirst() {
        if (isEmpty()) {
            return false;
        }
        head = head.next;
        if (head == null) {
            tail = null;             // list became empty
        }
        size--;
        return true;
    }

    // Time complexity: O(n) - singly linked, so we must walk the list to find
    // the node just before the tail (there is no way to step backwards)
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {          // only one node
            head = null;
            tail = null;
        } else {
            Link<T> previous = head;
            while (previous.next != tail) {
                previous = previous.next;
            }
            previous.next = null;
            tail = previous;
        }
        size--;
        return true;
    }

    // Time complexity: O(n) - worst case walks to the node before 'index'
    // Returns false if index is out of range.
    public boolean deleteAt(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        if (index == 0) {
            return deleteFirst();
        }
        if (index == size - 1) {
            return deleteLast();
        }
        Link<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }
        previous.next = previous.next.next;
        size--;
        return true;
    }

    // Time complexity: O(n) - linear search for the first match, then O(1) unlink
    // Removes only the first node whose data equals key.
    public boolean deleteKey(T key) {
        Link<T> previous = null;
        Link<T> current = head;
        while (current != null && !Objects.equals(current.data, key)) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return false;            // key not found
        }
        if (previous == null) {
            return deleteFirst();    // match is the head
        }
        previous.next = current.next;
        if (current == tail) {
            tail = previous;         // we removed the last node
        }
        size--;
        return true;
    }

    // Time complexity: O(n) - worst case checks every node
    // Returns the index of the first match, or -1 if not found.
    public int find(T key) {
        Link<T> current = head;
        int index = 0;
        while (current != null) {
            if (Objects.equals(current.data, key)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    // Time complexity: O(1)
    public boolean isEmpty() {
        return head == null;
    }

    // Time complexity: O(1)
    public int size() {
        return size;
    }

    // Time complexity: O(1)
    // Helper for Stack and Queue. Returns null if the list is empty.
    public T getFirst() {
        return isEmpty() ? null : head.data;
    }

    // Time complexity: O(1) - thanks to the tail pointer
    // Helper for Queue. Returns null if the list is empty.
    public T getLast() {
        return isEmpty() ? null : tail.data;
    }

    // Time complexity: O(n)
    // Helper so Stack can print in bottom-to-top order. Order is head to tail.
    public Object[] toArray() {
        Object[] result = new Object[size];
        Link<T> current = head;
        for (int i = 0; i < size; i++) {
            result[i] = current.data;
            current = current.next;
        }
        return result;
    }

    // Time complexity: O(n)
    // Format: [head, ..., tail]
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Link<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        return sb.append("]").toString();
    }

    // Time complexity: O(n)
    public void display() {
        System.out.println(toString());
    }
}
