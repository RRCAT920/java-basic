package time;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeAPITest {
    @Test
    public void testCalendar() {
        long start = System.currentTimeMillis();
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        try {
            from.setTime(dateFormat.parse("2020-01-01"));
            to.setTime(dateFormat.parse("2020-02-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int j = 1;
        while (!from.equals(to)) {
            j++;
            from.add(Calendar.DATE, 1);
        }
        System.out.println(j);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
