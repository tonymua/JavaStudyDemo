package com.demo.listener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.util.RedisUtil;

/**
 * @author:
 * @date: created in 9:13 2020/8/13
 * @version:
 */
@Component
public class ConsumeMessageListener {
    @Autowired
    private RedisUtil redisUtil;
    private static final Logger logger = LoggerFactory.getLogger(ConsumeMessageListener.class);

    @KafkaListener(topics = {"test1", "test2", "test3"}, containerFactory = "batchFactory")
    public void consumer(List<ConsumerRecord> records) {
        try {
            if (!redisUtil.hasKey("topic")) {
                //设置过期时间，需大于定时任务执行时间
                redisUtil.set("topic", "0", 150);
            }
        } catch (Exception e) {
            System.out.println("redis设置出错");
        }

        for (ConsumerRecord record : records) {
            //自增加1
            try {
                redisUtil.incr("topic", 1);
            } catch (Exception e) {
                System.out.println("redis自增出错");
            }
            //do something
            System.out.println("接收消息成功");
            System.out.println("业务代码");
            logger.info("topic is {}, offset is {}, value is {},Thread is {}", record.topic(), record.offset(), record.value(),Thread.currentThread().getName());
        }

    }


    //定时任务每天8点到18点每2分钟统计一次TPS（流量）
    @Scheduled(cron = "0 */2 8-18 * * ?")
    public void TPSCount() {
        try {
            if (redisUtil.hasKey("topic")) {
                String topic = (String) redisUtil.get("topic");
                //重置redis (k,v=0)
                redisUtil.set("topic", "0", 150);
                long topicNum = Long.parseLong(topic);
                long tps = topicNum / 120;
                System.out.println("topic的个数 = " + topicNum + "; 120秒内的tps = " + tps + "; 时间为 = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                //预警 方案一:根据tps的值进行判断;方案二:将数据插入数据库处理

            } else {
                logger.error("未设置redis的key");
                System.out.println("未设置redis的key");
            }
        } catch (Exception e) {
            logger.error("redis未启动或运行异常");
            System.out.println("redis未启动或运行异常");
        }
    }
}