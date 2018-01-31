package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Diese Klasse realisiert einen Nutzerdialog zum Umbenennen von Objekten. Der Nutzer
 * wird durch ein neues Frame aufgefordert einen neuen Namen (Label) für das Objekt zu vergeben.
 * Er kann jedoch die Aktion abbrechen, dann wird der Name (Label) nicht geändert.
 * 
 * @author Benedikt Kurth
 * 
 * @version 1.0
 * 
 * @since 1.1
 * 
 * @see JFrame
 */
public class Warnung extends JFrame {

    /**
     * Enthält den Controller für diese Klasse
     *
     * @since 1.0
     * 
     * @see MainWindowController
     */
    private final MainWindowController          controller;
    
    /**
     * OK-Knopf als JButton für die Klasse.
     * 
     * @since 1.0
     */
    private JButton                             okKnopf;
    
    /**
     * Abbrechen-Knopf als JButton für die Klasse.
     * 
     * @since 1.0
     */
    private JButton                             abbrechenKnopf;

    /**
     * Vollständiger Konstruktor. Der Konstruktor ruft die Methode setzteObjekteLayout.
     * 
     * @param controller    Referenz auf Controller
     * @param screenHeight  Dektophöhe
     * @param screenWidth   Desktopbreite
     * 
     * @since 1.0
     */
    public Warnung(MainWindowController controller,int screenHeight, int screenWidth) {
        this.controller = controller;
                
        //Baue Dialog auf
        setzeObjekteLayout();
        
        //Setzte Position und Größe des Fensters
        setBounds((screenWidth/2) - 150, (screenHeight/2) - 60, 300, 120);
        
        //Setze Label (als Text und im Titel
        
        setTitle("Achtung!");
        
        //Zeige Fenster
        setVisible(true);
    }

    /**
     * Diese Methode definiert alle Objekte und setzt das Layout.
     * 
     * @since 1.0
     */                        
    private void setzeObjekteLayout() {

        //Instanziere Objekte für Dialog-Fenster
        okKnopf = new JButton();
        abbrechenKnopf = new JButton();
        JTextArea nachricht = new JTextArea();
        
        //Setzte Fenstereigenschaften
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(300, 120));
        setMinimumSize(new java.awt.Dimension(300, 120));
        setPreferredSize(new java.awt.Dimension(300, 120));
        setSize(new java.awt.Dimension(300, 120));
        getContentPane().setLayout(new java.awt.FlowLayout());
        setResizable(false);
        
        nachricht.setText("Achtung, die Änderungen wurden nicht gespeichert.");
        getContentPane().add(nachricht);
        
        //Eigenschaften OK-Knopf & Anfügen
        okKnopf.setText("OK");
        okKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bestaetigen(evt);
            }
        });
        getContentPane().add(okKnopf);

        //Eigenschaften Abbrechen-Knopf & Anfügen
        abbrechenKnopf.setText("Abbrechen");
        abbrechenKnopf.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abbrechen(evt);
            }
        });
        getContentPane().add(abbrechenKnopf);

        //"Packe" Darstellungen
        pack();
    }                      

    /**
     * Die Methode wird aufgerufen, wenn der Abbrechen-Knopf gedrückt wurde.
     * Die Methode beendet diesen Dialog ohne weitere Aktion.
     * 
     * @param evt   NICHT VERWENDET 
     */
    private void abbrechen(java.awt.event.ActionEvent evt) {                                         
        this.dispose();
    }                                        

    /**
     * Die Methode wird aufgerufen, wenn der OK-Knopf gedrückt wurde.
     * Die Methode ruft Controller-Methoden auf und beendet dann diesen Dialog.
     * 
     * @param evt   NICHT VERWENDET 
     */
    private void bestaetigen(java.awt.event.ActionEvent evt) {                                         
        controller.laden();
        this.dispose();
    }                                                       
}