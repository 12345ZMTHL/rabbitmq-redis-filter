package com.jiang.rabbitmqredisfilter.simpleness;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SimpleSender
 * @description: 简单模式的生产者
 * @author: lvjx
 * @create: 2020-05-29 10:32
 **/
@Component
public class SimpleSender {
    @Autowired
    AmqpTemplate t;

    public void send(String msg) {
        // 这里向 helloworld 队列发送消息
        t.convertAndSend("helloworld", msg);
    }
}
