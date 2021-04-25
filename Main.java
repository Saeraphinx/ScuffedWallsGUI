import java.awt.*;
import javax.swing.*;
import java.io.*; 

public class Main {
    public static void main(String[] args) {
        Window main = new Window();
        testing();
    }

    public static void testing() {
        ScuffedFile test = new ScuffedFile("C:\\Users\\tmodd\\Documents\\GitHub\\ScuffedWallsGUI\\test.sw");
        test.importDiff();
    }
}