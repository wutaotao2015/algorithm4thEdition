package com.wtt.stack.practice;

import java.util.Iterator;

public class Parentheses {

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';

    public static boolean isBalanced(String s) {

        Stack1<Character> stack1 = new Stack1<>();
        for (char c : s.toCharArray()) {
            if (c == LEFT_PAREN) stack1.push(c);
            if (c == LEFT_BRACE) stack1.push(c);
            if (c == LEFT_BRACKET) stack1.push(c);

            if (c == RIGHT_PAREN) {
                if (stack1.isEmpty()) return false;
                if (stack1.pop() != LEFT_PAREN) return false;
            }
            if (c == RIGHT_BRACE) {
                if (stack1.isEmpty()) return false;
                if (stack1.pop() != LEFT_BRACE) return false;
            }
            if (c == RIGHT_BRACKET) {
                if (stack1.isEmpty()) return false;
                if (stack1.pop() != LEFT_BRACKET) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        //[()]{}{[()()]()}
        //[(])
        System.out.println(args[0]);
        System.out.println(isBalanced(args[0]));
    }

}

//链表实现的栈
class Stack1<T> implements Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
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

    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private int n;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T t) {
        n++;
        Node oldFirst = first;
        first = new Node();
        first.item = t;
        first.next = oldFirst;
    }

    public T pop() {
        n--;
        T t = first.item;
        first = first.next;
        return t;
    }

    public static void main(String[] args) {
        //to be or not to - be - - that - - - is
        Stack1<String> chainStack = new Stack1<>();
        for (String s : args) {
            if (!"-".equals(s)) {
                chainStack.push(s);
            } else if (!chainStack.isEmpty()) {
                System.out.print(chainStack.pop() + ",");
            }
        }
        System.out.println("(" + chainStack.size() + " left on generic stack" + ")");
        for (String s : chainStack) {
            System.out.println(s);
        }
    }
}