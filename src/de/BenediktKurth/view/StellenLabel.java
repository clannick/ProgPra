package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Stellen;

import javax.swing.Icon;

/**
 *
 * @author clannick
 */
public class StellenLabel extends PosNameLabel {

    private static Icon darstellung = new javax.swing.ImageIcon(PosNameLabel.class.getResource("/ressource/kreis.png"));
    private static Icon darstellungMitPunkt = new javax.swing.ImageIcon(PosNameLabel.class.getResource("/ressource/kreis_mit.png"));
  

    public StellenLabel(Stellen basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
        super.setText(basis.getLabel());

        super.setBounds(basis.getPosition().getX(), basis.getPosition().getY(), IDBase.getSize() + 50, IDBase.getSize() + 50);
        super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        super.setIcon(darstellung); // 
        super.setText(basis.getLabel());
        super.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        super.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        super.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        super.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        
        super.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.lala(evt, getInterneID());
                switchColor(-1);
                repaintThis();
                
                
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                controller.setPosition(evt, getInterneID());
                controller.createView(mother.getDarstellung());
                controller.setzteDarstellung(mother.getZeichenflaeche(), mother.getDarstellung());
                mother.getZeichenflaeche().repaint();

            }

        });    
    }

    private void repaintThis(){
        super.repaint();
    }
    public void switchColor(int geht) {
        switch (geht) {
            case 1:
                super.setIcon(darstellungMitPunkt);
                super.repaint();
            default:
                super.setIcon(darstellung);
                super.repaint();
        }

    }

}
