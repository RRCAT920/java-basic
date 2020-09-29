package jdk14;

/**
 * @author huzihao
 * @since 2020/9/30 00:01
 */
public record Student(int id, String name) {
    private static int number = 0;

    public Student {
        number++;
        this.id = number;
    }

    public Student(String name) {
        this(0, name);
    }

    public static int getNumber() {
        return number;
    }
}
