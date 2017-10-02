/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.BenediktKurth.model;

import java.awt.geom.Ellipse2D;
import javax.swing.JLabel;

/**
 *
 * @author clannick
 */
public class StellenLabel extends JLabel{
    
    Stellen meineStelle;
    Ellipse2D neuerKreis;
    
    public StellenLabel(Stellen meineStelle){
        super();
        this.meineStelle = meineStelle;
        this.neuerKreis = new Ellipse2D.Double(0, 0, meineStelle.getSize(), meineStelle.getSize());
        
        
        
    }
    
    public Ellipse2D getKreis(){
        return this.neuerKreis;
    }
    
    
}
