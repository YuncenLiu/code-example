package com.kunlun.cron;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.kunlun.cron
 * @author: Xiang想
 * @createTime: 2024-03-04  09:20
 * @description: TODO
 * @version: 1.0
 */
public class CronTest {
    public static void main(String[] args) {
        String cronExpression = "0 * * * * ?"; // 每两小时一次的 Cron 表达式
        SimpleDateFormat simd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            CronExpression exp = new CronExpression(cronExpression);


            LocalDateTime now = LocalDateTime.now().plusDays(1);;

            Date tomorrowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
            String tomorrowStr = simd.format(tomorrowDate);
            System.out.println("明天 ： " + tomorrowStr);


            LocalDateTime todayStart = now.with(LocalTime.MIN);
            todayStart = todayStart.minusSeconds(1);
            Date startDate = Date.from(todayStart.atZone(ZoneId.systemDefault()).toInstant());


            LocalDateTime todayEnd = now.with(LocalTime.MAX);
            todayEnd = todayEnd.plusSeconds(1);
            Date endDate = Date.from(todayEnd.atZone(ZoneId.systemDefault()).toInstant());


            Date startTime = exp.getNextValidTimeAfter(startDate);
            System.out.println("明天，根据表达式 '" + cronExpression + "' 执行的时间点为：");
            while (startTime.before(endDate)) { // 输出当天的执行时间
                String calculateTomorrowStr = sim.format(startTime);
                if (calculateTomorrowStr.contains(tomorrowStr)) {
                    System.out.println(calculateTomorrowStr);
                }

                startTime = exp.getNextValidTimeAfter(startTime);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
