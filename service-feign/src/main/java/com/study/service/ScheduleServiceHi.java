package com.study.service;

import com.study.hystrix.ScheduleServiceHiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author YanCh
 * Create by 2020-02-10 15:17
 **/
@Service
@FeignClient(value = "service-hi",fallback = ScheduleServiceHiHystrix.class)
public interface ScheduleServiceHi {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
