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
     * @param id
     *  Die ID ist eine engültige (final) Identifikationsnummer für jedes Objekt.
     */
    protected final String id;

    public IDBase(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
}
