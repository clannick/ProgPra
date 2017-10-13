package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import javax.swing.JPopupMenu;

/**
 *
 * @author clannick
 */
public class PopUpMenu extends JPopupMenu{
    
    private final MainWindowController controller;
    
    
    public PopUpMenu(MainWindowController controller,int interneID, HauptFenster mother) {
        this.controller = controller;
        
        
        umbennen = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        loeschen = new javax.swing.JMenuItem();
        kopieren = new javax.swing.JMenuItem();

        umbennen.setText("Umbennen");
        umbennen.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                umbennenMouseClicked(evt);
            }
        });
        super.add(umbennen);
        super.add(jSeparator1);

        loeschen.setText("Löschen");
        loeschen.addMouseListener(new java.awt.event.MouseAdapter() {
        
            
            @Override
             public void mouseReleased(java.awt.event.MouseEvent evt) {
                controller.removePosNameBase(interneID);
                controller.erzeugeNeueDarstellung();
                
            }
        });
            
        super.add(loeschen);

        kopieren.setText("Kopieren");
        kopieren.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kopierenMouseClicked(evt);
            }
        });
        super.add(kopieren);


    }                        

    private void umbennenMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void loeschenMouseClicked(java.awt.event.MouseEvent evt) {                                      
       
    }                                     

    private void kopierenMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    }                                     


    // Variables declaration - do not modify                     
 
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem kopieren;
    private javax.swing.JMenuItem loeschen;
    private javax.swing.JMenuItem umbennen;
    // End of variables declaration                   
}
