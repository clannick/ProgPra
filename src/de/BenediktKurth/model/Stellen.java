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
    
    Stellen(String id){
        super(id, "null", "null","null");
        this.initialMarking = "null";
        
    }

    public void setInitialMarking(String initialMarking) {
        this.initialMarking = initialMarking;
    }


    public String getInitialMarking() {
        return initialMarking;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", " + initialMarking;
    }
    
}
