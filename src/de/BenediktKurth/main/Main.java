package de.BenediktKurth.main;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.NoSaveFileException;
import de.BenediktKurth.model.PNMLParser;
import de.BenediktKurth.model.PNMLWriter;
import de.BenediktKurth.model.Transition;
import de.BenediktKurth.view.MainWindow;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
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
		
                String[] stringTemp = new String[1];
                stringTemp[0] = "D:\\Desktop\\ProPra\\Beispiele\\Beispiel-01.pnml";
               //PNMLParser temp = new PNMLParser(stringTemp);
               ArrayListSearchID<IDBase> halla = PNMLParser.loadAndGet(stringTemp[0]);

               MainWindowController testController = new MainWindowController();
               MainWindow test = new MainWindow(testController);
               testController.setComponents(test, halla);
               test.setVisible(true);
               
        }
       
}

