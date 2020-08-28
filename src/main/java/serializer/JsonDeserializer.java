package serializer;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * Suggest not to use directly, use {@link Serializer} class instead
 *
 * @param <T> De-serializing Class
 */
public class JsonDeserializer<T> implements Deserializer<T> {

    private Gson gson = GsonUtil.getGson();
    private Class<T> deserializedClass;

    public JsonDeserializer(Class<T> deserializedClass) {
        this.deserializedClass = deserializedClass;
    }

    public JsonDeserializer() {
    }

    @SuppressWarnings("unchecked")
    public void configure(Map<String, ?> map, boolean b) {
//        if (deserializedClass == null) {
//            deserializedClass = (Class<T>) map.get("value.deserializer");
//        }
    }

    public T deserialize(String s, byte[] bytes) {
            return gson.fromJson(new String(bytes), deserializedClass);
    }

    public void close() {
    }

}

