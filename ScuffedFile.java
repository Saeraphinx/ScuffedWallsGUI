import java.io.*;
import java.util.*;

public class ScuffedFile {
    private File SWFile;

    public ScuffedFile(String diffString) {
        SWFile = new File(diffString);
    }

    public void importDiff() {
        ArrayList<ScuffedEvent> eventList = new ArrayList<ScuffedEvent>();

        Scanner in = new Scanner(SWFile);

        int currentEventCount = -1;
        while(in.hasNextLine()) {
            /*
             *  TODO:
             *   Deal with empty lines
             *   Deal with Hashtags
             */ 
            String currentLine = in.nextLine(); 
            if(currentLine.substring(0,2).equals("\"")) {
                continue;
            }

            if(currentLine.substring(0,1).matches("\\d+")) {
                eventList.add(new ScuffedEvent(readBeat(currentLine), readType(currentLine)));
                currentEventCount++;
            }

            if (currentLine.substring(0,1).equals(" ")) {
                eventList.get(currentEventCount).addScuffedEventData(readDataType(currentLine), readDataData(currentLine));
            }

        }
    }

    private int readBeat(String line) {
        String temp = line;
        temp = temp.trim();
        return Integer.parseInt(temp.substring(0,temp.indexOf(":")));
    }

    private String readType(String line) {
        String temp = line;
        temp = temp.trim();
        // Deal with hashtags
        return temp.substring(temp.indexOf(":")+1);
    }

    private String readDataType(String line) {
        String temp = line;
        temp = temp.trim();
        return temp.substring(0,temp.indexOf(":"));
    }

    private String readDataData(String line) {
        String temp = line;
        temp = temp.trim();
        //deal with hastags
        return temp.substring(temp.indexOf(":")+1);
    }

}
