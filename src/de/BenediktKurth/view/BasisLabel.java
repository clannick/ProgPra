package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Klasse stellt Basis für die Objekt-Darstellung dar. Enthält alle Grundwert
 * und Funktionaltitäten der Objekte.
 *
 * @author clannick
 *
 * @since 1.0
 *
 * @version 1.0
 *
 * @see HauptFenster
 * @see MainWindowController
 * @see BorderFactory
 * @see Point
 * @see JLabel
 */
public abstract class BasisLabel extends JLabel {

    /**
     * Interne ID des Objektes. Bezieht sich auf die Interne ID des
     * Basisdatenmodels. Diese ist eindeutig und als integer-Wert hinterlegt.
     *
     * @since 1.0
     */
    protected final int interneID;

    /**
     * Referenz auf HauptFenster das dieses Objekt aufgerufen hat. Wird benötigt
     * um status von Markiert (Focus) zu ändern.
     *
     * @since 1.0
     */
    private HauptFenster mother;

    /**
     * Referenz auf Kontroller das dieses Objekt aufgerufen hat. Wird benötigt
     * um Methoden(Verschiebe) des Kontroller aufzurufen.
     *
     * @since 1.0
     */
    protected MainWindowController controller;

    /**
     * Art und Farbe des Marierktrahmens (FocusOn).
     *
     * @since 1.0
     *
     * @see BorderFactory
     */
    private Border markiertUmrandung = BorderFactory.createLineBorder(Color.yellow);

    /**
     * Statusanzeige, ob das Objekt markiert ist (Focus).
     *
     * @since 1.0
     */
    protected boolean isMarkiert;

    /**
     * Wird als Referenzwert für VerschiebbareLabels benötigt. Da
     * addMouseListener.mousePressed für alle Labels existier, muss auch hier
     * der erste Punkt zum Verschieben gesetzt werden. Erleichter den Umgang mit
     * den Mothoden von MouseEvent.
     *
     * @since 1.0
     *
     * @see Point
     */
    protected volatile Point punkt;

    /**
     * Vollständiger Konstruktor mit Initalisierung. Erhält Referenz des Basisdatenmodel (speichert
     * diese aber nicht), weiter wird der Klasse "sein" Kontroller und das
     * HauptFenster mitgeteilt. Es wird auch überprüft, ob ein Rahmen
     * (istMarkiert) gesetzt werden muss.
     *
     * @param basis Referenz auf Basisdatenmodel.
     * @param controller Referenz auf Kontroller.
     * @param mother Referenz auf HauptFenster.
     *
     * @since 1.0
     *
     * @see JLabel
     */
    public BasisLabel(IDBase basis, MainWindowController controller, HauptFenster mother) {
        //Hole alle benötigten Basisdaten und setzte Referenzen.
        this.interneID = basis.gibInterneID();
        this.controller = controller;
        this.mother = mother;
        
        //Initalisiere Hilfsvariablen
        this.punkt = new Point(0, 0);
        this.isMarkiert = false;

        //Setzte JLabel EIgenschaften für Layout und Mouseanzeige
        super.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        super.setLayout(null);

        //Guck ob dieses Objekt markiert ist
        for (Integer x : mother.getInterneIDmarkierter()) {
            if (x == interneID) {
                super.setBorder(markiertUmrandung);
                this.isMarkiert = true;
                break;
            }
        }

        //Erzeuge MouseListner um alle "Standart" Maus-Aktionen zu realisieren       
        super.addMouseListener(new java.awt.event.MouseAdapter() {
            //Wenn Maus Clicked ausgelöst wird
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Wurde die linke Maustaste gedrückt?
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    //Wurde das erste Objekt markiert oder sollen mehrere Objekte markiert werden
                    if (evt.isControlDown() || mother.getInterneIDmarkierter().isEmpty()) {
                        //Setzte focus dieses Objektes
                        setzeFocusAndBorder(interneID);
                        //Lasse neue Darstellung erzeugen
                        controller.neueDarstellungOhneTest();

                        //Nutzer möchte ein anderes Objekt markieren
                    } else {
                        //Setze Fokus aller anderen Objekte zurück
                        mother.focusZuruecksetzen();
                        //Setzte focus dieses Objektes
                        setzeFocusAndBorder(interneID);
                        //Lasse neue Darstellung erzeugen
                        controller.neueDarstellungOhneTest();
                    }
                    //Wurde die rechte Maustaste gedrückt?  
                } else if (evt.getButton() == MouseEvent.BUTTON3) {
                    //Nutzer möchte Workflownetz simulieren
                    controller.simuliereSicheresWorklflownetz(evt, gibInterneID());
                }
            }

            //Methode wird genutzt um Drag-and-Drop der Objekt zu realisieren
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                //Setze aktuellen Punkt der Maus
                punkt = evt.getPoint();
            }
        });
    }

    /**
     * Hilfsmethode um Focus dieses Objektes zu setzten.
     * 
     * @since 1.0
     * 
     * @param interneID Interne-ID des Objektes
     */
    private void setzeFocusAndBorder(int interneID) {
        //Setze diesen Rahmen
        super.setBorder(markiertUmrandung);
        //Füge dieses Objekt der Markiertenliste hinzu
        mother.getInterneIDmarkierter().add(interneID);
    }

    /**
     * HIlfmethode um die Interne ID dieses Objektes zu ermitteln.
     * 
     * @return Interne-ID des Objektes als int-Wert
     * 
     * @since 1.0
     */
    public final int gibInterneID() {
        return this.interneID;
    }
}