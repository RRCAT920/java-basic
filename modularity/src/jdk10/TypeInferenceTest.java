package jdk10;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author huzihao
 * @since 2020/9/15 01:47
 */
public class TypeInferenceTest {
    @Test
    public void typeInfer() {
        var numbers = Arrays.asList(1, 2, 3, 4, 5);
        for (var number : numbers) {
            System.out.println(numbers);
        }
    }
}
