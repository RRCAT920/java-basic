package jdk11;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @author huzihao
 * @since 2020/9/15 13:31
 */
public class TypeInferTest {
    @Test
    public void typeInferUpgrade() {
//        var consumer1 = (Consumer<String>) (@Deprecated t) -> System.out.println(t);
        var consumer2 =
                (Consumer<String>) (@Deprecated var t) -> System.out.println(t);
    }
}
