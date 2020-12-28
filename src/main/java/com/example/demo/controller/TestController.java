package com.example.demo.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.bean.ShardingTest;
import com.example.demo.dao.ShardingTestDao;
import com.example.demo.service.ShardingTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class TestController {

    @Resource
    ShardingTestService shardingTestService;

    @RequestMapping("test")
    public Object test() {
        return shardingTestService.list();
    }
    @RequestMapping("one")
    public Object one(long id) {
        return shardingTestService.getById(id);
    }

    @RequestMapping("some")
    public Object some() {
        return shardingTestService.list(new QueryWrapper<ShardingTest>().between("create_time", DateUtil.parse("2020-01-03"), DateUtil.parse("2020-06-03", DatePattern.NORM_DATE_PATTERN)));
    }


    @RequestMapping("add")
    public Object add() {
        List<ShardingTest> list = new ArrayList<>();
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2020-01-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2020-07-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2020-06-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2020-02-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2020-03-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2021-04-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2021-08-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2020-11-03 00:00:00")).build());
        list.add(ShardingTest.builder().name("233").createTime(DateUtil.parseDateTime("2020-11-03 00:00:00")).build());
        return shardingTestService.saveBatch(list);
    }
}
