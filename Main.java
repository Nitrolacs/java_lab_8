import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Основной класс программы, служит для её запуска
 */
public class Main {

    /**
     * Поток вывода данных (поддерживает русский язык)
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Выводит меню программы.
     */
    private static void printMenu() {

        out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃       Меню программы       ┃
                ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
                ┃ 1. Запустить экзамен       ┃
                ┃ 2. Выход                   ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }

    /**
     * Проверяет, что ввод - целое число.
     *
     * @return userInt Целое число.
     */
    public static int checkInt() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int userInt = 0;
        boolean correctInput = false;

        do {
            try {
                userInt = Integer.parseInt(userInput);
                if (userInt <= 0) {
                    out.print("┃ Число должно быть больше нуля. Введите число: ");
                    userInput = scanner.nextLine();
                } else {
                    correctInput = true;
                }

            } catch (NumberFormatException ex) {
                out.print("┃ Неверный ввод. Введите число: ");
                userInput = scanner.nextLine();
            }
        } while (!correctInput);
        return userInt;
    }


    /**
     * Запуск экзамена.
     */
    private static void launchExam() {
        out.print("┃ Введите количество парт в аудитории: ");
        int numberOfDesk = checkInt();


    }


    /**
     * Основная функция программы, в которой происходит
     * ввод и вывод данных, выполнение алгоритма.
     *
     * @param args массив переменных, введённых при запуске
     *             основной функции.
     */
    public static void main(String[] args) {
        int userChoice;

        do {
            printMenu();
            out.print("┃ Введите номер пункта: ");
            userChoice = checkInt();

            switch (userChoice) {
                case 1 -> launchExam();
                // Выход
                case 2 -> out.print("""
                        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
                        ┃ Завершение программы... ┃
                        ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛
                        """);
                default -> out.println("""
                        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                        ┃  Такого пункта нет в меню.  ┃
                        ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
            }

        } while (userChoice != 2);
    }
}
