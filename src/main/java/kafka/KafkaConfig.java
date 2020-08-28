package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class KafkaConfig {

    public static Properties getProducerProperties() {

        Properties producerConfig = new Properties();
        producerConfig.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.152.128:9092");
        producerConfig.put(StreamsConfig.CLIENT_ID_CONFIG, "client-id-12");
        producerConfig.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        producerConfig.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        producerConfig.put(ProducerConfig.ACKS_CONFIG, "0");
        producerConfig.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        return producerConfig;
    }

    public static Properties getConsumerProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,  "192.168.152.128:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "gid-1");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  "org.apache.kafka.common.serialization.StringDeserializer");

        return properties;
    }
}
