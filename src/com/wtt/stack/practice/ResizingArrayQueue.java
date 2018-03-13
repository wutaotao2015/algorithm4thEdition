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

    private T[] arr = (T[]) new Object[1];

    //first比last大1 是空队列的状态
    private int first = 0;
    private int last = -1;
    private int n = 0;

    public int size() {
        return n;
    }

    public int capacity() {
        return arr.length;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void enqueue(T t) throws Exception {
        if (last == arr.length - 1) {
            resize(arr.length * 2);
        }
        arr[++last] = t;
        n++;
    }

    public T dequeue() {
        if (first > last) throw new NullPointerException("queue is empty,cannot dequeue");
        T t = arr[first];
        arr[first++] = null;
        n--;
        if ((last - first) == arr.length / 4) {
            resize(arr.length / 2);
            last = last - first;
            first = 0;
        }
        return t;
    }

    private void resize(int newCapacity) {

        T[] tmp = (T[]) new Object[newCapacity];
        for (int i = 0; i < n; i++) {
            tmp[i] = arr[first + i];
        }
        arr = tmp;
    }

    public static void main(String[] args) throws Exception {

        ResizingArrayQueue<String> stack2 = new ResizingArrayQueue<>();
        String test = "to be or not to - be - - that - - - is";
        String[] testArr = test.split("\\s+");
        for (String s : testArr) {
            if (!"-".equals(s)) {
                stack2.enqueue(s);
            } else {
                System.out.println(stack2.dequeue() + ":" + stack2.size() + "-" + stack2.capacity());
            }
        }
        System.out.println(stack2.size() + " left on stack" + "-" + stack2.capacity());
        for (String s : stack2) {
            System.out.println(s);
        }
        System.out.println("over");
    }
}
