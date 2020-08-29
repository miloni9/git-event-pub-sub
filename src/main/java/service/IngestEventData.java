package service;

import configuration.MasterProperty;
import kafka.ProducerDataListener;
import kafka.KafkaConfig;
import kafka.ProduceData;
import model.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import rest.APICall;
import serializer.GsonUtil;
import serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * IngestEventData Service is Developed to get the Data of git public events and
 * pass that data to kafka producer.
 * <p>
 * The service is Scheduler based. It will be performing same service after 15 minutes.
 * <p>
 * All configuration for API URL and Kafka Server is managed using the file management
 * for the ease of updating the configuration runtime.
 *
 * @author Miloni Shah
 * @since Aug, 2020
 */
public class IngestEventData {
    static Properties props = null;

    private static String url;
    private static String page;
    private static String perPage;
    private static String bootstrapServer;
    private static String toTopic;

    public static void main(String[] args) {

        try {
            props = MasterProperty.getServiceClassProperty(IngestEventData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = props.getProperty("APIURL");
        page = props.getProperty("page");
        perPage = props.getProperty("per_page");
        bootstrapServer = props.getProperty("bootstrap_server");
        toTopic = props.getProperty("toTopic");

        Map<String, String> params = new HashMap<>();
        params.put("page", page);
        params.put("per_page", perPage);



        KafkaProducer<String, Event> producerEvent = new KafkaProducer<String, Event>(KafkaConfig.getProducerProperties(bootstrapServer), new JsonSerializer<String>(), new JsonSerializer<Event>());

        ProduceData<String, Event> produceData = new ProduceData<>(producerEvent, toTopic);

        APICall apiCall = new APICall().setURL(url).setParams(params).setDataListener(new ProducerDataListener<List<Event>>() {
            @Override
            public void sendDataToKafka(List<Event> eventList) {
                for (Event event : eventList) {
                    System.out.println("--->> Event Data:: " + GsonUtil.getGson().toJson(event));
                    try {
                        produceData.sendData(String.valueOf(event.getId()), event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                try {
                    apiCall.httpGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, 0, 10, TimeUnit.MINUTES);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("------------------------------------- In Shutdown Hook  --------------------------------------------");
            }
        }));


    }

}
