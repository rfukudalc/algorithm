package main;

public interface MyMap<K, V> {

    V get(K k);

    V put(K key, V value);

    void remove(K key);

    int size();
}
