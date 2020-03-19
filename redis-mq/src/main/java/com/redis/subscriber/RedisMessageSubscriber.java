package com.redis.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author YanCh
 * Create by 2020-03-18 20:54
 **/
@Service
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

    public static List<String> messageList = new ArrayList<>();


    @Override
    public void onMessage(Message message, byte[] bytes) {
        messageList.add(message.toString());
        log.info("订阅方收到了消息=====>{}",message.toString());
    }
}
