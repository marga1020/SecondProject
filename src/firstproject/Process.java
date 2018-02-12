/*
 * Gaston C. Marian
 * A process class
 * This is a basic Process class with getters, setters, a toString, 
 * and a constructor with three parameters for creation time, name, and state.
 * Honestly doesn't do much. Location may be useful for future work
 */
package firstproject;

/**
 *
 * @author 1Owner
 */
public class Process {
    private int time;
    private String name, state;
    private Tape tape;
    private String location;
    
    public Process(){
        time = 0;
        name = "";
        state = "";
    }
    
    public Process(int t, String n, String s){
        time = t;
        name = n;
        state = s;          
        // Add line to affect the Tape object with String
        location = "Process List";
    }
    
    public void setLocationNew(){
        location = "New List";
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
