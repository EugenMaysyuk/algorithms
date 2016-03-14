import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Randomized Queue.
 * <p>
 * Created by Eugene on 11-Mar-16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] array;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] resized = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            resized[i] = array[i];
        }
        array = resized;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        array[size] = item;
        size++;
        if (size == array.length) {
            resize(array.length * 2);
        }
    }

    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        Item item = array[randomIndex];
        array[randomIndex] = array[size - 1];
        size--;
        array[size] = null; // set null to last item that we already moved
        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        return array[StdRandom.uniform(size)];
    }

    private class RIterator implements Iterator<Item> {
        private int[] arr;
        private int s = size;

        public RIterator() {
            arr = new int[s];
            for (int i = 1; i < s; i++) {
                arr[i - 1] = i;
            }
            StdRandom.shuffle(arr);
        }

        @Override
        public boolean hasNext() {
            return s > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return array[arr[--s]];
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new RIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();

        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);
        randomQueue.enqueue(4);
        randomQueue.enqueue(5);
        randomQueue.enqueue(6);
        randomQueue.enqueue(7);
        randomQueue.enqueue(8);
        randomQueue.enqueue(9);
        randomQueue.dequeue();
        randomQueue.dequeue();

        StdOut.println("Output: ");
        for (Integer x : randomQueue) {
            StdOut.print(x + " ");
        }
    }
}
