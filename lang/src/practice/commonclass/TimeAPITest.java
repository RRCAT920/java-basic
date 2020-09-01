package practice.commonclass;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author huzihao
 * @since 2020/8/27 01:02
 */
public class TimeAPITest {
    @Test
    public void testParsing8Minus() throws ParseException {
        var formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("2017-08-16");
        System.out.println(date);
    }

    @Test
    public void testParsing8Plus() {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        TemporalAccessor temporalAccessor = formatter.parse("2017-08-16");
        System.out.println(temporalAccessor);
    }
}
