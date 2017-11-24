package de.BenediktKurth.model;

/**
 * Diese Klasse stellt eine Stelle im Basisdatenmodel da.
 * Es werden zusätzlich zur Position und dem Label, auch eine
 * evtl. Markierung gespeichert.
 * 
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public final class Stellen extends PosNameBase{

    /**
     * Markierungs-Wert als String. Wird benötigt um die pnml-Datei-Daten einzulesen.
     * 
     * @since 1.0
     */
    private String markierungString;
    
    /**
     * Markierungs-Wert als boolean. 
     * Muss bei Programmänderung evtl. angepasst werden. Es gibt Petrinetze mit 
     * mehr als nur einer Markierung pro Stelle.
     * Derzeit wird die Markierung aus dem String-Wert erstellt.
     * 
     * @since 1.0
     */
    private Boolean markiert;
   
    /**
     * Leerer Konstruktor. Es wird eine ID vom Programm vergeben. Die Position, das 
     * Label und die Markierungen werden automatisch gesetzt.
     * Wir benötigt um innerhalb des Programmes neue Stellen zu erzeugen.
     * 
     * @since 1.0
     */
    public Stellen(){
        super();
        this.markierungString = "0";
        this.markiert = false;
    }
    
    /**
     * Konstruktor mit eigener ID. Die Position, das 
     * Label und die Markierungen werden automatisch gesetzt.
     * 
     * @param id Eigene ID als String-Wert
     * 
     * @since 1.0
     */
    public Stellen(String id){
        super(id, "null", "null","null");
        this.markierungString = "0";
        this.markiert = false;
    }
 
    /**
     * Methode setzt die Boolean-Markierung der Stelle. Der Eingabestring
     * wird überprüft, ob er "1" oder "true" ist. Sollte beides nicht der Fall
     * sein, so wird die Markierung auf false gesetzt.
     * Die Mehtode setzt den Eingangsstring als Markierungs-String.
     * 
     * @param initialMarking Markierungs-Wert als String-Wert
     * 
     * @since 1.0
     */
    public void setInitialMarking(String initialMarking) {
        //Setzte neuen markierungsString
        this.markierungString = initialMarking;
        
        //Prüfe ob 1 oder true, wenn nicht setzte markiert auf false.
        this.markiert = (   initialMarking.equals("1") 
                        ||  initialMarking.equals("true"));
    }
    
    /**
     * Methode setzt die Boolean- und die String-Markierung der Stelle. Wird zur Simultion des
     * Workflownetzes benötigt. Die String-Markierung wird zur Datenkonsistens für ein späteres
     * Speichern gesetzt.
     * 
     * @param flag Boolean-Wert für die Stellen-Markierungen (String und boolean)
     * 
     * @since 1.0
     */
    public void setMarkiert(boolean flag){
        //Setzte markiert auf flag 
        this.markiert = flag;
        
        //Setzte String-Wert der Stelle 
        if (flag){
            this.markierungString = "1";
        } else {
            this.markierungString = "0";
        }
    }
    
    /**
     * Methode holt den markierungString der Stelle. Diese Methode wird vom PNMLWirter
     * genutzt. 
     * 
     * @return Gibt den MarkierungsString der Stelle zurück.
     * 
     * @since 1.0
     */
    public String getMarkierungString() {
        return markierungString;
    } 
    public Boolean getMarkiert(){
        return markiert;
    }
}