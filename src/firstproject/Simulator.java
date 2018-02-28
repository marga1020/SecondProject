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
import java.util.Iterator;
import javax.swing.JLabel;

/**
 *
 * @author 1Owner
 */
public class Simulator {
    private int allowedRunTime, currentRun, clockTime, oldTime;
    private ArrayList<Process> processList, newList, readyList, runningList, waitingList, endedList;
    private JLabel processesLabel, newLabel, readyLabel, runningLabel, waitingLabel, endedLabel, tqLabel;
    private ArrayList<Process>[] provisionalList = new ArrayList[3];
    private int processesUnallocated;
    private int rules = 0;
    private int stateOrderRules = 0;
    private int waitingOrderRules = 0;
    private int newOrderRules = 0;
    
    // Constructor
    public Simulator(int rT, ArrayList<Process> pL, JLabel pLabel, JLabel nL, JLabel reL, JLabel ruL, JLabel wL, JLabel eL, JLabel tq){
        allowedRunTime = currentRun = rT;
        processList = pL;
        clockTime = -1;
        newList = new ArrayList<>();
        readyList = new ArrayList<>();
        runningList = new ArrayList<>();
        waitingList = new ArrayList<>();
        endedList = new ArrayList<>();
        processesLabel = pLabel;
        newLabel = nL;
        readyLabel = reL;
        runningLabel = ruL;
        waitingLabel = wL;
        endedLabel = eL;
        LabelBreakFormat processListText = new LabelBreakFormat();                    // JLabel supports html, but not newline. Thanks freitass on SO: https://stackoverflow.com/questions/1090098/newline-in-jlabel
        for(Process x: processList){
            processListText.addToText(x.toString());
        }
        processesLabel.setText(processListText.toString());
        processesUnallocated = processList.size();
        for (int i = 0; i < provisionalList.length; i++){
            provisionalList[i] = new ArrayList<>();
        }
        tqLabel = tq;
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
        for (int i = 0; i < provisionalList.length; i++){
            provisionalList[i].clear();
        }
        clockTime = -1;
        processesLabel.setText("");
        newLabel.setText("");
        readyLabel.setText("");
        runningLabel.setText("");
        waitingLabel.setText("");
        endedLabel.setText("");
        tqLabel.setText("");
    }
    
    public ArrayList<Process> getNewList(){
        return newList;
    }
    
    public ArrayList<Process> getReadyList(){
        return readyList;                                   
    }                                                       
    
    public ArrayList<Process> getProcessList(){
        return processList;
    }
    
    public ArrayList<Process> getWaitingList(){
        return waitingList;
    }
    
    public ArrayList<Process> getEndedList(){
        return endedList;
    }
    
    public ArrayList<Process> getRunningList(){
        return runningList;
    }
    

    private void increment() {
        
        if( processesUnallocated != 0 ){
            moveToNew();
        }
        
        if( !newList.isEmpty() ){
            moveToReady('n', newList);
        }
        
        if( !runningList.isEmpty() ){
            incrementRunning();
        }
        
        if( runningList.isEmpty() ){
            moveToRunning();
        }
        
        if( !waitingList.isEmpty() ){
            incrementWaiting();
        }
        
        moveProvisionalToReady();
        resetUsedBooleans();
    }

    private void setLabels() {                                                 // Each try/catch block tries to set the labels. 
        LabelBreakFormat textBeingSet = new LabelBreakFormat();               // Empty arrays throw exceptions which are cought and used to make empty labels
        
        try{
        for(Process x: newList){
            textBeingSet.addToText(x.toString());
        }
        newLabel.setText(textBeingSet.toString());
        textBeingSet.resetText();
        } catch (Exception ex){
            readyLabel.setText("");
        }
        
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
        try {
            tqLabel.setText("Time Quantum: " + currentRun);
        }catch (Exception ex){
            tqLabel.setText("");
        }
    }
    
    private void moveToNew(){
        for (Process x: processList){
            if (clockTime == x.getTime() && x.getUsed() == false && x.getLocation().equals("Process List")){
                newList.add(x);
                x.setUsed(true);
                x.setLocationNew();
                processesUnallocated--;
            }
        }
    }
    
    private void moveToReady(char where, ArrayList<Process> processes){
        ArrayList<Process> toRemove = new ArrayList<>();
        for(Process processToGo: processes){       
            if(processToGo.getUsed() == false){
                processToGo.setLocationReady();
                if (where == 'n'){
                    provisionalList[0].add(processToGo);
                    toRemove.add(processToGo);
                }
                else if (where == 'r'){
                    provisionalList[1].add(processToGo);
                    toRemove.add(processToGo);
                }
            }
        }
        for(Process x: toRemove){
            if (newList.contains(x)){
                newList.remove(x);
            }
            else if (runningList.contains(x)){
                runningList.remove(x);
            }
        }
    }
    
    private void moveToReady(Process processToGo){
        waitingList.remove(processToGo);
        provisionalList[2].add(processToGo);
    }
    
    private void moveToRunning(){
        if(!readyList.isEmpty()){
            runningList.add(readyList.get(0));
            readyList.remove(readyList.get(0));
        }
    }
    
    private void incrementRunning(){
          
        if (runningList.get(0).getUsed() == false){
            Tape runningTape = runningList.get(0).getTape();
            System.out.println(currentRun + " is the current Run");     // Testing
            if (currentRun == 0 ){
                if (runningTape.getType().equals("C") && runningTape.getTimeLeft() !=0){
                    moveToReady('r', runningList);
                    currentRun = allowedRunTime;
                }
                else {
                    try{
                        runningList.get(0).incrementTape();
                        moveToWaiting();
                        currentRun = allowedRunTime;
                    }catch(Exception ex){
                        moveToTerminated();
                        currentRun = allowedRunTime;
                    }
                }
            }
            else{
                currentRun--;
                if (runningTape.getType().equals("C") && runningTape.getTimeLeft() == 0){
                //if (runningTape.getType().equals("I")){
                    try{
                        runningList.get(0).incrementTape();
                        moveToWaiting();
                        currentRun = allowedRunTime;
                    }catch(Exception ex){
                        moveToTerminated();
                        currentRun = allowedRunTime;
                    }
                }
                else{
                    runningList.get(0).incrementTape();
                    runningList.get(0).setUsed(true);
                }
            }
        }
    }
    
    private void incrementWaiting(){
        ArrayList<Process> toMove = new ArrayList<>();
        for (Process x: waitingList){
            if(x.getUsed() == false){
                if (!x.currentWaitEnded()){
                    x.incrementTape();
                    x.setUsed(true);
                }
                else{
                    x.incrementTape();
                    toMove.add(x);
                }
            }
        }
        if (!toMove.isEmpty()){
            for (Process x: toMove){
                moveToReady(x);
            }
        }
    }
    
    private void moveToWaiting(){
        if (runningList.get(0).getUsed() == false){
            runningList.get(0).setUsed(true);
            waitingList.add(runningList.get(0));
            runningList.remove(runningList.get(0));
        }
    }
    
    private void moveToTerminated(){
        if (runningList.get(0).getUsed() == false){
            runningList.get(0).setUsed(true);
            endedList.add(runningList.get(0));
            runningList.remove(runningList.get(0));
        }
    }
  
    private void moveProvisionalToReady(){
        ArrayList<Process> intoReady = sortByRulesForReady();
        
        for (Process x: intoReady){
            readyList.add(x);
        }
        
    }
    
    public void setRules(int i){
        rules = i;
    }
    
    public void setOrderRule(int i){
        stateOrderRules = i;
    }
    public void setWaitingOrderRules(int i){
        waitingOrderRules = i;
    }
    public void setReadyOrderRules(int i){
        newOrderRules = i;
    }
    
    public void setStateOrder(int i){                      // Provisional List Starts N R W
        ArrayList<Process> temp = new ArrayList<>();
        if (i == 0){            // NRW from NRW, continue
            
        }
        else if (i == 1){       // NWR from NRW,    pL0 is right. pass 2 to 1, 1 to 2
            temp = provisionalList[1];
            provisionalList[1] = provisionalList[2];
            provisionalList[2] = temp;
        }
        else if (i == 2){       // WNR from NRW,    none are right. pass 2 to 0, 1 to 2, and 0 to 1
            temp = provisionalList[0];
            provisionalList[0] = provisionalList[2];        // 2 to 0
            provisionalList[2] = provisionalList[1];        // 1 to 2
            provisionalList[1] = temp;                      // 0 to 1
        }
        else if (i == 3){       // WRN from NRW,    pL1 is right. pass 2 to 0, 0 to 2
            temp = provisionalList[0];
            provisionalList[0] = provisionalList[2];        // 2 to 0
            provisionalList[2] = temp;                      // 0 to 2
        }
        else if (i == 4){       // RNW from NRW,    pL2 is right, pass 1 to 0, 0 to 1
            temp = provisionalList[0];
            provisionalList[0] = provisionalList[1];        // 2 to 0
            provisionalList[1] = temp;                      // 0 to 2
        }
        else if (i == 5){       // RWN from NRW,    none are right. pass 2 to 1, 1 to 0, and 0 to 2
            temp = provisionalList[0];
            provisionalList[0] = provisionalList[1];        // 1 to 0
            provisionalList[1] = provisionalList[2];        // 2 to 1
            provisionalList[2] = temp;                      // 0 to 2
        }
    }
    
    private ArrayList<Process> sortByRulesForReady(){
        ArrayList<Process> sorted = new ArrayList<>();
        if (!provisionalList[2].isEmpty()){
            sortWaiting(waitingOrderRules);
        }
        if (!provisionalList[0].isEmpty()){
            sortNew(newOrderRules);
        }
        setStateOrder(stateOrderRules);
        for(int i = 0; i < provisionalList.length; i++){
            try{
                for(Process x: provisionalList[i]){
                    sorted.add(x);
                }
                provisionalList[i].clear();
            }catch(Exception ex){  }
        }
        return sorted;
    }
    
    private void resetUsedBooleans(){
        for (Process x: processList){
            x.setUsed(false);
        }
    }

    private void sortWaiting(int order) { // order 0 - data, 1 - dataR, 2 - alpha, 3 - alphaR
        ArrayList<Process> temp = new ArrayList<>();
        temp = provisionalList[2];
        if (order == 0){
            provisionalList[2] = sortByData(temp);
        }
        else if (order == 1){
            provisionalList[2] = reverse(sortByData(temp));
        }
        else if (order == 2){
            provisionalList[2] = sortByAlpha(temp);
        }
        else if (order == 3){
            provisionalList[2] = reverse(sortByData(temp));
        }
    }

    private void sortNew(int order) { // order 0 - data, 1 - dataR, 2 - alpha, 3 - alphaR
        ArrayList<Process> temp = new ArrayList<>();
        temp = provisionalList[0];
        if (order == 0){
            provisionalList[0] = sortByData(temp);
        }
        else if (order == 1){
            provisionalList[0] = reverse(sortByData(temp));
        }
        else if (order == 2){
            provisionalList[0] = sortByAlpha(temp);
        }
        else if (order == 3){
            provisionalList[0] = reverse(sortByAlpha(temp));
        }
    }
    
    private ArrayList<Process> reverse(ArrayList<Process> arr){
        ArrayList<Process> temp = new ArrayList<>();
        for (int i = arr.size()-1; i >= 0; i--){
            temp.add(arr.get(i));
        }
        return temp;
    }
    
    private ArrayList<Process> sortByAlpha(ArrayList<Process> arr){
        String ord = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        
        ArrayList<Process> retVal = new ArrayList<>();
        
        String[] names = new String[arr.size()];
        ArrayList<String> name2 = new ArrayList<>();
        
        for (int i = 0; i < names.length; i++){
            names[i] = arr.get(i).getName();
        }
        
        for (int i = 0; i < names.length; i++){
            int lowest = 100;
            for(int j = 0; j < names.length; j++){
                char compare = names[j].charAt(0);
                if (!name2.contains(names[j]) && ord.indexOf(compare) < lowest){
                    lowest = j;
                }
            }
            name2.add(names[lowest]);
        }
        
        for (int i = 0; i < arr.size(); i++){
            if (arr.get(i).getName().equals(name2.get(i))){
                retVal.add(arr.get(i));
            }
        }
        return retVal;
    }

    private ArrayList<Process> sortByData(ArrayList<Process> temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
