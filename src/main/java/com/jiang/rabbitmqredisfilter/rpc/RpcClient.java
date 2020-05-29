package com.jiang.rabbitmqredisfilter.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName: RpcClient
 * @description: RPC模式，客户端
 * @author: lvjx
 * @create: 2020-05-29 16:59
 **/
@Component
@Slf4j
public class RpcClient {
    @Autowired
    AmqpTemplate t;

    /**
     *使用 SPEL 表达式获取随机队列名,具体百度
     */
    @Value("#{rndQueue.name}")
    String rndQueue;

    /**
     * 消息从这里发出
     * @param n
     */
    public void send(int n) {
        // 发送调用信息时, 通过前置消息处理器, 对消息属性进行设置, 添加返回队列名和关联id
        t.convertAndSend("rpc_queue", (Object)n, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties p = message.getMessageProperties();
                p.setReplyTo(rndQueue);
                p.setCorrelationId(UUID.randomUUID().toString());
                return message;
            }
        });
    }

    /**
     * 接受服务端返回的结果，返回的所求的第几个斐波那契数
     */
    @RabbitListener(queues = "#{rndQueue.name}")
    public void receive(long r, @Header(name= AmqpHeaders.CORRELATION_ID) String correlationId) {
        log.info(correlationId+" - 收到: "+r);
    }
}
