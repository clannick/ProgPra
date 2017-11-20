package de.BenediktKurth.Exceptions;

/**
 * Diese Exception wird bei einem nicht korrekten Workflownetz geworfen.
 * Die Klasse informiert den Nutzer über den Grund, warum das vorliegende Netz
 * kein Workflownetz ist.
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 * 
 * @see Exception
 */
public class WorkflownetzException extends Exception {

    private static final long serialVersionUID = 104L;
    
    /**
     * Kontruktor gibt nur Nachricht an super-Klasse (Exception) weiter.
     * 
     * @param nachricht Art des Workflownetzt-Fehlers 
     */
    public WorkflownetzException(String nachricht){
        super(nachricht);
        
    }
}
