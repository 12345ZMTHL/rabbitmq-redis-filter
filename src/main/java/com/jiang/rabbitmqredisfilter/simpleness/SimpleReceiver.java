package com.jiang.rabbitmqredisfilter.simpleness;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * @ClassName: SimpleReceiver
 * @description: 简单模式的消费者
 * @author: lvjx
 * @create: 2020-05-29 10:34
 **/
@Component
@RabbitListener(queues = "helloworld")
@Slf4j
public class SimpleReceiver {

    @RabbitHandler
    public void receive(String msg, Channel c, @Header(name= AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        Thread.sleep(new Random().nextInt(10000));
        log.info("接收到消息:"+msg);
        // 手动发送确认回执
        c.basicAck(tag, false);
    }
}
