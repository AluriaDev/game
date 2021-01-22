package io.github.aluria.game.utils;

public final class RomanNumbers {

  private static final String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
  private static final int[] numbers = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

  public static String toRoman(int number) {
    for (int i = 0; i < numbers.length; i++) {
      if (number >= numbers[i]) {
        return symbols[i] + toRoman(number - numbers[i]);
      }
    }
    return "";
  }

  public static int fromRoman(String number) {
    for (int i = 0; i < symbols.length; i++) {
      if (number.startsWith(symbols[i])) {
        return numbers[i] + fromRoman(number.replaceFirst(symbols[i], ""));
      }
    }
    return 0;
  }
}
