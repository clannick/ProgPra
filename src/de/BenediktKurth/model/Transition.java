package de.BenediktKurth.model;

/**
 * Klasse zur Speicherung der Basisdaten einer Transition.
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public final class Transition extends PosNameBase{


    /**
     * Leerer Konstruktor. Es wird der Konstruktor von IDBase aufgrufen.
     * 
     * @since 1.0
     */
    public Transition(){
        super();
    }
    
    /**
     * Fast leerer Konstruktor. Es wird der Konstruktor von PosNameBase aufgrufen.
     * 
     * @param id    Id des Objektes als String
     * 
     * @since 1.0
     */    
    public Transition(String id){
        super(id, "", "", "");
    }
}
