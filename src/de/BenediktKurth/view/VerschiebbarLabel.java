package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.model.Vector2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
        
        int interneID = this.interneID;
        
        super.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public synchronized void mouseDragged(MouseEvent evt){
               
                
                if (isMarkiert) {
                    int dx = evt.getX() - point.x;
                    int dy = evt.getY() - point.y;
                    Vector2D test = new Vector2D(dx,dy);
                    System.out.println(dx + " " + dy);
                    controller.verschiebeMarkierteUmOffset(mother.getDarstellung(), mother.getInterneIDmarkierter(), test);
                    point = evt.getPoint();
                       
                }
                
                
              
            }
        });

       
    }
    
    
    
    
    
}
