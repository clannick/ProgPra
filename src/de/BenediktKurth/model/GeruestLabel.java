package de.BenediktKurth.model;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public abstract class GeruestLabel extends IDBase {
  
   /**
     *
     * @param label
     *  Das Label ist ein Bezeichner der im Programm genutzt wird.
     *
     * @param xPosition
     *  xPosition gibt die räumliche Lage des Objektes in der Horizontalen an. Es wird der am weitesten links liegende Punkt genutzt.
     *
     * @param yPosition
     *  yPosition gibt die räumliche Lage des Objektes in der Vertikalen an. Es wird der am weitesten oben liegende Punkt genutzt.
    */ 
    
   private String label;
   private String xPosition;
   private String yPosition;
   
   
   /**
    * Konstruktor erwarter vier Strings 
    */
   GeruestLabel (String id, String label, String xPosition, String yPosition){
       super(id);
       this.label = label;
       this.xPosition = xPosition;
       this.yPosition = yPosition;
   }


    public String getLabel() {
        return label;
    }

    public String getxPosition() {
        return xPosition;
    }

    public String getyPosition() {
        return yPosition;
    }

    public void setName(String label) {
        this.label = label;
    }

    public void setPosition(String xPosition, String yPosition) {
        this.xPosition = xPosition; 
        this.yPosition = yPosition;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
   @Override
    public String toString(){
        return id +", "+ label +", "+ xPosition +", "+ yPosition;
    }

    
    
   
   
    
}
