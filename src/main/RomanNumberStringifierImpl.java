package main;

public class RomanNumberStringifierImpl implements RomanNumberStringifier {

    @Override
    public String stringify(int n) {

        if (n < 0 || n > 3999) return "The given number is out of the available range.";

        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanNumbers = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < 13; i++) {
            int j = n / numbers[i];
            ans.append(romanNumbers[i].repeat(j));
            n = n % numbers[i];
        }

        return ans.toString();
    }
}
