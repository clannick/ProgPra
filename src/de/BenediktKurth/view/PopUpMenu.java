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
        
        umbennen.setText("Umbenennen");
        umbennen.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mother.Umbenennen();
            }
        });
        super.add(umbennen);
        super.add(jSeparator1);

        loeschen.setText("Löschen");
        loeschen.addMouseListener(new java.awt.event.MouseAdapter() {
        
            
            @Override
             public void mouseReleased(java.awt.event.MouseEvent evt) {
                controller.remove(interneID);
                mother.getDarstellung().remove(interneID);
                
                
            }
        });
            
        super.add(loeschen);




    }                        

                                     
                                   

                                  


    // Variables declaration - do not modify                     
 
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem loeschen;
    private javax.swing.JMenuItem umbennen;
    // End of variables declaration                   
}
