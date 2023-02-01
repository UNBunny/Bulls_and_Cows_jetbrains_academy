package bullscows;

import java.io.Console;
import java.security.SecureRandom;
import java.util.*;

public class Main {
    public static String[] randomizer(int value, int length, List<String> list) {
        var random = new SecureRandom();
        String[] str = new String[value];
        for (int i = 0; i < value; ) {
            boolean tf = false;
            var randomElement = list.get(random.nextInt(length));
            for (int j = 0; j < i; j++) {
                if (str[j].equals(randomElement)) {
                    tf = true;
                    break;
                }
            }
            if (!tf) {
                str[i] = randomElement;
                i++;
            }
        }
//        System.out.println(Arrays.asList(str));
        return str;
    }

    public static void main(String[] args) {

        //Создаем лист всех доступных цифр и чисел
        var list = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
                "y", "z");

        //Ввод длины
        int secretLength = 0;
        try {
            System.out.println("Input the length of the secret code:");
            secretLength = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.printf("Error");
            return;
        }
        if (secretLength == 0) {
            System.out.println("Error");
            return;
        }

        //Ввод количества чисел
        System.out.println("Input the number of possible symbols in the code:");
        int numberSymbol = new Scanner(System.in).nextInt();
        if (numberSymbol > 36 || secretLength > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z)");
            return;
        } else if (numberSymbol < secretLength) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", secretLength, numberSymbol);
            return;
        } else if (numberSymbol < 10) {
            System.out.println("The secret is prepared: " + "*".repeat(secretLength) + " (" + list.get(0) + "-" +
                    list.get(numberSymbol) + ")");
        } else if (numberSymbol == 10) {
            System.out.println("The secret is prepared: " + "*".repeat(secretLength) + " (" + list.get(0) + "-" +
                    list.get(9) + ", " + list.get(10) + ")");
        } else if (numberSymbol > 10) {
            System.out.println("The secret is prepared: " + "*".repeat(secretLength) + " (" + list.get(0) + "-" +
                    list.get(9) + ", " + list.get(10) + "-" + list.get(numberSymbol - 1));
        } else System.out.println("Okay, let's start a game!");

        // В массив arr передаем рандомную строку из функции randomizer и создаем класс
        String[] arr = randomizer(secretLength, numberSymbol, list);
        Game game = new Game(arr);

        //Пока Status != false выполняем
        int count = 1;
        while (game.getStatus()) {
            try {
                System.out.printf("Turn %d:\n", count);
                String num = new Scanner(System.in).next();
                game.Play(num);
                count++;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            }
        }
    }
}
