package com.wtt.stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/** 链表头部新增，删除都方便
 *  尾部新增方便，但删除很耗费性能，需从开头开始一直遍历直到尾部
 * Created by wutaotao
 * 2018/3/10 21:00
 */
public class ChainBag<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }
    private class Node{
        // Node不用自己声明泛型T，它与ChainBag保持一致
        T content;
        Node next;
    }
    private Node first;
    private int n;

    public boolean isEmpty() {
        return first == null;
    }
    public int size(){
        return n;
    }
    public void add(T t) {
        n++;
        Node oldFirst = first;
        first = new Node();
        first.content = t;
        first.next = oldFirst;
    }

    private class BagIterator implements Iterator<T> {

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T content = current.content;
            current = current.next;
            return content;
        }

    }
    public static void main(String[] args) {
        //to be or not to - be - - that - - - is
        ChainBag<String> chainBag = new ChainBag<>();
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
