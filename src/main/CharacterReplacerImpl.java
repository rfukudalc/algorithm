package main;

import org.jetbrains.annotations.NotNull;

public class CharacterReplacerImpl implements CharacterReplacer {

    public @NotNull String replaceCharacters(@NotNull String str) {
        // 文字列を格納するためのStringBuilderオブジェクトを作成
        StringBuilder result = new StringBuilder();

        // 文字列を1文字ずつ処理
        for (char c : str.toCharArray()) {
            // 文字が数字またはアルファベットである場合は置き換える
            if (Character.isLetterOrDigit(c)) {
                // Z -> A に置き換える
                if (c == 'Z') {
                    result.append('A');
                    // z -> a に置き換える
                } else if (c == 'z') {
                    result.append('a');
                    // 9 -> 0 に置き換える
                } else if (c == '9') {
                    result.append('0');
                    // その他の文字は、次の文字に置き換える
                } else {
                    result.append((char) (c + 1));
                }
                // 文字が数字でもアルファベットでもない場合は、そのまま追加する
            } else {
                result.append(c);
            }
        }

        // 置き換えた文字列を返す
        return result.toString();
    }

    public @NotNull String replaceCharactersWithStreamApi(@NotNull String str) {
        return str.chars().map(
                        c -> {
                            if (Character.isLetterOrDigit(c)) {
                                return switch (c) {
                                    case 'Z' -> 'A';
                                    case 'z' -> 'a';
                                    case '9' -> '0';
                                    default -> c + 1;
                                };
                            } else {
                                return c;
                            }
                        }
                )
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
