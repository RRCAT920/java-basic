package practice.commonclass;

import org.junit.Test;

/**
 * @author huzihao
 * @since 2020/8/30 15:53
 */
public class StringAndNumberTest {
    @Test
    public void testTransform() {
        String str = "123";
        int strToNumber = Integer.parseInt(str);
        System.out.println(strToNumber);

        int numberToStr = 456;
        String numberStr = String.valueOf(numberToStr);
        System.out.println(numberStr);
    }
}
