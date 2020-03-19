package com.redis.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

/**
 * 
 * @author YanCh
 * Create by 2020-03-19 9:15
 **/
@Component
public class RedisMessagePublisher implements MessagePublisher{
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ChannelTopic channelTopic;

    public RedisMessagePublisher(RedisTemplate<String,Object> redisTemplate,ChannelTopic channelTopic) {
        this.channelTopic = channelTopic;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(String message){
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
