package de.BenediktKurth.model;

import javax.swing.JPanel;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public class Arc extends IDBase {
    

    Arc(String id, String source, String target){
        super(id);
        this.source = source;
        this.target = target;
        Arc.topSize = "3";
        this.darstellung = new JPanel ();
        
    }
    
    
    /**
     * @param source
     *      Indentifikationstext des Startelements der Kante
     * @param target
     *      Indentifikationstext der Endelements der Kante
     * @param topSize 
     *      Gibt die global Größe des Pfeilkopfes an
     */
        
    
    private final String source;
    private final String target;
    private static String topSize;

    
    /**
     * 
     * @return String   Größe des Pfeilkopfes als String  (Global, static)
     */
    public static String getTopSize() {
        return topSize;
    }
    
    /**
     * 
     *  Diese Methode verändert die globale Größe des Pfeilkopfes mithilfe eines
     *  Strings
     * 
     * @param topSize   Gewünschte Pfeilkopfgröße aller Pfeilköpfe (String)
     */
    public static void setTopSize(String topSize) {
        Arc.topSize = topSize;
    }
    
    /**
     * 
     *  Diese Methode verändert die globale Größe des Pfeilkopfes mithilfe eines
     *  integer-Wertes.
     *  Die Methode konvertiert einen int-Wert zu einem String und speichert sie
     * 
     * @param topSize   Gewünschte Pfeilkopfgröße aller Pfeilköpfe (int)
     */
    public static void setTopSize(int topSize) {
        Integer tempInt = (Integer)topSize;
        String tempString = tempInt.toString();
        Arc.topSize = tempString;
    }
    
    

        
    
     /**
     * @return Liefert den String mit der Quelle (Source) des Pfeils zurück.
     */
    public String getSource (){
        return source;
    }

     /**
     * @return Liefert den String mit dem Ziel (Target) des Pfeils zurück.
     */
    public String getTarget (){
        return target;
    }
    
    /**
     * TEST-Methode für Console
     * @return  String  ID SOURCE TARGET 
     */
    @Override
    public String toString(){
        return id + " " + source + " " + target;
    }
}
