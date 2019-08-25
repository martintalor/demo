package com.zhuyifan.threadpool;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Scope("prototype")
public class FilePackSection   extends BaseSectionThreadPool {
    private static ExecutorService fixedUpStreamThreadPool;
    public static AtomicInteger threadJobSize = new AtomicInteger(0);

    private final static Logger logger = LoggerFactory.getLogger(FilePackSection.class);
    public FilePackSection() {
        synchronized (logger){
            if(fixedUpStreamThreadPool == null){
                fixedUpStreamThreadPool =  Executors.newFixedThreadPool(threadNumber,
                        new FilePackSection.DepThreadFactory("FilePackSection"));
            }
        }
    }


    public void doThreadAct() throws Exception {
    	threadJobSize.incrementAndGet();
        fixedUpStreamThreadPool.submit(new Runnable() {

            public void run() {
            	/*dosomthing*/
            	threadJobSize.decrementAndGet();
            }});

    }
 
}
