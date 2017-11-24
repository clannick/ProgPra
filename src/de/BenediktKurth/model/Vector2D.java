package de.BenediktKurth.model;

/**
 * Hilfsklasse zur Darstellung von x / y Koordinaten.
 * 
 * Einfache Klasse mit Getter und Setter Methoden für 2 Integer Werten.
 * 
 * @author clannick
 * 
 * @version 1.0
 * 
 * @since 1.0
 */

public class Vector2D {
    
    /**
     * Integer-Wert für die x Koordinate
     * 
     * @since 1.0
     */
    private int x;
    
    /**
     * Integer-Wert für die y Koordinate
     * 
     * @since 1.0
     */
    private int y;
    
    /**
     * Vollständiger Kostruktor mit klarem Anfangszustand.
     * 
     * @param x Integer-Wert für die x Koordinate.
     * 
     * @param y Integer-Wert für die y Koordinate.
     * 
     * @since 1.0
     */
    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Methode setzt die x Koordinate auf einen integer-Wert.
     * 
     * @param x Integer-Wert für die x-Achse.
     * 
     * @since 1.0
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Methode setzt die y Koordinate auf einen integer-Wert.
     * 
     * @param y Integer-Wert für die y-Achse.
     * 
     * @since 1.0
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Methode gibt die x Koordinate als integer-Wert.
     * 
     * @return x Koordinate als integer-Wert
     * 
     * @since 1.0
     */
    public int getX() {
        return x;
    }

    /**
     * Methode gibt die y Koordinate als integer-Wert.
     * 
     * @return y Koordinate als integer-Wert
     * 
     * @since 1.0
     */
    public int getY() {
        return y;
    }
}