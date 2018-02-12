/*
 * Gaston C. Marian
 * A process class
 * This is a basic Process class with getters, setters, a toString, 
 * and a constructor with three parameters for creation time, name, and state.
 * Honestly doesn't do much. Location may be useful for future work
 */
package firstproject;

import java.util.LinkedList;

/**
 *
 * @author 1Owner
 */
public class Process {
    private int time;
    private String name, state;
    private Tape tape;
    private String location;
    private LinkedList tapeContents;
    
    public Process(){
        time = 0;
        name = "";
        tapeContents = null;
    }
    
    public Process(int t, String n, LinkedList l){
        time = t;
        name = n;
        tapeContents = l;          
        // Add line to affect the Tape object with String
        location = "Process List";
        tape = this.getTapeSection();
    }
    
    public void setLocationNew(){
        location = "New List";
    }
    
    public Tape getTapeSection() {
        return (Tape) tapeContents.getFirst();
    }
    
    public void setLocationWaiting(){
        location = "Waiting List";
    }
    
    public void setLocationRunning(){
        location = "Running List";
    }
    
    public void setLocationReady(){
        location = "Ready List";
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setTime(int t){
        time = t;
    }
    public void setName(String n){
        name = n;
    }
    public void setState(String s){
        state = s;
    }
    
    public int getTime(){
        return time;
    }
    public String getName(){
        return name;
    }
    public String getState(){
        return state;
    }
    
    public void incrementTape(){
        // tape.increment();
        tape = this.getTapeSection();
        if(tape.getTimeLeft() != 0) {
            tape.increment();
        }
    }
    
    public Boolean currentWaitEnded(){
        // code to call tape and how to find the current length
        // return true when true, but for now it waits forever.
        return false;
    }
    
    public String toString(){
        String retVal = "";
        retVal = time+ " :: " + name + " :: " + state;
        return retVal;
    }
    
}
