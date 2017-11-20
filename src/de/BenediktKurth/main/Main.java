package de.BenediktKurth.main;

import de.BenediktKurth.Exceptions.FileNotLoadException;
import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PNMLParser;
import de.BenediktKurth.view.HauptFenster;

/**
 *
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        String stringTemp = "F:\\Desktop\\ProPra\\Beispiele\\Beispiel-03.pnml";
        ArrayListSearchID<IDBase> halla = new ArrayListSearchID<>();
        
        try {
            halla = PNMLParser.loadAndGet(stringTemp);
        } catch (FileNotLoadException ex) {
            
        }

        MainWindowController testController = new MainWindowController();
        
        HauptFenster testWindow = new HauptFenster(testController);
        testController.setComponents(testWindow, halla);
        testController.neueDarstellungMitTest();
        testWindow.setVisible(true);

        
    }

}
