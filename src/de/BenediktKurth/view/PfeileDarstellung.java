package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Transition;
import de.BenediktKurth.model.Vector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JLabel;

/**
 * Diese Klasse stellt die Pfeilspitzen der Kanten da. Die Klasse ist gelöst von 
 * der eigentlichen Kantendarstellung, um spätere Änderungen sperat zu realisieren.
 * Die Pfeilspitzen werden als erstes unter alle anderen Objekt gezeichnet. Es werden
 * die globalen Positionen genutzt und die Java-Klasse Math.*.
 * 
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 * 
 * @see JLabel
 * @see Arc
 * @see Stellen
 * @see Transition
 * @see Vector2D
 * @see java.awt
 */
public class PfeileDarstellung extends JLabel {

    /**
     * Referenz auf die Position das QuellObjektes als Vector2D.
     * 
     * @since 1.0
     * 
     * @see Vector2D
     */
    private final Vector2D              sourcePosition;
    
    /**
     * Referenz auf die Position das ZielObjektes als Vector2D.
     * 
     * @since 1.0
     * 
     * @see Vector2D
     */
    private final Vector2D              targetPosition;
    
    /**
     * Kreisradius bzw. halbe Kantenlänge eines Quadrates der Objekte auf Basis der
     * globalen Größe.
     * 
     * @since 1.0
     * 
     * @see IDBase
     */
    private final double                RADIUSKREIS_GROESSE = IDBase.getGroesse() / 2;
    
    /**
     * Pfeilgröße in relation zur Größe der Objekte.
     * 
     * @since 1.0
     * 
     * @see IDBase
     */
    private final double                PFEIL_GROESSE = IDBase.getGroesse() / 3;
    
    /**
     * Referenz auf Zeilobjekt. Wird benötigt da je nach Objekt eine andere Berechnung
     * für den Schnittpunkt Kante/Objekt benötigt wird.
     * 
     * @since 1.0
     */
    private final IDBase                target;

    /**
     * Vollständigert Konstruktor. Erhält Referenzen auf "seine" Kante aus den Basisdaten
     * und dem Kontroller.
     * 
     * @param arc           Referenz auf Kante. Zur Ermittlung Source- und Target-Position
     * @param controller    Referenz auf Kontroller. Zum ermitteln der Arbeitsflächengrößen und des Targets
     * 
     * @since 1.0
     */
    public PfeileDarstellung(Arc arc, MainWindowController controller) {
        //Konstruktor JLabel
        super();
        
        //Setzte Position und ermittle Target
        this.sourcePosition = arc.getPositionSource();
        this.targetPosition = arc.getPositionTarget();
        this.target = controller.sucheMitID(arc.getTarget());
     
        //Setze Rahmen über gesamte Arbeitsflächengröße
        super.setBounds(0, 0, controller.getZeichenflaecheGroesse().getX(), controller.getZeichenflaecheGroesse().getY());
    }

    /**
     * Die Methode zeichnet die Pfeilspitzen auf den Hintergrund. Es wird mithilfe
     * des Mittelpunktes und diverser Winkelfunktionen (sin,cos,tan) der passenden 
     * Schnittpunkt ermitellt und dann ein Pfeil ausgehen dieser Position gezeichnet.
     * 
     * @param g  Graphics Übergibt die "Zeichenfläche" auf der gezeichnet wird.
     * 
     * @since 1.0
     * 
     * @see Math
     */
    @Override
    public void paint(Graphics g) {
        //Paintmethode JLabel, für sichere Konsitenz
        super.paintComponent(g);
        
        //Setze Farbe auf Schwarz
        g.setColor(Color.black);
        
        //Kantenglättung
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Hol x und y Werte
        double xSource = sourcePosition.getX();
        double ySource = sourcePosition.getY();
        double xTarget = targetPosition.getX();
        double yTarget = targetPosition.getY();

        //Berechne Winkel
        double phi = Math.atan((yTarget - ySource) / (xTarget - xSource));
        
        //Wenn Target links von Source, dann 180° hinzu addieren
        if (xTarget < xSource) {
            phi += Math.PI;
        }
        double phiInGrad = Math.toRadians(360.0);
        //Winkel Positiv machen
        phi += Math.PI * 2;
        
        //Wenn Winkel größer 360 Grad dann -360 Grad
        if (phi >= phiInGrad) {
            phi -= phiInGrad;
        }
        
        //Verschiebung zur "richtigen" Targetposition
        Double xOffset = 0.0;
        Double yOffset = 0.0;

        //Berechne Schnittpunkt gerade und Objekt
        if (target instanceof Stellen) {
            // Kreisberechnung
            xOffset = -Math.cos(phi) * RADIUSKREIS_GROESSE;
            yOffset = -Math.sin(phi) * RADIUSKREIS_GROESSE;
            
        } else if (target instanceof Transition) {
            
            // Vierecksberechnung
            if (phi == Math.toRadians(0.0)){
                //Links Mitte
                xOffset = -RADIUSKREIS_GROESSE;
                yOffset = 0.0;
                
            } else if (phi > Math.toRadians(0.0) && phi < Math.toRadians(45.0)){
                //Seite 1
                xOffset = -RADIUSKREIS_GROESSE;
                yOffset = -RADIUSKREIS_GROESSE*Math.tan(phi);
                
            } else if(phi == Math.toRadians(45.0)){
                //Oben Links
                xOffset = -RADIUSKREIS_GROESSE;
                yOffset = -RADIUSKREIS_GROESSE;
                
            } else if (phi > Math.toRadians(45.0) && phi < Math.toRadians(90.0)){
                //Seite 2
                xOffset = -RADIUSKREIS_GROESSE/Math.tan(phi);
                yOffset = -RADIUSKREIS_GROESSE;
                
            } else if (phi == Math.toRadians(90.0) ) {
                //Oben Mitte
                xOffset = 0.0;
                yOffset = -RADIUSKREIS_GROESSE;
                
            } else if (phi > Math.toRadians(90.0) && phi < Math.toRadians(135.0)){
                //Seite 3
                xOffset = RADIUSKREIS_GROESSE*Math.tan(phi-Math.toRadians(90.0));
                yOffset = -RADIUSKREIS_GROESSE;
                
            } else if(phi == Math.toRadians(135.0)){
                //Oben Rechts
                xOffset = RADIUSKREIS_GROESSE;
                yOffset = -RADIUSKREIS_GROESSE;
                
            } else if (phi > Math.toRadians(135.0) && phi < Math.toRadians(180.0)){
                //Seite 4
                xOffset = RADIUSKREIS_GROESSE;
                yOffset = -RADIUSKREIS_GROESSE/Math.tan(phi-Math.toRadians(90.0));
                
            } else if(phi == Math.toRadians(180.0)){
                //Rechts Mitte
                xOffset = RADIUSKREIS_GROESSE;
                yOffset = 0.0;
                
            } else if (phi > Math.toRadians(180.0) && phi < Math.toRadians(225.0)){
                //Seite 5
                xOffset = RADIUSKREIS_GROESSE;
                yOffset = RADIUSKREIS_GROESSE*Math.tan(phi-Math.toRadians(180.0));
                
            } else if(phi == Math.toRadians(225.0)){
                //Rechts Unten
                xOffset = RADIUSKREIS_GROESSE;
                yOffset = RADIUSKREIS_GROESSE;
                
            } else if (phi > Math.toRadians(225.0) && phi < Math.toRadians(270.0)){
                //Seite 6
                xOffset = RADIUSKREIS_GROESSE/Math.tan(phi-Math.toRadians(180.0));
                yOffset = RADIUSKREIS_GROESSE;
                
            } else if(phi == Math.toRadians(270.0)){
                //Unten Mitte
                xOffset = 0.0;
                yOffset = RADIUSKREIS_GROESSE;
                
            } else if (phi > Math.toRadians(270.0) && phi < Math.toRadians(315.0)){
                //Seite 7
                xOffset = -RADIUSKREIS_GROESSE*Math.tan(phi-Math.toRadians(270.0));
                yOffset = RADIUSKREIS_GROESSE;
                
            } else if(phi == Math.toRadians(315.0)){
                //Unten Links
                xOffset = -RADIUSKREIS_GROESSE;
                yOffset = RADIUSKREIS_GROESSE;
                
            } else if (phi > Math.toRadians(315.0) && phi < Math.toRadians(360.0)){
                //Seite 8
                xOffset = -RADIUSKREIS_GROESSE;
                yOffset = RADIUSKREIS_GROESSE/Math.tan(phi-Math.toRadians(270.0));
            }

        } else {
            //Keine Stelle oder Transition (für Erweiterungen)
            xOffset = 0.0;
            yOffset = 0.0;
        }

        //Zielposition am Objekt
        int xSpitze = (int)xTarget + xOffset.intValue();
        int ySpitze = (int)yTarget + yOffset.intValue();

        //Pfeil erstellen        
        Polygon pfeil = new Polygon();

        //Pfeilspitze
        pfeil.addPoint(xSpitze, ySpitze);

        //Erster Punkt 
        double xAlt = - PFEIL_GROESSE;
        double yAlt =  PFEIL_GROESSE / 2.5;
        double xNeu = ( xAlt * Math.cos(phi) ) - ( yAlt * Math.sin(phi) ) + xSpitze;
        double yNeu = ( xAlt * Math.sin(phi) ) + ( yAlt * Math.cos(phi) ) + ySpitze;
        pfeil.addPoint((int) xNeu, (int) yNeu);

        //Zweiter Punkt 
        yAlt = - yAlt;
        xNeu = xAlt * Math.cos(phi) - yAlt * Math.sin(phi) + xSpitze;
        yNeu = xAlt * Math.sin(phi) + yAlt * Math.cos(phi) + ySpitze;
        pfeil.addPoint((int) xNeu, (int) yNeu);

        //Pfeil in Graphics einfügen
        temp.fillPolygon(pfeil);

        //Vernichte Grafik
        g.dispose();
        temp.dispose();
    }
}