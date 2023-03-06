package main;

import org.jetbrains.annotations.NotNull;

public class MyBase64Impl implements MyBase64 {
    private static final String BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public String encodeToString(byte @NotNull [] bytes) {
        StringBuilder sb = new StringBuilder();
        int len = bytes.length;
        // バイト配列を反復処理するためのカウンタ変数を初期化する
        int i = 0;
        // 3バイトずつ反復処理し、各3バイトを4つのBase64文字にエンコードするための変数を初期化する
        int b1, b2, b3;

        while (i < len) {
            // 1バイト目を抽出する
            b1 = bytes[i++] & 0xff;
            // 配列内の最後のバイトである場合、残りの1または2バイトをエンコードして"="または"=="でパディングする
            if (i == len) {
                sb.append(BASE64_ALPHABET.charAt(b1 >>> 2)); // 1バイト目の最初の6ビットをエンコードする
                sb.append(BASE64_ALPHABET.charAt((b1 & 0x3) << 4)); // 1バイト目の最後の2ビットをエンコードして4つの0でパディングする
                sb.append("=="); // "=="でパディングする
                break;
            }
            // 2バイト目を抽出する
            b2 = bytes[i++] & 0xff;
            // 配列内の最後から2番目のバイトである場合、残りの2バイトをエンコードして"="でパディングする
            int i1 = ((b1 & 0x3) << 4) | ((b2 & 0xf0) >>> 4);
            if (i == len) {
                sb.append(BASE64_ALPHABET.charAt(b1 >>> 2)); // 1バイト目の最初の6ビットをエンコードする
                sb.append(BASE64_ALPHABET.charAt(i1)); // 1バイト目の最後の2ビットと2バイト目の最初の4ビットをエンコードする
                sb.append(BASE64_ALPHABET.charAt((b2 & 0xf) << 2)); // 2バイト目の最後の4ビットをエンコードして2つのゼロでパディングする
                sb.append("="); // "="でパディングする
                break;
            }
            // 3番目のバイトを取り出す
            b3 = bytes[i++] & 0xff;
            // 最初のバイトをエンコード
            sb.append(BASE64_ALPHABET.charAt(b1 >>> 2)); // 最初のバイトの最初の6ビットをエンコード
            sb.append(BASE64_ALPHABET.charAt(i1)); // 最初のバイトの最後の2ビットと2番目のバイトの最初の4ビットをエンコード
            sb.append(BASE64_ALPHABET.charAt((b2 & 0xf) << 2) | ((b3 & 0xc0) >>> 6)); // 2番目のバイトの最後の2ビットと3番目のバイトの最初の6ビットをエンコード
            sb.append(BASE64_ALPHABET.charAt(b3 & 0x3f)); // 3番目のバイトの最後の6ビットをエンコード
        }
        return sb.toString();
    }
}
