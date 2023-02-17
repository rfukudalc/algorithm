package main;

public class MyBase64Impl implements MyBase64 {
    private static final String BASE64_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public String encodeToString(byte[] bytes) {

        // Convert each byte to 8-bit binary string
        StringBuilder binaryString = new StringBuilder();

        for (byte b : bytes) {
            String s = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            binaryString.append(s);
        }

        // Divide binary string into 6-bit chunks
        String[] chunks = binaryString.toString().split("(?<=\\G.{6})");

        // Pad last chunk with zeroes if necessary
        if (chunks[chunks.length - 1].length() < 6) {
            chunks[chunks.length - 1] = String.format("%-6s", chunks[chunks.length - 1]).replace(' ', '0');
        }

        // Convert each 6-bit chunk to corresponding Base64 character
        StringBuilder base64String = new StringBuilder();
        for (String chunk : chunks) {
            int index = Integer.parseInt(chunk, 2);
            base64String.append(BASE64_CHARACTERS.charAt(index));
        }

        // Pad last characters with '=' if necessary
        int padding = 4 - (base64String.length() % 4);
        if (padding != 4) {
            base64String.append("=".repeat(padding));
        }

        return base64String.toString();
    }
}
