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
                3
               2
                        """;
        //noinspection ConstantConditions
        assert 7 == test.length();
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
