package de.BenediktKurth.model;

import java.util.ArrayList;

/**
 * Diese Klasse ist eine reine Hilfsklasse. Die Funktionalität einer ArrayListe
 * wird durch eine Funktion erweitert.
 *
 * Plannung: - Exceptions
 *
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 */
public class ArrayListCheckAdd extends ArrayList<Integer>{

    /**
     * Die Methode nimmt einen int-Wert entgegen und prüft Liste ob der int-Wert
     * bereits enthalten ist oder nicht. Wenn der Wert bereits enthalt wird die
     * Methode abgebrochen und false zurück geben. Wenn int-Wert nicht enthalten 
     * super.add() und es wird true zurückgegeben.
     * 
     * @param neuerWert  Einzufügender int-Wert
     * 
     * @return boolean  True -> erfolgreich hinzugefügt
     *                  False -> Wert bereits enthalten
     */
    public boolean addCheck(int neuerWert){
        //Hilfsvariable
        Integer temp = neuerWert;
        
        int i = 0;
        
        // Durchsuche Liste ob neuerWert enthalten ist
        while (i < this.size()){
            if (this.get(i).equals(temp)){
                //Abbruch der Methode und Rüchgabe
                return false;
            }
        }
        
        //Aufruf der Methode der Superklasse
        this.add(temp);
        
        return true;
    }
}
