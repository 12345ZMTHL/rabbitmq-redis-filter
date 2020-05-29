package com.jiang.rabbitmqredisfilter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName: WebFiflet
 * @description:
 * @author: lvjx
 * @create: 2020-05-29 13:47
 **/
@WebFilter(filterName = "reowseFilter", urlPatterns = "/click/simple")
@Slf4j
public class BrowseFilter implements Filter {
    @Autowired
    private Jedis jedis;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("加载过滤器"+filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        String date = "2020-05-29";//这里可以写成当天的日期，这样每天的浏览量都会存在redis中，这里写死是为了测试
        if (!jedis.exists(date)){
            jedis.set(date,"1");
        }
        jedis.incr(date);
        log.info("浏览人数为:"+jedis.get(date));
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
