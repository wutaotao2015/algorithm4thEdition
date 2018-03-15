package com.wtt.chapter1.practice;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
public class Bag<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator(){
        return new BagIterator();
    }
    private class BagIterator implements Iterator<T> {

        private Node cur = first;
        @Override
        public boolean hasNext(){return cur != null;}
        @Override
        public T next(){
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
    private int n;

    public int size(){return n;}
    public boolean isEmpty(){return first == null;}
    public void add(T t) {
        n++;
        Node oldFirst = first;
        first = new Node();
        first.item = t;
        first.next = oldFirst;
    }
    public static void main(String[] args) {
        //to be or not to - be - - that - - - is
        Bag<String> chainBag = new Bag<>();
        for (String s : args) {
            if (!"-".equals(s)) {
                chainBag.add(s);
            }
        }
        // bag element can't be removed
        StdOut.println(chainBag.size());
        for (String s : chainBag) {
            StdOut.print(s + ",");
        }
        StdOut.println();
    }
}
