package de.BenediktKurth.model;

import de.BenediktKurth.Exceptions.ArcFehlerException;
import javax.swing.JLabel;

/**
 * Diese Klasse stellt einen Pfeil da. Als Variablen hat ein Pfeil seine ID,
 * seinen Ursprung und sein Ziel, weiter hat er eine Darstellung. (reine
 * Datenträgerklasse, keine Funktionalitäten nur getter/setter)
 *
 * Plannung:    - Exceptions 
 *              - Prüfung Source/Target set 
 *              - JLabel Darstellung
 *
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 */
public final class Arc extends IDBase {

    /**
     * Indentifikationstext des Startelements der Kante
     *
     */
    private String source;

    /**
     * Indentifikationstext der Endelements der Kante
     */
    private String target;

    /**
     * Leerer Konstrukter -> es wird eine klare ID von IDBase erzeugt und Source
     * und Target auf "null" gesetzt.
     *
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     *
     */
    public Arc() {
        super();
        this.source = "null";
        this.target = "null";
        this.darstellung = null;
    }

    /**
     * Vollständiger Konstrukter -> es werden alle benötigten Daten eingegeben.
     * Die ID wird von IDBase auf eindeutigkeit überprüft und von einer intern
     * Methode ob Source und Target existieren. 
     * 
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @throws ArcFehlerException
     *
     */
    public Arc(String id, String source, String target, ArrayListSearchID tempListe) throws ArcFehlerException {
        super(id);
        
        if (pruefeSourceTarget(source, target, tempListe)){
        //Setze Source und Target Werte und erzeuge Darstellung.
        this.source = source;
        this.target = target;

        this.darstellung = new JLabel();   
        }
    }
    
    /**
     * Methode prüft ob Source und Target existieren und ob es gleiche 
     * Objekte sind. Es wird geprüft ob der Pfeil von Stelle zu Transition 
     * oder umgekehrt verläuft und ob diese existieren. 
     * Sollte das nicht der Fall sein wird eine Exception erzeugt. 
     * 
     * @throws de.BenediktKurth.Exceptions.ArcFehlerException 
     * 
     * @since 1.0
     * 
     * @param source    String-Wert für das Startobjekt
     * 
     * @param target    String-Wert für das Zielobjekt
     * 
     * @param tempListe ArrayListSearchID mit den Basisdaten
     * 
     * @return boolean  True: Pfeil könnte eingefügt werden.
     *                  False: Pfeil kann aus o.g. Gründen nicht eingefügt werden.
     */
    public boolean pruefeSourceTarget(  String source, 
                                        String target, 
                                        ArrayListSearchID tempListe) 
                                        throws ArcFehlerException{
               
        Boolean vonSnachT = ((tempListe.searchID(source) instanceof Stellen) &&
                            (tempListe.searchID(target) instanceof Transition));
        Boolean vonTnachS = ((tempListe.searchID(source) instanceof Transition) &&
                            (tempListe.searchID(target) instanceof Stellen));
        
        if (vonSnachT || vonTnachS){
            return true;
     
        } else {
            throw new ArcFehlerException("Kante mit id " + id + " von " + source + " nach "
                + target + " konnte nicht eingefügt werden.");
            
        }
    }
    /**
     * Methode setzt die Start-ID als String mit Prüfung auf existens.
     * 
     * @since 1.0
     *
     * @version 1.0
     *
     * @param source    ID des Startes als String 
     * 
     * @param tempListe ArrayListSearchID mit den Basisdaten 
     * 
     * @see ArrayListSearchID
     */
    public void setzeSource(String source, ArrayListSearchID tempListe) {
        
        if (tempListe.searchID(source) != null){
            this.source = source;
        }
        
    }

    /**
     * Methode setzt die Ziel-ID als String mit Prüfung auf existens.
     * 
     * @since 1.0
     *
     * @version 1.0
     *
     * @param target    ID des Zieles als String 
     * 
     * @param tempListe ArrayListSearchID mit den Basisdaten 
     * 
     * @see ArrayListSearchID
     */
    public void setzeTarget(String target, ArrayListSearchID tempListe) {
        
        if (tempListe.searchID(target) != null){
            this.source = target;
        }
        
    }

    /**
     * Methode liefert die ID des Startes als String
     * 
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @return Liefert den String mit der Quelle (Source) des Pfeils zurück.
     */
    public String gibSource() {
        return source;
    }

    /**
     * Methode liefert die ID des Zieles als String
     * 
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @return Liefert den String mit dem Ziel (Target) des Pfeils zurück.
     */
    public String gibTarget() {
        return target;
    }

    /**
     * TEST-Methode für Console
     *
     * @return String ID SOURCE TARGET
     */
    @Override
    public String toString() {
        return id + " " + source + " " + target;
    }
}
