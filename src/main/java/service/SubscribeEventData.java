package service;

import com.google.gson.Gson;
import configuration.MasterProperty;
import kafka.ConsumeData;
import kafka.ConsumerDataListener;
import kafka.KafkaConfig;
import model.Commits;
import model.Event;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SubscribeEventData class is used to consume data from Kafka topic and
 * then it will divide the data into sliding window to apply different filters.
 *
 * @author Miloni Shah
 * @since August, 2020
 */
    public class SubscribeEventData {
    static Properties props = null;
    private static String bootstrapServer;
    private static String fromTopic;
    static int count = 0;

    public static void main(String[] args) throws Exception {

        try {
            props = MasterProperty.getServiceClassProperty(SubscribeEventData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bootstrapServer = props.getProperty("bootstrap_server");
        fromTopic = props.getProperty("from_topic");
        int windowSize = Integer.parseInt(props.getProperty("window_size"));
        List<Event> eventList = new ArrayList<>();

        /* Kafka Consumer and Sliding window */
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(KafkaConfig.getConsumerProperties(bootstrapServer));
        consumer.subscribe(Arrays.asList(fromTopic));

        ConsumeData<String, String> consumeData = new ConsumeData<>(consumer, fromTopic);
        consumeData.consumeData(
                new ConsumerDataListener<String>() {
                    @Override
                    public void receiveDataFromKafka(String data) {
                        count++;
                        Event event = new Gson().fromJson(data, Event.class);
                        System.out.println("--->> Event Data :: "+data);
                        if (eventList.size() < windowSize) {
                            eventList.add(event);
                        } else {
                            eventList.remove(0);
                            eventList.add(event);
                        }
                        if (count <= windowSize) {
                            System.out.println("Sliding Window From :: " + 1 + " To :: " + windowSize);
                        } else {
                            System.out.println("Sliding Window From :: " + (count - (windowSize - 1)) + " To :: " + count);
                        }
                        System.out.println("<=====Most common words=====>");

                        mostUsedWords(eventList);
                        commonHourOfDay(eventList);
                    }
                });

    }

    /* Most common 'hour of day' the commits took place */
    private static void commonHourOfDay(List<Event> eventList) {
        Map<String, Integer> wordCount = eventList.stream()
                //Map event Data to hour of day
                .map(new Function<Event, String>() {
                    @Override
                    public String apply(Event event) {
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
                })
                //check for null values
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return (s != null);
                    }
                })
                //key value pair of hours with it's count
                .collect(Collectors.groupingBy(w -> w,
                        Collectors.summingInt(w -> 1)));
        wordCount
                .entrySet()
                .stream()
                //sort data according to the count of hour
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .forEach(e -> System.out.println("Most common hour--->" + e.getKey()));
    }

    /* 5 most commonly used words in the commit messages */
    public static void mostUsedWords(List<Event> eventList) {

        Map<String, Integer> hourOfTheDay = eventList.stream()
                //find the event with commit message
                .filter(new Predicate<Event>() {
                    @Override
                    public boolean test(Event event) {
                        if (event.getPayload() != null) {
                            if (event.getPayload().getCommits() != null && event.getPayload().getCommits().size() > 0) {
                                return true;
                            }
                        }
                        return false;
                    }
                })
                //map data to string values and split each word in commit
                .flatMap(new Function<Event, Stream<String>>() {
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
                })
                //check for nulls and word length more than 2
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return (s != null && s.length() >= 2);
                    }
                }).map(String::toLowerCase)
                //group the string values with the occurrence count of that word
                .collect(Collectors.groupingBy(w -> w,
                        Collectors.summingInt(w -> 1)));

        hourOfTheDay
                .entrySet()
                .stream()
                //sort in alphabetical order
                .sorted(Map.Entry.comparingByKey())
                //sort according the occurrence count
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}
