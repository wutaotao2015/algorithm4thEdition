package com.wtt.stack.practice;

/**
 * Created by wutaotao
 * 2018/3/12 21:59
 */
import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * ClassName    : Stack <br>
 * Function     : TODO ADD FUNCTION. <br>
 * date         : Sep 27, 2016 5:07:34 PM <br>
 *
 * @version
 */
public class Stack<Item> implements Iterable<Item>
{
    private Node first;
    private int n;
    private class Node
    {
        Item item;
        Node next;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public int size()
    {
        return n;
    }

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item top()
    {
        return first.item;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>
    {
        private Node p = first;
        @Override
        public boolean hasNext()
        {
            return p != null;
        }

        @Override
        public Item next()
        {
            Item item = p.item;
            p = p.next;
            return item;
        }

    }

    public static void main(String[] args)
    {
        Stack<String> s;
        s = new Stack<String>();
        while (!StdIn.isEmpty())
        {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
