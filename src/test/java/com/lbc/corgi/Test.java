package com.lbc.corgi;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Test {

    private static final Log LOG = LogFactory.getLog(Test.class);

    public static void commonly(String... parm) {
        System.out.println(parm);
    }

    public static void main(String[] args) throws Exception {
//        StatsMapCache instance = StatsMapCache.getInstance();
//        Constructor<StatsMapCache> constructor = StatsMapCache.class.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        StatsMapCache statsMapCache = constructor.newInstance();
//        System.out.println(instance==statsMapCache);
//        Long long1 = DateCtrlUtil.stringToLong("2019-04-25 10:00:00");
//        Long aLong = DateCtrlUtil.stringToLong("2019-04-25 11:00:00");
//
//        String s = DateCtrlUtil.longToTimeString(aLong - long1);
//        System.out.println(s);
//        LocalDate date = LocalDate.parse("2019-04-15 00:00:00", DateCtrlUtil.dateFormat).plusWeeks(-MonitorConstants.PRE_WEEKS);
//        System.out.println(date);
//        LocalDate localDate = LocalDate.now().plusWeeks(-MonitorConstants.PRE_WEEKS);
//        System.out.println(localDate);

        String time = "1556525949";
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(time)), ZoneId.systemDefault());
//        System.out.println(localDateTime);
//        Long aLong = DateCtrlUtil.localDateTimeToSecond(localDateTime);
//        System.out.println(aLong);

//        String sTime = "2019-04-22 22:00:00";
//
//        LocalDateTime weekPreDate = LocalDateTime.parse(sTime, DateCtrlUtil.dateFormat).plusWeeks(-MonitorConstants.PRE_WEEKS);
//        Long aLong = DateCtrlUtil.localDateTimeToSecond(weekPreDate);
//        if (aLong > Long.parseLong(time)) {
//            System.out.println(true);
//        }
        for (int i = 2; i < 100; i++) {
            boolean flag = false;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) flag = true;
            }
            if (!flag) {
                System.out.println(String.format("%s is prime", i));
            }
        }
    }
}
