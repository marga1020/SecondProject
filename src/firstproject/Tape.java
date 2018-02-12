
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
    
    public void increment() {
        time --;
    }
    
    public Boolean isValid() {
        return valid;
    }
    
}
