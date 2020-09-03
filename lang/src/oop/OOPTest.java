package oop;

import org.junit.Test;

import java.util.Random;

/**
 * @author huzihao
 * @since 2020/9/2 22:19
 */
public class OOPTest {

    interface A {
        void say();
    }

    /**
     * 多态是运行时行为
     */
    @Test
    public void testPoly() {
        new A[] {
            () -> System.out.println("A"),
            () -> System.out.println("B")
        }[new Random().nextInt(2)].say();
    }
}
