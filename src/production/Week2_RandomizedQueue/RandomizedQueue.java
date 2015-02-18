import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This file is created by @Muffin_C on 2/4/15 20:15.
 * This file is part of Project algs4partI.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomIterator implements Iterator<Item> {
        private int currentAtIndex;
        private int[] indice;

        RandomIterator() {
            currentAtIndex = 0;
            indice = new int[itemCount];

            for (int i = 0; i <= itemCount - 1; i++) {
                indice[i] = i;
            }

            StdRandom.shuffle(indice);
        }

        @Override
        public boolean hasNext() {
            return currentAtIndex != itemCount;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                return items[indice[currentAtIndex++]];
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Item[] items;
    private int totalSize;
    private int itemCount;

    public RandomizedQueue() {
        itemCount = 0;
        totalSize = 10;
        items = (Item[]) new Object[totalSize];
        itemCount = 0;
    }

    public boolean isEmpty() {
        return itemCount == 0;
    }

    public int size() {
        return itemCount;
    }                       // return the number of items on the queue

    private void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i <= itemCount - 1; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
        totalSize = newSize;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else {
            items[itemCount++] = item;
            if (itemCount == totalSize) {
                resize(totalSize * 2);
            }
        }
    }          // add the item

    public Item dequeue() {
        if (itemCount == 0) {
            throw new NoSuchElementException();
        } else {
            int indexToRemove = StdRandom.uniform(size());
            Item foo = items[indexToRemove];
            items[indexToRemove] = items[itemCount - 1];
            items[itemCount - 1] = null;
            itemCount--;
            if (itemCount == totalSize / 4) {
                resize(totalSize / 2);
            }
            return foo;
        }
    }                   // delete and return a random item

    public Item sample() {
        if (itemCount == 0) {
            throw new NoSuchElementException();
        } else {
            return items[StdRandom.uniform(size())];
        }
    }                    // return (but do not delete) a random item

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    public static void main(String[] args) {
    }
}
