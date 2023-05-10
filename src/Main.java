import java.util.Scanner;

class Main { // калькулятор продолжает своё выполение пока не будет введена команда "выход"
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для завершения программы введите 'выход'\n");
        while (true) {
            System.out.print("Введите арифметическое выражение (например, 2 + 3): ");
            String input = scanner.nextLine();
            if (input.equals("выход")) {
                System.out.println("Программа завершена.");
                break;
            }
            String result = calc(input);
            System.out.println("Результат: " + result);
        }
    }

    public static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            return "Некорректное выражение.";
        }
        try {
            boolean isRomanBool = false; //переменная принимает значение true если используется выражение с двумя римскими цифрами
            if (parts[0].matches("[IVXLCDM]+") && parts[2].matches("[IVXLCDM]+")){
                isRomanBool = true;
            } else if (parts[0].matches("[IVXLCDM]+") ^ parts[2].matches("[IVXLCDM]+")){
                return "При использовании римских цифр оба числа должны быть заданы римскими цифрами (в формате: 'IVXLCDM').";
            }
            int number1 = convertToInt(parts[0]);
            int number2 = convertToInt(parts[2]);
            if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
                return "Числа должны быть заданы в диапазоне от 1 до 10 (или от I до X).";
            }
            String operator = parts[1];
            int result;
            switch (operator) {
                case "+":
                    result = number1 + number2;
                    return returnResult(isRomanBool, result);
                case "-":
                    result = number1 - number2;
                    return returnResult(isRomanBool, result);
                case "*":
                    result = number1 * number1;
                    return returnResult(isRomanBool, result);
                case "/":
                    result = number1 / number2;
                    return returnResult(isRomanBool, result);
                default:
                    return "Некорректный оператор.";
            }
        } catch (NumberFormatException e) {
            return "Некорректный формат чисел.";
        }
        catch (IllegalArgumentException e){
            return "Римские числа должны быть заданы в диапазоне от I до X / Неправильная запись римской цифры.";
        }
    }
    //перевод строки в целочисленный тип
    //при вводе римских чисел вызывается метод toInt перечисления RomanNumeral
    public static int convertToInt(String str) {
        if (str.matches("[IVXLCDM]+")) {
            return RomanNumeral.toInt(str);
        } else {
            return Integer.parseInt(str);
        }
    }
    //вывод результата в виде строки
    //осуществляется проверка того, было ли выражение с римскими цифрами
    // если да - вызывается метод toRomanNumeral перечисления RomanNumeral
    public static String returnResult(boolean isRoman, int result){
        if (isRoman && result < 1){
            return "Ошибка (результат не положительное число). Результатом вычисления с использованием римских цифр может быть только положительное число";
        } else if (isRoman){
            return  RomanNumeral.toRomanNumeral(result);
        } else {
            return Integer.toString(result);
    }
}}