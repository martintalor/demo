package com.zhuyifan.scheduled;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.zhuyifan.util.PropertiesUtils;

import java.lang.reflect.Constructor;

/**
 * @author 朱一帆
 * @version V1.0
 * @Package com.iflytek.dep.server.service.impl
 * @Description:
 * @date 2019/6/4--22:24
 */
@Primary
@Service("AckReteyScheduledService")
public class AckRetryServerScheduledImpl implements AckRetryServerScheduled {
   // private Logger logger = LoggerFactory.getLogger(AckRetryServerScheduledImpl.class);
    @Autowired
    PropertiesUtils propertiesUtil;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Override
    public boolean start() {
        String schedulerClass = "com.iflytek.dep.server.scheduled.AckRetryThread";
        String cron = "0 30 20 * * ?"; //每晚八点半的定时任务
        //propertiesUtil.getPropertiesValue("cron.dep.server.beat")
        try {
            Class c = Class.forName(schedulerClass);
            Constructor constructor = c.getConstructor();
            Runnable runnable = (Runnable) constructor.newInstance();
            QuartzScheduler quartzScheduler = ScheduledFutureFactory.getQuartzScheduler(schedulerClass);
            if (quartzScheduler == null) {
                quartzScheduler = ScheduledFutureFactory.createQuartzScheduler(schedulerClass, runnable,cron ,
                        threadPoolTaskScheduler);
            }
            quartzScheduler.start();
            //logger.info("\n开启ack重探定时任务 schedulerClass: {}  成功", schedulerClass);
            return true;
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            return false;
        }

    }

    @Override
    public boolean close() {

        //关闭定时任务
        String schedulerClass = "com.iflytek.dep.server.scheduled.AckRetryThread";
        String cron = "0 30 20 * * ?"; //每晚八点半的定时任务
        //propertiesUtil.getPropertiesValue("cron.dep.server.beat")
        try {
            Class c = Class.forName(schedulerClass);
            Constructor constructor = c.getConstructor();
            Runnable runnable = (Runnable) constructor.newInstance();
            QuartzScheduler quartzScheduler = ScheduledFutureFactory.getQuartzScheduler(schedulerClass);
            if (quartzScheduler == null) {
                quartzScheduler = ScheduledFutureFactory.createQuartzScheduler(schedulerClass, runnable,cron ,
                        threadPoolTaskScheduler);
            }
            quartzScheduler.stop();
            //logger.info("\n关闭ack重探定时任务 schedulerClass: {}  成功", schedulerClass);
            return true;
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
            return false;
        }
    }
}
