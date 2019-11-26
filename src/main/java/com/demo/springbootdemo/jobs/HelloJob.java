package com.demo.springbootdemo.jobs;

import com.demo.springbootdemo.thread.TestThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 早起问候定时任务
 *
 * @author: guan.kai
 * @date: 2019/8/28 11:32
 **/
@Component
@Lazy(false)
@EnableScheduling
public class HelloJob {
    private final Logger logger = LoggerFactory.getLogger(HelloJob.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 每天早上8点跑
     */
    @Scheduled(cron = "0 0 8 * * ?")
    public void scheduled(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        String str = String.format("%s-早上好！", date);
        logger.error(str);
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void test(){
        Future<?> future = threadPoolTaskExecutor.submit(new TestThread("hhhh"));

        while (true) {
            if (future.isDone()){
                try {
                    String value = future.get().toString();
                    System.out.println(value);
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }
//        jdbcTemplate.queryForList("");

    }
}
