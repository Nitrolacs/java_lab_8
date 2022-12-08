import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс студента, он наследуется от класса Thread, чтобы этот класс был потоком
 */

public class Student extends Thread {

    /**
     * Поток вывода данных (поддерживает русский язык)
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);


    /**
     * Стол экзаменатора (один для всех студентов)
     */
    private static final AtomicBoolean examinerTableIsFree = new AtomicBoolean(true);

    /**
     * Номер студента
     */
    private final int num;

    /**
     * Конструктор экземпляра студента
     */
    Student(int num) {
        this.num = num;
    }

    /**
     * Метод получения целого случайного числа в заданном диапазоне
     *
     * @param min минимальное значение
     * @param max максимальное значение
     * @return случайное число, находящееся между min и max
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
            Thread.sleep(num * 1000L);
        } catch (InterruptedException ignored) {
        }

        // Пока студент не сдал экзамен
        while (!examIsPassed) {

            out.printf("┃ Студент №%d сидит за партой и повторяет.\n", num + 1);

            if (examinerTableIsFree.get()) {

                examinerTableIsFree.set(false);

                out.printf("┃ Студент №%d садится за стол экзаменатора и начинает сдачу экзамена.\n", num + 1);

                try {
                    Thread.sleep(5000);

                    int result = randomInt(1, 3);

                    switch (result) {
                        case 1 -> {
                            out.printf("┃ Экзаменатор поставил оценку %d студенту №%d. Студент выходит из аудитории.\n",
                                    randomInt(3, 5), num + 1);
                            examIsPassed = true;
                        }

                        case 2 -> {
                            out.printf("┃ Экзаменатор выгоняет студента №%d из аудитории.\n", num + 1);
                            examIsPassed = true;
                        }

                        case 3 -> {
                            out.printf("┃ Экзаменатор отправляет студента №%d подумать за парту.\n", num + 1);

                            try {
                                Thread.sleep(num * 3000L);
                            } catch (InterruptedException ignored) {
                            }

                        }
                    }

                    examinerTableIsFree.set(true);

                } catch (InterruptedException ignored) {
                }
            }

            else {
                try {
                    Thread.sleep(num * 3000L);
                } catch (InterruptedException ignored) {
                }
            }
        }

    }
}
