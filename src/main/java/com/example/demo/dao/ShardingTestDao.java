package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.ShardingTest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShardingTestDao  extends BaseMapper<ShardingTest> {
}
