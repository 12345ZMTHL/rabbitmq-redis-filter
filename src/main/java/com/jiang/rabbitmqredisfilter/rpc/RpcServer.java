package com.jiang.rabbitmqredisfilter.rpc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RpcServer
 * @description: RPC模式，服务端
 * @author: lvjx
 * @create: 2020-05-29 16:58
 **/
@Component
public class RpcServer {
    @RabbitListener(queues = "rpc_queue")
    public long getFbnq(int n){
        return f(n);
    }

    /**
     * 求第几个斐波那契数
     * @param n
     * @return
     */
    private long f(int n) {
        if (n==1 || n==2) {
            return 1;
        }
        return f(n-1) + f(n-2);
    }
}
