package main;

import org.jetbrains.annotations.NotNull;

public interface ByteToBinaryStringConverter {
    String bytesToBinaryString(byte @NotNull [] bytes);
}
