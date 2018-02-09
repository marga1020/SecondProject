/* 
 * Initial code retrieved from http://raider.mountunion.edu/csc/CSC370/Spring2018/projects/project01/ClockStarter.java
 * humble beginnings of a Clock class
 *
 * Modifications by Gaston C. Marian
 *
 * This class is threadable and counts ticks in the simulation
 * Useful for acting as a system clock
 *
 *
 *
 *
 *
 */
package firstproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class ClockStarter implements Runnable{
    
    private Thread thread;
    private int currentTime;
    private boolean running = true;
    private JLabel jLabel;
    private int sleepTime = 1000;
    private Simulator sim;
    
    public ClockStarter(JLabel j ) {
        jLabel = j;
        currentTime = -1;
    }
    
    public void run() {
        
        while (true){
            if (!running){
                incrementTime();
                //System.out.println("ClockStarter currentTime  "
                //                    + getCurrentTime());
            }
            try{ Thread.sleep(sleepTime); } catch (Exception e) {}
        }
    }
    
    public void setSleepTime(int x){
        sleepTime = 50000/x;
    }
    
    public int getSleepTime(){
        return sleepTime;       // method is unlikely to be needed
    }
    
    public void incrementTime(){
        currentTime++;
        sim.setClockTime(currentTime);
        jLabel.setText(""+ getCurrentTime());
    }
    
    public int getCurrentTime(){
        return currentTime;
    }
    
    public void resetCurrentTime(){
        currentTime = -1;
    }
    
    public void setSim(Simulator s){
        sim = s;
    }
  
    public static void main(String args[]) 
    {
        System.out.println("ClockStarter test code");
        //ClockStarter theClock = new ClockStarter();
        
    }
    
    public void switchRun(){
        if(!running){
            running = true;
        }
        else {
            running = false;
        }
    }
}