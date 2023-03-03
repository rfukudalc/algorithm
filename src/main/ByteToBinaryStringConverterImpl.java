package main;

import org.jetbrains.annotations.NotNull;

public class ByteToBinaryStringConverterImpl implements ByteToBinaryStringConverter {

    @Override
    public String bytesToBinaryString(byte @NotNull [] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String binary = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            sb.append(binary);
        }

        // split the binary string into 8-bit chunks
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < sb.length(); i += 8) {
            result.append(sb.substring(i, Math.min(i + 8, sb.length())));
            if (i + 8 < sb.length()) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
