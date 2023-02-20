package main;

import org.jetbrains.annotations.NotNull;

public class MyBase64Impl implements MyBase64 {
    private static final String BASE64_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public String encodeToString(byte @NotNull [] bytes) {

        StringBuilder binaryString = new StringBuilder();

        // 各バイトを2進数の文字列に変換する
        for (byte b : bytes) {
            String s = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            binaryString.append(s);
        }

        // 2進数の文字列を6ビット長のビット列に分解する
        String[] chunks = binaryString.toString().split("(?<=\\G.{6})");

        // 必要に応じてビット列の最後尾に0を追加
        if (chunks[chunks.length - 1].length() < 6) {
            chunks[chunks.length - 1] = String.format("%-6s", chunks[chunks.length - 1]).replace(' ', '0');
        }

        // 6ビットに分割したかたまりを、それぞれ対応する Base64 文字列に変換する
        StringBuilder base64String = new StringBuilder();
        for (String chunk : chunks) {
            int index = Integer.parseInt(chunk, 2);
            base64String.append(BASE64_CHARACTERS.charAt(index));
        }

        // 必要に応じて文字列の最後に'='を追加する
        int padding = 4 - (base64String.length() % 4);
        if (padding != 4) {
            base64String.append("=".repeat(padding));
        }

        return base64String.toString();
    }
}
