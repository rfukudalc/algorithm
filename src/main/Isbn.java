package main;

public interface Isbn {

    String getPrefix();

    String getGroup();

    String getRegistrant();

    String getPublication();

    String getCheckDigit();

    String getIsbn();
}
