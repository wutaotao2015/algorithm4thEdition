package com.wtt.stack.practice;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        private Node cur = first;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            T t = cur.item;
            cur = cur.next;
            return t;
        }
    }

    private class Node{
        T item;
        Node next;
    }
    private Node first;
    private Node last;
    private int n;

    public int size() {return n;}
    public boolean isEmpty(){return first == null;}
    public void enqueue(T t){
        n++;
        Node oldLast = last;
        last = new Node();
        last.item = t;
        //注意空链表时对头指针的赋值
        if(isEmpty()) {
            first = last;
        }else{
            oldLast.next = last;
        }
    }
    public T dequeue(){
        if (isEmpty()) throw new NullPointerException("empty queue");
        n--;
        T t = first.item;
        first = first.next;
        //这是出队的最后一个元素，保留first指针即可
        if (first == null) last = null;
        return t;
    }

    public static void main(String[] args) {

        //to be or not to - be - - that - - - is
        Queue<String> queue = new Queue<>();
        for (String s : args) {
            if (!"-".equals(s)) {
                queue.enqueue(s);
            } else if (!queue.isEmpty()) {
                StdOut.print(queue.dequeue() + ",");
            }
        }
        StdOut.println("(" + queue.size() + " left on generic stack" + ")");
        for (String s : queue) {
            System.out.println(s);
        }
    }
}
