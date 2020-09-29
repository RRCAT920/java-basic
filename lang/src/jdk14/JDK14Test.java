package jdk14;

import org.junit.Test;

import java.util.Random;

/**
 * @author huzihao
 * @since 2020/9/29 17:50
 */
public class JDK14Test {
    @Test
    public void patternMatching() {
        var sql = switch (new Random().nextInt(2)) {
            case 0 -> "hello";
            case 1 -> null;
            default -> throw new IllegalStateException("Unexpected value: %s"
                    .formatted(new Random().nextInt(2)));
        };
        if (sql instanceof String str) sql = str.toUpperCase();

        System.out.println(sql);
    }

    @Test
    public void testRecord() {
        final var s1 = new Student("张三");
        final var s2 = new Student("李四");
        assert 1 == s1.id();
        assert 2 == s2.id();
        assert 2 == Student.getNumber();
        System.out.println(s1.toString());
    }

    @Test
    public void textBlocks() {
        var sql = """
                select *\s\
                from customers\
                """;
        //noinspection ConstantConditions
        assert "select * from customers".equals(sql);
    }
}
