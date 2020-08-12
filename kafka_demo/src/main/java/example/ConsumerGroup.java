package example;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import config.KafkaConfig;

/**
 * @author:
 * @date: created in 19:14 2020/8/3
 * @version:
 */
public class ConsumerGroup {
    private ExecutorService executorService;
    private final KafkaConsumer<String, String> consumer;

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KafkaConfig.ADDRESS);
        properties.put("group.id", KafkaConfig.CONSUMER_GROUP_ID);
        properties.put("enable.auto.commit", KafkaConfig.CONSUMER_ENABLE_AUTO_COMMIT);
        properties.put("auto.commit.interval.ms", KafkaConfig.CONSUMER_AUTO_COMMIT_INTERVAL_MS);
        properties.put("session.timeout.ms", KafkaConfig.CONSUMER_SESSION_TIMEOUT_MS);
        properties.put("max.poll.records", KafkaConfig.CONSUMER_MAX_POLL_RECORDS);
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        return properties;
    }

    //构造方法，初始化实例
    public ConsumerGroup() {
        this.consumer = new KafkaConsumer<>(initConfig());// 初始化配置
        this.consumer.subscribe(Arrays.asList(KafkaConfig.CONSUMER_TOPIC)); // 订阅topic=test
    }

    // 创建线程池
    public void init(int numberOfThreads) {
        executorService = new ThreadPoolExecutor(numberOfThreads, numberOfThreads * 2, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());
        while (true) {
            executorService.submit(new ConsumerThread());
        }
    }

    // 关闭线程池
    public void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    private class ConsumerThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(KafkaConfig.CONSUMER_POLL_TIME_OUT);// 每隔时间段进行消息拉取
                for (final ConsumerRecord<String, String> record : records) {
                    System.out.println("ThreadName: " + Thread.currentThread().getName() + " key: " + record.key()
                        + " value: " + record.value() + " topic: " + record.topic());
                }
            }

        }
    }

    public static void main(String[] args) {
        ConsumerGroup consumerGroup = new ConsumerGroup();
        //创建线程池，传入核心线程数
        consumerGroup.init(10);
        //关闭线程池
        consumerGroup.shutdown();
    }

}