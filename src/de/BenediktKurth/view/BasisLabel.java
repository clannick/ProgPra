package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.Vector2D;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author clannick
 */
public class BasisLabel extends JLabel {

    private final int interneID;

    private HauptFenster mother;

    protected Vector2D position = new Vector2D(0,0);
    
    protected boolean markiert;
    
    protected boolean focus;
    
    
    
    public BasisLabel(IDBase basis, MainWindowController controller, HauptFenster mother) {
        this.interneID = basis.getInterneID();
       
        this.mother = mother;
        
        super.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        JLabel test = this;
        super.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.lala(evt, getInterneID());
            
                if (evt.getButton() == MouseEvent.BUTTON3){
                    PopUpMenu temp = new PopUpMenu(controller, basis.getInterneID(), mother);
                    temp.show(test,evt.getX(),evt.getY());
                    
                }
            }
            
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                

            }

        });

        
        
        
    }

    public final Vector2D getPosition() {
        return position;
    }



    public final int getInterneID() {
        return this.interneID;
    }
    

}
