package com.lbc.corgi;

import com.lbc.mo.utils.DateCtrlUtil;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class DateUtilTest {
    //1553483211170
    @Test
    public void longToString() {
        LocalDate now = LocalDate.now().with(DayOfWeek.MONDAY);
        Date date = DateCtrlUtil.localDate2Date(now);
        LocalDate next = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        Date nextDay = DateCtrlUtil.localDate2Date(next);

        System.out.println(date);
        System.out.println(nextDay);
    }

}
