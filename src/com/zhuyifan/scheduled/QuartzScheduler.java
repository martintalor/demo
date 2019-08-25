package com.zhuyifan.scheduled;


import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ScheduledFuture;

/**
 * @author dzr
 * @version V1.0
 * @Package com.iflytek.dep.common.ftp
 * @Description:
 * @date 2019/2/7--10:00
 */
public class QuartzScheduler {


    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> scheduledFuture;

    private String cron = "";//事件表达式

    private Runnable runnable;//定时任务

    public QuartzScheduler(Runnable runnable, String cron, ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        super();
        this.runnable = runnable;
        this.cron = cron;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    public String getCron() {
        return cron;
    }

    /**
     * 停止定时任务
     */
    public void stop() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    /**
     * 设置时间表达式
     */
    public void start() {
        stop();
        scheduledFuture = threadPoolTaskScheduler.schedule(runnable, new QuartzTrigger(cron));
    }
}