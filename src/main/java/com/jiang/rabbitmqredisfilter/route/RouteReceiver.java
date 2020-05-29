package com.jiang.rabbitmqredisfilter.route;

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
 * @description: 路由模式的消费者
 * @author: lvjx
 * @create: 2020-05-29 16:15
 **/
@Component
@Slf4j
public class RouteReceiver {
    @RabbitListener(bindings = @QueueBinding(value = @Queue,exchange = @Exchange(name = "route", declare = "false"),key = {"error"}))
    public void receive1(String s, Channel c, @Header(name= AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        log.info("路由模式(key = error) - 收到: "+s);
        c.basicAck(tag, false);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue, exchange = @Exchange(name = "route", declare = "false"),key = {"error","info","warning"}))
    public void receive2(String s,Channel c, @Header(name= AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        log.info("路由模式(key = error,info,warning) - 收到: "+s);
        c.basicAck(tag, false);
    }
}
