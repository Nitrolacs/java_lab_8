import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    /**
     * Поток вывода данных (поддерживает русский язык)
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Считывание данных из консоли
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Функция выводит меню программы.
     */
    public static void printMenu() {

        out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃                 Меню программы                 ┃
                ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
                ┃ 1. Добавление профессии по умолчанию           ┃
                ┃ 2. Выход                                       ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }

    /**
     * Функция для вывода предупреждения.
     *
     * @param choice Выбор сообщения.
     */
    public static void printError(int choice) {
        if (choice == 1) {
            out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃      Профессии ещё не добавлены       ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

        if (choice == 2) {
            out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃  Неверный номер профессии.  ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

        if (choice == 3) {
            out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃  Новое значение задано.  ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

        if (choice == 4) {
            out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃  Такого пункта нет в меню.  ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

    }


    /**
     * Основная функция программы, в которой происходит
     * ввод и вывод данных, выполнение алгоритма.
     *
     * @param args массив переменных, введённых при запуске
     *             основной функции.
     */
    public static void main(String[] args) {
        String userChoice;

        do {
            printMenu();
            out.print("┃ Введите номер пункта: ");
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1" -> System.out.println("In development");
                // Выход
                case "2" -> out.println("""
                        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
                        ┃ Завершение программы... ┃
                        ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛
                        """);
                default -> printError(4);
            }

        } while (!userChoice.equals("2"));
    }
}
