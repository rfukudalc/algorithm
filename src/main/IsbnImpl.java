package main;

import org.jetbrains.annotations.NotNull;

public class IsbnImpl implements Isbn {

    private final String prefix;
    private final String group;
    private final String registrant;
    private final String publication;
    private final String checkDigit;

    public IsbnImpl(String prefix, String group, String registrant, String publication, String checkDigit) {
        this.prefix = prefix;
        this.group = group;
        this.registrant = registrant;
        this.publication = publication;
        this.checkDigit = checkDigit;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getGroup() {
        return group;
    }

    @Override
    public String getRegistrant() {
        return registrant;
    }

    @Override
    public String getPublication() {
        return publication;
    }

    @Override
    public String getCheckDigit() {
        return checkDigit;
    }

    @Override
    public String getIsbn() {
        return combineFiveElements(prefix, group, registrant, publication, checkDigit);
    }

    private @NotNull String combineFiveElements(String prefix, String group, String registrant, String publication, String checkDigit) {
        return String.join("-", prefix, group, registrant, publication, checkDigit);
    }
}
