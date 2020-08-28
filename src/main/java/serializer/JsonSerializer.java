package serializer;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Suggest not to use Directly, Use {@link Serializer} class instead
 *
 * @param <T> Serializing model Class
 * @author Miloni Shah
 */
public class JsonSerializer<T> implements Serializer<T> {

    private Gson gson = GsonUtil.getGson();

    public void configure(Map<String, ?> map, boolean b) {
    }

    public byte[] serialize(String topic, T t) {
        return gson.toJson(t).getBytes(Charset.forName("UTF-8"));
    }

    public void close() {
    }
}

