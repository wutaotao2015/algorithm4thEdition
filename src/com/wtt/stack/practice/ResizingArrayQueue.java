package com.wtt.stack.practice;

import java.util.Iterator;

public class ResizingArrayQueue<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new ResizeQueueIterator();
    }

    private class ResizeQueueIterator implements Iterator<T> {

        private int cur = first;

        @Override
        public boolean hasNext() {
            return last - cur >= 0;
        }

        @Override
        public T next() {
            return arr[cur++];
        }
    }

    private T[] arr = (T[]) new Object[100];
    private int first = 0;
    private int last = -1;
    private int n = 0;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void enqueue(T t) throws Exception {
        if (last == arr.length - 1) {
            throw new Exception("queue is full");
        }
        arr[++last] = t;
        n++;
    }

    public T dequeue() {
        if (first > last) throw new NullPointerException("empty queue");
        T t = arr[first];
        arr[first++] = null;
        n--;
        return t;
    }

    public static void main(String[] args) throws Exception {
        ResizingArrayQueue<String> stack2 = new ResizingArrayQueue<>();
        String test = "to be or not to - be - - that - - - is";
        String[] testArr = test.split("\\s+");
        for (String s : testArr) {
            if (!"-".equals(s)) {
                stack2.enqueue(s);
            } else {
                System.out.print(stack2.dequeue() + ",");
            }
        }
        System.out.println(stack2.size() + " left on stack");
        for (String s : stack2) {
            System.out.println(s);
        }
        System.out.println("over");
    }
}
