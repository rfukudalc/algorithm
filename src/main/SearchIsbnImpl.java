package main;

import org.jetbrains.annotations.NotNull;

public class SearchIsbnImpl implements SearchIsbn {

    @Override
    public String search(@NotNull MyMap myMap, Isbn isbn) {
        return myMap.get(isbn).toString();
    }
}
