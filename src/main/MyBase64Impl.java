package main;

import org.jetbrains.annotations.NotNull;

public class MyBase64Impl implements MyBase64 {

    @Override
    public String encodeToString(byte[] src) {
        var encodedByte = encode(src);
        return new String(encodedByte);
    }

    private static final char[] base64EncodeChars = new char[] {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    private static byte @NotNull [] encode(byte @NotNull [] data) {
        StringBuilder sb = new StringBuilder();
        int length = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < length) {
            b1 = data[i++] & 0xff;

            if (i == length) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }

            b2 = data[i++] & 0xff;

            int i1 = ((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4);

            if (i == length) {
                sb.append(base64EncodeChars[b1 >>> b2]);
                sb.append(base64EncodeChars[i1]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }

            b3 = data[i++] & 0xff;

            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[i1]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return String.valueOf(sb).getBytes();
    }
}
