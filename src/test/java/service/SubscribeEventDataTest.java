package service;

import com.google.gson.Gson;
import configuration.Constants;
import kafka.KafkaConfig;
import model.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class SubscribeEventDataTest {

    SubscribeEventData subscribeEventData;



    private List<Event> getEventData() {

        List<Event> eventList=new ArrayList<>();
        String string ="{\"actor\":{\"id\":57368351,\"login\":\"luizalbertocviana\",\"display_login\":\"luizalbertocviana\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/luizalbertocviana\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/57368351?\"},\"public\":true,\"payload\":{\"issue\":null,\"action\":null,\"comment\":null,\"push_id\":5604016741,\"size\":1,\"distinct_size\":1,\"ref\":\"refs/heads/master\",\"head\":\"2cdc5e7233a536d581d34af0741eeba3b38c2c07\",\"before\":\"9d4dae8db9d4f14958e474250e4d8c07feedc5cf\",\"commits\":[{\"sha\":\"2cdc5e7233a536d581d34af0741eeba3b38c2c07\",\"author\":{\"email\":\"luizalbertocviana@gmail.com\",\"name\":\"Luiz Alberto\"},\"message\":\"removed default_value_type since it is used only once\",\"distinct\":true,\"url\":\"https://api.github.com/repos/luizalbertocviana/data-structures-cpp/commits/2cdc5e7233a536d581d34af0741eeba3b38c2c07\"}]},\"repo\":{\"id\":241204231,\"name\":\"luizalbertocviana/data-structures-cpp\",\"url\":\"https://api.github.com/repos/luizalbertocviana/data-structures-cpp\"},\"created_at\":\"2020-08-29T11:57:13Z\",\"id\":13347138098,\"type\":\"PushEvent\"}";
        eventList.add(new Gson().fromJson(string, Event.class));
        eventList.add(new Gson().fromJson(string, Event.class));
        eventList.add(new Gson().fromJson(string, Event.class));
        string="{\"actor\":{\"id\":51347104,\"login\":\"ghkddudrms\",\"display_login\":\"ghkddudrms\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/ghkddudrms\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/51347104?\"},\"public\":true,\"payload\":{\"issue\":null,\"action\":null,\"comment\":null,\"push_id\":5604016754,\"size\":1,\"distinct_size\":1,\"ref\":\"refs/heads/master\",\"head\":\"1703524924782b22fdf3cda054ba78e78c207426\",\"before\":\"267f571e9457394f2536f3057bbf904a4b3605f9\",\"commits\":[{\"sha\":\"1703524924782b22fdf3cda054ba78e78c207426\",\"author\":{\"email\":\"51347104+ghkddudrms@users.noreply.github.com\",\"name\":\"ghkddudrms\"},\"message\":\"updated kill_list.ini\",\"distinct\":true,\"url\":\"https://api.github.com/repos/ghkddudrms/botamdb/commits/1703524924782b22fdf3cda054ba78e78c207426\"}]},\"repo\":{\"id\":194986389,\"name\":\"ghkddudrms/botamdb\",\"url\":\"https://api.github.com/repos/ghkddudrms/botamdb\"},\"created_at\":\"2020-08-29T11:57:14Z\",\"id\":13347138133,\"type\":\"PushEvent\"}";
        eventList.add(new Gson().fromJson(string, Event.class));
        eventList.add(new Gson().fromJson(string, Event.class));
        eventList.add(new Gson().fromJson(string, Event.class));
        return eventList;
    }

}
