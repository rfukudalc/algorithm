package main;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.HashMap;

public class ChineseNumberStringifierImpl implements ChineseNumberStringifier {

    @Override
    public String stringify(int n) {

        int lengthLimit = 5;

        final HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int j = 1; j <= lengthLimit; j++) {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            hashMap.put(j, k);
        }

        var firstDigit = getTheFifthDigit(hashMap.get(5));
        var secondDigit = getTheFourthDigit(hashMap.get(4));
        var thirdDigit = getTheThirdDigit(hashMap.get(3));
        var fourthDigit = getTheSecondDigit(hashMap.get(2));
        var lastDigit = getTheLastDigit(hashMap.get(1));

        return firstDigit + secondDigit + thirdDigit + fourthDigit + lastDigit;
    }

    final HashMap<Integer, String> chineseNums = new HashMap<>() {
        @Serial
        private static final long serialVersionUID = 1L;
        {
            put(1, "壱");
            put(2, "弐");
            put(3, "参");
            put(4, "肆");
            put(5, "伍");
            put(6, "陸");
            put(7, "漆");
            put(8, "捌");
            put(9, "玖");
        }
    };

    private @NotNull String getTheFifthDigit(int num) {
        if (num == 0) return "";
        return chineseNums.get(num) + "萬";
    }

    private @NotNull String getTheFourthDigit(int num) {
        if (num == 0) return "";
        return chineseNums.get(num) + "阡";
    }

    private @NotNull String getTheThirdDigit(int num) {
        if (num == 0) return "";
        return chineseNums.get(num) + "佰";
    }

    private @NotNull String getTheSecondDigit(int num) {
        if (num == 0) return "";
        return chineseNums.get(num) + "拾";
    }

    private @NotNull String getTheLastDigit(int num) {
        if (num == 0) return "";
        return chineseNums.get(num);
    }
}
