package algo.ch02.queue;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by chiyx on 2016/9/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 4;

    private Object[] elementData;

    /**
     * 实际的元素个数
     */
    private int size;

    public RandomizedQueue() {
        elementData = new Object[INIT_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("item is null");
        }
        // 当容量用完时，双倍扩充容量
        if (size == elementData.length) {
            reallocElementData(size * 2);
        }
        elementData[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int rand = StdRandom.uniform(size);
        Item rs = (Item) elementData[rand];
        int last = size - 1;
        // 将队尾元素交换到出队元素的位置，然后移除队尾元素
        elementData[rand] = elementData[last];
        elementData[last] = null;
        size--;
        if (size > 0 && size == elementData.length / 4) {
            reallocElementData(elementData.length / 2);
        }

        return rs;
    }

    @SuppressWarnings("unchecked")
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        int rand = StdRandom.uniform(size);
        return (Item) elementData[rand];
    }


    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void reallocElementData(int capacity) {
        Object[] newElementData = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item).append(" ");
        return s.toString();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Object[] innerData;

        private int innerSize;

        private RandomizedQueueIterator() {
            innerSize = size;
            innerData = new Object[innerSize];
            for (int i = 0; i < size; i++) {
                innerData[i] = elementData[i];
            }
        }

        @Override
        public boolean hasNext() {
            return innerSize > 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Item next() {
            if (innerSize == 0) throw new NoSuchElementException("no more element");
            int rand = StdRandom.uniform(innerSize);
            Item rs = (Item) innerData[rand];
            int last = innerSize - 1;
            innerData[rand] = innerData[last];
            innerData[last] = null;
            innerSize--;
            return rs;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("RandomizedQueueIterator unsupported remove");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(10);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(9);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(6);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(8);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(5);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(4);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(3);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(7);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(2);
        StdOut.println(queue);
        StdOut.println(queue);
        queue.enqueue(1);
        StdOut.println(queue);
        StdOut.println(queue);
        StdOut.println(queue.dequeue());
        StdOut.println(queue);
        StdOut.println(queue);
        StdOut.println(queue.dequeue());
        StdOut.println(queue);
        StdOut.println(queue);
        StdOut.println(queue.dequeue());
        StdOut.println(queue);
        StdOut.println(queue);
        StdOut.println(queue.dequeue());
        StdOut.println(queue);
        StdOut.println(queue);
        StdOut.println(queue.dequeue());
        StdOut.println(queue);
        StdOut.println(queue);
    }
}
