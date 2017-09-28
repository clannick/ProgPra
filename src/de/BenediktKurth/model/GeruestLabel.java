package de.BenediktKurth.model;

/**
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
   
   /**
    *  Label enthält den angezeigten Bezeichner des Objektes
    */
   private String label;
   
    /**
    *  xPosition enthält die horizontale Position des Objektes (oberste Seite)
    */
   private String xPosition;
   
    /**
    *  yPosition enthält die vertikale Position des Objektes (linke Seite)
    */
   private String yPosition;
   
   /**
    * Leerer Konstruktor - Erzeugt ein leeres Objekt mit klarer ID
    * Einträge ohne Wert werden mit String "null" belegt.
    * 
    * @since 1.0
    */
   GeruestLabel (){
       // Ruft Konstruktor von IDBase auf -> klare ID 
       super();
       
       this.label = "null";
       this.xPosition = "null";
       this.yPosition = "null";
   }
   
   /**
    * Konstruktor erwarter vier Strings 
    * 
    * @param id     
    * 
    * @param label
    * 
    * @param xPosition
    * 
    * @param yPosition
    * 
    * @since 1.0
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
