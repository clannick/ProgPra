package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.model.Vector2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author clannick
 */
public abstract class VerschiebbarLabel extends BasisLabel{
    
    protected Vector2D position = new Vector2D(0, 0);   

    
    public VerschiebbarLabel(PosNameBase basis, MainWindowController controller, HauptFenster mother) {
        super(basis, controller, mother);
        int xPosition = basis.getPosition().getX();
        int yPosition = basis.getPosition().getY();
        
        if ( xPosition > mother.getZeichenflaeche().getPreferredSize().height){
            xPosition = mother.getZeichenflaeche().getPreferredSize().height;
        }
        if ( yPosition > mother.getZeichenflaeche().getPreferredSize().width){
            yPosition = mother.getZeichenflaeche().getPreferredSize().width;
        }
        
        basis.setPosition(xPosition, yPosition);
        this.position = new Vector2D(xPosition, yPosition);
        
       
    }
    
    
    
    
    
}
