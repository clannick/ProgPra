package de.BenediktKurth.model;

import java.util.ArrayList;

/**
 *  Die Funktionalität einer ArrayListe wird durch die Funktion einfuegen erweitert. 
 *  Diese Klasse ist eine reine Hilfsklasse.
 *
 * Plannung: - Exceptions
 *
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 */
public final class ArrayListCheckAdd extends ArrayList<Integer>{

    private static final long serialVersionUID = 403L;

    /**
     * Die Methode nimmt einen int-Wert entgegen und prüft Liste ob der int-Wert
     * bereits enthalten ist oder nicht. Wenn der Wert bereits enthalt wird die
     * Methode abgebrochen und false zurück geben. Wenn int-Wert nicht enthalten 
     * super.add() und es wird true zurückgegeben.
     * 
     * @since 1.0
     * 
     * @param neuerWert  int Einzufügender int-Wert
     * 
     * @return boolean  True: erfolgreich hinzugefügt
     *                  False: Wert bereits enthalten
    */
    public boolean einfuegen(int neuerWert){
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
        
        //neuerWert nicht enthalten - > neuerWert einfügen
        this.add(temp);
        
        return true;
    }
}
