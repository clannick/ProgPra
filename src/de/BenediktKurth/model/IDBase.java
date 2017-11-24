package de.BenediktKurth.model;

/**
 * Basis Klasse für das gesamte Basisdatenmodel. Es definiert eine gemeinsame ID
 * als Grundlage und beinhaltet die globale Größe zur Darstellung von Objekten.
 * Jedes Objekt erhält weiter eine Farbe für die Darstellung.
 * 
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 * 
 * @see FarbenEnum
 */
public abstract class IDBase {
    
    /**
     * ID als String-Wert.
     * 
     * @since 1.0
     */
    protected final String          id;
    
    /**
     * Interne ID des Objektes.
     * 
     * @since 1.0
     */    
    protected final int             interneID;

    
    /**
     * Interne ID für alle Objekte des Basisdatenmodels. Kann von außen nicht beinflusst werden.
     * 
     * @since 1.0
     */        
    private static int              idCounter = 0;
    
    /**
     * Größe der globalen Darstellung von Objekten und Pfeilspitzen.
     * 
     * @since 1.0
     */    
    private static int              size = 50;
    
    /**
     * Farbwert des Objektes. Für Arc derzeit nutzlos (evtl. später).
     * 
     * @since 1.0
     * 
     * @see FarbenEnum
     */
    private FarbenEnum              meineFarbe = FarbenEnum.weiss;

    /**
     * Leerer Konstrukter erzeugt eine ID aus Basis des idCounter.
     * 
     * @since 1.0
     */
    public IDBase () {
        Integer temp = getIdCounter();
        this.id = temp.toString();
        this.interneID = temp;
    }
    
     /**
     * Konstrukter mit vorhander ID, es wird trotzdem interne ID erstellt.
     * 
     * @param id    String ID des Objektes als String
     * 
     * @since 1.0
     */
    public IDBase(String id) {
        this.id = id;
        this.interneID = getIdCounter();
    }
    
    /**
     * Methode setzt den idCounter zurück auf 0.
     * Achtung: Missbrauch kann Programmfehler verursachen!
     * 
     * @since 1.0
     */
    public final static void resetIdCounter(){
       IDBase.idCounter = 0; 
    } 

    /**
     * Mehode zum anspassen der globalen Größe der Darstellung der Objekte.
     * Es wird 50 mit einem Faktor x multipliziert und damit die Darstellung relativ 
     * verstellt (Schieberegler).
     * 
     * @param faktor Faktor mit dem miltipliziert werden soll ( x * 50 )
     */
    public final static void setSize(int faktor){
        float temp = faktor/100.0f;
        IDBase.size = (int)(50 * temp);
    }

    /**
     * Methode zum setzen der Farbe eines Objektes.
     * Auswahl aus FarbenEnum.
     * 
     * @param meineFarbe    Neue Farbe des Objektes (Auswahl aus FarbenEnum).
     * 
     * @since 1.0
     * 
     * @see FarbenEnum
     */
    public void setMeineFarbe(FarbenEnum meineFarbe) {
        this.meineFarbe = meineFarbe;
    }
    
    /**
     * Methode gibt den Farbwert des Objektes zurück.
     * 
     * @since 1.0
     * 
     * @return Farbwert aus FarbenEnum.
     * 
     * @see FarbenEnum
     */
    public FarbenEnum getMeineFarbe() {
        return meineFarbe;
    }

    /**
     * Die Methode gibt die globale Größe für die Datstellung zurück.
     * 
     * @since 1.0
     * 
     * @return Integer-Wert für die Größe der Darstellung.
     */
    public final static int getSize(){
        return IDBase.size;
    }
    
    /**
     * Mehtode gibt die Interne ID des Objektes zurück.
     * 
     * @since 1.0
     * 
     * @return Integer-Wert für die interne ID.
     */
    public final int getInterneID(){
        return this.interneID;
    }   

    /**
     * Mehtode gibt die ID des Objektes als String zurück.
     * Achtung: Kann von interner ID abweichen.
     * 
     * @since 1.0
     * 
     * @return Integer-Wert für die interne ID.
     */    
    public final String getId() {
        return id;
    }

    /**
     * Diese Method gibt den IdCounter der Klasse (Static) zurück und zählt dann 
     * IDCounter um eins hoch.
     * 
     * @since 1.0
     * 
     * @return int   IdCounter der Struktur
     */
    public final static int getIdCounter() {
        int temp = IDBase.idCounter;
        IDBase.idCounter++;
        return temp;
    }
}