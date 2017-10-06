package de.BenediktKurth.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

    
    private final static Icon viereck = new ImageIcon("/ressource/viereck.png");
   
    
    public Stellen(){
        super();
        this.markierung = "null";
        
        this.darstellung = new JLabel("neu", viereck,0);      
    }
    
    public Stellen(String id){
        super(id, "null", "null","null");
        this.markierung = "null";
        
        this.darstellung = new JLabel("neu", viereck,0); 
        
    }
    
    
    public Stellen(String id, String x, String y){
        super(id, "null", x, y );
        this.markierung = "null";
        
        this.darstellung = new JLabel("neu", viereck,0); 
        
    }

    public void setInitialMarking(String initialMarking) {
        this.markierung = initialMarking;
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


    
    
    @Override
    public String toString(){
        return super.toString() + ", " + markierung;
    }
    
}
