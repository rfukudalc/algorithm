package main;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiConsumer;

public class MyTreeMapImpl<K, V> implements MyTreeMap<K, V> {

    private int size;
    private Node root;
    private final Comparator<? super K> comparator;

    public MyTreeMapImpl(Comparator<? super K> comparator) {
        this.comparator = comparator;
        root = null;
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

    @Override
    public V get(K key) {
        var matchedNode = getRecursive(root, key);
        if (matchedNode != null) return matchedNode.value;

        return null;
    }

    private Node getRecursive(Node node, K key) {
        if (node == null || node.key.equals(key)) return node;
        if (comparator.compare(node.key, key) < 0) return getRecursive(node.right, key);

        return getRecursive(node.left, key);
    }

    @Override
    public V put(K key, V value) {
        var updatedVal = get(key);
        root = putRecursive(root, key, value);

        return updatedVal;
    }

    Node putRecursive(Node node, K key, V value) {
        // 到達したノードが NULL だった場合、そこに新規ノードを追加する
        if (node == null) {
            node = new Node(key, value);
            size++;

            return node;
        // 追加するノードキーが存在する場合は、値の書き換えのみを行う
        } else if (node.key.equals(key)) {
            node.value = value;

            return node;
        }
        // ツリーの頂点から比較を開始し、挿入するキーが到達したノードのキーより小さければ、左側を横断するように再帰呼び出しを行う
        if (comparator.compare(key, node.key) < 0)
            node.left = putRecursive(node.left, key, value);

        // ツリーの頂点から比較を開始し、挿入するキーが到達したノードのキーより大きければ、右側を横断するように再帰呼び出しを行う
        else if (comparator.compare(key, node.key) > 0)
            node.right = putRecursive(node.right, key, value);

        return node;
    }

    @Override
    public V remove(K key) {
        var removedVal = get(key);
        root = removeRecursive(root, key);

        return removedVal;
    }

    Node removeRecursive(Node node, K key) {
        if (node == null) return null;

        // ツリーの頂点から比較を開始し、削除するキーが到達したノードのキーより小さければ、左側を横断するように再帰呼び出しを行う
        if (comparator.compare(key, node.key) < 0)
            node.left = removeRecursive(node.left, key);

        // ツリーの頂点から比較を開始し、削除するキーが到達したノードのキーより大きければ、右側を横断するように再帰呼び出しを行う
        else if (comparator.compare(key, node.key) > 0)
            node.right = removeRecursive(node.right, key);

        // 削除対象のノードに到達したら下記の処理を行う
        else { // 削除対象のノードが子ノードを一つだけ持つ場合
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            }
            // 削除対象のノードが子ノードを二つ持つ場合
            Node minNode = minNode(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = removeRecursive(node.right, node.key);
        }

        return node;
    }

    Node minNode(@NotNull Node node) {
        Node minNode = node;
        while (node.left != null) {
            minNode = node.left;
            minNode.key = node.left.key;
            node = node.left;
        }
        return minNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Node node : inOrder()) {
            action.accept(node.key, node.value);
        }
    }

    private LinkedList<Node> inOrder() {
        return inOrderRecursive(root, new LinkedList<>());
    }

    private LinkedList<Node> inOrderRecursive(Node node, LinkedList<Node> list) {

        if (node != null) {
            // ノードを昇順に並び替えるため、ツリーの左下から順にリストへの追加を行う
            inOrderRecursive(node.left, list);
            list.add(node);
            inOrderRecursive(node.right, list);
        }
        return list;
    }
}
