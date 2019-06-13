package com.lbc.mo.sharding;

import com.lbc.mo.constants.MonitorConstants;
import com.lbc.mo.utils.DateCtrlUtil;
import io.shardingsphere.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class ComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    private String logicTableName;

    public ComplexShardingAlgorithm(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, Collection<ShardingValue> collection1) {
        List<String> shardingSuffix = new ArrayList<>();
        Collection<Date> shardingStartTime = getShardingValue(collection1, MonitorConstants.APP_STATE_SHARDING);
        for (Date date : shardingStartTime) {
            String result = this.logicTableName + "_" + DateCtrlUtil.monthString(date);
            shardingSuffix.add(result);
        }

        return shardingSuffix;
    }

    private Collection<Date> getShardingValue(Collection<ShardingValue> shardingValues, final String key) {
        Collection<Date> valueSet = new ArrayList<>();
        Iterator<ShardingValue> iterator = shardingValues.iterator();
        while (iterator.hasNext()) {
            ShardingValue next = iterator.next();
            if (next instanceof ListShardingValue) {
                ListShardingValue value = (ListShardingValue) next;
                /**例如：根据user_id + order_id 双分片键来进行分表*/
                if (value.getColumnName().equals(key)) {
                    return value.getValues();
                }
            }
        }
        return valueSet;
    }
}
