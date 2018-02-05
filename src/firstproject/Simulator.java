/*
 * Gaston C. Marian 1/29/2018
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

/**
 *
 * @author 1Owner
 */
public class Simulator {
    private int runTime, clockTime, oldTime;
    private ArrayList<Process> processList, newList, readyList;
    
    // Constructor
    public Simulator(int rT, ArrayList<Process> pL){
        runTime = rT;
        processList = pL;
        clockTime = -1;
        newList = new ArrayList<>();
        readyList = new ArrayList<>();
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
        for (Process x: processList){
            if (x.getTime()==clockTime){            // and the time has come for it to be initialized
                if (!newList.contains(x)){          // move it into the new process list
                    x.setLocationNew();             // Location setting likely to be useful in full implementation of sim
                    newList.add(x);
                }
            }
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
}
