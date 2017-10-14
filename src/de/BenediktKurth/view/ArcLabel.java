package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.Vector2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;

/**
 *
 * @author clannick
 */
public class ArcLabel extends BasisLabel {

    private Vector2D sourcePosition;

    private Vector2D targetPosition;
    private int breite, hoehe, posX, posY;
    


    public ArcLabel(Arc basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
        this.sourcePosition = basis.getPositionSource();
        this.targetPosition = basis.getPositionTarget();

        this.breite = Math.abs(sourcePosition.getX() - targetPosition.getX() )+ 2;
        this.hoehe = Math.abs(sourcePosition.getY() - targetPosition.getY() )+ 2;

        this.posX = Math.min(sourcePosition.getX(), targetPosition.getX()) - 1;
        this.posY = Math.min(sourcePosition.getY(), targetPosition.getY())- 1;
     
        
        super.setBounds(posX, posY, breite, hoehe);

        //super.setBorder(BorderFactory.createLineBorder(Color.green));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Vector2D test1 = sourcePosition;
        Vector2D test2 = targetPosition;
        g.setColor(Color.black);
        
        
        if (hoehe < 3 || breite < 3) {
            g.drawLine(1, 1, breite - 1, hoehe - 1);
        } else {
            if ((test1.getX() < test2.getX() && (test1.getY() < test2.getY()))){
                //Von oben links nach unten rechts
              
                 g.drawLine(0, 0, breite, hoehe);
               
            } else if ((test1.getX() > test2.getX() && (test1.getY() < test2.getY()))){
                //Von oben rechts nach unten links
                g.drawLine(0, hoehe, breite, 0);

            } else if ((test1.getX() < test2.getX() && (test1.getY() > test2.getY()))){
                //Von unten links -> oben rechts
                g.drawLine(0, hoehe, breite, 0);

            } else if ((test1.getX() > test2.getX() && (test1.getY() > test2.getY()))){
                //Von unten rechts -> oben links
                g.drawLine(0, 0, breite, hoehe);
            }
        }

    }

}
