package main;

import java.util.stream.IntStream;

public class StringDecompressorImpl implements StringDecompressor {

    @Override
    public String decompressString(String input) {
        // 引数がnullまたは空文字列の場合は、空文字列を返す
        if (input == null || input.isEmpty()) {
            return "";
        }

        // 結果を格納するStringBuilderを作成する
        StringBuilder result = new StringBuilder();

        // 文字列を1文字ずつ処理する
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // 文字列が数字である場合には、それまでの文字列をその数字分繰り返す
            if (Character.isDigit(c)) {
                int count = c - '0';
                result.append(String.valueOf(input.charAt(i - 1)).repeat(Math.max(0, count - 1)));
            } else {
                // 数字でない場合には、そのまま追加する
                result.append(c);
            }
        }

        // 結果の文字列を返す
        return result.toString();
    }

    @Override
    public String decompressStringWithStreamApi(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        // 1文字ずつ処理するストリームを作成する
        IntStream.range(0, input.length())
                .forEach(i -> {
                    char c = input.charAt(i);
                    if (Character.isDigit(c)) {
                        // 数字の場合は、直前の文字を指定された回数分繰り返す
                        int count = Character.getNumericValue(c);
                        char previousChar = input.charAt(i - 1);
                        result.append(String.valueOf(previousChar).repeat(Math.max(0, count - 1)));
                    } else {
                        // 文字の場合は、そのまま追加する
                        result.append(c);
                    }
                });

        return result.toString();
    }
}
