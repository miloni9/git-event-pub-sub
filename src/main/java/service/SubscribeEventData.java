package service;

import com.google.gson.Gson;
import kafka.KafkaConfig;
import model.Commits;
import model.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubscribeEventData {


    public static void main(String[] args) {
        consumer();
    }

    public static void consumer() {
        List<Event> eventList = new ArrayList<>();

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(KafkaConfig.getConsumerProperties());
        consumer.subscribe(Arrays.asList("git-event"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                Event event = new Gson().fromJson(record.value(), Event.class);
                System.out.println(record.value());
                if (eventList.size() < 10) {
                    eventList.add(event);
                } else {
                    eventList.remove(0);
                    eventList.add(event);
                    wc(eventList);
                    hod(eventList);
                }
            }
        }
    }

    private static void hod(List<Event> eventList) {
        Map<String, Integer> wc = eventList.stream().map(new Function<Event, String>() {
            @Override
            public String apply(Event event) {
//                2020-08-27T05:10:33Z
                SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                Date date = null;
                try {
                    date = input.parse(event.getCreatedAt());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                calendar.setTime(date);

                return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return (s != null);
            }
        }).collect(Collectors.groupingBy(w -> w,
                Collectors.summingInt(w -> 1)));
        wc.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .forEach(e -> System.out.println("Most common hour===>"+e.getKey() + ": " + e.getValue()));
    }

    public static void wc(List<Event> eventList) {

        Map<String, Integer> wc = eventList.stream().filter(new Predicate<Event>() {
            @Override
            public boolean test(Event event) {
                if (event.getPayload() != null) {
                    if (event.getPayload().getCommits() != null && event.getPayload().getCommits().size() > 0) {
                        return true;
                    }
                }
                return false;
            }
        }).flatMap(new Function<Event, Stream<String>>() {
            @Override
            public Stream<String> apply(Event event) {
                List<String> stringList = new ArrayList<>();
                event.getPayload().getCommits().forEach(new Consumer<Commits>() {
                    @Override
                    public void accept(Commits commits) {
                        stringList.addAll(Arrays.asList(commits.getMessage().split(" ")));
                    }
                });
                return stringList.stream();
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return (s != null && s.length() >= 2);
            }
        }).map(String::toLowerCase)
                .collect(Collectors.groupingBy(w -> w,
                        Collectors.summingInt(w -> 1)));
        wc.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(e -> System.out.println("Most common words===>"+e.getKey() + ": " + e.getValue()));
    }
}
