import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This file is created by @Muffin_C on 2/4/15 19:23.
 * This file is part of Project algs4partIAssignment.
 */

/**
 * This file is created by @Muffin_C on 2/4/15 19:44.
 * This file is part of Project algs4partI.
 */

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Item item, Node prev, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node currentNode;

        DequeIterator() {
            currentNode = head.next;
        }

        @Override
        public boolean hasNext() {
            return currentNode.item != null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Item item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public Deque() {
        size = 0;
        head = new Node(null, null, null);
        tail = new Node(null, head, null);
        head.next = tail;
    }
    public boolean isEmpty() { // is the deque empty?
        return size == 0;
    }

    public int size() { // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node foo = new Node(item, head, head.next);
            head.next = foo;
            foo.next.prev = foo;
            size++;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            Node foo = new Node(item, tail.prev, tail);
            tail.prev = foo;
            foo.prev.next = foo;
            size++;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node foo = head.next;
            head.next = head.next.next;
            head.next.prev = head;
            size--;
            foo.next = null;
            foo.prev = null;
            return foo.item;
        }
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Node foo = tail.prev;
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
            size--;
            foo.next = null;
            foo.prev = null;
            return foo.item;
        }
    }

    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    public static void main(String[] args) {

    }
}
