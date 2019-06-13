package com.lbc.mo.sharding;

import com.lbc.mo.constants.MonitorConstants;
import com.lbc.mo.utils.DateCtrlUtil;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Date;

public class DatePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    private static final Logger LOG = LoggerFactory.getLogger(DatePreciseShardingAlgorithm.class);

    private String logicTableName;

    public DatePreciseShardingAlgorithm(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> shardingValue) {
        String result = "";
        if (MonitorConstants.APP_STATE.equals(this.logicTableName)) {
            result = this.logicTableName + "_" + DateCtrlUtil.getYear(shardingValue.getValue());
        } else if (MonitorConstants.CONTAINER_STATS.equals(this.logicTableName)) {
            result = this.logicTableName + "_" + DateCtrlUtil.dateString(shardingValue.getValue());
        }
        return result;
    }
}

