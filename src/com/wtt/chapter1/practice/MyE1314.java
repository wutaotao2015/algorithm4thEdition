package com.wtt.chapter1.practice;

// 从对2个指针的控制实现逻辑上的循环队列
// 增加元素为last++,last == length时last = 0
// 删除元素为first++,first == length时first = 0

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyE1314<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new MyE1314Iterator();
    }

    private class MyE1314Iterator implements Iterator<T> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public T next() {
            T t = q[(first + i) % q.length];
            i++;
            return t;
        }
    }

    private T[] q = (T[]) new Object[2];
    private int first = 0;
    private int last = 0;
    private int n = 0;

    public int capacity(){
        return q.length;
    }
    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public T peek() {
        // 空队列 peek 抛出异常，阻止返回null
        if (isEmpty()) throw new NoSuchElementException("queue underflow");
        return q[first];
    }

    private void resize(int newLength) {

        T[] tmp = (T[]) new Object[newLength];
        for (int i = 0; i < n; i++) {
            tmp[i] = q[(first + i) % q.length];
        }
        q = tmp;
        first = 0;
        last = n;
    }

    public void enqueue(T t) {

        if (n == q.length) resize(q.length * 2);
        q[last++] = t;
        if (last == q.length) last = 0;
        n++;
    }

    public T dequeue() {
        // 空栈删除 为 下溢出
        if (n == 0) throw new NoSuchElementException("queue underflow");
        T t = q[first];
        q[first] = null;
        first++;
        n--;
        if (first == q.length) first = 0;
        // n == 0 不用缩小数组，但是上面已经抛出了异常
        if (n == q.length / 4) resize(q.length / 2);
        return t;
    }
    public static void main(String[] args) throws Exception {

        MyE1314<String> stack2 = new MyE1314<>();
        String test = "to be or not to - be - - that - - - is";
        String[] testArr = test.split("\\s+");
        for (String s : testArr) {
            if (!"-".equals(s)) {
                stack2.enqueue(s);
            } else {
                System.out.println(stack2.dequeue() + ":" + stack2.size() + "-" + stack2.capacity());
            }
        }
        System.out.println(stack2.size() + " left on stack" + "-" + stack2.capacity());
        for (String s : stack2) {
            System.out.println(s);
        }
        System.out.println("over");
    }
}














