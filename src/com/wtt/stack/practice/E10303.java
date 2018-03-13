package com.wtt.stack.practice;

/**
 * Created by wutaotao
 * 2018/3/12 21:59
 */

import java.util.Iterator;

/**
 * ClassName    : E10303 <br>
 * Function     : TODO ADD FUNCTION. <br>
 * date         : Sep 28, 2016 2:25:18 PM <br>
 */
public class E10303 {
    public static boolean isValid(int[] seq) {
        Stack<Integer> stack = new Stack<Integer>();
        int currentNum = 9;
        int index = 9;
        while (currentNum >= 0) {
            if (index >= 0 && seq[index] == currentNum) {
                index--;
                currentNum--;
            } else if (!stack.isEmpty() && stack.top() == currentNum) {
                stack.pop();
                currentNum--;
            } else {
                if (index < 0)
                    break;
                stack.push(seq[index]);
                index--;
            }
            if (stack.size() == 0) System.out.print("null");
            for (Integer i : stack) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        String line = "2 5 6 7 4 8 9 3 1 0";
        String[] values = line.split("\\s+");
        int[] nums = new int[10];

        for (int i = 0; i < values.length; i++) {
            nums[i] = Integer.parseInt(values[i]);
        }

        if (isValid(nums)) {
            System.out.println("OK");
        } else {
            System.out.println("No");
        }
    }

}

class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item top() {
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private Node p = first;

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public Item next() {
            Item item = p.item;
            p = p.next;
            return item;
        }
    }
}

