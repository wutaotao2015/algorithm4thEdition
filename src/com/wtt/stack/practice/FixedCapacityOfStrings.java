package com.wtt.stack.practice;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class FixedCapacityOfStrings implements Iterable<String>{

    private String[] arr;
    private int n;

    public void init(int capacity) {
        arr = new String[capacity];
    }

    public void push(String a) {
        arr[n++] = a;
    }

    public String pop() {
        return arr[--n];
    }

    public String peek() {
        return arr[n-1];
    }
    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isFull() {
        return arr.length == n;
    }
    public int size() {
        return n;
    }



    @Override
    public Iterator<String> iterator() {
        return new ReverArrayIterator();
    }

    private class ReverArrayIterator implements Iterator<String> {

        int i = n - 1;

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public String next() {
            if (!hasNext()) throw new NullPointerException("the stack is empty");
            return arr[i--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

        FixedCapacityOfStrings fixedStack = new FixedCapacityOfStrings();
        fixedStack.init(50);

        //to be or not to - be - - that - - - is - - - - -
        for (String s : args) {
            if (!"-".equals(s)) {
                fixedStack.push(s);
            } else if (fixedStack.isEmpty()) {
                StdOut.print("badInput,");
            } else{
                StdOut.print(fixedStack.pop() + ",");
            }
        }
        StdOut.println();
        StdOut.println("(" + fixedStack.size() + " left on String stack" + ")");
        for (String s : fixedStack) {
            StdOut.print(s + ",");
        }
    }
}
