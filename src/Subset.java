/**
 * This file is created by @Muffin_C on 2/4/15 21:17.
 * This file is part of Project algs4partI.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }

    }
}
