package jdk12;

import java.util.Scanner;

/**
 * @author huzihao
 * @since 2020/9/28 15:21
 */
public class JDK12Test {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        switchExpression();
    }
    public static void switchExpression() {
        var name = in.next();
        var index = switch (Fruit.valueOf(name.toUpperCase())) {
            case APPLE, ORANGE, MANGO -> 0;
            case PAPAYA -> 2;
            default -> 120;
        };
        System.out.println(index);
    }
}
