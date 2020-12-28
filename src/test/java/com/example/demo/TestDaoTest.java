package com.example.demo;

import com.example.demo.bean.Test;
import com.example.demo.dao.TestDao;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootShardingSphereApplication.class)
public class TestDaoTest {

    @Resource
    TestDao testDao;

    @org.junit.Test
    public void TestDao(){
        testDao.insert(Test.builder().id(1).name("233").build());
        testDao.insert(Test.builder().id(2).name("666").build());
        List<Map<String, Object>> maps = testDao.selectMaps(null);

        System.out.println(maps.get(0).get("name"));
        testDao.deleteById(2);

    }
}
