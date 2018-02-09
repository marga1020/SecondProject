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
    }
    
    // If the system clock time has changed, the work is done to recalculate
    // process positions.
    public void setClockTime(int t){
        oldTime = clockTime;
        clockTime = t;
        if(clockTime > oldTime){
            increment();
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
        
        // This loop sends processes from process to new when they are meant to enter
        // then from new to Ready onen tick after they enter new
        for (Process x: processList){
            if(x.getName().equals("Process List")){
                if (x.getTime()==clockTime){            // and the time has come for it to be initialized
                    if (!newList.contains(x)){          // move it into the new process list
                        x.setLocationNew();             // Location setting likely to be useful in full implementation of sim
                        newList.add(x);
                    }
                }
            }
            if(x.getName().equals("New List")){
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
        
        // this will see if the process in run has been there for the timeQuantum
        if (runningList.get(0) == null){
            
        }
        else{
            
        }
    }
}
