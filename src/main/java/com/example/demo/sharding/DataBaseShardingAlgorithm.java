package com.example.demo.sharding;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DataBaseShardingAlgorithm implements RangeShardingAlgorithm<Date>, PreciseShardingAlgorithm<Date> {

    private static final String DATABASE_NAME = "paydb";

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
        tables.add(DATABASE_NAME+DateUtil.year(startTime));

        while (true) {
            startTime = DateUtil.offsetMonth(startTime, 12);
            if (startTime.before(endTime)) {
                tables.add(DATABASE_NAME+DateUtil.year(startTime));
            } else {
                break;
            }
        }
        tables.add(DATABASE_NAME+DateUtil.year(endTime));
        return tables;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        Date value = preciseShardingValue.getValue();
        return DATABASE_NAME+DateUtil.year(value);
    }
}