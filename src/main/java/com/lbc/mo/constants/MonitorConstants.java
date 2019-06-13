package com.lbc.mo.constants;

import java.io.File;
import java.math.BigDecimal;

public class MonitorConstants {
    public static final String GPU_LABLE_V100 = "V100-32480M";
    public static final String GPU_LABLE_V100_OK = "GS00";
    //sharding container_stats
    public static final String CONTAINER_STATS = "container_stats";
    //sharding_Column
    public static final String CONTAINER_STATS_SHARDING = "fetch_time";
    public static final String APP_STATE = "app_state";
    public static final String APP_STATE_SHARDING = "start_time";
    public static final String USER_WEEKLY_LOG_ADD = "ADD";
    public static final String USER_WEEKLY_LOG_RM = "RM";
    //bills
    public static final String OPERATOR_UID = "1074";
    public static final String OPERATOR_UID_PARM = "{operatorUid}";
    public static final String USER_NAME_PARM = "{userName}";
    public static final String USER_UID_PARM = "{userId}";
    public static final String GROUPID_PARM = "{groupId}";
    public static final String START_TIME = "{startTime}";
    public static final String END_TIME = "{endTime}";
    public static final String RESPONSE_STATUS = "1";
    public static final String COINS_TYOE = "POINT";
    public static final Integer AI_LAB = 1001;
    public static final Double COMMON_USER_BASE_COINS = 20000.00;
    public static final Double WHITELIST_USER_BASE_COINS = 100000.00;
    public static final BigDecimal WHITELIST_USER_QUOTA = new BigDecimal(100000.00);
    public static final BigDecimal USER_BASE_LIMIT = new BigDecimal(5000.00);
    public static final BigDecimal USER_MAX_LIMIT = new BigDecimal(20000.00);
    public static final BigDecimal USER_MIN_LIMIT = new BigDecimal(2000.00);
    public static final BigDecimal USER_CHANGE_QUOTA = new BigDecimal(1000.00);
    public static final Double USER_BASE_ALPHA = 0.2;
    public static final BigDecimal HALF = new BigDecimal(0.50);
    public static final BigDecimal ZERO = new BigDecimal(0.00);
    public static final BigDecimal UP_ALPHA = new BigDecimal(30);
    public static final Long APP_KILLED_TIMES = 3L;
    public static final Integer PRE_WEEKS = 3;
    public static final Integer CONTAINERS_STATISTICS_SERVICE = 8090;
    public static final Integer PERIOD_SERVICE = 8080;
    public static final String COINS_EXCEL_OUTPUT_PATH = File.separator + "UserCoins" + File.separator;
    public static final String COINS_EXCEL_OUTPUT_PATH_FILENAME = File.separator + "UserCoins_%s.xlsx";

    private MonitorConstants() {
    }


}
