package de.BenediktKurth.main;

import de.BenediktKurth.control.MainWindowController;
import de.BenediktKurth.model.Adjazenzmatrix;
import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PNMLParser;
import de.BenediktKurth.model.Stellen;
import de.BenediktKurth.view.MainWindow;
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
                stringTemp[0] = "D:\\Desktop\\ProPra\\Beispiele\\Beispiel-02.pnml";
               //PNMLParser temp = new PNMLParser(stringTemp);
               ArrayListSearchID<IDBase> halla = PNMLParser.loadAndGet(stringTemp[0]);

               /*
               MainWindowController testController = new MainWindowController();
               MainWindow test = new MainWindow(testController);
               testController.setComponents(test, halla);
               test.setVisible(true);
               */
               long currentTime = System.currentTimeMillis();
               Adjazenzmatrix matrix = new Adjazenzmatrix(halla);
               matrix.printMatrix();
               long time = System.currentTimeMillis();
               long dTime = (time - currentTime);
               System.out.println(dTime);
               
               System.out.println(matrix.getAnfang().toString()); 
               System.out.println(matrix.getEnde().toString());
               System.out.println(matrix.pruefeWorkflownetz());

    }
}
                
               
               


