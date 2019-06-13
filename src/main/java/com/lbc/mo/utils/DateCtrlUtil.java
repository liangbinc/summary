package com.lbc.mo.utils;

import org.apache.commons.lang.time.FastDateFormat;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateCtrlUtil {
    public static final FastDateFormat DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss", TimeZone.getTimeZone("GMT+00:00"));
    public static final FastDateFormat YYYYMMDD = FastDateFormat.getInstance("yyyyMMdd");
    public static final FastDateFormat YYYYMM = FastDateFormat.getInstance("yyyyMM");
    public static final FastDateFormat YM = FastDateFormat.getInstance("yyyy_MM_");
    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateCtrlUtil() {
    }

    public static String getCurrentTimeString() {
        Date currentDate = new Date();
        return DATETIME_FORMAT.format(currentDate);
    }

    public static Long getCurrentTimeLong() {
        Date currentDate = new Date();
        return currentDate.getTime();
    }

    public static Long stringToLong(String string) throws ParseException {
        return localDateTime2Date(LocalDateTime.parse(string, dateFormat)).getTime();
    }

    public static String longToString(Long dateLong) {
        Date date = new Date(dateLong);
        return DATETIME_FORMAT.format(date);
    }

    public static String longToTimeString(Long dateLong) {
        Date date = new Date(dateLong);
        return TIME_FORMAT.format(date);
    }

    public static String dateString(Date date) {
        return YYYYMMDD.format(date);
    }

    public static String monthString(Date date) {
        return YYYYMM.format(date);
    }

    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getYear(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return String.valueOf(instance.get(Calendar.YEAR));
    }

    public static LocalDateTime secondToLocalDate(Long second) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
    }

    public static Long localDateTimeToSecond(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }


    public static Date strToDate(String time) {
        return localDateTime2Date(LocalDateTime.parse(time, dateFormat));
    }

    public static LocalDate strToLocalDate(String time) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }
}
