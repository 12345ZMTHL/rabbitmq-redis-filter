package com.jiang.rabbitmqredisfilter.publish;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RouteSender
 * @description: 发布模式得生产者
 * @author: lvjx
 * @create: 2020-05-29 15:52
 **/
@Component
public class PublishSender {
    @Autowired
    AmqpTemplate t;

    public void send(String msg) {
        // 指定向 logs 交换机发送, 不指定队列名或路由键
        t.convertAndSend("fanout", "", msg);
    }
}
