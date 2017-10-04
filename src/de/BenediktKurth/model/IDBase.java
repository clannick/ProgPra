package de.BenediktKurth.model;

import javax.swing.JPanel;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public abstract class IDBase {
    
    /**
     * 
     * @since 1.0
     */
    protected final String id;
    protected final int idInt;
    
    protected JPanel darstellung;
        
    static int idCounter = 0;

    /**
     * Leerer Konstrukter erzeugt eine ID aus Basis des idCounter und erhöht 
     * idCounter um eins

     * @since 1.0
     */
    public IDBase () {
        Integer temp = IDBase.idCounter;
        this.id = temp.toString();
        this.darstellung = null;
        this.idInt = idCounter++;
    }
     /**
     * Konstrukter mit vorhander ID, erhöht anschließend idCounter um eins
     * 
     * @param id
     * 
     * @since 1.0
     * 
     */
    public IDBase(String id) {
        this.id = id;
        this.darstellung = null;
        this.idInt = idCounter++;
    }

    /**
     * Method gibt die ID des Objektes als String zurück
     * 
     * @return String   ID des Objektes
     * 
     * @since 1.0
     */
    public String getId() {
        return id;
    }
    
    /**
     * Method gibt die ID des Objektes als int zurück
     * 
     * @return int   ID des Objektes als int
     * 
     * @since 1.0
     */    
    public int getID(){
        int tempInt = Integer.parseInt(this.id);
        return tempInt;
    }
    
    public int getInterneID(){
        return this.idInt;
    }
    
    /**
     * Diese Method gibt den IdCounter der Klasse (Static) zurück
     * 
     * @return int   IdCounter der Struktur
     * 
     * @since 1.0
     */
    public int getIdCounter() {
        return IDBase.idCounter;
    }
    
}
