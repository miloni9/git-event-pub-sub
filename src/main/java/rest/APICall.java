package rest;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kafka.DataListner;
import lombok.SneakyThrows;
import model.Event;
import okhttp3.*;
import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class APICall {

    @SneakyThrows
    public static void httpGet(String URL, Map<String, String> params, DataListner dataListner) {
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
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                    String jsonArray = response.body().string();
/*

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
*/
                    Type listType = new TypeToken<List<Event>>() {}.getType();
                    List<Event> eventList = new Gson().fromJson((jsonArray),listType);
                  /*  for (int i = 0; i < jsonArray.length(); i++) {

                        String json = new Gson().toJson(jsonArray.get(i));
                        System.out.println(json);
                        Event event = new Gson().fromJson(json, Event.class);
                        eventList.add(event);
                    }*/
                    dataListner.sendDataToKafka(eventList);
//                    System.out.println(responseBody.string());
                }
            }
        });

    }

}
