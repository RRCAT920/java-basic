package time8plus;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author huzihao
 * @since 2020/8/27 00:33
 */
public class BigNumberTest {
    @Test
    public void testBigDecimal() {
        var number1 = new BigDecimal("123213.123");
        var number2 = new BigDecimal("438290.213");
        System.out.println(number1.divide(number2, 15, RoundingMode.HALF_UP));
    }
}
