package de.BenediktKurth.model;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public class Stellen extends GeruestLabel{

    private String initialMarking;
    private int size;
    
    
    public Stellen(){
        super();
        this.initialMarking = "null";
        this.size = 50;
    }
    
    
    public Stellen(String id){
        super(id, "null", "null","null");
        this.initialMarking = "null";
        
    }

    public void setInitialMarking(String initialMarking) {
        this.initialMarking = initialMarking;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public String getInitialMarking() {
        return initialMarking;
    }

    public int getSize() {
        return size;
    }
    
    
    
    @Override
    public String toString(){
        return super.toString() + ", " + initialMarking;
    }
    
}
