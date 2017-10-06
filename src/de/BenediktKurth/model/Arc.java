package de.BenediktKurth.model;

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
public class Arc extends IDBase {

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

        this.darstellung = new JLabel();
    }

    /**
     * Vollständiger Konstrukter -> es werden alle benötigten Daten eingegeben.
     * Die ID wird von IDBase auf eindeutigkeit überprüft. Source und Target
     * werden ungeprüft übernommen. (Achtung evtl. falsche IDs von S oder T)
     * 
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     *
     */
    Arc(String id, String source, String target) {
        super(id);
        this.source = source;
        this.target = target;

        this.darstellung = new JLabel();

    }

    /**
     * Methode setzt die Start-ID als String
     * 
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     *
     * @param source    ID des Startes als String (keine Prüfung)
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Methode setzt die Ziel-ID als String
     * 
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     *
     * @param target    ID des Zieles als String (keine Prüfung)
     */
    public void setTarget(String target) {
        this.target = target;
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
    public String getSource() {
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
    public String getTarget() {
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
