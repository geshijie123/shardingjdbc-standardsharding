package com.example.demo.sharding;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserShardingAlgorithm implements RangeShardingAlgorithm<Date>, PreciseShardingAlgorithm<Date> {

    private static final String TABLE_NAME = "sharding_test";

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        Range<Date> dates = rangeShardingValue.getValueRange();
        Date startTime = null, endTime = null;
        if (dates != null && dates.hasLowerBound() && dates.lowerEndpoint() != null) {
            startTime = dates.lowerEndpoint();
        } else {
            startTime = DateUtil.parseDateTime("2020-01-01 00:00:00");
        }
        if (dates != null && dates.hasUpperBound() && dates.upperEndpoint() != null) {
            endTime = dates.upperEndpoint();
        } else {
            endTime = new Date();
        }


        Set<String> tables = new HashSet<>();
        tables.add(TABLE_NAME+(DateUtil.month(startTime)+1));

        while (true) {
            startTime = DateUtil.offsetMonth(startTime, 1);
            if (startTime.before(endTime)) {
                tables.add(TABLE_NAME+(DateUtil.month(startTime)+1));
            } else {
                break;
            }
        }
        tables.add(TABLE_NAME+(DateUtil.month(endTime)+1));
        return tables;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        Date value = preciseShardingValue.getValue();
        return TABLE_NAME+(DateUtil.month(value)+1);
    }
}