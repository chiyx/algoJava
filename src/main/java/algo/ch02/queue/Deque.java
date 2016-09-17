package algo.ch02.queue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * chiyx's deque implement
 * Created by chiyx on 2016/9/17.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;

    private Node<Item> last;

    private int size;

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

        private Node(Node<Item> prev, Item item, Node<Item> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        Node<Item> oldFirst = first;
        Node<Item> newNode = new Node<Item>(null, item, oldFirst);
        first = newNode;
        if (oldFirst == null) {
            last = newNode;
        } else {
            oldFirst.prev = newNode;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        Node<Item> oldLast = last;
        Node<Item> newNode = new Node<Item>(oldLast, item, null);
        last = newNode;
        if (oldLast == null) {
            first = newNode;
        } else {
            oldLast.next = newNode;
        }
        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("empty deque");
        }
        Item rs = first.item;
        Node<Item> next = first.next;
        first.item = null;
        first.next = null;
        first = next;
        // when only one item
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return rs;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("empty deque");
        }
        Item rs = last.item;
        Node<Item> prev = last.prev;
        last.item = null;
        last.prev = null;
        last = prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return rs;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIter();
    }

    private class DequeIter implements Iterator<Item> {

        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("no more element");
            }
            Item rs = current.item;
            current = current.next;
            return rs;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove unsupported");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 100; i++) {
           if (i % 2 == 0) {
               deque.addFirst(i);
           }
           else {
               deque.addLast(i);
           }
        }
        for (Integer aDeque : deque) {
            StdOut.print(aDeque + " ");
        }
        StdOut.println();
    }

}
