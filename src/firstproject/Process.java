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
        location = "Process List";
    }
    
    public void setLocationNew(){
        location = "New List";
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
    
    public String toString(){
        String retVal = "";
        retVal = time+ " :: " + name + " :: " + state;
        return retVal;
    }
    
}
