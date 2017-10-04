/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.BenediktKurth.control;

import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.PosNameBase;

import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Transition;

import de.BenediktKurth.view.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 *
 * @author clannick
 */
public class MainWindowController {
    
    MainWindow window;
    ArrayListSearchID speicherArray;
    private int idCounter = 0;
    
    public void setComponents(MainWindow window, ArrayListSearchID speicherArray) {
        this.window = window;
        this.speicherArray = speicherArray;
    }
    
    public MainWindowController(){
        super();
    }
    
    public Stellen makeStellen (){
        Integer tempInt = idCounter++;
        String idString = "StellenID" + tempInt.toString();
        Stellen neueStelle = new Stellen(idString,"0","0");
        speicherArray.add(neueStelle);
    
              
        return neueStelle;
    }

 
    
    
    
    public ArrayListSearchID getArray(){
        return this.speicherArray;
    }
    
    public void paintComponents (JPanel zeichenflaeche){
                
                int i = 0;
                while (i < speicherArray.size()){
                    
                    if ((speicherArray.get(i) instanceof Transition) ){
                        PosNameBase temp = (PosNameBase)speicherArray.get(i);
                        int x = temp.getXint();
                        int y = temp.getYint();
                        
                        Graphics g = zeichenflaeche.getGraphics();
                        g.setColor(Color.black);
                        g.drawRect(x, y, 50, 50);
                        g.dispose();
                        
         
       
                        i++;
                    } else if (speicherArray.get(i) instanceof Stellen) {
                        PosNameBase temp = (PosNameBase)speicherArray.get(i);
                        int x = temp.getXint();
                        int y = temp.getYint();
                        
                        Graphics g = zeichenflaeche.getGraphics();
                        g.setColor(Color.black);
                        g.drawOval(x, y, 50, 50);
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
                        //Oberhalb
    
                        
                        
                        
                        g.dispose();
                        i++;;
                    }
        }
        
    }
    
    
}
