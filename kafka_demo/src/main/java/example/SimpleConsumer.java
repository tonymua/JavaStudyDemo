package example;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import config.KafkaConfig;

/**
 * @author:
 * @date: created in 18:04 2020/8/3
 * @version:
 */
public class SimpleConsumer {
//    private Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

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

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(initConfig());
        consumer.subscribe(Arrays.asList(KafkaConfig.CONSUMER_TOPIC));
        while (true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(KafkaConfig.CONSUMER_POLL_TIME_OUT);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println("key: "+consumerRecord.key()+" value: "+consumerRecord.value()+" topic: "+consumerRecord.topic());
            }
        }

    }
}