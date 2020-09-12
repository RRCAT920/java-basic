package reflection.unique;

/**
 * @author huzihao
 * @since 2020/9/12 13:02
 */
public class Person extends Creature<String> implements Comparable<Person>, MyInterface {
    @Override
    public int compareTo(Person o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("hello");
    }
}
