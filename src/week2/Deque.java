package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double-ended queue or deque (pronounced "deck") is a generalization of a stack
 * and a queue that supports adding and removing items from either the front or
 * the back of the data structure.
 * <p>
 * Created by Eugene on 08-Mar-16.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }

    public Deque() {

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }

        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            oldLast.next = last;
        }

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item removed = first.item;
        first = first.next;
        size--;

        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }

        return removed;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item removed = last.item;
        last = last.prev;
        size--;

        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }

        return removed;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException();
                }

                Item item = current.item;
                current = current.next;

                return item;
            }
        };
    }

    public static void main(String[] args) {
        /*Deque<Integer> integers = new Deque<Integer>();
        for (int i = 0; i < 1000000000; i++) {
            try {
                Random random = new Random();
                int operationIndex = random.nextInt(4);
                switch (operationIndex) {
                    case 0:
                        integers.addFirst(42);
                        break;
                    case 1:
                        integers.addLast(42);
                        break;
                    case 2:
                        integers.removeFirst();
                        break;
                    case 3:
                        integers.removeLast();
                        break;

                }


            } catch (java.util.NoSuchElementException e) {

            }
        }*/
    }
}
