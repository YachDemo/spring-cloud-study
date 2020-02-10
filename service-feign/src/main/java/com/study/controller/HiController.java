package com.study.controller;

import com.study.service.ScheduleServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author YanCh
 * Create by 2020-02-10 15:18
 **/
@RestController
public class HiController {
    @Autowired
    ScheduleServiceHi scheduleServiceHi;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return scheduleServiceHi.sayHiFromClientOne(name);
    }
}
