package com.study.hystrix;

import com.study.service.ScheduleServiceHi;
import org.springframework.stereotype.Component;

/**
 * 
 * @author YanCh
 * Create by 2020-02-10 15:31
 **/
@Component
public class ScheduleServiceHiHystrix implements ScheduleServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry,"+name;
    }
}
