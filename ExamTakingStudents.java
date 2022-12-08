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
    ExamTakingStudents(int numberOfDesk) {

        // Используем поток IntStream, чтобы заменить обычный цикл for
        students = IntStream.range(0, numberOfDesk).mapToObj(Student::new).
                collect(Collectors.toCollection(ArrayList<Student>::new));
    }


    /**
     * Метод запуска всех студентов
     */
    public void start() {
        out.println("┃ Новая группа студентов заходит в аудиторию, каждый садится за свою парту");

        students.forEach(Thread::start);
    }
}
