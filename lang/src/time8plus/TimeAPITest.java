package time8plus;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;


public class TimeAPITest {
    @Test
    public void testLocal() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);

        LocalDateTime localDateTime = LocalDateTime.of(2020, 10, 21, 12, 0, 0);
        System.out.println(localDateTime);

        LocalDateTime withDayOfMonth = localDateTime.withDayOfMonth(23);
        System.out.println(withDayOfMonth);
    }

    @Test
    public void testInstant() {
        Instant now = Instant.now();
        System.out.println(now);
        Instant sameNow = Instant.ofEpochMilli(now.toEpochMilli());
        System.out.println(sameNow);

        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
    }

    @Test
    public void testDateTimeFormatter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime now = LocalDateTime.now();
        String dateString = dateTimeFormatter.format(now);
        System.out.println(dateString);

        TemporalAccessor parse = dateTimeFormatter.parse(dateString);
        System.out.println(parse);

        var formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    }

}
