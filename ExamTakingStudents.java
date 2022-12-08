import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Класс, в котором происходит сдача экзамена студентами
 */
public class ExamTakingStudents {

    /**
     * Поток вывода данных (поддерживает русский язык)
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);


    /**
     * Студенты
     */
    ArrayList<Student> students;


    /**
     * Конструктор класса
     */
    ExamTakingStudents() {
    }


    /**
     * Создаёт необходимые потоки.
     *
     * @param numberOfDesk Количество создаваемых потоков.
     * @return Список с созданными потоками.
     */
    private ArrayList<Student> creatingThreads(int numberOfDesk) {
        // Используем поток IntStream, чтобы заменить обычный цикл for
        return IntStream.range(0, numberOfDesk).mapToObj(Student::new).
                collect(Collectors.toCollection(ArrayList<Student>::new));
    }

    /**
     * Метод запуска всех студентов
     */
    public void start(int numberOfDesk) throws InterruptedException {

        students = creatingThreads(numberOfDesk);

        while (true) {

            out.println("""
                                            
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃ Новая группа студентов заходит в аудиторию, каждый садится за парту. ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                    """);

            students.forEach(Thread::start);

            try {
                for (Student thread : students) {
                    thread.join();
                }
            } catch (InterruptedException ignored) {
            }

            out.print("""
                                            
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃ Аудитория освободилась ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
                    """);

            students = creatingThreads(numberOfDesk);

        }


    }
}
