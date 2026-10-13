/**
 * DoublyLink
 *
 * A single node of a doubly linked list. It holds one piece of data and
 * references to both the next and the previous node.
 */
public class DoublyLink<T> {
    public T data;                 // the value stored in this node
    public DoublyLink<T> next;     // next node (null if this is the tail)
    public DoublyLink<T> previous; // previous node (null if this is the head)

    // Time complexity: O(1)
    public DoublyLink(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    // Time complexity: O(1)
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
