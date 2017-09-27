package de.BenediktKurth.model;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public class Arc extends IDBase {
    
    /**
     * @param source
     *      Indentifikationstext des Startelements der Kante
     * @param target
     *      Indentifikationstext der Endelements der Kante
     */
    
    private String source;
    private String target;
    
    Arc(String id, String source, String target){
        super(id);
        this.source = source;
        this.target = target;
        
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
    
    @Override
    public String toString(){
        return id + " " + source + " " + target;
    }
}
