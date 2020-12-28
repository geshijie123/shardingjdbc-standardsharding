package com.example.demo.bean;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Test {
    private long id;
    private String name;
}
