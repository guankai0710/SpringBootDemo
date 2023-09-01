package com.demo.springbootdemo.jobs;

import com.demo.springbootdemo.thread.TestThread;
import com.demo.springbootdemo.utils.CollectorsUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

//    @Autowired
//    private RedisUtil redisUtil;

//    @Autowired
//    private Connection rabbitMqConnection;

//    private ThreadPoolExecutor executor = null;
//
//    @PostConstruct
//    public void initThreadPool() {
//        this.executor = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.AbortPolicy());
//        logger.info("线程池初始化完成！");
//    }

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

    @Scheduled(cron = "0/10 * 8 * * ?")
    public void testThread(){
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
    }

//    @PreDestroy
//    public void shutDown() {
//        if (executor != null) {
//            executor.shutdown();
//            logger.info("线程池关闭！");
//        }
//    }

    public static void main(String[] args) {
        BigDecimal start1 = new BigDecimal("944.02");
        BigDecimal start2 = new BigDecimal("944.02");
        List<String> list1 = Lists.newArrayList("117.10");
        List<String> list2 = Lists.newArrayList("14.65","66.83","-29.30","11.48","23.40","9.39","9.00","29.80");
        List<String> list3 = Lists.newArrayList("7.65","5.29","-2.29","3.38","-9.37","26.38","-8.28","-5.74","11.85","12.39","-81.25","-19.83","8.43","24.53","37.45","22.97","-6.20","91.60");
        List<String> list4 = Lists.newArrayList("17.01","-2.68","1.91","39.82","-39.25","0.86","32.42","33.35","110.04","8.27","-27.80","17.85","27.05");
        List<String> list5 = Lists.newArrayList("13.70","19.88","0.19","-0.45","29.73","1.08");
        String sum1 = list1.stream().collect(CollectorsUtils.summingBigDecimal(v -> new BigDecimal(v))).toPlainString();
        String sum2 = list2.stream().collect(CollectorsUtils.summingBigDecimal(v -> new BigDecimal(v))).toPlainString();
        String sum3 = list3.stream().collect(CollectorsUtils.summingBigDecimal(v -> new BigDecimal(v))).toPlainString();
        String sum4 = list4.stream().collect(CollectorsUtils.summingBigDecimal(v -> new BigDecimal(v))).toPlainString();
        String sum5 = list5.stream().collect(CollectorsUtils.summingBigDecimal(v -> new BigDecimal(v))).toPlainString();
        ArrayList<String> arrayList = Lists.newArrayList(sum1, sum2, sum3, sum4, sum5);

        List<String> result1 = Lists.newArrayList(start2.toPlainString());
        for (String v : arrayList) {
            BigDecimal bigDecimal = new BigDecimal(v);
            BigDecimal add = start2.add(bigDecimal);
            result1.add(add.toPlainString());
            start2 = add;
        }

        List<String> list = Lists.newArrayList();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        list.addAll(list4);
        list.addAll(list5);

        List<String> result2 = Lists.newArrayList(start1.toPlainString());
        for (String v : list) {
            BigDecimal bigDecimal = new BigDecimal(v);
            BigDecimal add = start1.add(bigDecimal);
            result2.add(add.toPlainString());
            start1 = add;
        }
        System.out.println(arrayList.toString());
        System.out.println(result1.toString());
        System.out.println(result2.toString());

    }
}
