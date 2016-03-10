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
        return size() == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] resized = (Item[]) new Object[capacity];
        for (int i = 0; i < array.length; i++) {
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
            resize(size * 2);
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
        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        return array[randomIndex];
    }

    private class RIterator implements Iterator<Item> {
        private int arr[];
        private int s = size;

        public RIterator() {
            arr = new int[s];
            for (int i = 0; i < s; i++) {
                arr[i] = i;
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
}
