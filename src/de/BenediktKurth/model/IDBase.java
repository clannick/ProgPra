package de.BenediktKurth.model;

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
    
    static int idCounter = 1;

    /**
     * Leerer Konstrukter erzeugt eine ID aus Basis des idCounter und erhöht 
     * idCounter um eins

     * @since 1.0
     */
    public IDBase () {
        Integer temp = getIdCounter();
        this.id = temp.toString();
        idCounter++;
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
        idCounter++;
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
     * STATISCHE-Method gibt den IdCounter zurück
     * 
     * @return int   IdCounter der Struktur
     * 
     * @since 1.0
     */
    public static int getIdCounter() {
        return idCounter;
    }
    
}
