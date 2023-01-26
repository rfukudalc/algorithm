package main;

import org.jetbrains.annotations.NotNull;

public class RegisterIsbnImpl implements RegisterIsbn {

    @Override
    public String register(@NotNull MyMap myMap, Isbn isbn, String bookName) {
        return myMap.put(isbn, bookName).toString();
    }
}
