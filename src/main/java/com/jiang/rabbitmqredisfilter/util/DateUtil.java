package com.jiang.rabbitmqredisfilter.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @description: 时间处理对象
 * @author: lvjx
 * @create: 2020-05-29 15:30
 **/
public class DateUtil {

    public static Long betweenWithChronoUnit(Date startDate, Date endDate, ChronoUnit chronoUnit){
        LocalDateTime startTime= LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime endTime = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        return ChronoUnit.DAYS.between(startTime, endTime);
    }

    public static Date plusChronoUnit(Date date, long amountToAdd, ChronoUnit cu) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return convertDate(ldt.plus(amountToAdd, cu));
    }

    public static Date convertDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }
}
