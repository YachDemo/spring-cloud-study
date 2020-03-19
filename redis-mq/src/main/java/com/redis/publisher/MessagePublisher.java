package com.redis.publisher;

/**
 * @author YanCh
 * Create by 2020-03-19 9:22
 **/
public interface MessagePublisher {

    void publish(String message);

}
