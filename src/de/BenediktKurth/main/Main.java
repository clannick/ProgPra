package de.BenediktKurth.main;

import de.BenediktKurth.control.MainWindowController;

import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PNMLParser;
import de.BenediktKurth.view.HauptFenster;

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
        long currentTime = System.currentTimeMillis();
        
        String stringTemp = "D:\\Desktop\\ProPra\\Beispiele\\Beispiel-03.pnml";
        ArrayListSearchID<IDBase> halla = PNMLParser.loadAndGet(stringTemp);

        MainWindowController testController = new MainWindowController();
        
        HauptFenster testWindow = new HauptFenster(testController);
        testController.setComponents(testWindow, halla);
        testWindow.setVisible(true);
       
        
   
        
        
        

        //System.out.println(matrix.getAnfang().toString());
        //System.out.println(matrix.getEnde().toString());
        

        /*
        int[][] intArray = new int[2][2];
        intArray[0][0] = 1;
        intArray[0][1] = 2;
        intArray[1][0] = 3;
        intArray[1][1] = 4;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(intArray[i][j] + " ");
            }
            System.out.println();
        }
         */
        long time = System.currentTimeMillis();
        long dTime = (time - currentTime);
        System.out.println(dTime);
        System.out.println(halla.size());
        
    }

}
