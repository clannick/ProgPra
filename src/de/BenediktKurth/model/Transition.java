package de.BenediktKurth.model;

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

    JLabel transitionDarstellung;
    
    public Transition(){
        super();
    }
    
    public Transition(String id){
        super(id, "", "", "");
    }
    
}
