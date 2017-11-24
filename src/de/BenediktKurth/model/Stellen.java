package de.BenediktKurth.model;

/**
 * Diese Klasse stellt eine Stelle im Basisdatenmodel da.
 * Es werden zusätzlich zur Position und dem Label, auch eine
 * evtl. Markierung gespeichert.
 * 
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public final class Stellen extends PosNameBase{

    /**
     * 
     */
    private String markierungString;
    private Boolean markiert;
   

    
    public Stellen(){
        super();
        this.markierungString = "0";
        this.markiert = false;
        
            
    }
    
    public Stellen(String id){
        super(id, "null", "null","null");
        this.markierungString = "0";
        this.markiert = false;
        
        
    }
 
    public Stellen(String id, String x, String y){
        super(id, "null", x, y );
        this.markierungString = "0";
        this.markiert = false;
        
        
        
    }
   
    public void setInitialMarking(String initialMarking) {
        this.markierungString = initialMarking;
        if (initialMarking.equals("1")){
            this.markiert = true;
        } else {
            this.markiert = false;
        }
    }

    public String getMarkierungString() {
        return markierungString;
    } 
    
    public void setMarkiert(boolean flag){
        
        this.markiert = flag;
        
        if (flag){
            this.markierungString = "1";
        } else {
            this.markierungString = "0";
        }
        
       
    }

    public Boolean getMarkiert(){
        return markiert;
    }

}
