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

    private Node getRecursive(Node root, K key) {
        if (root == null || root.key.equals(key)) return root;
        if (comparator.compare(root.key, key) < 0) return getRecursive(root.right, key);

        return getRecursive(root.left, key);
    }

    @Override
    public V put(K key, V value) {
        var updatedVal = get(key);
        root = putRecursive(root, key, value);

        return updatedVal;
    }

    Node putRecursive(Node root, K key, V value) {
        // 到達したノードが NULL だった場合、そこに新規ノードを追加する
        if (root == null) {
            root = new Node(key, value);
            size++;

            return root;

            // 追加するノードキーが存在する場合は、値の書き換えのみを行う
        } else if (root.key.equals(key)) {
            root.value = value;

            return root;
        }
        // ツリーの頂点からノードキーを精査し、対象のキーがルートノードキーより小さければ、左側を横断するように再帰呼び出しを行う
        if (comparator.compare(key, root.key) < 0)
            root.left = putRecursive(root.left, key, value);

            // ツリーの頂点からノードキーを精査し、対象のキーがルートノードキーより大きければ、右側を横断するように再帰呼び出しを行う
        else if (comparator.compare(key, root.key) > 0)
            root.right = putRecursive(root.right, key, value);

        return root;
    }

    @Override
    public V remove(K key) {
        var removedVal = get(key);
        root = removeRecursive(root, key);

        return removedVal;
    }

    Node removeRecursive(Node root, K key) {
        if (root == null) return null;

        // ツリーの頂点からノードキーを精査し、削除対象のキーがルートノードキーより小さければ、左側を横断するように再帰呼び出しを行う
        if (comparator.compare(key, root.key) < 0)
            root.left = removeRecursive(root.left, key);

            // ツリーの頂点からノードキーを精査し、削除対象のキーがルートノードキーより大きければ、右側を横断するように再帰呼び出しを行う
        else if (comparator.compare(key, root.key) > 0)
            root.right = removeRecursive(root.right, key);

            // 削除対象のノードに到達したら下記の処理を行う
        else { // 削除対象のノードが子ノードを一つだけ持つ場合
            if (root.left == null) {
                size--;
                return root.right;
            } else if (root.right == null) {
                size--;
                return root.left;
            }
            // 削除対象のノードが子ノードを二つ持つ場合
            Node minNode = minNode(root.right);
            root.key = minNode.key;
            root.value = minNode.value;
            root.right = removeRecursive(root.right, root.key);
        }

        return root;
    }

    Node minNode(@NotNull Node root) {
        Node minNode = root;
        while (root.left != null) {
            minNode = root.left;
            minNode.key = root.left.key;
            root = root.left;
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

    private LinkedList<Node> inOrderRecursive(Node root, LinkedList<Node> list) {

        if (root != null) {
            // ノードを昇順に並び替えるため、ツリーの左下から順にリストへの追加を行う
            inOrderRecursive(root.left, list);
            list.add(root);
            inOrderRecursive(root.right, list);
        }
        return list;
    }
}
