package main;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class MyTreeMapImpl<K, V> implements MyTreeMap<K, V> {

    private int size;
    private Node root;
    private final Comparator comparator;

    public MyTreeMapImpl(Comparator<? super K> comparator) {
        this.comparator = comparator;
        root = null;
    }

    @Override
    public V get(K key) {
        var matchedNode = getRecursive(root, key);
        if (matchedNode != null) return matchedNode.value;
        return null;
    }

    @SuppressWarnings("unchecked")
    private Node getRecursive(Node root, K key) {
        if (root == null || root.key == key) return root;
        if (comparator.compare(root.key, key) < 0) return getRecursive(root.right, key);
        return getRecursive(root.left, key);
    }

    @Override
    public void put(K key, V value) {
        root = putRecursive(root, key, value);
    }

    @Override
    public void remove(K key) {
        root = removeRecursive(root, key);
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {

        @NotNull K key;
        V value;

        Node left, right;

        public Node(@NotNull K key, V value) {
            this.key = key;
            this.value = value;

            left = right = null;
        }
    }

    @SuppressWarnings("unchecked")
    Node putRecursive(Node root, K key, V value) {
        // 到達したノードが NULL だった場合、そこに新規ノードを追加する
        if (root == null) {
            root = new Node(key, value);
            size++;
            return root;
        }
        // ツリーの上からノードキーを精査し、左側を横断するように再帰呼び出しを行う
        if (comparator.compare(key, root.key) < 0)
            root.left = putRecursive(root.left, key, value);

        // ツリーの上からノードキーを精査し、右側を横断するように再帰呼び出しを行う
        else if (comparator.compare(key, root.key) > 0)
            root.right = putRecursive(root.right, key, value);

        return root;
    }

    @SuppressWarnings("unchecked")
    Node removeRecursive(Node root, K key) {
        if (root == null) return null;

        // キーがルートノードのキーより小さい場合
        if (comparator.compare(key, root.key) < 0)
            root.left = removeRecursive(root.left, key);
        else if (comparator.compare(key, root.key) > 0)
            root.right = removeRecursive(root.right, key);
        else {
            // node contains only one child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);
            root.right = removeRecursive(root.right, root.key);
        }
        size--;
        return root;
    }

    K minValue(@NotNull Node root) {
        K minVal = root.key;
        while (root.left != null) {
            minVal = root.left.key;
            root = root.left;
        }
        return minVal;
    }
}
