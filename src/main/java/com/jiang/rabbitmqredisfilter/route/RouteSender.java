package com.jiang.rabbitmqredisfilter.route;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SubscibeSender
 * @description: 路由模式得生产者,路由模式的key只能单个匹配
 * @author: lvjx
 * @create: 2020-05-29 16:05
 **/
@Component
public class RouteSender {
    @Autowired
    AmqpTemplate t;

    public void send(String key,String msg) {
        t.convertAndSend("route",key,msg);
    }
}
