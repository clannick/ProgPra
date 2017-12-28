package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Diese Klasse realisiert einen Nutzerdialog zum Umbenennen von Objekten. Der Nutzer
 * wird durch ein neues Frame aufgefordert einen neuen Namen (Label) für das Objekt zu vergeben.
 * Er kann jedoch die Aktion abbrechen, dann wird der Name (Label) nicht geändert.
 * 
 * @author Benedikt Kurth
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @see JFrame
 */
public class Umbenennen extends JFrame {

    /**
     * Enthält den Controller für diese Klasse
     *
     * @since 1.0
     * 
     * @see MainWindowController
     */
    private final MainWindowController controller;
    
    /**
     * Interne ID des Objektes das umbennant werden soll.
     *
     * @since 1.0
     * 
     */
    private final int interneID;
    
    /**
     * OK-Knopf als JButton für die Klasse.
     * 
     * @since 1.0
     */
    private JButton okKnopf;
    
    /**
     * Abbrechen-Knopf als JButton für die Klasse.
     * 
     * @since 1.0
     */
    private JButton abbrechenKnopf;
    
    /**
     * Einganefeld und Anzeigenfeld für das Label (Bez.) eines Obejktes.
     * 
     * @since 1.0
     */
    private JTextField textFeld;

    /**
     * Vollständiger Konstruktor. Der Konstruktor ruft die Methode setzteObjekteLayout.
     * 
     * @param interneID     Referenz auf die interne ID des Objektes
     * @param label         Neues Label für Objekt
     * @param controller    Referenz auf Controller
     * @param screenHeight  Dektophöhe
     * @param screenWidth   Desktopbreite
     * 
     * @since 1.0
     */
    public Umbenennen(int interneID, String label, MainWindowController controller,int screenHeight, int screenWidth) {
        this.controller = controller;
        this.interneID = interneID;
        
        //Baue Dialog auf
        setzteObjekteLayout();
        
        //Setzte Position und Größe des Fensters
        setBounds((screenWidth/2) - 125, (screenHeight/2) - 60, 250, 120);
        
        //Setze Label (als Text und im Titel
        textFeld.setText(label);
        setTitle("Umbenennen: " + label);
        
        //Zeige Fenster
        setVisible(true);
    }

    /**
     * Diese Methode definiert alle Objekte und setzt das Layout.
     * 
     * @since 1.0
     */                        
    private void setzteObjekteLayout() {

        //Instanziere Objekte für Dialog-Fenster
        textFeld = new JTextField();
        okKnopf = new JButton();
        abbrechenKnopf = new JButton();

        //Setzte Fenstereigenschaften
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(250, 120));
        setMinimumSize(new java.awt.Dimension(250, 120));
        setPreferredSize(new java.awt.Dimension(250, 120));
        setSize(new java.awt.Dimension(250, 120));
        getContentPane().setLayout(new java.awt.FlowLayout());
        setResizable(false);
        
        //Eigenschaften Textfeld & Anfügen
        textFeld.setPreferredSize(new java.awt.Dimension(200, 30));
        getContentPane().add(textFeld);

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
        String temp = textFeld.getText();
        controller.umbennenLabel(interneID, temp);
        controller.neueDarstellungOhneTest();
        this.dispose();
    }                                                       
}