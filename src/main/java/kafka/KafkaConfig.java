package kafka;

import configuration.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;
import java.util.Random;

/**
 * KafkaConfig class will help to create the object for configuration of kafka Consumer and producer.
 */
public class KafkaConfig implements Constants {

    public static Properties getProducerProperties(String bootstrapServer) {

        Properties producerConfig = new Properties();
        producerConfig.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        producerConfig.put(StreamsConfig.CLIENT_ID_CONFIG, CLIENT_ID + new Random().nextInt(10000));
        producerConfig.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        producerConfig.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        producerConfig.put(ProducerConfig.ACKS_CONFIG, ACKS_CONFIG);
        producerConfig.put(ProducerConfig.LINGER_MS_CONFIG, LINGER_MS_CONFIG);
        return producerConfig;
    }

    public static Properties getConsumerProperties(String bootstrapServer) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ENABLE_AUTO_COMMIT_CONFIG);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, AUTO_COMMIT_INTERVAL_MS_CONFIG);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, SESSION_TIMEOUT_MS_CONFIG);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET_CONFIG);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KEY_DESERIALIZER_CLASS_CONFIG);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VALUE_DESERIALIZER_CLASS_CONFIG);

        return properties;
    }
}
