package com.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author YanCh
 * Create by 2020-02-10 12:10
 **/
@RestController
public class ServiceHiController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String hi(){
        return "hi，" + port;
    }
}
