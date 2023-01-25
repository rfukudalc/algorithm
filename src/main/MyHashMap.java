package main;

public interface MyHashMap<K, V> {

    V get(Object k);

    void put(K key, V value);

    void remove(Object key);

    int size();
}
