package de.BenediktKurth.model;

import javax.swing.JLabel;

/**
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public abstract class PosNameBase extends IDBase {
  
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
   
   private static int size = 50;  
   
   /**
    * Leerer Konstruktor - Erzeugt ein leeres Objekt mit klarer ID
    * Einträge ohne Wert werden mit String "null" belegt.
    * 
    * @since 1.0
    */
   PosNameBase (){
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
   PosNameBase (String id, String label, String xPosition, String yPosition) {
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

    public void setPosition(String xPosition, String yPosition) {
        this.xPosition = xPosition; 
        this.yPosition = yPosition;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public static void setSize(int faktor){
        float temp = faktor/100.0f;
        PosNameBase.size = (int)(50 * temp);
    }
    
    public static int getSize(){
        return PosNameBase.size;
    }
    
    public int getXint (){
        int x = Integer.parseInt(xPosition);
        return x;
    }
    
    public int getYint (){
        int y = Integer.parseInt(yPosition);
        return y;
    }
    
    public JLabel getDarstellung(){
        return this.darstellung;
    }

    
   @Override
    public String toString(){
        return id +", "+ label +", "+ xPosition +", "+ yPosition;
    }

    
    
   
   
    
}
