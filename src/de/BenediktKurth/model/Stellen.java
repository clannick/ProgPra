package de.BenediktKurth.model;

import javax.swing.JLabel;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public class Stellen extends PosNameBase{

    private String markierung;
    private int size;
   
    
    
    public Stellen(){
        super();
        this.markierung = "null";
        this.size = 50;
        
    }
    
    public Stellen(String id){
        super(id, "null", "null","null");
        this.markierung = "null";
        
    }
    
    
    public Stellen(String id, String x, String y){
        super(id, "null", x, y );
        this.markierung = "null";
        
    }

    public void setInitialMarking(String initialMarking) {
        this.markierung = initialMarking;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public String getMarkierungString() {
        return markierung;
    }
    
    public int getMarkierung() {
        if (markierung.equals("null")){
            return 0;
        } else {
            Integer temp = Integer.parseInt(markierung);
            return temp;
        }
    }

    public int getSize() {
        return size;
    }
    
    
    
    @Override
    public String toString(){
        return super.toString() + ", " + markierung;
    }
    
}
