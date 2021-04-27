import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.util.*;
import filters.*;

public class Window extends JFrame implements ActionListener {
    private static JFrame frame;
    private JMenuBar topbar;
    private JMenu fileSelect;
    private JMenuItem i1diff, i2exe, diffStat, swEXEStat;
    private final JFileChooser fc = new JFileChooser();

    private ScuffedFile currentSWDiff;
    private ScuffedApplication SWexe;

    private DefaultListModel<String> eventSelList;
    private JList<String> eventSel;
    private JButton importButton;
    
    public Window() {
        frame = new JFrame();    
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*
     * TODO:
     * Add file status in top left
     * add file verifacation in testing to see if valid file
     * Deal with invalid files / issues
     * add scroll to list
     * 
     */

    public void setupFrame() {
        setupFrameFileSelect();
        setupFrameFileSelectStatus();
        setupFrameLevelDataLoad();

        frame.setName("Scuffed Walls GUI");
        frame.getContentPane().setBackground(new Color(100,100,100));
        frame.setLayout(null); 
        frame.setVisible(true);
    }

    /**
     * Setup Menu Bar
     */
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

    /**
     * Setup Menu Bar Status
     */
    private void setupFrameFileSelectStatus() {
        diffStat = new JMenuItem(" Difficulty Not Loaded");
        swEXEStat = new JMenuItem("SW EXE Not Loaded");

        topbar.add(diffStat);
        topbar.add(swEXEStat);
    }

    /**
     * Setup Left Sidebar
     */
    private void setupFrameLevelDataLoad() {
        eventSelList = new DefaultListModel<String>();
        eventSel = new JList<>(eventSelList);
        eventSel.setBounds(20, 20, 200, 480);
        importButton = new JButton("Import Scuffed File");
        importButton.setBounds(20, 505, 200, 25);
        importButton.addActionListener(this);
        importButton.setEnabled(false);

        importButton.setBackground(new Color(50,50,50));
        eventSel.setBackground(new Color(50,50,50));
        frame.add(importButton);
        frame.add(eventSel);
    }

    /**
     * Load Events into Sidebar List
     */
    private void loadEventsFromFileIntoList() {
        frame.remove(eventSel);
        eventSelList = null;
        eventSel = null;
        eventSelList = new DefaultListModel<String>();
        ArrayList<ScuffedEvent> eventList;
        if (currentSWDiff != null && currentSWDiff.isValid()) {
            eventList = currentSWDiff.importDiff();
        } else {return;}

        for (int i = 0; i < eventList.size(); i++) {
            ScuffedEvent currentEvent = eventList.get(i);
            eventSelList.addElement(currentEvent.getBeat() + ": " + currentEvent.getEventType());
        }
        eventSel = new JList<>(eventSelList);
        eventSel.setBounds(20, 20, 200, 480);
        eventSel.setBackground(new Color(50,50,50));
        eventSel.setForeground(Color.WHITE);
        //this does not work
        eventSel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        eventSel.setLayoutOrientation(JList.VERTICAL);

        frame.add(eventSel);

        // TODO: add in auto-select - need to make it actually display
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
                importButton.setEnabled(true);
                importButton.setForeground(Color.GREEN);
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
        } else if (e.getSource() == importButton) {
            loadEventsFromFileIntoList();
        }
    }

    public void reloadStatusBar() {
        if (currentSWDiff != null && currentSWDiff.isValid()) {
            diffStat.setText(" Difficulty Loaded: " + currentSWDiff.getFileName());
            topbar.updateUI();
        }
        if (SWexe != null && SWexe.isValid()) {
            swEXEStat.setText("SW EXE Loaded: " + SWexe.getFileName());
            topbar.updateUI();
        }
    }
}
