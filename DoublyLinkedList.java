import java.util.Objects;

/**
 * DoublyLinkedList
 * Generic, doubly linked, double-ended list (head and tail pointers).
 * Every node points both forward and backward, so the tail can be removed
 * in O(1). This is the main advantage over the singly linked LinkedList,
 * where deleteLast is O(n).
 */
public class DoublyLinkedList<T> {

    private DoublyLink<T> head;  // first node (null if empty)
    private DoublyLink<T> tail;  // last node (null if empty)
    private int size;            // number of nodes currently in the list

    // Time complexity: O(1)
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Time complexity: O(1)
    public void insertFirst(T newItem) {
        DoublyLink<T> newLink = new DoublyLink<>(newItem);
        if (isEmpty()) {
            tail = newLink;
        } else {
            newLink.next = head;
            head.previous = newLink;
        }
        head = newLink;
        size++;
    }

    // Time complexity: O(1)
    public void insertLast(T newItem) {
        DoublyLink<T> newLink = new DoublyLink<>(newItem);
        if (isEmpty()) {
            head = newLink;
        } else {
            tail.next = newLink;
            newLink.previous = tail;
        }
        tail = newLink;
        size++;
    }

    // Time complexity: O(n) - worst case walks to the node at 'index'
    // (O(1) when index is 0 or size)
    // Valid indexes are 0 to size. Throws IndexOutOfBoundsException otherwise.
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
        DoublyLink<T> after = nodeAt(index);   // node currently at 'index'
        DoublyLink<T> before = after.previous;
        DoublyLink<T> newLink = new DoublyLink<>(newItem);
        newLink.next = after;
        newLink.previous = before;
        before.next = newLink;
        after.previous = newLink;
        size++;
    }

    // Time complexity: O(1)
    public boolean deleteFirst() {
        if (isEmpty()) {
            return false;
        }
        unlink(head);
        return true;
    }

    // Time complexity: O(1) - tail.previous gives the new tail directly,
    // no walking needed (unlike the singly linked version)
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        unlink(tail);
        return true;
    }

    // Time complexity: O(n) - worst case walks to the node at 'index'
    // Returns false if index is out of range.
    public boolean deleteAt(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        unlink(nodeAt(index));
        return true;
    }

    // Time complexity: O(n) - linear search, then O(1) unlink
    // Removes only the first node whose data equals key.
    public boolean deleteKey(T key) {
        DoublyLink<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, key)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Time complexity: O(n)
    // Returns the index of the first match, or -1 if not found.
    public int find(T key) {
        DoublyLink<T> current = head;
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

    // Time complexity: O(n)
    // Format: [head, ..., tail]
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoublyLink<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        return sb.append("]").toString();
    }

    // Time complexity: O(n) - walks head to tail
    public void displayForward() {
        System.out.println(toString());
    }

    // Time complexity: O(n) - walks tail to head using the previous pointers
    // Format: [tail, ..., head]
    public void displayBackward() {
        StringBuilder sb = new StringBuilder("[");
        DoublyLink<T> current = tail;
        while (current != null) {
            sb.append(current.data);
            if (current.previous != null) {
                sb.append(", ");
            }
            current = current.previous;
        }
        System.out.println(sb.append("]").toString());
    }

    // Time complexity: O(n) - worst case walks half the list.
    // Starts from whichever end is closer to the index (index must be valid).
    private DoublyLink<T> nodeAt(int index) {
        DoublyLink<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    // Time complexity: O(1) - removes a node we already have a reference to
    // and fixes head, tail, and neighbor pointers.
    private void unlink(DoublyLink<T> node) {
        if (node.previous == null) {
            head = node.next;            // node was the head
        } else {
            node.previous.next = node.next;
        }
        if (node.next == null) {
            tail = node.previous;        // node was the tail
        } else {
            node.next.previous = node.previous;
        }
        node.next = null;
        node.previous = null;
        size--;
    }
}
