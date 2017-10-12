package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Transition;
import javax.swing.Icon;

/**
 *
 * @author clannick
 */
public class TransitionLabel extends PosNameLabel {

    private static Icon darstellungWeiss = new javax.swing.ImageIcon(PosNameLabel.class.getResource("/ressource/viereck.png"));
    private static Icon darstellungGruen = new javax.swing.ImageIcon(PosNameLabel.class.getResource("/ressource/viereck_gruen.png"));
    private static Icon darstellungRot = new javax.swing.ImageIcon(PosNameLabel.class.getResource("/ressource/viereck_rot.png"));

    public TransitionLabel(Transition basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
        super.setText(basis.getLabel());

        super.setBounds(basis.getPosition().getX(), basis.getPosition().getY(), IDBase.getSize() + 50, IDBase.getSize() + 50);
        super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        super.setIcon(darstellungWeiss); // 
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
            case -1:
                super.setIcon(darstellungRot);
                super.repaint();
                break;
            case 1:
                super.setIcon(darstellungGruen);
                super.repaint();
            default:
                super.setIcon(darstellungWeiss);
                super.repaint();
        }

    }

}
