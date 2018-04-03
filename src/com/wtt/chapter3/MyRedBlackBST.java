package com.wtt.chapter3;

import java.util.NoSuchElementException;

/**
 * 红黑树创造性的利用链接的颜色将2-3树分解为二叉树，通过总结2-3树的4-节点的分解规律，得到了3条关于红链接的递归处理流程，
 * 并且通过左右旋转和颜色变化使得在整个树的变化过程中一直保持了树的平衡性和有序性，即树始终是黑色完美平衡的。
 * 通过这种方式，红黑树比普通的二叉查找树的高度更低，每个空节点到根节点的路径长度都是相等的，从而提高了查找和插入的速度。
 * <p>
 * 其中get方法与一般BST算法相同，虽然红链接放平才是黑色平衡的，但get方法执行时人不是绝对平衡的，这里更多的是平均平衡，
 * 即避免了普通BST按升序插入键时的性能最差的情况（形成单边树）。
 * 它比普通BST查询更快是因为普通BST不是平衡的。
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

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        // h 节点和它的左子节点都是2-节点时需要借节点
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        // 删除节点后向上返回的过程需要分解临时的4-节点
        return balance(h);
    }
    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        // 不存在红右连接，h 节点和它的左子节点都是2-节点时需要借节点
        // 这里翻转颜色相当于将左子节点，父节点，右子节点合并为一个4-节点
        flipColors(h);
        // 不存在红右连接，只有h.right.left可能为红，此时与上述生成的临时4-节点冲突，变为5-节点，需要解决
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }
    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            // 在左子树中删除，若为3-节点直接删除，否则moveRedLeft借个键
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            // 删除
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }
    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }
}
