package de.BenediktKurth.model;

/**
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 */
public abstract class PosNameBase extends IDBase {

    /**
     *
     * @param label Das Label ist ein Bezeichner der im Programm genutzt wird.
     *
     * @param xPosition xPosition gibt die räumliche Lage des Objektes in der
     * Horizontalen an. Es wird der am weitesten links liegende Punkt genutzt.
     *
     * @param yPosition yPosition gibt die räumliche Lage des Objektes in der
     * Vertikalen an. Es wird der am weitesten oben liegende Punkt genutzt.
     */
    /**
     * Label enthält den angezeigten Bezeichner des Objektes
     */
    private String label;


    /**
     * position enthält die Position des Objektes als Vector2D
     *
     * @see Vector2D
     */
    private Vector2D position;

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
        this.position = new Vector2D(30, 30);

    }

    /**
     * Konstruktor erwarter vier Strings
     *
     * @param id String
     *
     * @param label String
     *
     * @param xPosition String
     *
     * @param yPosition String
     *
     * @since 1.0
     */
    PosNameBase(String id, String label, String xPosition, String yPosition) {
        super(id);
        this.label = label;
        setPositionfromString(xPosition, yPosition);
    }

    public final String getLabel() {
        return label;
    }

    public final String getxPosition() {
        String rueckgabe = "" + this.position.getX();
        return rueckgabe;
    }

    public final String getyPosition() {
        String rueckgabe = "" + this.position.getY();
        return rueckgabe;
    }

    public final void setLabel(String label) {
        this.label = label;
    }

    public final Vector2D getPosition() {
        return this.position;
    }

    public final void setPosition(int x, int y) {
        if (x > -1) {
                this.position.setX(x);
        } else {
                this.position.setX(0);
            }
        if (y > -1) {
                this.position.setY(y);
        } else {
                this.position.setY(0);
            }

    }

    public final void setPositionfromString(String xPosition, String yPosition) {
      
        this.position = new Vector2D(0, 0);
        try {
            int x = Integer.parseInt(xPosition);
            int y = Integer.parseInt(yPosition);
            setPosition(x,y);

        } catch (NumberFormatException e) {
            setPosition(30,30);
        }
    }

    @Override
    public String toString() {
        return id + ", " + label + ".";
    }

}
