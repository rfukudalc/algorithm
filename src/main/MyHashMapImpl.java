package main;

import org.jetbrains.annotations.NotNull;

import java.util.*;

@SuppressWarnings({"unused", "unchecked"})
public class MyHashMapImpl<V, K> implements MyHashMap {

    private int size;
    private LinkedList<Node>[] buckets;

    // コンストラクターは引数で渡された数字をサイズとして固定長のハッシュマップを生成する
    public MyHashMapImpl(int initialCapacity) {
        initBuckets(initialCapacity);
        size = 0;
    }

    public Object get(@NotNull Object key) {
        int bucketIndex = generateHash((K) key);
        int indexWithinBucket = getIndexWithinBucket(key, bucketIndex);
        // 引数のキーと一致するノードを返却する
        if (indexWithinBucket != -1) {
            Node node = buckets[bucketIndex].get(indexWithinBucket);
            return node.value;
        }
        return null;
    }

    public void put(@NotNull Object key, Object value) {
        int bucketIndex = generateHash((K) key);
        int indexWithinBucket = getIndexWithinBucket(key, bucketIndex);
        // 引数「key」と一致するノードがバケットにある場合は、引数「value」でノードの値を更新する
        if (indexWithinBucket != -1) {
            Node node = buckets[bucketIndex].get(indexWithinBucket);
            node.value = value;
        } else { // 上記以外ではバケットにノードを追加する
            Node node = new Node(key, value);
            buckets[bucketIndex].add(node);
            size++;
        }
    }

    public void remove(@NotNull Object key) {
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

    // バケットの初期化処理（引数を固定サイズとする）
    private void initBuckets(int n) {
        buckets = new LinkedList[n];
        for (int bucketIndex = 0; bucketIndex < buckets.length; bucketIndex++) {
            buckets[bucketIndex] = new LinkedList<>(); // ハッシュマップのサイズだけバケットにリストデータ領域を確保
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
