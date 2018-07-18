package com.springboot2.carrot.timer;

import cronner.jfaster.org.job.annotation.Job;
import cronner.jfaster.org.job.api.ShardingContext;
import cronner.jfaster.org.job.api.simple.SimpleJob;

import java.util.concurrent.TimeUnit;

/**
 * 具体timer业务
 */
@Job(name = "cronner-simple-job",listener = JobListenerExample.class)
public class CronJobDemo implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("jobName=%s,jobParameter=%s,shardingItem=%s,shardingParameter=%s",
                shardingContext.getJobName(), shardingContext.getJobParameter(), shardingContext.getShardingItem(),
                shardingContext.getShardingParameter()));
    }
}
