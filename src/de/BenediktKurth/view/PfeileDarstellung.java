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
 *
 * @author clannick
 */
public class PfeileDarstellung extends JLabel {

    private final Vector2D sourcePosition;
    private final Vector2D targetPosition;

    private final double RADIUSKREIS = IDBase.getSize() / 2;
    private final double PFEIL_GROESSE = IDBase.getSize() / 3;
    private final IDBase TARGET;

    private final double GROESSE = IDBase.getSize() / 2;
    
    private final int FENSTER_GROESSE_X;
    private final int FENSTER_GROESSE_Y;
    

    public PfeileDarstellung(Arc arc, MainWindowController controller) {
        super();
        this.sourcePosition = arc.getPositionSource();
        this.targetPosition = arc.getPositionTarget();
        this.TARGET = controller.sucheMitID(arc.getTarget());
        this.FENSTER_GROESSE_X = controller.getZeichenflaecheGroesse().getX();
        this.FENSTER_GROESSE_Y = controller.getZeichenflaecheGroesse().getY();
        super.setBounds(0, 0, FENSTER_GROESSE_X, FENSTER_GROESSE_Y);
    }

    /**
     * Die Methode zeichnet die Pfeilspitzen auf den Hintergrund. Es wird mithilfe
     * des Mittelpunktes und diverser Winkelfunktionen (sin,cos,tan) die passenden 
     * Außenpositionen ermitellt und dann ein Pfeil ausgehen dieser Position gezeichnet.
     * Weiter wird der Rahmen für den Arbeitsbereich gezeichnet. 
     *
     * 
     * @param g  Graphics Übergibt die "Zeichenfläche" auf der gezeichnet wird.
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        
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
        if (xTarget < xSource) {
            phi = phi + Math.PI;
        }
        double phiInGrad = Math.toRadians(360.0);
        //Winkel Positiv machen
        phi = phi + Math.PI * 2;
        
        //Wenn Winkel größer 360 Grad dann -360 Grad
        if (phi >= phiInGrad) {
            phi -= phiInGrad;
        }
        
        //Verschiebung zur "richtigen" Targetposition
      
        Double xOffset = 0.0;
        Double yOffset = 0.0;

        //Berechne Schnittpunkt gerade und Objekt
        if (TARGET instanceof Stellen) {
            // Kreisberechnung
            xOffset = -Math.cos(phi) * RADIUSKREIS;
            yOffset = -Math.sin(phi) * RADIUSKREIS;
            
        } else if (TARGET instanceof Transition) {
            
            // Vierecksberechnung
            if (phi == Math.toRadians(0.0)){
                //Links Mitte
                xOffset = -GROESSE;
                yOffset = 0.0;
                
            } else if (phi > Math.toRadians(0.0) && phi < Math.toRadians(45.0)){
                //Seite 1
                xOffset = -GROESSE;
                yOffset = -GROESSE*Math.tan(phi);
                
            } else if(phi == Math.toRadians(45.0)){
                //Oben Links
                xOffset = -GROESSE;
                yOffset = -GROESSE;
                
            } else if (phi > Math.toRadians(45.0) && phi < Math.toRadians(90.0)){
                //Seite 2
                xOffset = -GROESSE/Math.tan(phi);
                yOffset = -GROESSE;
                
            } else if (phi == Math.toRadians(90.0) ) {
                //Oben Mitte
                xOffset = 0.0;
                yOffset = -GROESSE;
                
            } else if (phi > Math.toRadians(90.0) && phi < Math.toRadians(135.0)){
                //Seite 3
                xOffset = GROESSE*Math.tan(phi-Math.toRadians(90.0));
                yOffset = -GROESSE;
                
            } else if(phi == Math.toRadians(135.0)){
                //Oben Rechts
                xOffset = GROESSE;
                yOffset = -GROESSE;
                
            } else if (phi > Math.toRadians(135.0) && phi < Math.toRadians(180.0)){
                //Seite 4
                xOffset = GROESSE;
                yOffset = -GROESSE/Math.tan(phi-Math.toRadians(90.0));
                
            } else if(phi == Math.toRadians(180.0)){
                //Rechts Mitte
                xOffset = GROESSE;
                yOffset = 0.0;
                
            } else if (phi > Math.toRadians(180.0) && phi < Math.toRadians(225.0)){
                //Seite 5
                xOffset = GROESSE;
                yOffset = GROESSE*Math.tan(phi-Math.toRadians(180.0));
                
            } else if(phi == Math.toRadians(225.0)){
                //Rechts Unten
                xOffset = GROESSE;
                yOffset = GROESSE;
                
            } else if (phi > Math.toRadians(225.0) && phi < Math.toRadians(270.0)){
                //Seite 6
                xOffset = GROESSE/Math.tan(phi-Math.toRadians(180.0));
                yOffset = GROESSE;
                
            } else if(phi == Math.toRadians(270.0)){
                //Unten Mitte
                xOffset = 0.0;
                yOffset = GROESSE;
                
            } else if (phi > Math.toRadians(270.0) && phi < Math.toRadians(315.0)){
                //Seite 7
                xOffset = -GROESSE*Math.tan(phi-Math.toRadians(270.0));
                yOffset = GROESSE;
                
            } else if(phi == Math.toRadians(315.0)){
                //Unten Links
                xOffset = -GROESSE;
                yOffset = GROESSE;
                
            } else if (phi > Math.toRadians(315.0) && phi < Math.toRadians(360.0)){
                //Seite 8
                xOffset = -GROESSE;
                yOffset = GROESSE/Math.tan(phi-Math.toRadians(270.0));
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
