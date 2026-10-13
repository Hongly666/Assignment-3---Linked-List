/**
 * Generic Queue (FIFO) built on top of our LinkedList.
 * The front of the queue is the HEAD of the list and the rear is the TAIL.
 * insert uses insertLast and remove uses deleteFirst, both O(1), so no
 * circular array or shifting is needed.
 */
public class Queue<T> {
    private LinkedList<T> items;

    // Time complexity: O(1)
    // arraySize is kept only so code written for Assignment 2 still compiles.
    // A linked list has no fixed capacity, so it is ignored.
    public Queue(int arraySize) {
        items = new LinkedList<>();
    }

    // Time complexity: O(1)
    public Queue() {
        items = new LinkedList<>();
    }

    // Time complexity: O(1) - tail pointer
    public void insert(T newItem) {
        items.insertLast(newItem);
    }

    // Time complexity: O(1)
    public T remove() {
        if (items.isEmpty()) {
            return null;
        }
        T item = items.getFirst();
        items.deleteFirst();
        return item;
    }

    // Time complexity: O(1)
    public T peekFront() {
        return items.getFirst();   // null if empty
    }

    // Time complexity: O(1) - tail pointer
    public T peekRear() {
        return items.getLast();    // null if empty
    }

    // Time complexity: O(n)
    // Format: [front, ..., rear]
    @Override
    public String toString() {
        return items.toString();
    }

    // Time complexity: O(n)
    public void display() {
        System.out.println(toString());
    }
}
