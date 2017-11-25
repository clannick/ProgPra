package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;

import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.model.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Diese abstrakte Klasse dient als Basis für verschiebbare Objekte.
 * 
 * @author Benedikt Kurth
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @see Vector2D
 */
public abstract class VerschiebbarLabel extends BasisLabel{
    
    /**
     * Position für das Objekt.
     * 
     * @since 1.0
     */
    protected Vector2D position;   

    /**
     * Vollständiger Konstruktor. Erhält Referenz des Basisdatenmodel (speichert
     * diese aber nicht), weiter wird der Klasse "sein" Kontroller und das
     * HauptFenster mitgeteilt. Es wird die Position gesetzt und ein MouseMotionListner
     * hinzugefügt.
     * 
     * @since 1.0
     * 
     * @param basis Referenz auf Basisdatenmodel.    
     * @param controller Referenz auf Kontroller.
     * @param mother Referenz auf HauptFenster.
     *
     * @see MainWindowController
     * @see HauptFenster
     * @see PosNameBase
     * @see Vector2D
     */
    public VerschiebbarLabel(PosNameBase basis, MainWindowController controller, HauptFenster mother) {
        //Rufe Konstruktor von VerschiebbarLabel auf
        super(basis, controller, mother);
             
        //Setzte Position des Objektes
        this.position = new Vector2D(basis.gibPosition().gibX(), basis.gibPosition().gibY());
        
        //Füge MouseMotionListner hinzu für Drag-and-Drop Verschieben der Objekte
        super.addMouseMotionListener(new MouseMotionAdapter() {
            //Maus wird bei gedrückter linker Maustaste bewegt
            @Override
            public synchronized void mouseDragged(MouseEvent evt){
                //Ist dieses Objekt markiert?
                if (isMarkiert) {
                    //Berechne differenz von ursprunglicher Position und der jetzigen
                    int dx = evt.getX() - punkt.x;
                    int dy = evt.getY() - punkt.y;
                    
                    //Setzte die ursprunglichen auf die jetzige
                    punkt = evt.getPoint();                    
                    
                    //Erzeuge entsprechenden Vector2D und rufe Methode des Kontrollers auf
                    Vector2D test = new Vector2D(dx,dy);
                    controller.verschiebeMarkierteUmOffset(mother.getDarstellung(), mother.getInterneIDmarkierter(), test);
                }
            }
        });
    }
}