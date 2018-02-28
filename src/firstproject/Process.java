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
    private int enteranceOrder;
    private int time;
    private String name, state;
    private Tape tape;
    private String location;
    private LinkedList tapeContents, deadTape;
    private boolean used = false;
    
    public Process(){
        time = 0;
        name = "";
        tapeContents = null;
        enteranceOrder=0;
    }
    
    public Process(int t, String n, LinkedList l, int i){
        time = t;
        name = n;
        tapeContents = l;
        deadTape = new LinkedList();
        // Add line to affect the Tape object with String
        location = "Process List";
        tape = this.getTapeSection();
        enteranceOrder = i;
    }
    
    public int getOrder(){
        return enteranceOrder;
    }
    
    public void setLocationNew(){
        location = "New List";
    }
    
    public boolean getUsed(){
        return used;
    }
    
    public void setUsed(boolean u){
        used = u;
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
    
    public void setLocationEnded(){
        location = "Ended List";
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
    
    public Tape getTape(){
        return tape;
    }
    
    public void incrementTape(){
        tape = this.getTapeSection();
        if(tape.getTimeLeft() != 0) {
            tape.decrement();
        }
        else {
            this.switchTapeSection();
        }
    }
    
    public boolean ioWaitNext(){
        try {
            Tape temp = (Tape) tapeContents.peek();
            return temp.getType().equals("I");
        }catch(Exception ex){
            return false;
        }
    }
    
    public void switchTapeSection() {
        deadTape.add(tapeContents.removeFirst());
        tape = this.getTapeSection();
    }
    
    
    public Boolean currentWaitEnded(){
        return tape.getTimeLeft() == 0;
    }
    
    public String toString(){
        String retVal = "";
        retVal = time+ " :: " + name + " :: " + tapeContentsToString();
        return retVal;
    }

    private String tapeContentsToString() {
        String theTapeContentsString = "";
        for (int i = 0; i < tapeContents.size(); i++){
            theTapeContentsString = theTapeContentsString + tapeContents.get(i).toString() + " ";
        }
        return theTapeContentsString;
    }
    
}
