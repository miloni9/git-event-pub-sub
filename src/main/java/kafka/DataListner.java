package kafka;

import model.Event;

import java.util.List;

public interface DataListner {

    public void sendDataToKafka(List<Event> data);
}