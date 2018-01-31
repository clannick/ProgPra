package de.BenediktKurth.model;

import de.BenediktKurth.myExceptions.ArcFehlerException;

/**
 * Diese Klasse stellt einen Pfeil da. Als Variablen hat ein Pfeil seine ID,
 * seinen Ursprung und sein Ziel, weiter hat er eine Darstellung. (reine
 * Datenträgerklasse, keine Funktionalitäten nur getter/setter).
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 */
public final class Arc extends IDBase {

    /**
     * Indentifikationstext des Startelements der Kante.
     * 
     * @since 1.0
     */
    private String      source;

    /**
     * Indentifikationstext der Endelements der Kante
     * 
     * @since 1.0
     */
    private String      target;
    
    /**
     * Position des Source-Objektes.
     * 
     * @since 1.0
     */
    private Vector2D    positionSource;
    
    /**
     * Position des Target-Objektes.
     * 
     * @since 1.0
     */
    private Vector2D    positionTarget;
    
    /**
     * Leerer Konstrukter: Es wird eine klare ID von IDBase erzeugt und Source
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
    }
    
    /**
     * Vollständiger Konstrukter: Es werden alle benötigten Daten eingegeben.
     * Die ID wird von IDBase auf eindeutigkeit überprüft und von einer intern
     * Methode ob Source und Target existieren. 
     * 
     * @since 1.0
     * 
     * @param id        String 
     * 
     * @param source    String
     * 
     * @param target    String
     * 
     * @param tempListe ArrayListSearchID
     *
     * @throws ArcFehlerException   Der Pfeil konnte nicht zwischen den gewünschten 
     *                              Objekten erstellt werden. Wird von Methode
     *                              pruefeSourceTarget geworfen.
     * 
     * @see #pruefeSourceTarget(String, String, ArrayListSearchID)
     * @see ArrayListSearchID
     */
    public Arc(String id, String source, String target, ArrayListSearchID<IDBase> tempListe) throws ArcFehlerException {
        super(id);
        
        if (pruefeSourceTarget(source, target, tempListe)){
            //Setze Source und Target Werte
            this.source = source;
            this.target = target;

            //Setze Positionen von Source und Target
            this.positionSource = ((PosNameBase)tempListe.sucheID(source)).getPosition();
            this.positionTarget = ((PosNameBase)tempListe.sucheID(target)).getPosition();
        } else {
            throw new ArcFehlerException("");
        }
    }
    
    /**
     * Fast Vollständiger Konstrukter: Es werden nicht alle benötigten Daten eingegeben.
     * Die ID wird von IDBase erzeugt und von einer intern
     * Methode wir geprüft, ob Source und Target existieren. 
     * 
     * @since 1.0
     * 
     * @param source    String mit der ID des Source-Objektes
     * 
     * @param target    String mit der ID des Target-Objektes
     * 
     * @param tempListe ArrayListSearchID   SpeicherArray mit den Basisdaten
     *
     * @throws ArcFehlerException   Der Pfeil konnte nicht zwischen den gewünschten 
     *                              Objekten erstellt werden. Wird von Methode
     *                              pruefeSourceTarget geworfen.
     * 
     * @see #pruefeSourceTarget(String, String, ArrayListSearchID)
     * @see ArrayListSearchID
     */
    public Arc(String source, String target,ArrayListSearchID<IDBase> tempListe)throws ArcFehlerException{
        super();
        if (pruefeSourceTarget(source, target, tempListe)){
            //Setze Source und Target Werte
            this.source = source;
            this.target = target;

            //Setze Positionen von Source und Target
            this.positionSource = ((PosNameBase)tempListe.sucheID(source)).getPosition();
            this.positionTarget = ((PosNameBase)tempListe.sucheID(target)).getPosition();
        } else {
            throw new ArcFehlerException("");
        }
    }
    
    /**
     * Methode prüft ob Source und Target existieren und ob es gleiche 
     * Objekte sind. Es wird geprüft ob der Pfeil von Stelle zu Transition 
     * oder umgekehrt verläuft und ob diese existieren. 
     * Sollte das nicht der Fall sein wird eine Exception erzeugt. 
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
     * 
     * @throws de.BenediktKurth.myExceptions.ArcFehlerException Der Pfeil konnte nicht 
     *          zwischen den gewünschten Objekten erstellt werden.
     * 
     * @see ArrayListSearchID
     */
    public boolean pruefeSourceTarget(  String source, 
                                        String target, 
                                        ArrayListSearchID<IDBase> tempListe) 
                                        throws ArcFehlerException{
        
        Boolean vonSnachT = ((tempListe.sucheID(source) instanceof Stellen) &&
                            (tempListe.sucheID(target) instanceof Transition));
        Boolean vonTnachS = ((tempListe.sucheID(source) instanceof Transition) &&
                            (tempListe.sucheID(target) instanceof Stellen));
        
        if (vonSnachT || vonTnachS){
            return true;
     
        } else {
            throw new ArcFehlerException("Kante mit id " + id + " von " + source + " nach "
                + target + " konnte nicht eingefügt werden.");
        }
    }
    
    /**
     * Methode liefert die ID des Startes als String
     *
     * @since 1.0
     * 
     * @return Liefert den String mit der Quelle (Source) des Pfeils zurück.
     */
    public String getSource() {
        return source;
    }

    /**
     * Methode liefert die ID des Zieles als String
     * 
     * @since 1.0
     * 
     * @return Liefert den String mit dem Ziel (Target) des Pfeils zurück.
     */
    public String getTarget() {
        return target;
    }

    /**
     * Methode liefert die Position des Source-Objektes als Vector2D.
     * 
     * @since 1.0
     * 
     * @return Liefert die Position des Source-Objektes.
     * 
     * @see Vector2D
     */
    public Vector2D getPositionSource() {
        return positionSource;
    }

    /**
     * Methode liefert die Position des Target-Objektes als Vector2D.
     * 
     * @since 1.0
     * 
     * @return Liefert die Position des Target-Objektes.
     * 
     * @see Vector2D
     */
    public Vector2D getPositionTarget() {
        return positionTarget;
    }
}