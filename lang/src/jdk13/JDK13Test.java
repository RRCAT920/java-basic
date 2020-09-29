package jdk13;

import org.junit.Test;

/**
 * @author huzihao
 * @since 2020/9/29 15:32
 */
public class JDK13Test {
    @Test
    public void switchYield() {
        // 略
    }

    @Test
    public void textBlocks() {
        var test = """
             2
                5
               4
                    """;
        //noinspection ConstantConditions
        assert (2 + 5 + 4) == test.length();
    }

    @Test
    public void concatTestBlocks() {
        var test = "hello " + """
                world!
                """;
        //noinspection ConstantConditions
        assert "hello world!\n".equals(test);
    }

    @Test
    public void stringInterpolate() {
        var str = "world";
        var test = """
                hello, %s!
                """.formatted(str);
        assert "hello, world!\n".equals(test);
    }
}
