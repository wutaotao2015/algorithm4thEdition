package com.wtt.chapter3;

import com.wtt.chapter1.practice.Queue;

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

    public Val get(Key key) {
        return get(root, key);
    }

    private Val get(Node x, Key key) {
        if (x == null) return null;
        int i = key.compareTo(x.key);
        if (i < 0) {
            return get(x.left, key);
        } else if (i > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
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
        return select(x.right, leftN - k - 1);
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
        Node x = delete(root, key);
        if (x == null) throw new NullPointerException("the key not existed");
        root = x;
    }
    // 在以x为根节点的子树中，删掉键为key的节点
    private Node delete(Node x, Key key) {

        // 以下4行是递归删除的标准模式，
        // 包括寻找到指定需要删除的节点的过程和该节点不存在的情况
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x = delete(x.left, key);
        else if (cmp > 0) x = delete(x.right, key);
        else {
            // 单链路的节点删除很明显
            if (x.left == null) x = x.right;
            if (x.right == null) x = x.left;
            // 左右子树都存在的时候需要选择右子树的最小键作为根节点
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi) {

        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}
