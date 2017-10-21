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
    private final int breite;
    
    /**
     * Enthält die Strecke (in pixel) zwischen zwei Objekten vertikal.
     * 
     * @since 1.0
     */
    private final int hoehe;
    
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
        super(basis, controller, mother);
        
        this.sourcePosition = basis.getPositionSource();
        this.targetPosition = basis.getPositionTarget();
       
        this.breite = Math.abs(sourcePosition.getX() - targetPosition.getX() )+ 2;
        this.hoehe = Math.abs(sourcePosition.getY() - targetPosition.getY() )+ 2;

        this.posX = Math.min(sourcePosition.getX(), targetPosition.getX()) - 1;
        this.posY = Math.min(sourcePosition.getY(), targetPosition.getY())- 1;

        super.setBounds(posX, posY, breite, hoehe);
        super.setToolTipText("Arc: Von " + basis.getSource() + " nach " + basis.getTarget() + ".");
        

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
        super.paint(g);
 
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  
        Vector2D sourcePos = sourcePosition;
        Vector2D targetPos = targetPosition;
  
        g.setColor(Color.black);

        if (hoehe < 3 || breite < 3) {
            if ( sourcePos.getX() < targetPos.getX()) {
                //Von links nach rechts
                g.drawLine(1, 1, breite - 1, hoehe - 1);
              
            } else if (sourcePos.getX() > targetPos.getX() ){
                //Von rechts nach links
                g.drawLine(breite - 1, hoehe - 1, 1, 1);
                
            } else if (sourcePos.getY() < targetPos.getY() ){
                //Von oben nach unten 
                g.drawLine(1, 1, breite - 1, hoehe - 1);
                
            } else if (sourcePos.getY() > targetPos.getY() ){
                //Von unten nach oben
                g.drawLine(breite - 1, hoehe - 1, 1, 1);
            }
   
        } else {
           
            if ((sourcePos.getX() < targetPos.getX() && (sourcePos.getY() < targetPos.getY()))){
                //Von oben links nach unten rechts
                g.drawLine(0, 0, breite, hoehe);
          
            } else if ((sourcePos.getX() > targetPos.getX() && (sourcePos.getY() < targetPos.getY()))){
                //Von oben rechts nach unten links
                g.drawLine(breite, 0, 0, hoehe);

            } else if ((sourcePos.getX() < targetPos.getX() && (sourcePos.getY() > targetPos.getY()))){
                //Von unten links -> oben rechts
                g.drawLine(0, hoehe, breite, 0);

            } else if ((sourcePos.getX() > targetPos.getX() && (sourcePos.getY() > targetPos.getY()))){
                //Von unten rechts -> oben links
                g.drawLine(breite, hoehe, 0, 0);
            }
        }

        temp.dispose();
        g.dispose();
    }

}
