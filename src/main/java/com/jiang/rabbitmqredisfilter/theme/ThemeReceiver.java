package com.jiang.rabbitmqredisfilter.theme;

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
 * @ClassName: RouteReceiver
 * @description: 主题模式的消费者
 * @author: lvjx
 * @create: 2020-05-29 16:15
 **/
@Component
@Slf4j
public class ThemeReceiver {
    @RabbitListener(bindings = @QueueBinding(value = @Queue,exchange = @Exchange(name = "theme", declare = "false"),key = {"*.orange.*"}))
    public void receive1(String s, Channel c, @Header(name= AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        log.info("主题模式(key = *.orange.*) - 收到: "+s);
        c.basicAck(tag, false);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "theme", declare = "false"),key = {"*.*.rabbit","lazy.#"}))
    public void receive2(String s,Channel c, @Header(name= AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        log.info("主题模式(key = *.*.rabbit，lazy.#) - 收到: "+s);
        c.basicAck(tag, false);
    }
}
