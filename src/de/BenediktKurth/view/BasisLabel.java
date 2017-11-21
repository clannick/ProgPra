package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Vector2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.TOP;
import javax.swing.border.Border;

/**
 *
 * @author clannick
 */
public abstract class BasisLabel extends JLabel {

    protected final int interneID;

    private HauptFenster mother;

    protected MainWindowController controller;

    private Border stani = BorderFactory.createLineBorder(Color.yellow);
    
    protected boolean isMarkiert = false;

    protected Point point = new Point(0,0);

    public BasisLabel(IDBase basis, MainWindowController controller, HauptFenster mother) {
        this.interneID = basis.getInterneID();
        this.controller = controller;
        this.mother = mother;

        super.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        super.setLayout(null);

        for (Integer x : mother.getInterneIDmarkierter()) {
            if (x == basis.getInterneID()) {
                super.setBorder(stani);
                this.isMarkiert = true;
                break;
            }
        }

               
        super.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON1) {
                    
                    if (evt.isControlDown() || mother.getInterneIDmarkierter().isEmpty()) {
                        setFocusAndBorder(basis);
                        controller.neueDarstellungOhneTest();

                    } else {
                        mother.focusZuruecksetzen();
                        setFocusAndBorder(basis);
                        controller.neueDarstellungOhneTest();
                    }
                    
                } else {
                    controller.simuliereSicheresWorklflownetz(evt, getInterneID());
                    
                }
                
            }
            
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                   point = evt.getPoint();
                   repaint();
            }
            
     
            


        });

    }

    private void setFocusAndBorder(IDBase basis) {
        super.setBorder(stani);
        mother.getInterneIDmarkierter().add(basis.getInterneID());
    }

    public final int getInterneID() {
        return this.interneID;
    }

}
