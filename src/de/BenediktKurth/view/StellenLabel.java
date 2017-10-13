package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Vector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author clannick
 */
public class StellenLabel extends BasisLabel {

    private final static int OFFSET = 6;
    
    public StellenLabel(Stellen basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
        super.position = basis.getPosition();
        
        super.markiert = basis.getMarkiert();
        
        int size = IDBase.getSize() + OFFSET;
        int posX = this.position.getX() - (size/2);
        int posY = this.position.getY() - (size/2);
        
             
        
        super.setBounds(posX, posY, size, size);
        
       
        super.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        super.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        super.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));


   
    }

    public final void setPosition(Vector2D position) {
        this.position = position;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int pos = OFFSET / 2;
        g.setColor(Color.white);
        g.fillOval(pos, pos, IDBase.getSize(), IDBase.getSize());
        g.setColor(Color.black);
        g.drawOval(pos, pos, IDBase.getSize(), IDBase.getSize());
        if (super.markiert) {
            int size = (IDBase.getSize() + OFFSET) / 2;
            int kreisGroesse = IDBase.getSize()/8;
            size -= kreisGroesse/2;
            
            
            
            g.fillOval(size, size, kreisGroesse, kreisGroesse);
        }
    }
}
