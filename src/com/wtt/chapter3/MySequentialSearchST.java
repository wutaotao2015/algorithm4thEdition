package com.wtt.chapter3;

import com.wtt.chapter1.practice.MyChainQueue;

/**
 * 2018/3/26 15:51 add by wutaotao
 */
public class MySequentialSearchST<Key, Value> {


    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node first;
    private int n;

    public void put(Key key, Value value) {

        for (Node curNode = first; curNode != null; curNode = curNode.next) {
            if (curNode.key.equals(key)) {
                curNode.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        n++;
    }

    public Value get(Key key) {
        for (Node curNode = first; curNode != null; curNode = curNode.next) {
            if (curNode.key.equals(key)) {
                return curNode.value;
            }
        }
        return null;
    }

    public int size() {
        return n;
    }

    public Iterable<Key> keys() {
        MyChainQueue<Key> queue = new MyChainQueue<>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    public void delete(Key key) {
        // 循环写法
//        Node curNode = first;
//        Node prevNode = null;
//        while (curNode != null) {
//            if (curNode.key.equals(key)) {
//                if (prevNode == null) {
//                    first = first.next;
//                    n--;
//                    return;
//                } else {
//                    prevNode.next = curNode.next;
//                    curNode = null;
//                    n--;
//                    return;
//                }
//            }
//            prevNode = curNode;
//            curNode = curNode.next;
//        }
        //递归写法
        first = delete(first, key);
    }

    public Node delete(Node x, Key key) {
        if (x == null) return null;
        if (x.key.equals(key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public static void main(String[] args) {
        MySequentialSearchST<String, Integer> ssst = new MySequentialSearchST<>();
        ssst.put("wtt", 3);
        ssst.put("wll", 2);
        ssst.put("wsl", 5);
        System.out.println(ssst.get("wtt"));
        System.out.println(ssst.get("wsl"));
        System.out.println(ssst.get("wll"));
        System.out.println("size " + ssst.size());
        Iterable<String> keys = ssst.keys();
        for (String key : keys) {
            System.out.print(key + ",");
        }
        System.out.println();
        ssst.delete("wtt");
        System.out.println("size " + ssst.size());
        System.out.println(ssst.get("wtt"));
        keys = ssst.keys();
        for (String key : keys) {
            System.out.print(key + ",");
        }
    }
}
