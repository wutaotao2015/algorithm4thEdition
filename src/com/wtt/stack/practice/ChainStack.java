package com.wtt.stack.practice;

import java.util.Iterator;

public class ChainStack<T> implements Iterable<T>{
    @Override
    public Iterator<T> iterator(){
        return new MyChainStackIterator();
    }
    private class MyChainStackIterator implements Iterator<T> {

        private Node cur = first;
        @Override
        public boolean hasNext(){ return cur != null;}
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
    public boolean isEmpty(){return n == 0;}
    public T peek(){
        return first.item;
    }
    public void push(T t) {
        Node oldFirst = first;
        first = new Node();
        first.item = t;
        first.next = oldFirst;
        n++;
    }
    public T pop(){
        T t = first.item;
        first = first.next;
        return t;
    }
    public static void main(String[] args){

        ChainStack<String> stack = new ChainStack<>();
        String test = "to be or not to - be - - that - - - is";
        String[] arr = test.split("\\s+");
        for(String s: arr){
            if(!"-".equals(s)){
                stack.push(s);
            }else{
                System.out.print(stack.pop() + ",");
            }
        }
        System.out.println(stack.size() + " left on stack");
        for(String s : stack){
            System.out.println(s);
        }
        System.out.println("over");
    }
}