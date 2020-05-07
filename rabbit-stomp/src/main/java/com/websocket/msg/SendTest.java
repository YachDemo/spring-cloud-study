package com.websocket.msg;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author YanCh
 * @version v1.0
 * Create by 2020-05-07 16:50
 **/
public class SendTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.127");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("rabbitmq", "fanout");
        String routingKey = "rabbitmq_routingkey";
        String message = "{\"name\":\"Welcome to RabbitMQ message push!\"}";
        channel.basicPublish("rabbitmq", routingKey,null, message.getBytes());
        System.out.println("[x] Sent Message:"+message);
        channel.close();
        connection.close();
    }
}
