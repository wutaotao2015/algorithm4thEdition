package com.wtt.chapter3;

/**
 * Binary search tree
 * 二叉查找树
 *
 * 2018/3/27 10:00 add by wutaotao
 */
public class MyBST<Key extends Comparable<Key>, Val> {

    private class Node{
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
    public Node put(Node x, Key key, Val val){

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
}










