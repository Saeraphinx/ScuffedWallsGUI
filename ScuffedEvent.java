import java.util.ArrayList;

public class ScuffedEvent {
    private int beat;
    private String eventType;
    private ArrayList<String[]> eventData;

    public ScuffedEvent(int beat, String eventType) {
        this.beat = beat;
        this.eventType = eventType;
    }

    public void setScuffedEventHeader(int beat, String eventType) {
        this.beat = beat;
        this.eventType = eventType;
    }

    public void setScuffedEventData(ArrayList<String[]> eventData) {
        this.eventData = eventData;
    }

    public void addScuffedEventData(String type, String data) {
        String[] tempData = {type, data};
        eventData.add(tempData);
    }
}
