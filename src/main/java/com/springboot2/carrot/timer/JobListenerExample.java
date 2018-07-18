package com.springboot2.carrot.timer;

import cronner.jfaster.org.executor.ShardingContexts;
import cronner.jfaster.org.job.api.listener.CronnerJobListener;

/**
 * 创建一个作业监听
 */
public class JobListenerExample implements CronnerJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        System.out.println(String.format("----SpringBoot Job: %s begin----",shardingContexts.getJobName()));
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        System.out.println(String.format("----SpringBoot Job: %s end----",shardingContexts.getJobName()));
    }
}
