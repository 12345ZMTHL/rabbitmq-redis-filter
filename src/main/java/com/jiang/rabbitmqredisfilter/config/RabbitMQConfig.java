package com.jiang.rabbitmqredisfilter.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @ClassName: RabbitMQConfig
 * @description: rabbitmq配置文件
 * @author: lvjx
 * @create: 2020-05-29 10:30
 **/
@Configuration
public class RabbitMQConfig {
    /**
     * 简单模式
     * @return
     */
    @Bean("simpleQueue")
    public Queue simpleQueue() {
        /*
         * 可用以下形式:
         * new Queue("helloworld") - 持久,非排他,非自动删除
         * new Queue("helloworld",false,false,false,null)
         */
        return new Queue("helloworld",false);
    }

    @Bean("workQueue")
    public Queue workQueue() {
        // 这个构造方法创建的队列参数为: 持久,非排他,非自动删除
        return new Queue("workQueue");
    }

    /**
     * 发布模式，交换机采用fanout
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout");
    }

    /**
     * 路由模式，key只能匹配单个
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("route");
    }

    /**
     * 主题模式，与路由模式类似，可以说是路由模式的升级版，除了交换机不一样，最大的区别就是，主题模式可以匹配多个key，有自己的路由键规则
     *            * 可以通配单个单词。
     *            # 可以通配零个或多个单词
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("theme");
    }

    /**
     * RPC异步调用，双方既是生产者也是消费者，会等待对方返回结果
     * sendQueue：发送调用信息的队列
     * rndQueue：返回结果的队列
     * @return
     */
    @Bean
    public Queue sendQueue() {
        return new Queue("rpc_queue",false);
    }
    @Bean
    public Queue rndQueue() {
        return new Queue(UUID.randomUUID().toString(), false);
    }
}
