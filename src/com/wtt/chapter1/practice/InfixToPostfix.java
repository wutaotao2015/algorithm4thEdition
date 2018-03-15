package com.wtt.chapter1.practice;

import java.util.Iterator;

public class InfixToPostfix {

}
class Stack2<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new MyStack2Iterator();
    }

    private class MyStack2Iterator implements Iterator<T> {
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
    private int n;

    public int size(){return n;}
    public boolean isEmpty(){return first == null;}
    public void push(T t){
        n++;
        Node oldFirst = first;
        first = new Node();
        first.item = t;
        first.next = oldFirst;
    }
    public T pop(){
        n--;
        T t = first.item;
        first = first.next;
        return t;
    }
    public T peek(){
        return first.item;
    }

    public static void main(String[] args) {

        Stack2<String> stack2 = new Stack2<>();
        String test = "to be or not to - be - - that - - - is";
        String[] testArr = test.split("\\s+");
        for (String s : testArr) {
            if (!"-".equals(s)){
                stack2.push(s);
            }else{
                System.out.print(stack2.pop()+",");
            }
        }
        System.out.println(stack2.size() + " left on stack");
        for (String s : stack2) {
            System.out.println(s);
        }
        System.out.println("over");
    }
}




