package kafka;

import com.google.gson.Gson;
import model.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;

public class ConsumeData<K,V> {
    private KafkaConsumer<K, V> consumer;
    private String topic;

    public ConsumeData(KafkaConsumer<K, V> consumer, String topic) {
        this.consumer = consumer;
        this.topic = topic;
    }
    public void consumeData(ConsumerDataListener<V> dataListener) throws Exception{
        while (true) {
            ConsumerRecords<K, V> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<K, V> record : records) {
                dataListener.receiveDataFromKafka(record.value());
            }
        }
    }
}
