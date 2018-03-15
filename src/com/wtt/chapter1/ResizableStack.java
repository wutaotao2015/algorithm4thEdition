package com.wtt.chapter1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/** 栈的数组实现
 * Created by wutaotao
 * 2018/3/10 10:51
 */
public class ResizableStack<T> implements Iterable<T> {

    private T[] stack = (T[]) new Object[1];
    private int n = 0;

    public boolean isEmpty() {
        return n == 0;
    }

    public int size(){
        return n;
    }

    public int capacity(){
        return stack.length;
    }

    public void push(T t){
        if (n == stack.length) resize(stack.length * 2);
        stack[n++] = t;
    }

    public T pop() {
        T t = stack[--n];
        stack[n] = null;
        if (n > 0 && n == stack.length / 4) resize(stack.length / 2);
        return t;
    }

    private void resize(int newCapacity) {
        T[] tmp = (T[])new Object[newCapacity];
        for(int i = 0; i < n; i++) {
            tmp[i] = stack[i];
        }
        stack = tmp;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator();
    }

    private class ReverseIterator implements Iterator<T> {

        private int i = n;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return stack[--i];
        }

    }

    public static void main(String[] args) {

        ResizableStack<String> resizableStack = new ResizableStack();

        //to be or not to - be - - that - - - is
        for (String s : args) {
            if (!"-".equals(s)) {
                resizableStack.push(s);
                StdOut.println("aPu:size=" + resizableStack.size() + ",capa=" + resizableStack.capacity() + ",");
            } else if (!resizableStack.isEmpty()) {
                StdOut.println("popOut: " + resizableStack.pop() + ",");
                StdOut.println("aPo:size=" + resizableStack.size() + ",capa=" + resizableStack.capacity() + ",");
            }
        }
        StdOut.println("(" + resizableStack.size() + " left on generic stack" + ")");
    }

}
