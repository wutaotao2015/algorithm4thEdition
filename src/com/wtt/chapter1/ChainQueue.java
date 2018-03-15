package com.wtt.chapter1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 栈的链表实现
 * Created by wutaotao
 * 2018/3/10 16:03
 */
public class ChainQueue<T> implements Iterable<T> {

    private class Node {
         T content;
         Node next;
    }

    private Node first;
    private Node last;
    private int n;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void enqueue(T t) {
        Node oldLast = last;
        last = new Node();
        last.content = t;
        if (first == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    public T dequeue() {
        T t = first.content;
        first = first.next;
        n--;
        //this is the last element, first is null
        if (first == null) {
            last = null;
        }
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ChainQueueIterator();
    }

    private class ChainQueueIterator implements Iterator<T> {

        private Node cur = first;
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            T t = cur.content;
            cur = cur.next;
            return t;
        }
    }

    public static void main(String[] args) {

        String test = "to be or not to - be - - that - - - is";
        String[] arr = test.split("\\s+");
        ChainQueue<String> chainQueue = new ChainQueue<>();
        for (String s : arr) {
            if (!"-".equals(s)) {
                chainQueue.enqueue(s);
            } else if (!chainQueue.isEmpty()) {
                StdOut.print(chainQueue.dequeue() + ",");
            }
        }
        StdOut.println("(" + chainQueue.size() + " left on generic stack" + ")");
        for (String s : chainQueue) {
            System.out.println(s);
        }
    }
}
