package com.jiang.rabbitmqredisfilter.work;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: WorkReceiver
 * @description: 工作模式的消费者
 * @author: lvjx
 * @create: 2020-05-29 11:05
 **/
@Component
@Slf4j
public class WorkReceiver {
    @RabbitListener(queues="workQueue")
    public void receive1(String s) throws Exception {
        log.info("receiver1 - 收到: "+s);
    }

    @RabbitListener(queues="workQueue")
    public void receive2(String s) throws Exception {
        log.info("receiver2 - 收到: "+s);
    }
}
