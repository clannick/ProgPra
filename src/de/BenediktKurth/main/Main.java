package de.BenediktKurth.main;

import de.BenediktKurth.model.ArrayListSearchID;
import de.BenediktKurth.model.IDBase;
import de.BenediktKurth.model.PNMLParser;
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
		// TODO Auto-generated method stub
                String[] stringTemp = new String[1];
                stringTemp[0] = "D:\\Desktop\\ProPra\\Beispiele\\Beispiel-01.pnml";
               //PNMLParser temp = new PNMLParser(stringTemp);
               ArrayListSearchID<IDBase> halla = PNMLParser.loadAndGet(stringTemp[0]);
                
               int x = halla.size();
               System.out.println(x);
               
                       int i = 0;
                 while (i < halla.size()){
        System.out.println(halla.get(i).toString());
        i++;
        }
                   
        //TEst       
        }

}
