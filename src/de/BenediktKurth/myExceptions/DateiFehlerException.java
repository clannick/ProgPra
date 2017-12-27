package de.BenediktKurth.myExceptions;

/**
 * Diese Exception wird bei Fehlern beim laden von Dateien erstellt.
 * Wenn eine Datei keine passende Dateiendung bzw. Dateiformat hat oder
 * eine IO-Exception geworfen wird, informiert diese Klasse den Nutzer über
 * den Grund des Fehlers.
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 * 
 * @see Exception
 */
public class DateiFehlerException extends Exception {

    /**
     * Kontruktor gibt nur Nachricht an super-Klasse (Exception) weiter.
     * 
     * @param nachricht Art des Fehlers beim Laden der Datei.
     */    
    public DateiFehlerException(String nachricht){
        super(nachricht);
    }
}