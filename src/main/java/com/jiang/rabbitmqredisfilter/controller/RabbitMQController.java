package com.jiang.rabbitmqredisfilter.controller;

import com.jiang.rabbitmqredisfilter.publish.PublishSender;
import com.jiang.rabbitmqredisfilter.route.RouteSender;
import com.jiang.rabbitmqredisfilter.rpc.RpcClient;
import com.jiang.rabbitmqredisfilter.simpleness.SimpleSender;
import com.jiang.rabbitmqredisfilter.theme.ThemeSender;
import com.jiang.rabbitmqredisfilter.util.DateUtil;
import com.jiang.rabbitmqredisfilter.work.WorkSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: RabbitMQController
 * @description:
 * @author: lvjx
 * @create: 2020-05-29 10:44
 **/
@RestController
@RequestMapping("/click")
public class RabbitMQController {

    @Autowired
    private SimpleSender simpleSender;

    @Autowired
    private WorkSender workSender;

    @Autowired
    private PublishSender publishSender;

    @Autowired
    private RouteSender routeSender;

    @Autowired
    private ThemeSender themeSender;

    @Autowired
    private RpcClient rpcClient;

    @Autowired
    private Jedis jedis;

    @GetMapping("/simple")
    public String click(@RequestParam("msg") String msg){
        simpleSender.send(msg);
        return "简单模式+整合redis（Jedis）+过滤器";
    }

    @GetMapping("/work")
    public String work(@RequestParam("msg") String msg){
        workSender.send(msg);
        return "工作模式";
    }

    @GetMapping("/publish")
    public String publish(@RequestParam("msg") String msg){
        publishSender.send(msg);
        return "发布模式";
    }

    @GetMapping("/route")
    public String route(@RequestParam("msg") String msg,@RequestParam("key") String key){
        routeSender.send(key,msg);
        return "路由模式";
    }


    @GetMapping("/theme")
    public String theme(@RequestParam("msg") String msg,@RequestParam("key") String key){
        themeSender.send(key,msg);
        return "主题模式";
    }

    @GetMapping("/rpc")
    public String rpc(@RequestParam("number") Integer number){
        rpcClient.send(number);
        return "rpc模式";
    }

    @GetMapping("/getBrowseCount")
    public Long getBrowseCount(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);

        long size = DateUtil.betweenWithChronoUnit(start, end, ChronoUnit.DAYS) + 1;

        List<String> dateList = new ArrayList<>(Integer.parseInt(""+size));

        for (int i = 0;i < size;i++){
            String date = sdf.format(DateUtil.plusChronoUnit(start, i, ChronoUnit.DAYS));
            dateList.add(date);
        }

        Long browseCount = 0L;
        for (String date:dateList){
            long browse = Long.parseLong(jedis.get(date));
            browseCount += browse;
        }

        return browseCount;
    }
}
