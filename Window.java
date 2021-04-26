import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import filters.*;

public class Window extends JFrame implements ActionListener {
    private static JFrame frame;
    private JMenuBar topbar;
    private JMenu fileSelect;
    private JMenuItem i1diff, i2exe, diffStat, swEXEStat;
    private final JFileChooser fc = new JFileChooser();
    private ScuffedFile currentSWDiff;
    private ScuffedApplication SWexe;
    
    public Window() {
        frame = new JFrame();    
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupFrame();
    }

    /*
     * TODO:
     * Add file status in top left
     * add file verifacation in 
     */

    public void setupFrame() {
        setupFrameFileSelect();
        setupFrameFileSelectStatus();

        frame.setLayout(null); 
        frame.setVisible(true);
    }

    private void setupFrameFileSelect() {
        // Set up file select
        topbar = new JMenuBar();
        fileSelect = new JMenu("Files");  
        i1diff = new JMenuItem("Select ScuffedWalls Diff File");
        i2exe = new JMenuItem("Select ScuffedWalls Executable");

        topbar.add(fileSelect);
        fileSelect.add(i1diff); fileSelect.add(i2exe);
        frame.setJMenuBar(topbar);

        i1diff.addActionListener(this);
        i2exe.addActionListener(this);
    }

    private void setupFrameFileSelectStatus() {
        diffStat = new JMenuItem(" Difficulty Not Loaded");
        swEXEStat = new JMenuItem("SW EXE Not Loaded");

        topbar.add(diffStat);
        topbar.add(swEXEStat);
    }

/*
     *   *****************
     *   ** After-Setup **
     *   *****************
     */  

    public void actionPerformed(ActionEvent e) {
        // Set CurrentSWDiff.
        if (e.getSource() == i1diff) {
            SWFilter filter = new SWFilter();
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                currentSWDiff = new ScuffedFile(fc.getSelectedFile().getAbsolutePath());
                reloadStatusBar();
            }

        } else if (e.getSource() == i2exe) {
            // Set SW exe
            EXEFilter filter = new EXEFilter();
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                SWexe = new ScuffedApplication(fc.getSelectedFile().getAbsolutePath());
                reloadStatusBar();
            }
        }
    }

    public void reloadStatusBar() {
        if (currentSWDiff != null && currentSWDiff.isValid()) {
            diffStat.setText(" Difficulty Loaded: " + currentSWDiff.getFileName());
        }
        if (SWexe != null && SWexe.isValid()) {
            swEXEStat.setText("Application Loaded: " + SWexe.getFileName());
        }
    }
}
