package com.study.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.study.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author YanCh
 * Create by 2020-02-10 14:10
 **/
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        System.out.println("this is ribbon");
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    public String hiError(String name){
        return "hi,"+name+",sorry,error";
    }
}
