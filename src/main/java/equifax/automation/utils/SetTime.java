package equifax.automation.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;

public class SetTime {

    static SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    static LocalDate date = LocalDate.now();

    public static String setStartOfDay() {
        ZonedDateTime zdt = date.atStartOfDay(ZoneId.of("GMT-6"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(zdt.toInstant().toEpochMilli());
        return formatoFecha.format(cal.getTime());
    }

    public static String setEndOfDay() {
        LocalDateTime ldt = date.atTime(LocalTime.MAX);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ldt.atZone(ZoneId.of("GMT-6")).toInstant().toEpochMilli());
        return formatoFecha.format(cal.getTime());
    }
}
