package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
/**
 * A producer for sending data to kafka
 * @author Miloni Shah
 * @since August,2020
 */
public class ProduceData<K, V> {
    private KafkaProducer<K, V> producer;
    private String topic;

    public ProduceData(KafkaProducer<K, V> producer, String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    /* Producer call for sending data to Kafka topic*/
    public void sendData(K key, V value) throws Exception {

        ProducerRecord<K, V> producerRecord = new ProducerRecord<K, V>(
                topic, key, value);
        producer.send(producerRecord);
        producer.flush();
    }

}
