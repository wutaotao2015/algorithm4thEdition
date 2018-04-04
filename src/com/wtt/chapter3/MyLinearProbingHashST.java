package com.wtt.chapter3;

import com.wtt.chapter1.practice.Queue;

/**
 * 2018/4/4 16:19 add by wutaotao
 */
public class MyLinearProbingHashST<Key, Val> {

    private int m;
    private int n;
    private Key[] keys;
    private Val[] vals;

    public MyLinearProbingHashST() {
        this(2);
    }

    public MyLinearProbingHashST(int m) {
        this.m = m;
        n = 0;
        keys = (Key[])new Object[m];
        vals = (Val[])new Object[m];
    }
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
//    public Iterable<Key> keys() {
//        Queue<Key> queue = new Queue<>();
//
//    }
    private void resize(int newM) {

        MyLinearProbingHashST<Key, Val> linearProbingST = new MyLinearProbingHashST<>(newM);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                linearProbingST.put(keys[i], vals[i]);
            }
        }
        this.m = newM;
        this.keys = linearProbingST.keys;
        this.vals = linearProbingST.vals;
    }
    public void put(Key key, Val val) {

        if (key == null) throw new NullPointerException("put key is null");
        if (val == null) {
            delete(key);
            return;
        }

        if (n >= m/2) resize(m * 2);

        // 新增时是哈希值逐一向后加1的，查找也是同样的过程，
        //根据键是否匹配判断是更新还是新增。
        // 向后增1查找，到底时循环切到开头位置
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }
    public Val get(Key key) {
        if (key == null) throw new NullPointerException("put key is null");
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (key.equals(keys[i])) {
                return vals[i];
            }
        }
        return null;
    }
    // 删除后需要将后方的键重现插入
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("put key is null");
        if (!contains(key)) return;

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % m;
        n--;
        // 移动重新hash的过程中n的值是不变的,故需要在插入前n--
        while(keys[i] != null) {
            Key keyToRehash = keys[i];
            Val valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        if (n > 0 && n <= m / 8) resize(m / 2);
    }
    public boolean contains(Key key) {
        return get(key) != null;
    }
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) queue.enqueue(keys[i]);
        }
        return queue;
    }
    public static void main(String[] args) {

        // 这个例子可以看到重新hash引起键的位置重新排列的结果
        MyLinearProbingHashST<Integer, String> st = new MyLinearProbingHashST<>();
        st.put(3, "$");
        st.put(6, "$");
        st.put(9, "$");
        st.put(1, "$");
        for (Integer integer : st.keys()) {
            System.out.println(integer + " " +st.get(integer));
        }
        System.out.println("begin put others");
        st.put(2, "#");
        st.put(5, "#");
        st.put(4, "#");
        st.put(7, "#");
        for (Integer integer : st.keys()) {
            System.out.println(integer + " " +st.get(integer));
        }
        System.out.println();
    }
}
