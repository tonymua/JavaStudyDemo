package com.demo.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author:
 * @date: created in 20:01 2020/8/12
 * @version:
 */
@Component
public class SendMessage {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    //每5秒发送一次，cron(秒，分，时，月，星期，年)
    @Scheduled(cron = "*/5 * * * * ? ")
    @Async
    public void sendMessage1(){
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("test1", "第" + i + "条message111,请注意接收"+Thread.currentThread().getName());
        }
        System.out.println("发送messageTest111消息成功！");
    }

    @Scheduled(cron = "*/10 * * * * ?")
    @Async
    public void sendMessage2(){
        for (int i = 0; i < 20; i++) {
            kafkaTemplate.send("test2", "第" + i + "条messag222,请注意接收"+Thread.currentThread().getName());
        }
        System.out.println("发送messageTest222消息成功！");
    }

    @Scheduled(cron = "*/5 * * * * ?")
    @Async
    public void sendMessge3() {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("test3", "第" + i + "条message333,请注意接收"+Thread.currentThread().getName());
        }
        System.out.println("发送messageTest333消息成功！");
    }
}