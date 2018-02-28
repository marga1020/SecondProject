/*
 *
 * Rob Ranallo
 * Aaron Liezert
 * Gaston C. Marian
 * CSC 370, First Project
 * Due XX Feburary 2018
 * Making a program to simulate operating system processes as specified in the 
 * following link: http://raider.mountunion.edu/csc/CSC370/Spring2018/projects/project01/index.html
 *
 * This class handles GUI as well as calling all the other classes. MainFrame is good for leading
 *                                                
 *                                                                                                       
 */
package firstproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Gaston Marain
 */
public class MainFrame extends javax.swing.JFrame {
    private ArrayList<Process> processes, newList, readyList, runningList, waitingList, endedList;
    private static ClockStarter CS;
    private boolean clicked = false;
    private boolean clocked = false;
    private boolean running = false;
    private Thread thread;
    private int clockLimit;
    private int arr, temp, i;
    private Simulator sim;
    private String er;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        MyChangeListener mcl = new MyChangeListener();
        speedSlider.addChangeListener(mcl);
        processes = new ArrayList<>();
        newList = new ArrayList<>();
        readyList = new ArrayList<>();
        RuleListener rL = new RuleListener();
        stateComboBox.addActionListener(rL);
        waitingComboBox.addActionListener(rL);
        readyComboBox.addActionListener(rL);
        clockLabel.addPropertyChangeListener(new ClockChangeListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playPauseButton = new javax.swing.JButton();
        clockLabel = new javax.swing.JLabel();
        readDataButton = new javax.swing.JButton();
        oneTickButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        speedSlider = new javax.swing.JSlider();
        statusButton = new javax.swing.JButton();
        speedLabel = new javax.swing.JLabel();
        processesLabel = new javax.swing.JLabel();
        endedLabel = new javax.swing.JLabel();
        readyLabel = new javax.swing.JLabel();
        runningLabel = new javax.swing.JLabel();
        waitingLabel = new javax.swing.JLabel();
        testButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        processesListLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        readyListLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        endedListLabel = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        runningListLabel = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        waitingListLabel = new javax.swing.JLabel();
        newLabel = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        newListLabel = new javax.swing.JLabel();
        tqLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        stateComboBox = new javax.swing.JComboBox();
        instructionLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        waitingComboBox = new javax.swing.JComboBox();
        fromNewLabel = new javax.swing.JLabel();
        readyComboBox = new javax.swing.JComboBox();
        sampleData1Button = new javax.swing.JButton();
        sampleData2Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playPauseButton.setText("Run");
        playPauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playPauseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(playPauseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 80, -1));

        clockLabel.setText("-1");
        getContentPane().add(clockLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        readDataButton.setText("Read Data");
        readDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readDataButtonActionPerformed(evt);
            }
        });
        getContentPane().add(readDataButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        oneTickButton.setText("One Tick");
        oneTickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oneTickButtonActionPerformed(evt);
            }
        });
        getContentPane().add(oneTickButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 80, -1));

        inputArea.setColumns(20);
        inputArea.setRows(5);
        jScrollPane1.setViewportView(inputArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 310, 200));

        outputArea.setColumns(20);
        outputArea.setRows(5);
        jScrollPane2.setViewportView(outputArea);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 310, 310));

        speedSlider.setMaximum(200);
        speedSlider.setMinimum(10);
        getContentPane().add(speedSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 101, 20));

        statusButton.setText("Status");
        statusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusButtonActionPerformed(evt);
            }
        });
        getContentPane().add(statusButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        speedLabel.setText("Speed");
        getContentPane().add(speedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        processesLabel.setText("Processes");
        getContentPane().add(processesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        endedLabel.setText("Terminated");
        getContentPane().add(endedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, -1, -1));

        readyLabel.setText("Ready");
        getContentPane().add(readyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, -1, -1));

        runningLabel.setText("Running");
        getContentPane().add(runningLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, -1, -1));

        waitingLabel.setText("Waiting");
        getContentPane().add(waitingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, -1, -1));

        testButton.setText("Test Data");
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });
        getContentPane().add(testButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 100, -1));

        jScrollPane3.setViewportView(processesListLabel);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 220, 100));

        jScrollPane4.setViewportView(readyListLabel);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 210, 130));

        jScrollPane5.setViewportView(endedListLabel);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 230, 120));

        jScrollPane6.setViewportView(runningListLabel);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 180, 40));

        jScrollPane7.setViewportView(waitingListLabel);

        getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, 210, 150));

        newLabel.setText("New");
        getContentPane().add(newLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        jScrollPane8.setViewportView(newListLabel);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 200, 100));
        getContentPane().add(tqLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 110, 20));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, 30, 10));

        stateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NRW", "NWR", "WNR", "WRN", "RNW", "RWN" }));
        getContentPane().add(stateComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 530, 150, 22));

        instructionLabel.setText("Choose Rules");
        getContentPane().add(instructionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 500, -1, -1));

        jLabel2.setText("State Order");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 530, -1, 20));

        jLabel3.setText("From Waiting");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 570, -1, 20));

        waitingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Data Order", "Reverse Data Order", "Alphabetical", "Reverse Alphabetical" }));
        getContentPane().add(waitingComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 570, 150, 22));

        fromNewLabel.setText("From New");
        getContentPane().add(fromNewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 620, -1, 20));

        readyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Data Order", "Reverse Data Order", "Alphabetical", "Reverse Alphabetical" }));
        getContentPane().add(readyComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 620, 150, 22));

        sampleData1Button.setText("Sample Data 1");
        sampleData1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleData1ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(sampleData1Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        sampleData2Button.setText("Sample Data 2");
        sampleData2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleData2ButtonActionPerformed(evt);
            }
        });
        getContentPane().add(sampleData2Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void readDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readDataButtonActionPerformed
        resetTimeAndSim(evt);
        try{
            String inString = inputArea.getText();
            String[] inArray = inString.split("\n");
            
            if (inputArea.getText().equals("")){                  // Validation that input is there to be read
                outputArea.setText(" \nERROR:\nInput data is required.");
                return;
            }
            
            if (!(inArray.length%3 == 1)){                  // Validation that input is right number of lines
                outputArea.setText(" \nERROR:\nInput data is an invaid format."
                        +"\nProper format requires a first line time quantum,"
                        +"\nfollowed by groups of three lines representing "
                        +"\ncreation time, name, and trace tape for the process.");
                return;
            }
            
            try{                                            // Validation that input starts first line with time quantum
                clockLimit = Integer.parseInt(inArray[0]);
            } catch (Exception ex){
                outputArea.setText("\nERROR:\nInput data validation failed.\nMake sure the input format is correct."
                        +"\nFirst line is a number, the time quantum");
                return;
            }
            
            try{                                         // Validation that input is made of processes that have start time
                processProcesses(inArray);
            } catch (Exception ex){
                outputArea.setText("ERROR:\nInput data validation failed.\nMake sure the input format is correct."
                        +"\n3 lines - \n\ta number for time, \n\ta string name, \n\tand a tape\n"+er);
                return;
            }
            // Validation that names are unique
            if(!nonUniqueNames().equals("")){
                outputArea.setText("ERROR:\nInput data name error.\nMake sure all names are unique."
                        + "\nCheck for " + nonUniqueNames());
                return;
            }
            outputArea.setText("\n Data Read Successfully");
            sim = new Simulator(clockLimit, processes, processesListLabel, newListLabel, readyListLabel, runningListLabel, waitingListLabel, endedListLabel, tqLabel);
            sim.setOrderRule(stateComboBox.getSelectedIndex());
            sim.setWaitingOrderRules(waitingComboBox.getSelectedIndex());
            sim.setReadyOrderRules(readyComboBox.getSelectedIndex());
            try{
                CS.setSim(sim);
            }catch(Exception ex){
                
            }
        }
        catch(Exception ex){outputArea.setText("\n Enter Data in Left Text Area");}
    }//GEN-LAST:event_readDataButtonActionPerformed

    private void playPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playPauseButtonActionPerformed
        startThread();
        if(running){
            running = false;
            playPauseButton.setText("Run");
        }else{
            running = true;
            playPauseButton.setText("Pause");
        }
        CS.switchRun();
    }//GEN-LAST:event_playPauseButtonActionPerformed

    private void oneTickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oneTickButtonActionPerformed
        startThread();
        CS.incrementTime();
        try{
            sim.setClockTime(CS.getCurrentTime());
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_oneTickButtonActionPerformed

    private void statusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusButtonActionPerformed
        showStatus();
    }//GEN-LAST:event_statusButtonActionPerformed

    private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
        String baseInput = "4\n2\nA\nC 2 I 2 C 3"
                + "\n3\nB\nC 1 I 2 C 3"
                + "\n1\nC\nC 1 I 10 C 3"
                + "\n5\nD\nC 5 I 2 C 3"
                + "\n2\nE\nC 1 I 2 C 3";
        if (inputArea.getText().equals(baseInput)){
            int inputsToGenerate = 5;
            inputArea.setText(generateInputs(inputsToGenerate));
        }
        else{
            inputArea.setText(baseInput);
        }
    }//GEN-LAST:event_testButtonActionPerformed

    private void stateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stateComboBoxActionPerformed
        i = stateComboBox.getSelectedIndex();
    }//GEN-LAST:event_stateComboBoxActionPerformed

    private void waitingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_waitingComboBoxActionPerformed
        arr = waitingComboBox.getSelectedIndex();
    }//GEN-LAST:event_waitingComboBoxActionPerformed

    private void readyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readyComboBoxActionPerformed
        temp = waitingComboBox.getSelectedIndex();
    }//GEN-LAST:event_readyComboBoxActionPerformed

    private void sampleData1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleData1ButtonActionPerformed
        String baseInput = "4\n2\nA\nC 5 I 9 C 6 I 7 C 2\n"
                + "50\nB\nC 2 I 19 C 10 I 3 C 2";
        inputArea.setText(baseInput);
    }//GEN-LAST:event_sampleData1ButtonActionPerformed

    private void sampleData2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleData2ButtonActionPerformed
        String baseInput = "4\n" +
                            "4\n" +
                            "A1\n" +
                            "C 6 I 32 C 6\n" +
                            "9\n" +
                            "X2\n" +
                            "C 6 I 17 C 10\n" +
                            "56\n" +
                            "Z3\n" +
                            "C 6 I 4 C 6\n" +
                            "56\n" +
                            "Q4\n" +
                            "C 6 I 4 C 6\n" +
                            "10\n" +
                            "B5\n" +
                            "C 6 I 26 C 5";
        inputArea.setText(baseInput);
    }//GEN-LAST:event_sampleData2ButtonActionPerformed

private void startThread(){
    if (!clicked){
            CS = new ClockStarter(clockLabel);
            thread = new Thread(CS);
            thread.start();
            clicked = !clicked;
            try{
                CS.setSim(sim);
            }
            catch(Exception ex){}
        }
}    


// Method to create the output for the current time and return a string to be 
    // appended when the status button is clicked
    private String statusString(){       
        String retVal = "";
        String clockT = "";
        String timeQ = "timeQuantum --    "+ clockLimit+ "\n";
        String processLS = "processList.size() --    "+processes.size() + "\n";
        String processL = "";
        String newLS = "";
        String newL = "";
        String readyLS = "";
        String readyL = "";
        String runningLS = "";
        String runningL = "";
        String waitingLS = "";
        String waitingL = "";
        String endedLS = "";
        String endedL = "";
        
        try{                                                        // Sets the clock time in case ClockStarter hasn't
            clockT = "clock --    "+ CS.getCurrentTime()+ "\n";
        } catch(Exception ex){
            clockT = "clock --   -1\n";
        }
        
        for (int i = 0; i < processes.size(); i++){                 // sets up processL
                processL = processL + "  process " + i + " ----    " 
                        + processes.get(i).toString() + "\n";
        }
        
        newList = sim.getNewList();
        newLS = newLS + "newList.size() -- " + newList.size()+ "\n";
        
        if (newList.size() > 0){                                        // sets up newL
            for (int i = 0; i < newList.size(); i++){
                newL = newL + " " + i + " ----    " 
                        + newList.get(i).toString() + "\n";
            }
        }
        
        readyList = sim.getReadyList();
        readyLS = readyLS + "readyList.size() -- " + readyList.size()+ "\n";
        
        if (readyList.size() > 0){                                                     // sets up readyL
            for (int i = 0; i < readyList.size(); i++){
                readyL = readyL + " " + i + " ----    " + readyList.get(i).toString() + "\n";
            }
        }
        
        runningList = sim.getRunningList();
        runningLS = runningLS + "runningList.size() -- " + runningList.size()+ "\n";
        
        if (runningList.size() > 0){                                                     // sets up runningL
            for (int i = 0; i < runningList.size(); i++){
                runningL = runningL + " " + i + " ----    " + runningList.get(i).toString() + "\n";
            }
        }
        
        waitingList = sim.getWaitingList();
        waitingLS = waitingLS + "waitingList.size() -- " + waitingList.size()+ "\n";
        
        if (waitingList.size() > 0){                                                     // sets up waitingL
            for (int i = 0; i < waitingList.size(); i++){
                waitingL = waitingL + " " + i + " ----    " + waitingList.get(i).toString() + "\n";
            }
        }
        
        endedList = sim.getEndedList();
        endedLS = endedLS + "endedList.size() -- " + endedList.size()+ "\n";
        
        if (endedList.size() > 0){                                                     // sets up endedL
            for (int i = 0; i < endedList.size(); i++){
                endedL = endedL + " " + i + " ----    " + endedList.get(i).toString() + "\n";
            }
        }
        
        retVal = clockT + timeQ + processLS + processL + newLS + newL + 
                readyLS + readyL + runningLS + runningL + waitingLS + waitingL + endedLS + endedL + "\n";
        return retVal;
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Method takes in the text entered in the input field, uses them to make process objects,
    // then adds each process to the processes ArrayList. Errors are thrown if something can't
    // be made into a process (likely, the processes enterance time is not an Integer value)
    // and passes the problem input back to be output for the user to see
    private void processProcesses(String[] inArray) {
        for (int i = 1; i < inArray.length; i += 3){      
            try{
                String theTape = inArray[i + 2];
                int tapeCount = 0;
                for(int a = 0; a < theTape.length(); a++) {
                    if(theTape.charAt(a) == 'C' || theTape.charAt(a) == 'I') {
                        tapeCount++;
                    }
                }
                int[] tapeIndex = new int[tapeCount];
                int c = 0;
                for(int b = 0; b < tapeCount; b++) {
                    while (c < theTape.length()) {
                        if(theTape.charAt(c) == 'C' || theTape.charAt(c) == 'I') {
                            tapeIndex[b] = c;
                            c++;
                            break;
                        }
                        c++;
                    }
                }
                LinkedList<Tape> tape = new LinkedList();
                for(int d = 0; d < tapeCount; d++) {
                    int begin = tapeIndex[d];
                    int end;
                    if(d == (tapeCount- 1)) {
                        end = theTape.length();
                    }
                    else {
                        end = tapeIndex[d + 1];
                    }
                    String stringToAdd = theTape.substring(begin, end);
                    int intToAdd;
                    String[] arrayToAdd = stringToAdd.split(" ");
                    stringToAdd = arrayToAdd[0];
                    intToAdd = Integer.parseInt(arrayToAdd[1]);
                    tape.add(new Tape(stringToAdd, intToAdd));
                }
                
                Process temp = new Process(Integer.parseInt(inArray[i]), inArray[i+1], tape, i);
                processes.add(temp);
            }catch (Exception ex){
                er = "Check here: \nLine " + i + " - " +inArray[i]
                        + "\nLine " + (i+1) + " - " +inArray[i+1]
                        + "\nLine " + (i+2) + " - " +inArray[i+2];
                throw ex;
            }
        }
    }

    private void resetTimeAndSim(java.awt.event.ActionEvent evt) {
        if (running){
            playPauseButtonActionPerformed(evt);
            CS.resetCurrentTime();
        }
        try{
            CS.resetCurrentTime();
        }catch(Exception ex){}
        try{
            sim.clearSim();
        }catch(Exception ex){}
        
        if(!outputArea.getText().equals("")){
            outputArea.setText("");
            clocked = false;
        }
        
    }

    private String generateInputs(int inputsToGenerate) {
        Random generator = new Random();
        String retValue = generator.nextInt(7)+3 + "";
        int numbersOfLines = inputsToGenerate*3;
        int numberPerLines = inputsToGenerate;
        if (numberPerLines % 2 == 0){
            numberPerLines++;
        }
            
        String nameIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < numbersOfLines; i++){
            String line = "\n" + (generator.nextInt(10) + 1) + "\n" + nameIndex.charAt(i) + "\n";
            for (int j = 0; j < numberPerLines; j++){
                if (j % 2 == 0){
                    if (j != 0){
                        line = line + " ";
                    }
                    line = line + "C ";
                }
                else{
                    line = line + " I ";
                }
                int compare = generator.nextInt(100);
                if (compare <= 50){
                    line = line + (generator.nextInt(10) + 1);
                }
                else if (compare < 90){
                    line = line + (generator.nextInt(20) + 1);
                }
                else{
                    line = line + (generator.nextInt(30) + 1);
                }
            }
            
            retValue = retValue + line;
        }
        return retValue;
    }

    private String nonUniqueNames() {
        String repeats = "";
        if (processes.size() > 1){
            ArrayList<String> namesList = new ArrayList<>();
            for (Process x: processes){
                namesList.add(x.getName());
            }
            ArrayList<String> name2List = new ArrayList<>();
            for (String name : namesList) {
                for (String n : namesList) {
                    name2List.add(n);
                }
                name2List.remove(name);
                if (name2List.contains(name)) {
                    return name;
                }
                name2List.clear();
            }
        }
        return repeats;
    }

    private void showStatus() {
        try{
            sim.getProcessList();
            if (!clocked){
                outputArea.setText("OUTPUT\n\n");
                clocked = !clocked;
            }
            try{
                sim.setClockTime(CS.getCurrentTime());
            }
            catch(Exception ex){
                sim.setClockTime(-1);
            }
            outputArea.append(statusString());
            outputArea.setCaretPosition(outputArea.getText().length());}
        catch(Exception ex){
            outputArea.setText("\n You need to enter valid data\nand click to read it in");
        }
    }

    
    private class MyChangeListener implements ChangeListener{
            @Override
            public void stateChanged(ChangeEvent ce) {
                try{
                CS.setSleepTime(speedSlider.getValue());    // Current method can require up to 5 seconds of wait time after moving slider
                }catch(Exception ex){}
            }
    }
    
    private class ClockChangeListener implements PropertyChangeListener{
        @Override
        public void propertyChange(PropertyChangeEvent pce) {
            showStatus();
        }
            
    }
    
    private class RuleListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ae) {
            try{
                sim.setOrderRule(stateComboBox.getSelectedIndex());
                sim.setWaitingOrderRules(waitingComboBox.getSelectedIndex());
                sim.setReadyOrderRules(readyComboBox.getSelectedIndex());
            }catch(Exception ex){
                
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel clockLabel;
    private javax.swing.JLabel endedLabel;
    private javax.swing.JLabel endedListLabel;
    private javax.swing.JLabel fromNewLabel;
    private javax.swing.JTextArea inputArea;
    private javax.swing.JLabel instructionLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel newLabel;
    private javax.swing.JLabel newListLabel;
    private javax.swing.JButton oneTickButton;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JButton playPauseButton;
    private javax.swing.JLabel processesLabel;
    private javax.swing.JLabel processesListLabel;
    private javax.swing.JButton readDataButton;
    private javax.swing.JComboBox readyComboBox;
    private javax.swing.JLabel readyLabel;
    private javax.swing.JLabel readyListLabel;
    private javax.swing.JLabel runningLabel;
    private javax.swing.JLabel runningListLabel;
    private javax.swing.JButton sampleData1Button;
    private javax.swing.JButton sampleData2Button;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JComboBox stateComboBox;
    private javax.swing.JButton statusButton;
    private javax.swing.JButton testButton;
    private javax.swing.JLabel tqLabel;
    private javax.swing.JComboBox waitingComboBox;
    private javax.swing.JLabel waitingLabel;
    private javax.swing.JLabel waitingListLabel;
    // End of variables declaration//GEN-END:variables
}
