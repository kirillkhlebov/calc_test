enum RomanNumeral {
    C(100), XC(90), L(50), XL(40), X(10), IX(9), V(5), IV(4), I(1);
    private final int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public static String toRomanNumeral(int number) {
        StringBuilder sb = new StringBuilder();
        for (RomanNumeral numeral : RomanNumeral.values()) {
            while (number >= numeral.value) {
                sb.append(numeral);
                number -= numeral.value;
            }
        }
        return sb.toString();
    }

    public static int toInt(String romanNumeral) {
        romanNumeral = romanNumeral.toUpperCase();
        int result = 0;
        int i = 0;
        while (i < romanNumeral.length()) {
            char currentChar = romanNumeral.charAt(i);
            RomanNumeral currentNumeral = RomanNumeral.valueOf(String.valueOf(currentChar));
            if (i < romanNumeral.length() - 1) {
                char nextChar = romanNumeral.charAt(i + 1);
                RomanNumeral nextNumeral = RomanNumeral.valueOf(String.valueOf(nextChar));
                if (nextNumeral.value > currentNumeral.value) {
                    result += nextNumeral.value - currentNumeral.value;
                    i += 2;
                    continue;
                }
            }
            result += currentNumeral.value;
            i++;
        }
        if (!romanNumeral.equals(toRomanNumeral(result))) { // проверка коррекности перекодировки
            throw new IllegalArgumentException();
        }
        return result;
    }
}