package example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import config.KafkaConfig;

/**
 * @author:
 * @date: created in 15:27 2020/8/3
 * @version:
 */
public class SimpleProducer {
    private Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KafkaConfig.ADDRESS);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        return properties;

    }

    public static void main(String[] args) {
        Producer<String, String> producer = new KafkaProducer(initConfig());
        for (int i = 0; i < 200; i++) {
            producer.send(new ProducerRecord<String, String>(KafkaConfig.PRODUCER_TOPIC, Integer.toString(i),
                Integer.toString(i)));
            System.out.println("Message send successfully!");
        }
        producer.close();

    }
}