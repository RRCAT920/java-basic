import org.junit.Test;

import java.util.Arrays;

import comparable.Goods;

/**
 * @author huzihao
 * @since 2020/8/26 21:37
 */
public class CompareTest {
    /**
     * 自然排序
      */
    @Test
    public void testComparable() {
        var goods = new Goods[5];
        goods[0] = new Goods("Lenovo Mouse", 34);
        goods[1] = new Goods("Dell Mouse", 43);
        goods[2] = new Goods("Xiaomi Mouse", 12);
        goods[3] = new Goods("Huawei Mouse", 65);
        goods[4] = new Goods("Magic Mouse", 43);

        Arrays.sort(goods);
        System.out.println(Arrays.toString(goods));
    }

    /**
     * 定制排序
     */
    @Test
    public void testComparator() {
        var strings = new String[]{"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};

        Arrays.sort(strings, (str1, str2) -> -str1.compareTo(str2));
        System.out.println(Arrays.toString(strings));
    }
}
