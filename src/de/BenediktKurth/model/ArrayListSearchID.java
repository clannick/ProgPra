package de.BenediktKurth.model;


import java.util.ArrayList;
/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 *
 * @param <T> Ermöglicht den Typsicherenzugriff auf getId() der Klasse IDBase
 */
public final class ArrayListSearchID<T extends IDBase> extends ArrayList<T> {
    
    
    /**
     * Diese Methode ermöglicht es innerhalb der Liste ein bestimmtes Objekt 
     * anhand seiner ID zu ermitteln. 
     * Das betreffende Obejekt wird dann wieder zurückgegeben.
     * 
     * @since         1.0
     * @param   id    Gesuchte ID
     * @return        Gibt das gesuchte Objekt (IDBase) anhand seiner ID zurück
     */
    public T searchID (String id){
        
        T rueckgabeWert = null;
        
        // Gehe die Liste so lange durch bis ID gefunden oder Liste zu ende
        for (T x: this){
            if (x.id.equals(id)) {
                return x;
            }
        }
        
        // Sollte gesuchtes Objekt nicht gefunden werden wird null zurückgegeben.
        return rueckgabeWert;
    }
    
    public boolean idExist(String id) {
        T temp = searchID(id);
        if (temp == null){
            return false;
        }
        
        return true;
    }
    
    
    /**
     * Methode lässt nur ungleiche ID´s der Objekte zu.
     * Erweiterung zur Superklasse
     * 
     * @param eingabeObjekt Enthält die einzufügende Datei
     * 
     * @return  true, wenn ID einmalig
     *          false, wenn ID vorhanden
     */
    
    @Override
    public boolean add(T eingabeObjekt){
       
        if (!idExist(eingabeObjekt.getId())){
            super.add(eingabeObjekt);
            return true;
        }
        return false;
    }
    
    
    
    public T getWithInternID(int id){
        
        T rueckgabeWert = null;
        
        // Gehe die Liste so lange durch bis ID gefunden oder Liste zu ende
        for (T x: this){
            if (x.getInterneID() == id) {
                return x;
            }            
        }
        
        // Sollte gesuchtes Objekt nicht gefunden werden wird null zurückgegeben.
        return rueckgabeWert;       
    }
}
