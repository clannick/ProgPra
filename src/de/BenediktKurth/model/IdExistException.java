package de.BenediktKurth.model;

/**
 * Exception-Class für eine bereits vorhandene ID für IDBase
 * 
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public class IdExistException extends Exception{
    
    /**
     * 
     * @param massage   Gibt eine Textnachricht an den Benutzer aus. 
     */
    public IdExistException (String massage){
        super(massage);
    }
}
