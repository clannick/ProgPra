package de.BenediktKurth.model;


import java.util.ArrayList;
/**
 *  Die Funktionalität einer ArrayListe wird durch mehrere Funktionen erweitert. 
 *  Diese Klasse ist die Basis des Basisdatenmodels. Es können alle Supklassen von 
 *  IDBase genutzt werden.
 *
 * Plannung: - Exceptions
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
    
    /**
     * Methode prüft ob ein Obejkt vorhanden ist auf Basis der ID als String.
     * 
     * @param id ID des Obejktes als String
     * 
     * @return True: Objekt gefunden, False: Objekt nicht gefunden.
     */
    public boolean idExist(String id) {
        //Hole T-Objekt mit ID
        T temp = searchID(id);

        //Wenn nicht vorhanden dann false
        return temp != null;
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
       
        //Überprüfe ob interne ID bereitsvorhanden
        if (!idExist(eingabeObjekt.getId())){
            //Wenn noch nicht vorhanden, hinzufügen und true zurück
            super.add(eingabeObjekt);
            return true;
        }
        
        //Konnte nicht eingefügt werden, da ID bereits existiert
        return false;
    }
    
    /**
     * Diese Methode sucht auf Basis der Internen ID ein Objekt und gibt eine Referenz 
     * auf sie zurück.
     * 
     * @param interneID Interne ID des zu suchenden Obejktes.
     * 
     * @return Gibt bei erfolg gesuchtes Objekt zurück, ansonsten NULL.
     */
    public T getWithInternID(int interneID){
        
        //Initalisiere Rückgabe-Wert
        T rueckgabeWert = null;
        
        // Gehe die Liste so lange durch bis ID gefunden oder Liste zu ende
        for (T x: this){
            if (x.getInterneID() == interneID) {
                return x;
            }            
        }
        
        // Sollte gesuchtes Objekt nicht gefunden werden wird null zurückgegeben.
        return rueckgabeWert;       
    }
}