package de.BenediktKurth.model;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public final class Transition extends PosNameBase{

    public Transition(){
        super();
        //this.darstellung = new TransitionPanel(this.getLabel());
    }
    
    public Transition(String id){
        super(id, "", "", "");
    }


}
