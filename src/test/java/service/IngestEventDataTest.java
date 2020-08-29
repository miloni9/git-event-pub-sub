package service;

import com.google.gson.Gson;
import configuration.Constants;
import kafka.KafkaConfig;
import model.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import serializer.JsonSerializer;

@Tag("IngestEventDataTest")
@DisplayName("IngestEventDataTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngestEventDataTest {

    IngestEventData ingestEventData;

    private Event getEventData() {

        String string = "{\"actor\":{\"id\":66008870,\"login\":\"tidorisan\",\"display_login\":\"tidorisan\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/tidorisan\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/66008870?\"},\"public\":true,\"payload\":{\"issue\":null,\"action\":null,\"comment\":null,\"push_id\":5603629066,\"size\":2,\"distinct_size\":1,\"ref\":\"refs/heads/develop\",\"head\":\"85bb86e72466adbe782a6bb73d6fd37f498256a3\",\"before\":\"c4cb8bae3eb862b1db773636acce1a3e863a31cf\",\"commits\":[{\"sha\":\"f8a49f1b7fda117a215d7134637672eaad3ebd59\",\"author\":{\"email\":\"vagrant@localhost.localdomain\",\"name\":\"vagrant\"},\"message\":\"[create]_coupons/index/new/edit/show\",\"distinct\":false,\"url\":\"https://api.github.com/repos/tidorisan/TIdori_sushi_app/commits/f8a49f1b7fda117a215d7134637672eaad3ebd59\"},{\"sha\":\"85bb86e72466adbe782a6bb73d6fd37f498256a3\",\"author\":{\"email\":\"66008870+tidorisan@users.noreply.github.com\",\"name\":\"tidorisan\"},\"message\":\"Merge pull request #7 from tidorisan/feature_coupon_index/new/show/edit\\n\\n[create]_coupons/index/new/edit/show\",\"distinct\":true,\"url\":\"https://api.github.com/repos/tidorisan/TIdori_sushi_app/commits/85bb86e72466adbe782a6bb73d6fd37f498256a3\"}]},\"repo\":{\"id\":290931874,\"name\":\"tidorisan/TIdori_sushi_app\",\"url\":\"https://api.github.com/repos/tidorisan/TIdori_sushi_app\"},\"created_at\":\"2020-08-29T08:24:35Z\",\"id\":13346508506,\"type\":\"PushEvent\"}\n" +
                "--->> Input Event Data:: {\"actor\":{\"id\":64793534,\"login\":\"conda-forge-daemon\",\"display_login\":\"conda-forge-daemon\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/conda-forge-daemon\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/64793534?\"},\"public\":true,\"payload\":{\"issue\":null,\"action\":\"published\",\"comment\":null,\"push_id\":0,\"size\":0,\"distinct_size\":0,\"ref\":null,\"head\":null,\"before\":null,\"commits\":null},\"repo\":{\"id\":288431736,\"name\":\"regro/releases\",\"url\":\"https://api.github.com/repos/regro/releases\"},\"created_at\":\"2020-08-29T08:24:35Z\",\"id\":13346508507,\"type\":\"ReleaseEvent\"}";

        return new Gson().fromJson(string, Event.class);
    }

    private KafkaProducer<String, Event> kafkaProducer() {
        return new KafkaProducer<String, Event>(KafkaConfig.getProducerProperties(Constants.SERVERS), new JsonSerializer<String>(), new JsonSerializer<Event>());

    }
}
