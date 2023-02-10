package main;

public interface MyTreeMap<K, V> {

    V get(K key);

    void put(K key, V value);

    void remove(K key);

    int size();
}
