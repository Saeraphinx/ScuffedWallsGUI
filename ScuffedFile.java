import java.io.*;
import java.util.*;

public class ScuffedFile {
    private File SWFile;

    public ScuffedFile(String diffString) {
        SWFile = new File(diffString);
    }

    public ArrayList<ScuffedEvent> importDiff() {
        ArrayList<ScuffedEvent> eventList = new ArrayList<ScuffedEvent>();
        Scanner in;
        try {
            in = new Scanner(SWFile);
        } catch (Exception e) {
            System.out.print("Well you done goofed");
            return new ArrayList<ScuffedEvent>();
        }

        int currentEventCount = -1;
        while(in.hasNext()) {
            String currentLine = in.nextLine();
            if (currentLine.equals("") || currentLine.substring(0,1).equals("#")) {
                continue;
            }
            
            if(currentLine.substring(0,1).matches("\\d+")) { // read Event Header
                eventList.add(new ScuffedEvent(readBeat(currentLine), readType(currentLine)));
                currentEventCount++;
                continue;
            }

            if (currentLine.substring(0,1).equals(" ")) { // read eventData
                ScuffedEvent temp = eventList.get(currentEventCount);
                temp.addScuffedEventData(readDataType(currentLine), readDataData(currentLine));
                continue;
            }
        
        }

        in.close();
        return eventList;
    }

    private double readBeat(String line) {
        String temp = line;
        temp = temp.trim();
        return Double.parseDouble(temp.substring(0,temp.indexOf(":")));
    }

    private String readType(String line) {
        String temp = line;
        temp = temp.trim();
        int check = temp.indexOf("#");
        if(check != -1) {
            temp = temp.substring(0,check);
        }
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
        temp = temp.substring(temp.indexOf(":")+1);
        int check = temp.indexOf("#");
        if(check != -1) {
            temp = temp.substring(0,check);
        }
        return temp;
    }

}
