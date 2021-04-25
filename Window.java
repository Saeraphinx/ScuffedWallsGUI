import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import filters.*;

public class Window extends JFrame implements ActionListener {
    private static JFrame frame;
    private JMenuBar selectFileMenuBar;
    private JMenuItem i1diff;
    private JMenuItem i2exe;
    private final JFileChooser fc = new JFileChooser();
    private File currentSWDiff;
    private File SWexe;
    
    public Window() {
        frame = new JFrame();    
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupFrame();
    }

    public void setupFrame() {
        // Set up file select
        selectFileMenuBar = new JMenuBar();
        i1diff = new JMenuItem("Select ScuffedWalls Diff File");
        i2exe = new JMenuItem("Select ScuffedWalls Executable");
        selectFileMenuBar.add(i1diff); selectFileMenuBar.add(i2exe);
        frame.setJMenuBar(selectFileMenuBar);

        i1diff.addActionListener(this);
        i2exe.addActionListener(this);





        frame.setLayout(null); 
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {    
        // Set CurrentSWDiff.
    if (e.getSource() == i1diff) {
        SWFilter filter = new SWFilter();
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            currentSWDiff = fc.getSelectedFile();
        }

    } else if(e.getSource() == i2exe) {
        // Set SW exe
        EXEFilter filter = new EXEFilter();
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            SWexe = fc.getSelectedFile();
        }
   }
}
}
