package com.jiang.rabbitmqredisfilter.theme;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SubscibeSender
 * @description: 主题模式得生产者,路由模式的key只能单个匹配
 * @author: lvjx
 * @create: 2020-05-29 16:05
 **/
@Component
public class ThemeSender {
    @Autowired
    AmqpTemplate t;

    public void send(String key,String msg) {
        t.convertAndSend("theme",key,msg);
    }
}
