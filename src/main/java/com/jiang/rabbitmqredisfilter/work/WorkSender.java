package com.jiang.rabbitmqredisfilter.work;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WorkSender
 * @description:
 * @author: lvjx
 * @create: 2020-05-29 11:01
 **/
@Component
public class WorkSender {
    @Autowired
    AmqpTemplate t;

    public void send(String msg) {
        t.convertAndSend("workQueue", msg);
    }

}
