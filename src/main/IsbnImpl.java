package main;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 同一オブジェクトであれば即座に true を返す
        if (o == null || getClass() != o.getClass()) return false; // オブジェクトが null か異なるクラスオブジェクトなら false を返す
        IsbnImpl isbn = (IsbnImpl) o;
        // Isbn にキャストしたオブジェクトの各パラメータと比較対象のパラメータが全て一致すれば同一オブジェクトと判断し、true を返す
        return Objects.equals(prefix, isbn.prefix)
                && Objects.equals(group, isbn.group)
                && Objects.equals(registrant, isbn.registrant)
                && Objects.equals(publication, isbn.publication)
                && Objects.equals(checkDigit, isbn.checkDigit);
    }

    @Override
    public int hashCode() { // パラメータの複合でハッシュ値を生成する
        return Objects.hash(prefix, group, registrant, publication, checkDigit);
    }
}
