package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Vector2D;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author clannick
 */
public class BasisLabel extends JLabel {

    private final int interneID;

    private HauptFenster mother;

    protected Vector2D position = new Vector2D(0, 0);

    protected boolean markiert;

    protected boolean focus = false;

    private Border stani = BorderFactory.createLineBorder(Color.yellow);

    public BasisLabel(IDBase basis, MainWindowController controller, HauptFenster mother) {
        this.interneID = basis.getInterneID();

        this.mother = mother;

        super.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        super.setFocusable(true);
        JLabel test = this;

        super.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("3");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("2");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("1");
            }
        });
        


                                  
        super.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                if (evt.isControlDown() || !mother.getIstWasMarkiert()) {
                    setFocus(true);
                    setBorder(stani);
                    mother.setIstWasMarkiert(true);
                    mother.getInterneIDmarkierter().add(basis.getInterneID());
                    
                } else{
                        mother.focusZuruecksetzen();
                        setFocus(true);
                        setBorder(stani);
                        mother.setIstWasMarkiert(true);
                        mother.getInterneIDmarkierter().add(basis.getInterneID());
                }
               

                controller.lala(evt, getInterneID());

                if (evt.getButton() == MouseEvent.BUTTON3) {
                    PopUpMenu temp = new PopUpMenu(controller, basis.getInterneID(), mother);
                    temp.show(test, evt.getX(), evt.getY());

                }
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {

            }

        });

    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public boolean getFocus() {
        return focus;
    }

    public final Vector2D getPosition() {
        return position;
    }

    public final int getInterneID() {
        return this.interneID;
    }

}
