package de.BenediktKurth.main;

import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.NoSaveFileException;
import de.BenediktKurth.model.PNMLParser;
import de.BenediktKurth.model.PNMLWriter;
import de.BenediktKurth.model.Transition;
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
                stringTemp[0] = "D:\\Desktop\\ProPra\\Beispiele\\Test.pnml";
               //PNMLParser temp = new PNMLParser(stringTemp);
               ArrayListSearchID<IDBase> halla = PNMLParser.loadAndGet(stringTemp[0]);
                
               int x = halla.size();
               System.out.println(x);
               
                       int i = 0;
                 while (i < halla.size()){
        System.out.println(halla.get(i).toString());
        i++;
        

        }
                Transition temp = new Transition();
        halla.add(temp);
        int y = halla.size();
        System.out.println(y);
        halla.add(temp);
        int z = halla.size();
        System.out.println(z);
        System.out.println(halla.get(z-1).toString());
        System.out.println(halla.get(z-2).toString());
        
            try {
                PNMLWriter.saveFile("D:\\Desktop\\ProPra\\Beispiele\\Test.pnml", halla);
            } catch (NoSaveFileException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}
