package main;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MyMapImpl<K, V> implements MyMap {

    private int size;
    private LinkedList<Node>[] buckets;

    // コンストラクターは引数で渡された数字をサイズとして固定長のマップを生成する
    public MyMapImpl(int initialCapacity) {
        initBuckets(initialCapacity);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public V get(@NotNull Object key) {
        int bucketIndex = generateHash((K) key);
        int indexWithinBucket = getIndexWithinBucket(key, bucketIndex);
        // 引数のキーと一致するノードを返却する
        if (indexWithinBucket != -1) {
            Node node = buckets[bucketIndex].get(indexWithinBucket);
            return (V) node.value;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public V put(Object key, Object value) {
        int bucketIndex = generateHash((K) key);
        int indexWithinBucket = getIndexWithinBucket(key, bucketIndex);
        // 引数「key」と一致するノードがバケットにある場合は、引数「value」でノードの値を更新する
        Node node;
        if (indexWithinBucket != -1) {
            node = buckets[bucketIndex].get(indexWithinBucket);
            node.value = value;
        } else { // 上記以外ではバケットにノードを追加する
            node = new Node(key, value);
            buckets[bucketIndex].add(node);
            size++;
        }
        return (V) node.value;
    }

    @SuppressWarnings("unchecked")
    public void remove(Object key) {
        int bucketIndex = generateHash((K) key);
        int indexWithinBucket = getIndexWithinBucket(key, bucketIndex);
        // 引数「key」と一致するノードがバケットにある場合は削除する
        if (indexWithinBucket != -1) {
            buckets[bucketIndex].remove(indexWithinBucket);
            size--;
        }
    }

    public int size() {
        return size;
    }

    private static class Node {

        @NotNull Object key;
        Object value;

        public Node(@NotNull Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    // バケットの初期化処理（引数を固定サイズとする）
    private void initBuckets(int n) {
        buckets = new LinkedList[n];
        for (int bucketIndex = 0; bucketIndex < buckets.length; bucketIndex++) {
            buckets[bucketIndex] = new LinkedList<>(); // マップのサイズだけバケットにリストデータ領域を確保
        }
    }

    // ハッシュ値の生成処理
    private int generateHash(@NotNull K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % buckets.length;
    }

    // バケット内のインデックスを取得する処理
    private int getIndexWithinBucket(Object key, int bucketIndex) {
        int indexWithinBucket = 0;
        for (Node node : buckets[bucketIndex]) {
            // 引数のキーと一致するノードがある場合はそのインデックスを返却する
            if (node.key.equals(key)) {
                return indexWithinBucket;
            }
            indexWithinBucket++;
        }
        return -1;
    }
}
