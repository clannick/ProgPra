package de.BenediktKurth.model;

/**
 *
 * @author clannick
 */
public class Platzhalter {
    
    private int x;
    private int y;
    
    public Platzhalter(){
        this.x = 0;
        this.y = 0;
    }
    
    public Vector2D getPosition(){
        return new Vector2D(x,y);
    }
    
    public void setPosition(Vector2D position){ 
        this.x = position.getX();
        this.y = position.getY();
    }
}
