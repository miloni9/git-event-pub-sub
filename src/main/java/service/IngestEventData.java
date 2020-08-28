package service;

import configuration.MasterProperty;
import kafka.DataListner;
import kafka.KafkaConfig;
import model.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import rest.APICall;
import serializer.GsonUtil;
import serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class IngestEventData {
    static Properties props = null;
    static DataListner dataListner;
    private static KafkaProducer<String, Event> producerEvent = null;

    public static void main(String[] args) {

        try {
            props = MasterProperty.getServiceClassProperty(IngestEventData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producerEvent = new KafkaProducer<String, Event>(KafkaConfig.getProducerProperties(), new JsonSerializer<String>(), new JsonSerializer<Event>());

        dataListner = new DataListner() {
            @Override
            public void sendDataToKafka(List<Event> eventList) {

                for (Event event : eventList) {

                    System.out.println("<<<---" + GsonUtil.getGson().toJson(event));

                    ProducerRecord<String, Event> producerRecord = new ProducerRecord<String, Event>(
                            "git-event", String.valueOf(event.getId()), event);
                    producerEvent.send(producerRecord);
                    producerEvent.flush();
                }
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        params.put("per_page", "50");
        APICall.httpGet("https://api.github.com/events", params, dataListner);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("------------------------------------- In Shutdown Hook  --------------------------------------------");
            }
        }));


    }
}
