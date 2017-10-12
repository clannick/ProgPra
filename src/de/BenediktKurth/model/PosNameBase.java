package de.BenediktKurth.model;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
    *  xPosString enthält die horizontale Position des Objektes (oberste Seite)
    *  als String.
    */
   private String xPosString;
   
    /**
    *  yPosString enthält die vertikale Position des Objektes (linke Seite)
    *  als String.
    */
   private String yPosString;
   
   /**
    * position enthält die Position des Objektes als Vector2D
    * 
    * @see Vector2D
    */
   private final Vector2D position;
   
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
       this.xPosString = "null";
       this.yPosString = "null";
       this.position = new Vector2D(0,0);
       
       
   }
   
   /**
    * Konstruktor erwarter vier Strings 
    * 
    * @param id         String    
    * 
    * @param label      String
    * 
    * @param xPosition  String
    * 
    * @param yPosition  String
    * 
    * @since 1.0
    */
    PosNameBase(String id, String label, String xPosition, String yPosition) {
        super(id);
        this.label = label;
        this.xPosString = xPosition;
        this.yPosString = yPosition;
        this.position = new Vector2D(0,0);
        
        try {
            this.position.setX(Integer.parseInt(xPosition));
            this.position.setY(Integer.parseInt(yPosition));
        } catch (NumberFormatException e) {
            this.position.setX(0);
            this.position.setY(0);
        }
    }


    public final String getLabel() {
        return label;
    }

    public final String getxPosition() {
        return xPosString;
    }

    public final String getyPosition() {
        return yPosString;
    }

    public final void setLabel(String label) {
        this.label = label;
    }
   
    public final Vector2D getPosition(){
        return this.position;
    }
    
    public final void setPosition(int x, int y){
        this.position.setX(x);
        this.position.setY(y);
        
    }
    public final void setPositionfromString(String xPosition, String yPosition){
        this.xPosString = xPosition; 
        this.yPosString = yPosition;
        
        try {
            this.position.setX(Integer.parseInt(xPosition));
            this.position.setY(Integer.parseInt(yPosition));
        } catch (NumberFormatException e) {
            this.position.setX(0);
            this.position.setY(0);
        }
    }

    
   @Override
    public String toString(){
        return id +", "+ label +", "+ xPosString +", "+ yPosString;
    }

    
    
   
   
    
}
