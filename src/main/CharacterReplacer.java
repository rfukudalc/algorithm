package main;

import org.jetbrains.annotations.NotNull;

public interface CharacterReplacer {
    String replaceCharacters(@NotNull String str);

    String replaceCharactersWithStreamApi(@NotNull String str);

}
