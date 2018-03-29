package com.wtt.chapter3;

import com.wtt.chapter1.practice.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Binary search tree
 * 二叉查找树
 * <p>
 * 2018/3/27 10:00 add by wutaotao
 */
public class MyBST<Key extends Comparable<Key>, Val> {

    private class Node {
        Key key;
        Val val;
        Node left;
        Node right;
        int n;

        public Node(Key key, Val val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    // 一个根节点就可以代表一棵二叉树，利用递归的结构
    private Node root;

//    public Val get(Key key) {
//        return get(root, key);
//    }
//
//    private Val get(Node x, Key key) {
//        if (x == null) return null;
//        int i = key.compareTo(x.key);
//        if (i < 0) {
//            return get(x.left, key);
//        } else if (i > 0) {
//            return get(x.right, key);
//        } else {
//            return x.val;
//        }
//    }

    public Val get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    public void put(Key key, Val val) {
        //更新root,触发递归
        root = put(root, key, val);
    }

    // 更新以x为根节点的子树，层层递归更新从而更新整个二叉树
    public Node put(Node x, Key key, Val val) {

        if (x == null) return new Node(key, val, 1);
        int i = key.compareTo(x.key);
        if (i < 0) {
            x.left = put(x.left, key, val);
        } else if (i > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }
    // 非递归的put方法
//    public void put(Key key, Val val) {
//
//        Node z = new Node(key, val, 1);
//        if (root == null) {
//            root = z;
//            return;
//        }
//        Node parent = null;
//        Node x = root;
//        while (x != null) {
//            parent = x;
//            int cmp = key.compareTo(x.key);
//            if (cmp == 0) {
//                x.val = val;
//                return;
//            }else if (cmp < 0) {
//                x = x.left;
//            }else{
//                x = x.right;
//            }
//        }
//        int cmp = key.compareTo(parent.key);
//        if (cmp < 0) parent.left = z;
//        else parent.right = z;
//    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.n;
    }

    public Key max() {
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        // 所有的键都比参数key大，所以当对参数key向下取整时，
        // 返回的key即为null
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        } else if (cmp > 0) {
            Node rightFloor = floor(x.right, key);
            if (rightFloor == null) {
                return x;
            } else {
                return rightFloor;
            }
        } else {
            return x;
        }
    }

    // 向上取整 大者取大，小中取大，则可能为自身
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }

    // 有k个键小于该节点，求出该节点
    // 也就是说k可以等于0, 排名0就是第一个键
    public Key select(int k) {
        Node x = select(root, k);
        if (x == null) return null;
        return x.key;
    }

    private Node select(Node x, int k) {
        //当k<0或k>max(root)的时候，
        //递归方法到最左或最右的时候，节点为空
        if (x == null) return null;
        int leftN = size(x.left);
        if (k == leftN) return x;
        if (k < leftN) return select(x.left, k);
        // 进入右子树后，排名需要与节点数n进行比较，
        //所以排名需要减去leftN,减去根节点的1
        return select(x.right, k - leftN - 1);
    }

    public int rank(Key key) {
        // 返回值是排名，右子树中的排名会重新计算，故无法找到特定的节点
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return size(x.left);
        if (cmp < 0) return rank(x.left, key);
        return size(x.left) + 1 + rank(x.right, key);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    // 删除最小键后，最小节点的上一层的左连接转而指向右连接，从而删除最小的节点
    //同时，需要更新被删节点的父节点到根节点上的所有节点的size
    // 涉及到二叉树的新增或删除，都需要用到共同的节点公式
    // size(root) = size(left) + size(right) + 1
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    // 在以x为根节点的子树中，删掉键为key的节点
    private Node delete(Node x, Key key) {

        // 以下4行是递归删除的标准模式，
        // 包括寻找到指定需要删除的节点的过程和该节点不存在的情况
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // 单链路的节点删除很明显
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            // 左右子树都存在的时候需要选择右子树的最小键作为根节点
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

//    public Iterable<Key> keys() {
//        if (isEmpty()) return new Queue<Key>();
//        return keys(min(), max());
//    }
    // 中序遍历的另一种方法 非递归
    public Iterable<Key> keys() {
        Stack<Node> stack = new Stack<Node>();
        Queue<Key> queue = new Queue<Key>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            } else {
                x = stack.pop();
                queue.enqueue(x.key);
                x = x.right;
            }
        }
        return queue;
    }

    public Iterable<Key> keys(Key lo, Key hi) {

        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    // 这个方法充分说明了树中的每个节点都是一个根节点的概念
    // 由中序遍历改造而来
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {

        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int height() {
        return height(root);
    }

    // 最底下的节点高度为0
    private int height(Node x) {
        if (x == null) return -1;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int n = keys.length;
        MyBST<String, Integer> st = new MyBST<>();
//        BST<String, Integer> st = new BST<>();
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);

        StdOut.println("size = " + st.size());
        StdOut.println("min  = " + st.min());
        StdOut.println("max  = " + st.max());
        StdOut.println();


        // print keys in order using allKeys()
        StdOut.println("Testing keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // print keys in order using select
        StdOut.println("Testing select");
        StdOut.println("--------------------------------");
        for (int i = 0; i < st.size(); i++)
            StdOut.println(i + " " + st.select(i));
        StdOut.println();

        // test rank, floor, ceiling
        StdOut.println("key rank floor ceil");
        StdOut.println("-------------------");
        for (char i = 'A'; i <= 'Z'; i++) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s\n", s, st.rank(s), st.floor(s), st.ceiling(s));
        }
        StdOut.println();

        // test range search and range count
        String[] from = {"A", "Z", "X", "0", "B", "C"};
        String[] to = {"Z", "A", "X", "Z", "G", "L"};
        StdOut.println("range search");
        StdOut.println("-------------------");
        for (int i = 0; i < from.length; i++) {
            StdOut.printf("%s-%s (%2d) : ", from[i], to[i], st.size(from[i], to[i]));
            for (String s : st.keys(from[i], to[i]))
                StdOut.print(s + " ");
            StdOut.println();
        }
        StdOut.println();

        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        StdOut.println("After deleting the smallest " + st.size() / 2 + " keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // delete all the remaining keys
        while (!st.isEmpty()) {
            st.delete(st.select(st.size() / 2));
        }
        StdOut.println("After deleting the remaining keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        StdOut.println("After adding back the keys");
        StdOut.println("--------------------------------");
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

    }
}
