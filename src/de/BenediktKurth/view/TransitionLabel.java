package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.FarbenEnum;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Transition;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author clannick
 */
public class TransitionLabel extends VerschiebbarLabel {

    private FarbenEnum meineFarbe;
   
    public TransitionLabel(Transition basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
        super.setText(basis.getLabel());
               
        this.meineFarbe = basis.getMeineFarbe();
        
        int size = IDBase.getSize() + 21;
        int posX = this.position.getX() - (size / 2);
        int posY = this.position.getY() - ((size-20) / 2);
        
            
        super.setText(basis.getLabel());
        super.setToolTipText("Transition: ID " + basis.getId() + " Label "+ basis.getLabel() + " (x:" + this.position.getX() + "/y:" + this.position.getY() + ")" );
       
        super.setBounds(posX, posY, size, size);
        
        super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        super.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        
        super.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        switch (meineFarbe){
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
       
        g.fillRect(10, 0, IDBase.getSize(), IDBase.getSize());
        g.setColor(Color.black);
        g.drawRect(10, 0, IDBase.getSize(), IDBase.getSize());
        g.dispose();
    }
}