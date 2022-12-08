import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс студента, он наследуется от класса Thread, чтобы этот класс был потоком.
 */

public class Student extends Thread {

    /**
     * Поток вывода данных (поддерживает русский язык).
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);


    /**
     * Стол экзаменатора (один для всех студентов).
     */
    private static final AtomicBoolean examinerTableIsFree = new AtomicBoolean(true);

    /**
     * Номер студента.
     */
    private final int num;

    /**
     * Конструктор экземпляра студента.
     */
    Student(int num) {
        this.num = num;
    }

    /**
     * Код для сброса раскраски.
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Код красного цвете.
     */
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * Код зелёного цвета.
     */
    private static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Код жёлтого цвета.
     */
    private static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * Код бирюзового цвета.
     */
    private static final String ANSI_TURQUOISE = "\u001B[36m";

    /**
     * Код фиолетового цвета.
     */
    private static final String ANSI_PURPLE = "\u001B[35m";


    /**
     * Метод получения целого случайного числа в заданном диапазоне.
     *
     * @param min минимальное значение.
     * @param max максимальное значение.
     * @return случайное число, находящееся между min и max.
     */
    public static int randomInt(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }


    /**
     * В этом методе описан тот код, который будет выполняться в потоке.
     */
    @Override
    public void run() {
        boolean examIsPassed = false;

        try {
            Thread.sleep(randomInt(1, num + 1) * 1000L);
        } catch (InterruptedException ignored) {
        }
        out.printf(ANSI_TURQUOISE + "┃ Студент №%d сидит за партой и повторяет.\n" + ANSI_RESET,
                num + 1);

        // Пока студент не сдал экзамен.
        while (!examIsPassed) {

            if (examinerTableIsFree.get()) {

                examinerTableIsFree.set(false);

                out.printf(ANSI_YELLOW +
                        "┃ Студент №%d садится за стол экзаменатора и начинает сдачу экзамена.\n"
                        + ANSI_RESET, num + 1);

                try {
                    Thread.sleep(5000);

                    int result = randomInt(1, 3);

                    switch (result) {
                        case 1 -> {
                            out.printf(ANSI_GREEN + "┃ Экзаменатор поставил оценку " +
                                            "%d студенту №%d. Студент выходит из аудитории.\n" + ANSI_RESET,

                                    randomInt(3, 5), num + 1);
                            examIsPassed = true;

                            examinerTableIsFree.set(true);
                        }

                        case 2 -> {
                            out.printf(ANSI_RED + "┃ Экзаменатор выгоняет студента №%d из аудитории.\n" +
                                    ANSI_RESET, num + 1);
                            examIsPassed = true;

                            examinerTableIsFree.set(true);
                        }

                        case 3 -> {
                            out.printf(ANSI_PURPLE +
                                    "┃ Экзаменатор отправляет студента №%d подумать за парту.\n" +
                                    ANSI_RESET, num + 1);

                            out.printf(ANSI_TURQUOISE + "┃ Студент №%d сидит за партой и повторяет.\n" +
                                    ANSI_RESET, num + 1);

                            examinerTableIsFree.set(true);

                            try {
                                Thread.sleep(randomInt(1, num + 1) * 3000L);
                            } catch (InterruptedException ignored) {
                            }

                        }
                    }

                } catch (InterruptedException ignored) {
                }
            } else {
                try {
                    Thread.sleep(num * 3000L);
                } catch (InterruptedException ignored) {
                }
            }
        }

    }
}
