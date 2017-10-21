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

    /**
    *  Label enthält den angezeigten Bezeichner des Objektes
    */
   private String label;
   
    public Transition(){
        super();
        this.label = "";
        //this.darstellung = new TransitionPanel(this.getLabel());
    }
    
    public Transition(String id){
        super(id, "", "", "");
    }


}
