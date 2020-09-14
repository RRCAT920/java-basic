package module;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * @author huzihao
 * @since 2020/9/14 23:33
 */
public class DiamondOperatorTest {
    @Test
    public void diamondOperatorUpgrade() {
        // 8是不允许匿名实现类和菱形语法一同使用的
        Comparator<Object> comp = new Comparator<>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }
}
