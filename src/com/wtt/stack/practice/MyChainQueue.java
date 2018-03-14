package com.wtt.stack.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyChainQueue<T> implements Iterable<T>{

    @Override
    public Iterator<T> iterator(){
        return new ChainQueueIterator();
    }
    private class ChainQueueIterator implements Iterator<T> {

        private Node cur = first;
        @Override
        public boolean hasNext(){
            return cur != null;
        }
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
    private Node last;
    private int n;
    public int size(){return n;}
    public boolean isEmpty(){return n == 0;}
    public void enqueue(T t){
        Node oldLast = last;
        last = new Node();
        last.item = t;
        if (n == 0) {
            first = last;
        }else{
            oldLast.next = last;
        }
        n++;
    }
    public T dequeue(){
        if(n == 0) throw new NoSuchElementException("queue underflow");
        T t = first.item;
        first = first.next;
        if(n == 1) last = null;
        n--;
        return t;
    }

    public static void main(String[] args) {

        MyChainQueue<String> queue = new MyChainQueue<>();
        String test = "to be or not to - be - - that - - - is";
        String[] testArr = test.split("\\s+");
        for (String s : testArr) {
            if (!"-".equals(s)){
                queue.enqueue(s);
            }else{
                System.out.print(queue.dequeue() + ",");
            }
        }
        System.out.println(queue.size() + "");
        for (String s : queue) {
            System.out.println(s);
        }
        System.out.println("over");
    }
}
