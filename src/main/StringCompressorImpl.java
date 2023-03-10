package main;

import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StringCompressorImpl implements StringCompressor {

    @Override
    public String compressString(String input) {
        // 引数がnullまたは空文字列の場合は、空文字列を返す
        if (input == null || input.isEmpty()) {
            return "";
        }

        // 結果を格納するStringBuilderを作成する
        StringBuilder result = new StringBuilder();

        // 現在の文字とその出現回数を格納する変数を初期化する
        char currentChar = input.charAt(0);
        int count = 1;

        // 文字列を1文字ずつ処理する
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);

            // 現在の文字と同じ文字である場合には、カウントを1増やす
            if (c == currentChar) {
                count++;
            } else {
                // 異なる文字が現れた場合には、前の文字と出現回数を結合して、新しい文字として追加する
                result.append(currentChar);
                result.append(count);
                // 新しい文字として現在の文字と出現回数を初期化する
                currentChar = c;
                count = 1;
            }
        }

        // 最後の文字と出現回数を結合して、結果の文字列を追加する
        result.append(currentChar);
        result.append(count);

        // 結果の文字列を返す
        return result.toString();
    }

    @Override
    public String compressStringWithStreamApi(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // 入力された文字列をストリームに変換し、各文字をキャラクター型にマッピングする
        return input.chars()
                .mapToObj(c -> (char) c)
                // キャラクターをグループ化し、順序付けされたLinkedHashMapに格納し、出現回数をカウントする
                .collect(groupingBy(Function.identity(), LinkedHashMap::new, counting()))
                // キーと値をエントリーセットにしてストリームに変換し、フラットマップを使用してキーと値を交互に出力する
                .entrySet()
                .stream()
                .flatMap(entry -> Stream.of(String.valueOf(entry.getKey()), entry.getValue().toString()))
                // 最後に、キーと値の文字列を結合して圧縮された文字列を返す
                .collect(joining());
    }
}
