package de.BenediktKurth.model;

import de.BenediktKurth.Exceptions.ArcFehlerException;

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
    
    private Vector2D positionSource;
    private Vector2D positionTarget;
    
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
     * Leerer Konstrukter: Es wird eine klare ID von IDBase erzeugt und Source
     * und Target auf "null" gesetzt.
     *
     * @param source
     * @param tempListe
     *
     * @since 1.0
     *
     */
    public Arc(String source, ArrayListSearchID<IDBase> tempListe) {
        super();
        this.source = source;
        this.target = "null";
        
        if (tempListe.searchID(source) == null){
            this.positionSource = new Vector2D(0,0);
        } else {
            this.positionSource = ((PosNameBase)tempListe.searchID(source)).getPosition();
        }
        
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


            this.positionSource = ((PosNameBase)tempListe.searchID(source)).getPosition();
            this.positionTarget = ((PosNameBase)tempListe.searchID(target)).getPosition();
            
        }
    }
    
    public Arc(String source, String target,ArrayListSearchID<IDBase> tempListe)throws ArcFehlerException{
        super();
        this.source = source;
            this.target = target;


            this.positionSource = ((PosNameBase)tempListe.searchID(source)).getPosition();
            this.positionTarget = ((PosNameBase)tempListe.searchID(target)).getPosition();
        
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
     * @throws de.BenediktKurth.Exceptions.ArcFehlerException Der Pfeil konnte nicht 
     *          zwischen den gewünschten Objekten erstellt werden.
     * 
     * @see ArrayListSearchID
     */
    public boolean pruefeSourceTarget(  String source, 
                                        String target, 
                                        ArrayListSearchID<IDBase> tempListe) 
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
    public void setSource(String source, ArrayListSearchID<IDBase> tempListe) {
        
        if (tempListe.searchID(source) != null){
            this.source = source;
            this.positionSource = ((PosNameBase)tempListe.searchID(source)).getPosition();
        
        }
        
    }

    /**
     * Methode setzt die Ziel-ID als String mit Prüfung auf existens.
     * 
     * @throws de.BenediktKurth.Exceptions.ArcFehlerException
     * 
     * @since 1.0
     *
     *
     * @param target    ID des Zieles als String 
     * 
     * @param tempListe ArrayListSearchID mit den Basisdaten 
     * 
     * @see ArrayListSearchID
     */
    public void setTarget(String target, ArrayListSearchID<IDBase> tempListe) throws ArcFehlerException {
        
        if (pruefeSourceTarget(this.source, target, tempListe)){
            this.target = target;
            
            this.positionTarget = ((PosNameBase)tempListe.searchID(target)).getPosition();
        }
        
    }

    /**
     * Methode liefert die ID des Startes als String
     * 
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
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
    
 


    public Vector2D getPositionSource() {
        return positionSource;
    }

    public Vector2D getPositionTarget() {
        return positionTarget;
    }

    
}
