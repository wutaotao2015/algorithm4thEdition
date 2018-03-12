package com.wtt.stack;

import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityOfGenericity<T> {

    private T[] arr;
    private int n;

    public void init(int capacity) {
        arr = (T[]) new Object[capacity];
    }

    public void push(T a) {
        arr[n++] = a;
    }

    public T pop() {
        return arr[--n];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {

        FixedCapacityOfGenericity<String> fixedStack = new FixedCapacityOfGenericity<>();
        fixedStack.init(50);

        //to be or not to - be - - that - - - is
        for (String s : args) {
            if (!"-".equals(s)) {
                fixedStack.push(s);
            } else if (!fixedStack.isEmpty()) {
                StdOut.print(fixedStack.pop() + " ");
            }
        }
        StdOut.println("(" + fixedStack.size() + " left on generic stack" + ")");
    }
}
