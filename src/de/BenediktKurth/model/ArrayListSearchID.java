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
public class ArrayListSearchID<T extends IDBase> extends ArrayList {
    
    
    /**
     * Diese Methode ermöglicht es innerhalb der Liste ein bestimmtes Objekt anhand seiner ID zu ermitteln. Das betreffende Obejekt wird dann wieder zurückgegeben.
     * 
     * @since       1.0
     * @param id    Gesuchte ID
     * @return      Gibt das gesuchte Objekt (IDBase) anhand seiner ID zurück
     */
    public T searchID (String id){
        
        T rueckgabeWert = null;
        boolean gefunden = false;
        int i = 0;
        
        // Gehe die Liste so lange durch bis ID gefunden oder Liste zu ende
        while (!gefunden && (i < this.size())){
            T temp = (T)this.get(i);
            //System.out.println(temp.getId());
            if (temp.getId().equals(id)) {
                gefunden = true;
                return temp;
            }
            i++;
        }
        
        // Sollte gesuchtes Objekt nicht gefunden werden wird null zurückgegeben.
        return rueckgabeWert;
    }
    
    public boolean idExist(String id){
        T temp = searchID(id);
        if (temp != null){
            return true;
        }
        return false;
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
    public boolean add(Object eingabeObjekt){
       
        if (!idExist(((T)eingabeObjekt).getId())){
            super.add(eingabeObjekt);
            return true;
        }
        return false;
    }
}
