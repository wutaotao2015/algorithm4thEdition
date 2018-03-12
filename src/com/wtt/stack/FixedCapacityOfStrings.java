package com.wtt.stack;

import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityOfStrings {

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

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {

        FixedCapacityOfStrings fixedStack = new FixedCapacityOfStrings();
        fixedStack.init(50);

        //to be or not to - be - - that - - - is
        for (String s : args) {
            if (!"-".equals(s)) {
                fixedStack.push(s);
            } else if (!fixedStack.isEmpty()) {
                StdOut.print(fixedStack.pop() + " ");
            }
        }
        StdOut.println("(" + fixedStack.size() + " left on String stack" + ")");
    }
}
