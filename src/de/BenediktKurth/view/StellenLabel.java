package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.FarbenEnum;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Stellen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * Diese Klasse realisiert die Darstellung einer Stelle aus den Basisdatenmodel.
 * 
 * @author Benedikt Kurth
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @see FarbenEnum
 * @see Stellen
 * @see MainWindowController
 * @see HauptFenster
 */
public class StellenLabel extends VerschiebbarLabel {

    /**
     * Größe des Abstandes zum Objekt, damit ein Rahmen angezeigt wird.
     * 
     * @since 1.0
     */
    private final static int OFFSET = 1;
    
    /**
     * Variable für die Markierung der Stelle des Workflownetzes.
     * 
     * @since 1.0
     */
    private final boolean markiert;
    
    /**
     * Farbwert für das Objekt.
     * Auswahl aus FarbenEnum, verändert die Hintergrundfarbe des Kreises.
     * 
     * @since 1.0
     */
    private final FarbenEnum meineFarbe;

    /**
     * Vollständiger Konstruktor. Erhält Referenz des Basisdatenmodel (speichert
     * diese aber nicht), weiter wird der Klasse "sein" Kontroller und das
     * HauptFenster mitgeteilt. Es wird auch überprüft, ob das Objekt markiert ist.
     * Konstruktor setzt die Farbe des Objektes.
     * 
     * @since 1.0
     * 
     * @param basis Referenz auf Basisdatenmodel.    
     * @param controller Referenz auf Kontroller.
     * @param mother Referenz auf HauptFenster.
     *
     * @see MainWindowController
     * @see HauptFenster
     * @see Stellen
     */
    public StellenLabel(Stellen basis, MainWindowController controller, HauptFenster mother) {
        //Rufe Konstruktor von VerschiebbarLabel auf
        super(basis, controller, mother);

        //Setzte Markiert (WFN) und Farbe
        this.markiert = basis.getMarkiert();
        this.meineFarbe = basis.getMeineFarbe();
        
        //Ermittle Rahmengröße inkl. Offset
        int size = IDBase.getSize() + OFFSET;

        //Einrichtung der Variablen für die Position der Stelle
        int posX;
        int posY;
       
        //Überprüfe, ob ein relvantes Label vorhanden ist
        if (basis.getLabel().equals("null") || basis.getLabel().equals("")) {
            // Keine Bezeichnung für Label bzw. "null"
            posX = this.position.getX() - (size / 2);
            posY = this.position.getY() - (size / 2);
            
            //Setze Objektgröße und Position
            super.setBounds(posX, posY, size, size);
            
            //Setze Mausover-Text
            super.setToolTipText("Stelle: " + basis.getId() + " (x:" + this.position.getX() + "/y:" + this.position.getY() + ")");

            
        } else {
            //Stelle mit Label
            posX = this.position.getX() - (size / 2);
            posY = this.position.getY() - (size / 2) ;

            //Setze Objektgröße und Position
            super.setBounds(posX, posY, size, size + 20);
            
            //Setze Mausover-Text
            super.setToolTipText("Stelle: ID " + basis.getId() + " Label: " + basis.getLabel() + " (x:" + this.position.getX() + "/y:" + this.position.getY() + ")");
            
            //Setze Text des Objektes
            super.setText(basis.getLabel());

            //Setze Ausrichtung innerhalb des Objektes
            super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            super.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            super.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        }

    }

    /**
     * Diese Methode überschreibt die paint Mehtode der Superklasse, damit Stellen
     * gezeichnet werden.
     * 
     * @since 1.0
     * 
     * @param g     Referenz auf Grafik (s. JavaDoc)
     * 
     */
    @Override
    public void paint(Graphics g) {
        //Paintmethode JLabel, für sichere Konsitenz
        super.paint(g);
        
        //Kantenglättung
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Verschiebung des Kreises,wegen größerem Rahmen
        int posOffset = OFFSET / 2;
        
        //Setze Farbe des Objektes
        switch (meineFarbe) {
            case weiss:
                g.setColor(Color.white);
                break;
            case rot:
                g.setColor(Color.red);
                break;
            case gelb:
                g.setColor(Color.yellow);
                break;
            case grau:
                g.setColor(Color.gray);
                break;
            case gruen:
                g.setColor(Color.green);
                break;
            default:
                g.setColor(Color.black);
                break;
        }

        //Zeichne einen farbigen ausgefüllten Kreis
        g.fillOval(posOffset, posOffset, IDBase.getSize(), IDBase.getSize());
        
        //Setze Farbe auf Schwarz und zeichne einen leeren Kreis
        g.setColor(Color.black);
        g.drawOval(posOffset, posOffset, IDBase.getSize(), IDBase.getSize());
        
        //Wenn Markierung des WFN gesetzt ist, setzt einen Punkt in die mitte des Kreises
        if (markiert) {
            //Definiere Punktgröße, dazu abhänig ist die Position
            int punktGroesse=  IDBase.getSize() / 8;
            int size = (IDBase.getSize() + OFFSET) / 2;
            size -= punktGroesse / 2;

            //Zeichne ausgefüllten Punkt
            g.fillOval(size, size, punktGroesse, punktGroesse);
        }
        //Vernichte Grafik
        temp.dispose();
        g.dispose();
    }
}