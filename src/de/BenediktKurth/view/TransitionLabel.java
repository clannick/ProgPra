package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Transition;
import de.BenediktKurth.model.Vector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author clannick
 */
public class TransitionLabel extends BasisLabel {

   
    public TransitionLabel(Transition basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
        super.setText(basis.getLabel());
        super.position = basis.getPosition();
        
        int size = IDBase.getSize() + 20;
        int posX = this.position.getX() - (size / 2);
        int posY = this.position.getY() - ((size-20) / 2);
        
            
        super.setText(basis.getLabel());
       
        super.setBounds(posX, posY, size, size);
        
        super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        super.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        
        super.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    }


    public final void setPosition(Vector2D position) {
        this.position = position;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D temp = (Graphics2D) g;
        temp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.white);
        g.fillRect(10, 0, IDBase.getSize(), IDBase.getSize());
        g.setColor(Color.black);
        g.drawRect(10, 0, IDBase.getSize(), IDBase.getSize());

    }
}