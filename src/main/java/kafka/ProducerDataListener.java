package kafka;

/**
 * ProducerDataListener is used for producer callback option.
 */
public interface ProducerDataListener<T> {

    void sendDataToKafka(T data);
}