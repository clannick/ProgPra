package de.BenediktKurth.control;

import de.BenediktKurth.model.Adjazenzmatrix;
import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.Exceptions.KeineAnfangsstelleException;
import de.BenediktKurth.Exceptions.KeineEndstelleException;
import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.Exceptions.SackgasseException;

import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Transition;
import de.BenediktKurth.Exceptions.ZuVieleAnfangsstellenException;
import de.BenediktKurth.Exceptions.ZuVieleEndstellenException;

import de.BenediktKurth.view.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


/**
 *
 * @author Benedikt Kurth
 */
public class MainWindowController {
    
    MainWindow window;
    ArrayListSearchID speicherArray;
    private int idCounter = 0;
    private Adjazenzmatrix matrix;
    
    public MainWindowController(){
        super();
    }
    
    public void setComponents(MainWindow window, ArrayListSearchID speicherArray) {
        this.window = window;
        this.speicherArray = speicherArray;
        this.matrix = new Adjazenzmatrix(speicherArray);
    }
    

    
    public Stellen newStellen (){
        Integer tempInt = idCounter++;
        String idString = "StellenID" + tempInt.toString();
        Stellen neueStelle = new Stellen(idString,"0","0");
        speicherArray.add(neueStelle);
    
              
        return neueStelle;
    }
    
    public Transition newTransition(){
        Transition ruckgabeWert = new Transition();
        
        
        return ruckgabeWert;
    }
    
    public Arc newArc(){
        Arc ruckgabeWert = new Arc();
        
        
        return ruckgabeWert;
    }

    public void setSize(int faktor){
        PosNameBase.setSize(faktor);
        
    }
    
    public boolean testeWorkflownetz(){
        matrix.aktualisieren(speicherArray);
        try {
            return matrix.pruefeWorkflownetz();
        } catch (KeineAnfangsstelleException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZuVieleAnfangsstellenException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeineEndstelleException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZuVieleEndstellenException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SackgasseException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
 
    
    public void paintComponents (JScrollPane zeichenflaeche){
                
                int i = 0;
                while (i < speicherArray.size()){
                    
                    if ((speicherArray.get(i) instanceof Transition) ){
                        Transition temp = (Transition)speicherArray.get(i);
                        int x = temp.getXint();
                        int y = temp.getYint();
                        
                        Graphics g = zeichenflaeche.getGraphics();
                        g.setColor(Color.black);
                        g.drawRect(x, y, PosNameBase.getSize(), PosNameBase.getSize());
                        g.dispose();
                        
         
       
                        i++;
                    } else if (speicherArray.get(i) instanceof Stellen) {
                        Stellen temp = (Stellen)speicherArray.get(i);
                        int x = temp.getXint();
                        int y = temp.getYint();
                        JLabel darstellung = temp.getDarstellung();
                        darstellung.setLocation(x, y);
                        darstellung.setVisible(true);
                        
                        zeichenflaeche.add(darstellung);
                        Graphics g = zeichenflaeche.getGraphics();
                       
                        g.setColor(Color.black);
                        g.drawOval(x, y, PosNameBase.getSize(), PosNameBase.getSize());
                        g.dispose();
                        
                        i++; 
                    } else if (speicherArray.get(i) instanceof Arc) {
                        Arc temp = (Arc)speicherArray.get(i);
                        String sourceString = temp.getSource();
                        String targetString = temp.getTarget();
                        PosNameBase sourcePos = (PosNameBase)speicherArray.searchID(sourceString);
                        PosNameBase targetPos = (PosNameBase)speicherArray.searchID(targetString);
                        int xSource = sourcePos.getXint()+25; 
                        int ySource = sourcePos.getYint()+25;
                        int xTarget = targetPos.getXint()+25;
                        int yTarget = targetPos.getYint()+25;

                        if (xSource < xTarget){
                            xSource+=25;
                            xTarget-=25;
                      
                        } else if (xSource > xTarget){
                            xSource-=25;
                            xTarget+=25;
                      
                        } else if (ySource < yTarget){
                            ySource+= 25;
                            yTarget-=25;
                        } else if (ySource > yTarget){
                            ySource-= 25;
                            yTarget+=25;
                        }
                        Graphics g = zeichenflaeche.getGraphics();
                        g.setColor(Color.black);
                        g.drawLine(xSource, ySource, xTarget, yTarget);
                        g.drawOval(xTarget, yTarget, 10, 10);
                        //Oberhalb
    
                        
                        
                        
                        g.dispose();
                        i++;
                    }
        }
        
    }
    
    
}
