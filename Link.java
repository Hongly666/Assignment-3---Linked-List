/**
 * Link
 * A single node of a singly linked list. It holds one piece of data and
 * a reference to the next node. A null next means this is the last node.
 */
public class Link<T> {
    public T data;        // the value stored in this node
    public Link<T> next;  // reference to the next node (null if last)

    // Time complexity: O(1)
    public Link(T data) {
        this.data = data;
        this.next = null;
    }

    // Time complexity: O(1)
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
