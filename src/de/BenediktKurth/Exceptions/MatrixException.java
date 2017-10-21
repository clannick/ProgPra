package de.BenediktKurth.Exceptions;

/**
 * Diese Exception wird bei Fehlern bei der Erstellung von Adjazenmatrizen geworfen.
 * Die Klasse informiert den Nutzer über den vorliegenden Fehler.
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 * 
 * @see Exception
 */
public class MatrixException extends Exception {
    
    /**
     * Kontruktor gibt nur Nachricht an super-Klasse (Exception) weiter.
     * 
     * @param nachricht Art des Adjazenzmatrix-Fehlers 
     */    
    public MatrixException(String nachricht){
        super(nachricht);
    }
    
}

