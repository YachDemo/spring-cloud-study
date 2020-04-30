package com.study.controller;

import com.study.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2020-04-30 10:31
 **/
@RestController
@RequestMapping("/push/")
public class PushController {
    @Autowired
    private PushService pushService;

    @PostMapping("pushAll")
    public String pushToAll(@RequestParam String msg) {
        pushService.pushMsgToAll(msg);
        return "操作成功";
    }

    @PostMapping("/pushOne")
    public void pushMsgToOne(@RequestParam("userId") String userId,@RequestParam("msg") String msg){
        pushService.pushMsgToOne(userId,msg);
    }
}
