package com.wtt.chapter1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 栈的链表实现
 * Created by wutaotao
 * 2018/3/10 16:03
 */
public class ChainStack<T> implements Iterable<T> {

    private class Node {
        T content;
        Node next;
    }

    private Node first;
    private int n;

    public boolean isEmpty() {
        return first == null;
//        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(T t) {
        n++;
        Node oldFirst = first;
        first = new Node();
        first.content = t;
        first.next = oldFirst;
    }

    public T pop() {
        n--;
        T t = first.content;
        first = first.next;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public static void main(String[] args) {

        //to be or not to - be - - that - - - is
        ChainStack<String> chainStack = new ChainStack<>();
        for (String s : args) {
            if (!"-".equals(s)) {
                chainStack.push(s);
            } else if (!chainStack.isEmpty()) {
                StdOut.print(chainStack.pop() + ",");
            }
        }
        StdOut.println("(" + chainStack.size() + " left on generic stack" + ")");
    }
}
