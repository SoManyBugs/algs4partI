import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This file is created by @Muffin_C on 2/4/15 20:15.
 * This file is part of Project algs4partI.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int arraySize;
    private int head;
    private int tail;
    private int size;

    private class RandomIterator<Item> implements Iterator<Item> {
        private int current;
        private int[] indice;
        private Item[] arrayCopy;

        RandomIterator() {
            current = 0;
            indice = new int[size];
            arrayCopy = (Item[]) new Object[size];

            for (int i = 0; i < size - 1; i++) {
                indice[i] = i;
                arrayCopy[i] = array[(head + i) % arraySize];
            }

            StdRandom.shuffle(indice);
        }

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                return arrayCopy[indice[current++]];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public RandomizedQueue() {
        size = 0;
        arraySize = 10;
        array = (Item[]) new Object[arraySize];
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return size;
    }                       // return the number of items on the queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            array[tail++] = item;
            tail %= arraySize;
            size++;
            if (size == arraySize) {
                resize(arraySize * 2);
            }
        }
    }          // add the item

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            Item foo = array[head++];
            head %= arraySize;
            size--;
            if (size == arraySize / 4) {
                resize(arraySize / 2);
            }
            return foo;
        }
    }                   // delete and return a random item

    private void resize(int newSize) {
        int newHead = 0;
        int newTail = 0;
        Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i <= size - 1; i++) {
            newArray[i] = array[(head + i) % arraySize];
            newTail++;
        }
        head = newHead;
        tail = newTail;
        array = newArray;
        arraySize = newSize;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return array[(head + StdRandom.uniform(size())) % arraySize];
        }
    }                    // return (but do not delete) a random item

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    public static void main(String[] args) {
    }
}
