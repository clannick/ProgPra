package de.BenediktKurth.main;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.view.HauptFenster;

/**
 * Startmethode des Programmes.
 * Die Methode initalisiert einen Controller und ein HauptFenster.
 * Weiter werden die Verbindung zwischen Controller und Darstellung gesetzt und 
 * das HauptFenster sichtbar gemacht.
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 */
public class Main {

    /**
     * Startmethode des Programmes. 
     * 
     * @param args Startparameter des Programmes. (Es werden keine Unterstützt)
     * 
     * @since 1.0
     */
    public static void main(String[] args) {
        //Erzeuge Controller
        MainWindowController testController = new MainWindowController();
        
        //Erzeuge HauptFenster
        HauptFenster testWindow = new HauptFenster(testController);
        
        //Setze Referenz im Controller für das HauptFenster
        testController.setzeKomponenten(testWindow);
     
        //Zeige HauptFenster
        testWindow.setVisible(true);
    }
}