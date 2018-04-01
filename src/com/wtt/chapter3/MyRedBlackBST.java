package com.wtt.chapter3;

/**
 * 红黑树创造性的利用链接的颜色将2-3树分解为二叉树，通过总结2-3树的4-节点的分解规律，得到了3条关于红链接的递归处理流程，
 * 并且通过左右旋转和颜色变化使得在整个树的变化过程中一直保持了树的平衡性和有序性，即树始终是黑色完美平衡的。
 * 通过这种方式，红黑树比普通的二叉查找树的高度更低，每个空节点到根节点的路径长度都是相等的，从而提高了查找和插入的速度。
 * <p>
 * 其中get方法与一般BST算法相同，虽然红链接放平才是黑色平衡的，但get方法执行时人不是绝对平衡的，这里更多的是平均平衡，
 * 即避免了普通BST按升序插入键时的性能最差的情况（形成单边树）。
 * <p>
 * Created by wutaotao
 * 2018/4/1 22:34
 */
public class MyRedBlackBST<Key extends Comparable<Key>, Val> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {

        Key key;
        Val val;
        Node left;
        Node right;
        int n;
        boolean color;

        public Node(Key key, Val val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    private Node root;

    public Val get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Val get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public void put(Key key, Val val) {

        root = put(root, key, val);
        root.color = BLACK;
    }

    public Node put(Node x, Key key, Val val) {
        if (x == null)
            return new Node(key, val, 1, RED);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        return x.n;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        // 记录h和x节点
        Node x = h.right;
        // 中间节点的转移
        h.right = x.left;
        // 根节点的变化
        x.left = h;
        // 根节点的颜色继承
        x.color = h.color;
        // 红左
        h.color = RED;
        // 大小关系继承
        x.n = h.n;
        // h节点获得了中间子树的节点，节点大小需要重新计算
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        // 记录h和x节点
        Node x = h.left;
        // 中间节点的转移
        h.left = x.right;
        // 根节点的变化
        x.right = h;
        // 根节点的颜色继承
        x.color = h.color;
        // 红左
        h.color = RED;
        // 大小关系继承
        x.n = h.n;
        // h节点获得了中间子树的节点，节点大小需要重新计算
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }
}
