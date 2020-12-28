package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.ShardingTest;
import com.example.demo.dao.ShardingTestDao;
import org.springframework.stereotype.Service;

@Service
public class ShardingTestService extends ServiceImpl<ShardingTestDao, ShardingTest> {
}
