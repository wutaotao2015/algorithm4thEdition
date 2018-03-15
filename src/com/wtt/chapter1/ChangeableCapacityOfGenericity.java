package com.wtt.chapter1;

import edu.princeton.cs.algs4.StdOut;

public class ChangeableCapacityOfGenericity<T> {

    private T[] arr;
    private int n;

    public void init(int capacity) {
        arr = (T[]) new Object[capacity];
    }

    public void push(T t) {
        //栈的大小 = 数组的大小
        if (n == arr.length) resize(arr.length * 2);
        arr[n++] = t;
    }

    private void resize(int newCapacity) {
        T[] tmp = (T[])new Object[newCapacity];
        for (int i = 0; i < n; i++) {
            tmp[i] = arr[i];
        }
        arr = tmp;
    }

    public T pop() {
        T t = arr[--n];
        //这里如果不处理，则只是指针n移动，被pop的元素仍然在
        arr[n] = null;
        if (n > 0 && n == arr.length / 4) resize(arr.length/2);
        return t;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public int capacity(){
        return arr.length;
    }

    public static void main(String[] args) {

        ChangeableCapacityOfGenericity<String> changeableStack = new ChangeableCapacityOfGenericity<>();
        changeableStack.init(1);

        //to be or not to - be - - that - - - is
        for (String s : args) {
            if (!"-".equals(s)) {
                changeableStack.push(s);
//                StdOut.println("aPu:size=" + changeableStack.size() + ",capa=" + changeableStack.capacity() + ",");
            } else if (!changeableStack.isEmpty()) {
                StdOut.print(changeableStack.pop() + ",");
//                StdOut.println("aPo:size=" + changeableStack.size() + ",capa=" + changeableStack.capacity() + ",");
            }
        }
        StdOut.println("(" + changeableStack.size() + " left on generic stack" + ")");
    }
}