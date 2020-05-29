package com.jiang.rabbitmqredisfilter.publish;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @ClassName: PublishReceiver
 * @description: 发布模式得消费者
 * @author: lvjx
 * @create: 2020-05-29 15:56
 **/
@Component
@Slf4j
public class PublishReceiver {

    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "fanout", declare = "false")))
    public void receive1(String msg, Channel c, @Header(name= AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        log.info("消费者1- 收到: "+msg);
        c.basicAck(tag, false);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "fanout", declare = "false")))
    public void receive2(String msg,Channel c,@Header(name= AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        log.info("消费者2- 收到: "+msg);
        c.basicAck(tag, false);
    }
}
