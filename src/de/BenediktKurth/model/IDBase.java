package de.BenediktKurth.model;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public abstract class IDBase {
    
    /**
     * 
     */
    protected final String id;
    
    /**
     * 
     */    
    protected final int idInt;
     
    /**
     * 
     */    
    private boolean focusOn = false;
    
    /**
     * 
     */        
    private static int idCounter = 0;
    
    /**
     * 
     */    
    private static int size = 50;

    /**
     * Leerer Konstrukter erzeugt eine ID aus Basis des idCounter und erhöht 
     * idCounter um eins

     * @since 1.0
     */
    public IDBase () {
        Integer temp = IDBase.idCounter;
        this.id = temp.toString();
        
        this.idInt = getIdCounter();
    }
    
     /**
     * Konstrukter mit vorhander ID, erhöht anschließend idCounter um eins
     * 
     * @param id    String
     * 
     * @since 1.0
     * 
     */
    public IDBase(String id) {
        this.id = id;
        this.idInt = getIdCounter();
    }

    /**
     * Method gibt die ID des Objektes als String zurück
     * 
     * @return String   ID des Objektes
     * 
     * @since 1.0
     */
    public final String getId() {
        return id;
    }
    
    /**
     * Method gibt die ID des Objektes als int zurück
     * 
     * @return int   ID des Objektes als int
     * 
     * @since 1.0
     */    
    public final int f(){
        int tempInt = Integer.parseInt(this.id);
        return tempInt;
    }
    
    
    public final int getInterneID(){
        return this.idInt;
    }
    
    public final static void setSize(int faktor){
        float temp = faktor/100.0f;
        IDBase.size = (int)(50 * temp);
    }
    
    public final static int getSize(){
        return IDBase.size;
    }
    
    /**
     * Diese Method gibt den IdCounter der Klasse (Static) zurück
     * 
     * @return int   IdCounter der Struktur
     * 
     * @since 1.0
     */
    public final static int getIdCounter() {
        int temp = IDBase.idCounter;
        IDBase.idCounter++;
        return temp;
    }
    
    public final static void senkeIdCounter(){
       IDBase.idCounter--; 
    } 
    
    public final static void resetIdCounter(){
       IDBase.idCounter = 0; 
    } 

    public void setFocusOn(boolean focusOn) {
        this.focusOn = focusOn;
    }

    public boolean isFocusOn() {
        return focusOn;
    }
    
    
    
    
}
