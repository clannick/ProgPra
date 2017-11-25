package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;

import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Vector2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *  Diese Klasse implementiert die Darstellung einer Kante auf Basis der Arc-Klasse
 *  aus dem Basisdatenmodel. 
 * 
 *  Klasse wirft keine Exceptions.
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 * 
 * @see Vector2D
 * @see IDBase
 * @see BasisLabel
 */
public class ArcLabel extends BasisLabel {
    /**
     * Eindeutige Serialnummer für die Klasse.
     * 
     * @since 1.0
     */
    private static final long serialVersionUID = 501L;

    /**
     * Enthält die Position der Quelle des Striches.
     * 
     * @since 1.0
     * 
     * @see Vector2D
     */
    private final Vector2D sourcePosition;

    /**
     * Enthält die Position des Zieles des Striches.
     * 
     * @since 1.0
     * 
     * @see Vector2D
     */
    private final Vector2D targetPosition;
    
    /**
     * Enthält die Strecke (in pixel) zwischen zwei Objekten horizontal.
     * 
     * @since 1.0
     */
    private int breite;
    
    /**
     * Enthält die Strecke (in pixel) zwischen zwei Objekten vertikal.
     * 
     * @since 1.0
     */
    private int hoehe;
    
    /**
     * Enthält die horizontal Position des einzufügenden Striches.
     * Zur berechnung wird der Betrag von |x2 - x1| gebildet.
     * 
     * @since 1.0
     */
    private final int posX;
    
    /**
     * Enthält die vertikale Position des einzufügenden Striches.
     * Zur berechnung wird der Betrag von |y2 - y1| gebildet. 
     * 
     * @since 1.0
     */
    private final int posY;


    /**
     * Vollstädiger Konstruktor der Klasse. 
     * Auf basis der Basisdaten der Kante im Basisdatenmodel wird eine entsprechende 
     * Darstellung durch die paint()-Methode generiert. Der Kostruktor setzt lediglich 
     * die entsprechenden Komponenten und gibt controller und mother an BasisLabel weiter.
     * 
     * @since 1.0
     * 
     * @param basis         Arc                     Referenz auf Basisdaten
     * @param controller    MainWindowController    MVC - Controller
     * @param mother        HauptFenster            Das Frame aufdem Objekt "sitz".
     * 
     * @see Arc
     * @see MainWindowController
     * @see HauptFenster
     */
    public ArcLabel(Arc basis, MainWindowController controller, HauptFenster mother) {
        //Rufe Konstrtukor von BasisLabel auf
        super(basis, controller, mother);
       
        //Hole Positionen von Source und Target Obejkten
        this.sourcePosition = basis.gibPositionSource();
        this.targetPosition = basis.gibPositionTarget();
       
        //Berechne Bereite und Höhe des Rahmens für den Strich
        this.breite = Math.abs(sourcePosition.gibX() - targetPosition.gibX() )+ 2;
        this.hoehe = Math.abs(sourcePosition.gibY() - targetPosition.gibY() )+ 2;

        //Berechne Position des Rahmens für den Strich (oberste linke Ecke)
        this.posX = Math.min(sourcePosition.gibX(), targetPosition.gibX()) - 1;
        this.posY = Math.min(sourcePosition.gibY(), targetPosition.gibY())- 1;

        //Setzte Position, Größe und ToolTipText
        super.setBounds(posX, posY, breite, hoehe);
        super.setToolTipText("Arc: Von " + basis.gibSource() + " nach " + basis.gibTarget() + ".");
    }

    /**
     * Methode setzt die Breite des Rahmes des Striches. Die Methode wird benötigt
     * um das Verschieben per Drag-and-Drop zu ermöglichen, da die Strich-Positions
     * Ermittlung in der paint()-Methode auf diesen Wert zurückgreift.
     * 
     * @param breite Neue Breite des Rahmes als integer-Wert.
     * 
     * @since 1.0
     */
    public void setzeBreite(int breite) {
        this.breite = breite;
    }

    /**
     * Methode setzt die Höhe des Rahmes des Striches. Die Methode wird benötigt
     * um das Verschieben per Drag-and-Drop zu ermöglichen, da die Strich-Positions
     * Ermittlung in der paint()-Methode auf diesen Wert zurückgreift.
     * 
     * @param hoehe Neue Höhe des Rahmes als integer-Wert.
     * 
     * @since 1.0
     */
    public void setzeHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

     /**
     * Überschriebene Methode der Klasse JLabel um auf Oberfläche zu zeichen.
     * Die Methode zieht je nach Quell- und Zielpositon einen Strich zwischen 
     * den beiden Mittelpunkten der Objekte.
     * 
     * @since 1.0
     * 
     * @param g     Graphics enthält referenz auf die zu zeichnende Fläche
     * 
     * @see Graphics
     * @see Graphics2D
     */
    @Override
    public void paint(Graphics g) {
        //Rufe Paint-Methode der super-Klasse auf
        super.paint(g);
 
        //Umwandlung in Graphics2D um Antialiasing zu nutzen (Kantenglättung)
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Setzte Farbe auf Schwart
        g.setColor(Color.black);

        //Überprüfe ob es sich um eine Horizontale oder Vertikale Linie handelt, wenn ja mittig zeichnen
        if (this.getHeight()< 3 || this.getWidth() < 3) { 
            if ( sourcePosition.gibX() < targetPosition.gibX()) {
                //Von links nach rechts
                g.drawLine(1, 1, this.getWidth()-1, this.getHeight() - 1);
              
            } else if (sourcePosition.gibX() > targetPosition.gibX() ){
                //Von rechts nach links
                g.drawLine(this.getWidth() - 1, this.getHeight() - 1, 1, 1);
                
            } else if (sourcePosition.gibY() < targetPosition.gibY() ){
                //Von oben nach unten 
                g.drawLine(1, 1, this.getWidth() - 1, this.getHeight() - 1);
                
            } else if (sourcePosition.gibY() > targetPosition.gibY() ){
                //Von unten nach oben
                g.drawLine(this.getWidth() - 1, this.getHeight() - 1, 1, 1);
            }
   
        } else {
           
            //Es ist keine gerade Linie, sondern eine Diagonale von A nach B
            if ((sourcePosition.gibX() < targetPosition.gibX() && (sourcePosition.gibY() < targetPosition.gibY()))){
                //Von oben links nach unten rechts
                g.drawLine(0, 0, breite, hoehe);
          
            } else if ((sourcePosition.gibX() > targetPosition.gibX() && (sourcePosition.gibY() < targetPosition.gibY()))){
                //Von oben rechts nach unten links
                g.drawLine(breite, 0, 0, hoehe);

            } else if ((sourcePosition.gibX() < targetPosition.gibX() && (sourcePosition.gibY() > targetPosition.gibY()))){
                //Von unten links -> oben rechts
                g.drawLine(0, hoehe, breite, 0);

            } else if ((sourcePosition.gibX() > targetPosition.gibX() && (sourcePosition.gibY() > targetPosition.gibY()))){
                //Von unten rechts -> oben links
                g.drawLine(breite, hoehe, 0, 0);
            }
        }

        //Entferne diese Grafik (Speicherplatz sparen)
        temp.dispose();
        g.dispose();
    }
}