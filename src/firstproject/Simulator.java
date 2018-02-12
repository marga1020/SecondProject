/*
 * Rob Ranallo
 * Aaron Liezert
 * Gaston C. Marian
 * Simulates CPU allocation to various processes, full project specification can be found at the following site
 * http://raider.mountunion.edu/csc/CSC370/Spring2018/projects/project01/index.html
 *
 *
 * This class is the over arching simulator that takes in many processes
 * and clock times and determines where the processes go
 *
 *
 *
 *
 *
 *
 */
package firstproject;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author 1Owner
 */
public class Simulator {
    private int runTime, currentRun, clockTime, oldTime;
    private ArrayList<Process> processList, newList, readyList, runningList, waitingList, endedList;
    private JLabel processesLabel, readyLabel, runningLabel, waitingLabel, endedLabel;
    
    // Constructor
    public Simulator(int rT, ArrayList<Process> pL, JLabel pLabel, JLabel reL, JLabel ruL, JLabel wL, JLabel eL){
        runTime = currentRun = rT;
        processList = pL;
        clockTime = -1;
        newList = new ArrayList<>();
        readyList = new ArrayList<>();
        runningList = new ArrayList<>();
        waitingList = new ArrayList<>();
        endedList = new ArrayList<>();
        processesLabel = pLabel;
        readyLabel = reL;
        runningLabel = ruL;
        waitingLabel = wL;
        endedLabel = eL;
        LabelBreakFormat processListText = new LabelBreakFormat();                    // JLabel supports html, but not newline. Thanks freitass on SO: https://stackoverflow.com/questions/1090098/newline-in-jlabel
        for(Process x: processList){
            processListText.addToText(x.toString());
        }
        processesLabel.setText(processListText.toString());
    }
    
    // If the system clock time has changed, the work is done to recalculate
    // process positions.
    public void setClockTime(int t){
        oldTime = clockTime;
        clockTime = t;
        if(clockTime > oldTime){
            increment();
            setLabels();
        }
    }
    
    public void clearSim(){
        processList.clear();
        newList.clear();
        readyList.clear();
        clockTime = -1;
        processesLabel.setText("");
        readyLabel.setText("");
        runningLabel.setText("");
        waitingLabel.setText("");
        endedLabel.setText("");
    }
    
    public ArrayList<Process> getNewList(){
        return newList;
    }
    
    public ArrayList<Process> getReadyList(){
        return readyList;                                   // bug fixed here. Copy/pasted line return newList; without changing to ready
    }                                                       // Always double check copy/pasted text
    
    public ArrayList<Process> getProcessList(){
        return processList;
    }

    // Currently determines if a process should be out yet, if so it enters it into new
    // and then if the tick is one past the enterance number, it enters it into ready.
    // Future iterations will do more calculations
    private void increment() {
        
        // this will see if the process in run has been there for the timeQuantum
        if (runningList.isEmpty()){
            try{
                runningList.add(readyList.get(0));
                readyList.get(0).setLocationRunning();
                readyList.remove(readyList.get(0));
                currentRun = runTime;
            }catch(Exception ex){
                
            }
        }
        // else block shifts the 
        else{
            // increment the process Tape here
            currentRun --;
            if (currentRun == -1){
                waitingList.add(runningList.get(0));
                runningList.get(0).setLocationWaiting();
                runningList.remove(runningList.get(0));
            }
        }
        
        // This loop sends processes from process to new when they are meant to enter
        // then from new to Ready one tick after they enter new
        for (Process x: processList){
            if(x.getLocation().equals("Process List")){
                if (x.getTime()==clockTime){            // and the time has come for it to be initialized
                    if (!newList.contains(x)){          // move it into the new process list
                        x.setLocationNew();             
                        newList.add(x);
                    }
                }
            }
            if(x.getLocation().equals("New List")){
                if (x.getTime()<clockTime){             // and at least a tick has passed
                    if (!readyList.contains(x)){
                        x.setLocationReady(); 
                        readyList.add(x);
                    }
                    if (newList.contains(x)){
                        newList.remove(x);
                    }
                }
            }
        }
        
        // loops through waiting until they can move on
        for (Process x: waitingList){
            // x.incrementTape();
            if (x.currentWaitEnded()){
                readyList.add(x);
                x.setLocationReady();
                waitingList.remove(x);
            }
        }
    }

    private void setLabels() {                  // Each try/catch block tries to set the labels. 
        LabelBreakFormat textBeingSet = new LabelBreakFormat();               // Empty arrays throw exceptions which are cought and used to make empty labels
        
        try{
        for(Process x: readyList){
            textBeingSet.addToText(x.toString());
        }
        readyLabel.setText(textBeingSet.toString());
        textBeingSet.resetText();
        } catch (Exception ex){
            readyLabel.setText("");
        }
        
        try{
        for(Process x: runningList){
            textBeingSet.addToText(x.toString());
        }
        runningLabel.setText(textBeingSet.toString());
        textBeingSet.resetText();
        } catch (Exception ex){
            runningLabel.setText("");
        }
        
        try{
        for(Process x: waitingList){
            textBeingSet.addToText(x.toString());
        }
        waitingLabel.setText(textBeingSet.toString());
        textBeingSet.resetText();
        } catch (Exception ex){
            waitingLabel.setText("");
        }
        
        try{
        for(Process x: endedList){
            textBeingSet.addToText(x.toString());
        }
        endedLabel.setText(textBeingSet.toString());
        textBeingSet.resetText();
        } catch (Exception ex){
            endedLabel.setText(""); 
        }
    }
    
    class LabelBreakFormat{
        private String labelText = "<html>";
        public LabelBreakFormat(){
        }
        public void resetText(){
            labelText = "<html>";
        }
        public void addToText(String s){
            s.replaceAll("<","&lt;").replaceAll(">", "&gt;");       // future proofing just in case
            labelText = labelText + s + "<br/>";
        }
        private void finalizeText(){
            labelText = labelText + "</html>";
        }
        public String toString(){
            finalizeText();
            return labelText;
        }
    }
}
