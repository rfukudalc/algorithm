package main;

import org.jetbrains.annotations.NotNull;

public class MyBase64Impl implements MyBase64 {
    private static final char[] BASE64_ALPHABET = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    public String encodeToString(byte @NotNull [] bytes) {
        StringBuilder sb = new StringBuilder();
        int len = bytes.length;
        int i = 0;
        // 3バイトずつのセットをエンコードし、それぞれを4つのbase64文字に変換するための変数を初期化する
        int b1, b2, b3;
        // バイト配列を3バイトの増分で反復処理し、各セットの3バイトを4つのbase64文字にエンコードする
        while (i < len) {
            // 最初のバイトを取り出す
            b1 = bytes[i++] & 0xff;
            // 配列の最後のバイトである場合、残りの1または2バイトをエンコードして、"="または"=="でパディングする
            if (i == len) {
                sb.append(BASE64_ALPHABET[b1 >>> 2]); // 最初のバイトの最初の6ビットをエンコードする
                sb.append(BASE64_ALPHABET[(b1 & 0x3) << 4]); // 最初のバイトの最後の2ビットをエンコードして、4つのゼロでパディングする
                sb.append("=="); // "=="でパディングする
                break;
            }
            // 2番目のバイトを抽出する
            b2 = bytes[i++] & 0xff;
            // これが配列内で2番目に最後のバイトである場合、残りの2バイトをエンコードし、"="でパディングする
            int i1 = ((b1 & 0x3) << 4) | ((b2 & 0xf0) >>> 4);
            if (i == len) {
                sb.append(BASE64_ALPHABET[b1 >>> 2]); // 最初のバイトの最初の6ビットをエンコードする
                sb.append(BASE64_ALPHABET[i1]); // 最初のバイトの最後の2ビットと2番目のバイトの最初の4ビットをエンコードする
                sb.append(BASE64_ALPHABET[(b2 & 0xf) << 2]); // 2番目のバイトの最後の4ビットをエンコードして、2つの0でパディングする
                sb.append("="); // "="でパディングする
                break;
            }
            // 3番目のバイトを抽出する
            b3 = bytes[i++] & 0xff;
            // 最初のバイトをエンコードする
            sb.append(BASE64_ALPHABET[b1 >>> 2]); // 最初のバイトの最初の6ビットをエンコードする
            sb.append(BASE64_ALPHABET[i1]); // 最初のバイトの最後の2ビットと2番目のバイトの最初の4ビットをエンコードする
            sb.append(BASE64_ALPHABET[((b2 & 0xf) << 2) | ((b3 & 0xc0) >>> 6)]); // 2番目のバイトの最後の2ビットと3番目のバイトの最初の6ビットをエンコードする
            sb.append(BASE64_ALPHABET[b3 & 0x3f]); // 3番目のバイトの最後の6ビットをエンコードする
        }
        // Base64でエンコードされた文字列を返す
        return sb.toString();
    }
}
