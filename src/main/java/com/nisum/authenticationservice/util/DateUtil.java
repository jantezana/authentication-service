package com.nisum.authenticationservice.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class DateUtil {

    public static ZonedDateTime toZoneDateTime(Calendar calendar) {
        Objects.requireNonNull(calendar, "The calendar is null");
        Instant instant = calendar.toInstant();
        return ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    public static Calendar toCalendar(ZonedDateTime zonedDateTime) {
        Objects.requireNonNull(zonedDateTime, "The zonedDateTime is null");
        return GregorianCalendar.from(zonedDateTime);
    }
}
