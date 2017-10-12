package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.model.Transition;
import de.BenediktKurth.model.Vector2D;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author clannick
 */
public class PosNameLabel extends JLabel {

    private final int interneID;

    private HauptFenster mother;

    private Vector2D position;

    public PosNameLabel(PosNameBase basis, MainWindowController controller, HauptFenster mother) {
        this.interneID = basis.getInterneID();
        this.position = basis.getPosition();
        this.mother = mother;

        super.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.lala(evt, getInterneID());
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

    public final Vector2D getPosition() {
        return position;
    }

    public final void setPosition(Vector2D position) {
        this.position = position;
    }

    public final int getInterneID() {
        return this.interneID;
    }
    

}
