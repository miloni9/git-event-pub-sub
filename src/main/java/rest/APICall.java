package rest;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kafka.ProducerDataListener;
import lombok.SneakyThrows;
import model.Event;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * APICall class is used to call any API and pass the received data to kafka.
 * Added method for get call
 *
 * @author Miloni shah
 * @since Aug, 2020
 */
public class APICall {
    String URL;
    Map<String, String> params;
    ProducerDataListener dataListener;

    public APICall setURL(String URL) {
        this.URL = URL;
        return this;
    }

    public APICall setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public APICall setDataListener(ProducerDataListener dataListener) {
        this.dataListener = dataListener;
        return this;
    }

    public APICall() {
    }

    @SneakyThrows
    public void httpGet() throws Exception {
        final OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(URL).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .get()
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String jsonArray = response.body().string();

                Type listType = new TypeToken<List<Event>>() {
                }.getType();
                List<Event> eventList = new Gson().fromJson((jsonArray), listType);

                if (eventList != null && eventList.size() > 0) {
                    dataListener.sendDataToKafka(eventList);
                }
            }

        });

    }

}
