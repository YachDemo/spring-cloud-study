package com.example.shardjdbcdemo.controller;

import com.example.shardjdbcdemo.model.DemoInfo;
import com.example.shardjdbcdemo.service.DemoInfoService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author YanCh
 * @version v1.0
 * Create by 2020-03-31 15:16
 **/
@RestController
public class TestController {
    @Autowired
    DemoInfoService demoInfoService;


    @RequestMapping("/test")
    public String search(){
        DemoInfo demoInfo = new DemoInfo();
        demoInfo.setId(12349L);
        demoInfo.setUsername("321312");
        demoInfo.setPassword("312312");
        demoInfo.setEmail("3213123");
        demoInfo.setPhone("3212629131");
        demoInfo.setIsBind(false);
        demoInfo.setCreateTime(32132312);
        demoInfo.setUpdateTime(32131321L);
        demoInfoService.save(demoInfo);
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
        DemoInfo demoInfo1 = demoInfoService.getById(12349L);
        System.out.println(demoInfo1);
        return "success";
    }

}
