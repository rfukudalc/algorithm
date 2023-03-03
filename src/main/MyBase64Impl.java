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

    // This method encodes a byte array to a base64-encoded string
    public String encodeToString(byte @NotNull [] bytes) {
        StringBuilder sb = new StringBuilder();
        int len = bytes.length;
        // Initialize a counter variable to iterate through the byte array
        int i = 0;
        // Initialize variables to hold three bytes at a time
        int b1, b2, b3;
        // Iterate through the byte array in 3-byte increments and encode each set of three bytes to 4 base64 characters
        while (i < len) {
            // Extract the first byte
            b1 = bytes[i++] & 0xff;
            // If this is the last byte in the array, encode the remaining one or two bytes and pad with "=" or "=="
            if (i == len) {
                sb.append(BASE64_ALPHABET[b1 >>> 2]); // Encode the first 6 bits of the first byte
                sb.append(BASE64_ALPHABET[(b1 & 0x3) << 4]); // Encode the last 2 bits of the first byte and pad with 4 zeros
                sb.append("=="); // Pad with "=="
                break;
            }
            // Extract the second byte
            b2 = bytes[i++] & 0xff;
            // If this is the second-to-last byte in the array, encode the remaining two bytes and pad with "="
            int i1 = ((b1 & 0x3) << 4) | ((b2 & 0xf0) >>> 4);
            if (i == len) {
                sb.append(BASE64_ALPHABET[b1 >>> 2]); // Encode the first 6 bits of the first byte
                sb.append(BASE64_ALPHABET[i1]); // Encode the last 2 bits of the first byte and the first 4 bits of the second byte
                sb.append(BASE64_ALPHABET[(b2 & 0xf) << 2]); // Encode the last 4 bits of the second byte and pad with 2 zeros
                sb.append("="); // Pad with "="
                break;
            }
            // Extract the third byte
            b3 = bytes[i++] & 0xff;
            // Encode the first byte
            sb.append(BASE64_ALPHABET[b1 >>> 2]); // Encode the first 6 bits of the first byte
            sb.append(BASE64_ALPHABET[i1]); // Encode the last 2 bits of the first byte and the first 4 bits of the second byte
            sb.append(BASE64_ALPHABET[((b2 & 0xf) << 2) | ((b3 & 0xc0) >>> 6)]); // Encode the last 2 bits of the second byte and the first 6 bits of the third byte
            sb.append(BASE64_ALPHABET[b3 & 0x3f]); // Encode the last 6 bits of the third byte
        }
        // Return the base64-encoded string
        return sb.toString();
    }
}
