package com.wtt.stack.practice;

import java.util.Iterator;

public class Josephus {

    public static void printSquence(int N, int M){

        JosephusQueue<Integer> queue = new JosephusQueue<>();
        for(int i = 0; i < N; i++) {
            queue.enqueue(i);
        }

        while(queue.size() > 0){
            for(int i = 0; i < M - 1; i++){
                queue.enqueue(queue.dequeue());
            }
            System.out.print(queue.dequeue() + ",");
        }
        System.out.println("over");
    }

    public static void main(String[] args) {
        printSquence(7, 2);
    }
}

class JosephusQueue<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

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

    public class Node {
        T item;
        Node next;
    }

    private Node first;
    private Node last;
    private int n;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }
    public void enqueue(T t) {
        Node oldLast = last;
        last = new Node();
        last.item = t;
        if (isEmpty()){
            first = last;
        }else{
            oldLast.next = last;
        }
        n++;
    }
    public T dequeue(){
        T t = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        n--;
        return t;
    }
    public static void main(String[] args) {

        JosephusQueue<String> queue = new JosephusQueue<>();
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












