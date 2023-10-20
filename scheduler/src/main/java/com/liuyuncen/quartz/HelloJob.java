package com.liuyuncen.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.quartz
 * @author: Xiang想
 * @createTime: 2023-07-28  09:11
 * @description: TODO
 * @version: 1.0
 */
public class HelloJob implements Job {

    private final SimpleDateFormat SIM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello >> " + SIM.format(new Date()));
    }
}
