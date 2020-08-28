package serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    public static Gson getGson() {
        return new GsonBuilder().serializeSpecialFloatingPointValues().setDateFormat("dd-MM-yyyy HH:mm:ss").serializeNulls().disableHtmlEscaping().create();
    }
}
