
package firstproject;

public class Tape {
    
    private String type;
    private int time;
    private Boolean valid;
    
    public Tape(String ty, int t) {
        type = ty;
        time = t;
        if(type.equals('C') || type.equals('I')) {
            valid = true;
        }
        else {
            valid = false;
        }
    }
    
    public String getType(){
        return type;
    }
    
    public String toString(){
        return getType() + " " + getTimeLeft();
    }
    
    public void increment() {
        time --;
    }
    
    public Boolean isValid() {
        return valid;
    }
    
    public int getTimeLeft() {
        return time;
    }
    
}
