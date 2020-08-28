package serializer;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

/**
 * @param <T> Serializing Class
 * @author Niraj Sanghani
 */
public class Serializer<T> {

    /**
     * @param tClass Serializing Class
     * @param <T>
     * @return Serde<T> serialized class
     * @apiNote Get serde for Serializing class
     */
    public static <T> Serde<T> getSerde(Class<T> tClass) {
        return new Serializer<T>().getSerializer(tClass);
    }

    private Serde<T> getSerializer(Class<T> tClass) {

        JsonSerializer<T> jsonSerializer = new JsonSerializer<T>();
        JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<T>(tClass);
        return Serdes.serdeFrom(jsonSerializer, jsonDeserializer);
    }
}
