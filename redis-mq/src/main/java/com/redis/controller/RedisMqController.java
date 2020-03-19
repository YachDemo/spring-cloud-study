package com.redis.controller;

import com.redis.publisher.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author YanCh
 * Create by 2020-03-19 9:38
 **/
@RestController
public class RedisMqController {
    @Autowired
    MessagePublisher messagePublisher;

    @GetMapping("/send")
    public String sendMessage(){
        for (int i = 0; i < 100; i++) {
            messagePublisher.publish("{\n" +
                    "    \"code\": \"0000\",\n" +
                    "    \"msg\": \"操作成功！\",\n" +
                    "    \"data\": {\n" +
                    "        \"money\": 0.00,\n" +
                    "        \"point\": 0,\n" +
                    "        \"commission\": 20.00\n" +
                    "    }\n" +
                    "}");
        }
        return "操作成功！";
    }

}
