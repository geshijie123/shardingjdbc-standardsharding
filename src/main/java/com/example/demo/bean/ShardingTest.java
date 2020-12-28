package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShardingTest {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private long id;
    private String name;
    private Long orderId;
    private Date createTime;

}
