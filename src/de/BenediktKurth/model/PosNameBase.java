package de.BenediktKurth.model;

/**
 * Basisklasse für Positionen und Labels der Objekte
 * 
 * @author Benedikt Kurth
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public abstract class PosNameBase extends IDBase {

    /**
     * Label enthält den angezeigten Bezeichner des Objektes.
     * 
     * @since 1.0
     */
    private String              label;

    /**
     * position enthält die Position des Objektes als Vector2D.
     * 
     * @since 1.0
     *
     * @see Vector2D
     */
    private Vector2D            position;

    /**
     * Leerer Konstruktor - Erzeugt ein leeres Objekt mit klarer ID Einträge
     * ohne Wert werden mit String "null" belegt.
     *
     * @since 1.0
     */
    PosNameBase() {
        // Ruft Konstruktor von IDBase auf -> klare ID 
        super();

        this.label = "null";
        this.position = new Vector2D(100, 100);

    }

    /**
     * Konstruktor erwarter vier Strings. 
     *
     * @param id String         ID des Objektes
     *
     * @param label String      Label bzw. Name oder Bezeichnung des Objektes
     *
     * @param xPosition String  Position auf der x-Achse als String
     *
     * @param yPosition String  Position auf der y-Achse als String
     *
     * @since 1.0
     */
    PosNameBase(String id, String label, String xPosition, String yPosition) {
        super(id);
        this.label = label;
        setPositionfromString(xPosition, yPosition);
    }

    /**
     * Methode gibt das Label als String zurück.
     * 
     * @return Label als String.
     * 
     * @since 1.0
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Methode gibt die x Position als String zurück.
     * 
     * @return x Position als String.
     * 
     * @since 1.0
     */
    public final String gibXPosition() {
        String rueckgabe = "" + this.position.getX();
        return rueckgabe;
    }

    /**
     * Methode gibt die y Position als String zurück.
     * 
     * @return y Position als String.
     * 
     * @since 1.0
     */
    public final String gibYPosition() {
        String rueckgabe = "" + this.position.getY();
        return rueckgabe;
    }
    
    /**
     * Methode gibt die Position als Vector2D zurück.
     * 
     * @return Position als Vector2D.
     * 
     * @since 1.0
     * 
     * @see Vector2D
     */
    public final Vector2D getPosition() {
        return this.position;
    }
    
    /**
     * Methode zum setzen des Label eines Objektes.
     * 
     * @param label String mit neuem Label für das Objekt.
     * 
     * @since 1.0
     */
    public final void setLabel(String label) {
        this.label = label;
    }

    /**
     * Methode setzt die Position des Objektes mit zwei integer-Werten.
     * Die Methode überprüft ob die Werte positiv sind, ansonsten werden 
     * die Werte auf 0 gesetzt.
     * 
     * @param x Integer-Wert für die x-Achse.
     * 
     * @param y Integer-Wert für die y-Achse.
     * 
     * @since 1.0
     */
    public final void setPosition(int x, int y) {
       
        if (x > 0) {
                position.setX(x);
        } else {
                position.setX(0);
            }
        
        if (y > 0) {
                position.setY(y);
        } else {
                position.setY(0);
            }

    }

    /**
     * Methode zum setzen der Position aus Strings.
     * Die Methode wandelt String Positionen zu Integer-Werten um und setzt die Position
     * (Vector2D).
     * Sollte eine umwandlung nicht möglich sein, so werden die Werte auf 30,30 gestetzt.
     * 
     * @param xPosition x Position als String.
     * 
     * @param yPosition y Position als String.
     * 
     * @since 1.0
     * 
     * @see Vector2D
     */
    public final void setPositionfromString(String xPosition, String yPosition) {
      
        //Erzeuge neuen Vector 2D
        this.position = new Vector2D(0, 0);
        
        //Versuche Strings in Int zu wandeln
        try {
            int x = Integer.parseInt(xPosition);
            int y = Integer.parseInt(yPosition);
            setPosition(x,y);

        } catch (NumberFormatException e) {
            //Wenn es nicht funktioniert setzte Position auf (100,100)
            setPosition(50,50);
        }
    }
}