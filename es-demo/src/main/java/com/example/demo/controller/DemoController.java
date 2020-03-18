package com.example.demo.controller;

import com.example.demo.entity.Demo;
import com.example.demo.service.IDemoService;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.SnowflakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author YanCh
 * Create by 2020-02-26 16:40
 **/
@RestController
public class DemoController {
    @Autowired
    IDemoService demoService;
    @Autowired
    RedisUtils redisUtils;

    private static SnowflakeUtils snowflakeUtils = new SnowflakeUtils(1,2);

    @RequestMapping("/add")
    public String add(){
        List<Demo> demos = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Demo demo = new Demo();
            demo.setId(snowflakeUtils.nextId());
            demo.setUsername("addd"+i);
            demo.setPassword("dsada"+i);
            demo.setEmail("dddsa"+i);
            demo.setPhone("23"+i);
            demo.setIsBind(1);
            demo.setCreateTime(System.currentTimeMillis());
            demo.setUpdateTime(System.currentTimeMillis());
            demos.add(demo);
            if (demos.size() >= 100000){
                demoService.insertFetch(demos);
                demos.clear();
            }
        }
        return "11";
    }

    @RequestMapping("redis")
    public String redisTep(){
        redisUtils.set("111","323123");
        redisUtils.set("2222","323123");
        redisUtils.set("3333","323123");
        redisUtils.set("4444","323123");
        System.out.println(redisUtils.get("4444").toString());
        return "dsad";
    }
}
