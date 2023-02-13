package main;

import java.util.function.BiConsumer;

public interface MyTreeMap<K, V> {

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    int size();

    void forEach(BiConsumer<? super K,? super V> action);
}
