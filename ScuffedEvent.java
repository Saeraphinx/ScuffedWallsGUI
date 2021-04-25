import java.util.ArrayList;

public class ScuffedEvent {
    private double beat;
    private String eventType;
    private ArrayList<String[]> eventData;

    public ScuffedEvent(double beat, String eventType) {
        this.beat = beat;
        this.eventType = eventType;
        eventData = new ArrayList<String[]>();
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

    public double getBeat() {
        return beat;
    }

    public String getEventType() {
        return eventType;
    }

    public ArrayList<String[]> getEventData() {
        return eventData;
    }
}
