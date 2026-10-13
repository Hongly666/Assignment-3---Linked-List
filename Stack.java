/**
 * Generic Stack (LIFO) built on top of our LinkedList.
 * The top of the stack is the HEAD of the list, so push and pop only touch
 * the front, which is O(1). (Using the tail as the top would make pop O(n)
 * because deleteLast is O(n) in a singly linked list.)
 *
 * The list grows as needed, so there is no resize step anymore.
 */
public class Stack<T> {
    private LinkedList<T> items;

    // Time complexity: O(1)
    // arraySize is kept only so code written for Assignment 2 still compiles.
    // A linked list has no fixed capacity, so it is ignored.
    public Stack(int arraySize) {
        items = new LinkedList<>();
    }

    // Time complexity: O(1)
    public Stack() {
        items = new LinkedList<>();
    }

    // Time complexity: O(1)
    public void push(T newItem) {
        items.insertFirst(newItem);
    }

    // Time complexity: O(1)
    public T pop() {
        if (items.isEmpty()) {
            return null;
        }
        T item = items.getFirst();
        items.deleteFirst();
        return item;
    }

    // Time complexity: O(1)
    public T peek() {
        return items.getFirst();   // null if empty
    }

    // Time complexity: O(n)
    // Format: [bottom, ..., top]
    // The top is the head of the list, so we read the elements backwards.
    @Override
    public String toString() {
        Object[] data = items.toArray();
        StringBuilder sb = new StringBuilder("[");
        for (int i = data.length - 1; i >= 0; i--) {
            sb.append(data[i]);
            if (i > 0) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    // Time complexity: O(n)
    public void display() {
        System.out.println(toString());
    }
}
