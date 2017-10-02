/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.BenediktKurth.control;

import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.model.StellenLabel;
import de.BenediktKurth.view.MainWindow;
import javax.swing.JLabel;

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

  
    
    public StellenLabel makeStellen (){
        Integer tempInt = idCounter++;
        String idString = "StellenID" + tempInt.toString();
        Stellen neueStelle = new Stellen(idString);
        speicherArray.add(neueStelle);
        
        StellenLabel returnLabel = new StellenLabel(neueStelle);
        
        return returnLabel;
    }
    public ArrayListSearchID getArray(){
        return this.speicherArray;
    }
    
    
}
