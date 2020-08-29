package kafka;

/**
 * ConsumerDataListener is used for consumer callback option.
 */
public interface ConsumerDataListener<T> {

    void receiveDataFromKafka(T data);
}