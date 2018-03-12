package com.wtt.stack;

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
        if (isEmpty()) {
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
        if (isEmpty()) {
            last = null;
        }
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public static void main(String[] args) {

        //to be or not to - be - - that - - - is
        ChainQueue<String> chainStack = new ChainQueue<>();
        for (String s : args) {
            if (!"-".equals(s)) {
                chainStack.enqueue(s);
            } else if (!chainStack.isEmpty()) {
                StdOut.print(chainStack.dequeue() + ",");
            }
        }
        StdOut.println("(" + chainStack.size() + " left on generic stack" + ")");
    }
}
