package de.BenediktKurth.control;

import de.BenediktKurth.model.Adjazenzmatrix;
import de.BenediktKurth.model.Arc;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.PosNameBase;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.Transition;
import de.BenediktKurth.Exceptions.WorkflownetzException;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PNMLParser;
import de.BenediktKurth.model.PNMLWriter;
import de.BenediktKurth.model.Vector2D;
import de.BenediktKurth.view.HauptFenster;
import de.BenediktKurth.view.PosNameLabel;
import de.BenediktKurth.view.StellenLabel;
import de.BenediktKurth.view.TransitionLabel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;




/**
 *
 * @author Benedikt Kurth
 */
public class MainWindowController {
    
    private HauptFenster window;
    private ArrayListSearchID<IDBase> speicherArray;

    
    
    public MainWindowController(){
        super();
                
    }
    
    public void setComponents(HauptFenster window, ArrayListSearchID<IDBase> speicherArray) {
        this.window = window;
        this.speicherArray = speicherArray;
       
    }
    

    
    public void newStellen (){
        
        Stellen neueStelle = new Stellen();

        speicherArray.add(neueStelle);
      
        System.out.println(this.speicherArray.size());
    
              
        
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
    
    public void testeWorkflownetz(){
        
        Adjazenzmatrix temp = new Adjazenzmatrix(speicherArray);
        try {
            if (temp.pruefeWorkflownetz()){
                window.gibFehleranzeigeText().setText("Keine Fehler.");
                window.gibFehleranzeigeGross().setText("Es ist ein Workflownetz.");
            }
        } catch (WorkflownetzException ex) {
            window.gibFehleranzeigeText().setText(ex.getMessage());
            window.gibFehleranzeigeGross().setText("Es ist kein Workflownetz.");
        } 
    } 
    
    public void getArraySize() {
        System.out.println(this.speicherArray.size());
    }
    
    public void laden(String absoluterPfad) {
        IDBase.resetIdCounter();
        this.speicherArray = PNMLParser.loadAndGet(absoluterPfad);
        
    }

    public void speichern(String absoluterPfad) {
        PNMLWriter.saveFile(absoluterPfad, speicherArray);
    }
    
    public void createView(ArrayList<JLabel> darstellungen){
        darstellungen.removeAll(darstellungen);
        System.out.println(darstellungen.size());
        int i = 0;
        while ( i < this.speicherArray.size() ){
            IDBase temp = (IDBase)this.speicherArray.get(i);
            if (temp instanceof Stellen) {
                JLabel test = new StellenLabel((Stellen)temp, this, window);
                
                darstellungen.add(test);

                
                
            } else if (temp instanceof Transition){
                JLabel test = new TransitionLabel((Transition)temp, this, window);
                
                darstellungen.add(test);
               
              
            } else if (temp instanceof Arc) {
                Arc neueArcDarstellung = (Arc)temp;

                
            }
            i++;
        }
        
        
    }
    
    public void setzteDarstellung(JPanel panel, ArrayList<JLabel> darstellungen){
    int i = 0;
    
    while ( i < darstellungen.size() ) {
            JLabel temp = darstellungen.get(i);
            panel.add(temp);
            PosNameLabel testTemp = (PosNameLabel)temp;
            temp.setBounds(testTemp.getPosition().getX(), testTemp.getPosition().getY(), 100, 100);
            i++;
        }
        
        
       
    }

    public void lala(java.awt.event.MouseEvent evt, int interneID) {
       
        if (evt.getButton() == MouseEvent.BUTTON1) {
          System.out.println("Läuft" + interneID); 
       } else if (evt.getButton() == MouseEvent.BUTTON3 ){
          System.out.println("Läuft rechts"); 
       }
        
    }

    public void setPosition(MouseEvent evt, int interneID) {
        PosNameBase temp = (PosNameBase)this.speicherArray.getWithInternID(interneID);
        temp.setPosition(evt.getX(), evt.getY());
        System.out.println(evt.getX() + " " + evt.getY());
    }
    
    public void removePosNameBase(MouseEvent evt, int interneID) {
        IDBase temp = this.speicherArray.getWithInternID(interneID);
        this.speicherArray.remove(temp);
    }

        

     
    
}
