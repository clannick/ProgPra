package de.BenediktKurth.Exceptions;

/**
 * Diese Exception wird bei Fehlern bei der Erstellung von Kanten geworfen.
 * Sollten Kanten nicht von Transition zu einer Stelle oder umgekehrt verläuft
 * oder die ID´s der Source oder Target Basisdaten existieren nicht.
 * Weiter gibt der Konstruktor eine Nachricht an den Nutzer weiter.
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 * 
 * @see Exception
 */
public class ArcFehlerException extends Exception {

    private static final long serialVersionUID = 101L;

    /**
     * Kontruktor gibt nur Nachricht an super-Klasse (Exception) weiter.
     * 
     * @param nachricht Art des Arc-Fehlers 
     */    
    public ArcFehlerException(String nachricht){
        super(nachricht);
    }
    
}
