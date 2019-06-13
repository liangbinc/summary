package com.lbc.mo.sharding;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.lbc.mo.constants.MonitorConstants;
import com.lbc.mo.utils.DateCtrlUtil;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class DateRangeShardingAlgorithm implements RangeShardingAlgorithm<Date> {
    private static final Logger LOG = LoggerFactory.getLogger(DateRangeShardingAlgorithm.class);

    private String logicTableName;

    public DateRangeShardingAlgorithm(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> shardingValue) {
        Collection<String> result = Lists.newLinkedList();
        Range range = shardingValue.getValueRange();
        Date start = (Date) range.lowerEndpoint();
        Date end = (Date) range.upperEndpoint();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        if (MonitorConstants.APP_STATE.equals(this.logicTableName)) {
            while (tempStart.before(tempEnd)) {
                result.add(this.logicTableName + "_" + DateCtrlUtil.getYear(start));
                tempStart.add(Calendar.YEAR, 1);
            }
        } else if (MonitorConstants.CONTAINER_STATS.equals(this.logicTableName)) {
            while (tempStart.before(tempEnd)) {
                result.add(this.logicTableName + "_" + DateCtrlUtil.dateString(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
        return result;
    }
}
