package config;

import java.time.Duration;

/**
 * @author:
 * @date: created in 15:24 2020/8/3
 * @version:
 */
public class KafkaConfig {
    public static final String ADDRESS="xxxx:9092";
    public static final String PRODUCER_TOPIC="Hello-Kafka";
    public static final String CONSUMER_TOPIC="Hello-Kafka";
    public static final String CONSUMER_GROUP_ID = "1";								//groupId，可以分开配置
    public static final String CONSUMER_ENABLE_AUTO_COMMIT = "true";				//是否自动提交（消费者）
    public static final String CONSUMER_AUTO_COMMIT_INTERVAL_MS = "1000";
    public static final String CONSUMER_SESSION_TIMEOUT_MS = "30000";				//连接超时时间
    public static final int CONSUMER_MAX_POLL_RECORDS = 10;							//每次拉取数
    public static final Duration CONSUMER_POLL_TIME_OUT = Duration.ofMillis(3000);	//拉去数据超时时间
}