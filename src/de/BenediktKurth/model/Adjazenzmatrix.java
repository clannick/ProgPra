package de.BenediktKurth.model;

import de.BenediktKurth.Exceptions.WorkflownetzException;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *  Diese Klasse implementiert eine Adjazenzmatrix mit einer Workflownetz prüfung.
 *  Erste Aufgabe der Klasse ist die Erstellung einer Adjazenzmatrix.
 *  Zweite Aufgabe besteht aus einer Prüfung, ob alle Obejekt des Workflownetzes
 *  auf einem gerichteten Graphen zw. Anfang und Ende liegen.
 * 
 *  Klasse wirft keine Exceptions.
 * 
 *  Plannung:   - Exceptions
 *              - Verbesserung von prüfeWorkflow...
 *              - Kommentare
 *              - JavaDoc-Kommentare
 * 
 * @author Benedikt Kurth
 *
 * @since 1.0
 *
 * @version 1.0
 * 
 * @see ArrayListSearchID
 * @see <a href="https://de.wikipedia.org/wiki/Adjazenzmatrix" target="_blank">Wikipedia - Adjazenzmatrix</a>
 */
public final class Adjazenzmatrix {

    /**
     * Stellt die eigentliche Adjazenzmatrix dar.
     * 
     * @since 1.0
     */
    private boolean[][] matrix;
    
    /**
     * Zähler für alle Stellen und Transitions im Workflownetz.
     * 
     * @since 1.0
     */
    private int gesamtZaehler;
    
    /**
     * Zähler für alle Stellen im Workflownetz.
     * Derzeit überflüssig, gesamtZaehler würde ausreichen evtl. für spätere Versionen.
     * 
     * @since 1.0
     */    
    private int stellenZaehler;
    
    /**
     * Zähler für alle Transitions im Workflownetz.
     * Derzeit überflüssig, gesamtZaehler würde ausreichen evtl. für spätere Versionen.
     * 
     * @since 1.0
     */    
    private int transitionZaehler;
    
    /**
     * Typsichere ArrayList auf IDBase-Objekts, diese speziel für alle Pfeile.
     * 
     * @since 1.0
     * 
     * @see ArrayListSearchID
     */
    private ArrayListSearchID<Arc> arcListe;
    
    /**
     * Typsichere ArrayList auf IDBase-Objekts, enthält die Basisdaten des
     * Workflownetzes.
     * 
     * @since 1.0
     * 
     * @see ArrayListSearchID
     */
    private ArrayListSearchID<IDBase> gesamtListe;
    
    /**
     * Hilfsliste (Rekursion) Liste mit allen besuchten Knoten auf dem Hinweg
     * (Vom Anfang zum Ende).
     * 
     * @since 1.0
     * 
     * @see java.util.ArrayList
     */
    private ArrayList<Integer> besuchtePunkteHin;
    
    /**
     * Hilfsliste (Rekursion) Liste mit allen besuchten Knoten auf dem Rückweg
     * (Vom Ende zum Anfang).
     * 
     * @since 1.0
     * 
     * @see java.util.ArrayList
     */
    private ArrayList<Integer> besuchtePunkteHer;

    /**
     * Der Kontruktor überprüft, ob eine nicht leere Basisdatenmenge übergeben
     * wurde.
     * Sollte ein leere Basisdatenmenge übergeben werden, wird keine Aktion 
     * ausgeführt!
     * Kontruktor ruft Methode aktualisieren auf.
     * 
     * @since 1.0
     * 
     * @param array  ArrayListSearchID mit den Basisdaten für die Adjazenzmatrix.
     * 
     * @see ArrayListSearchID
     * @see #aktualisieren(de.BenediktKurth.model.ArrayListSearchID)
     */
    /*public Adjazenzmatrix(ArrayListSearchID<IDBase> array) {
        if (!array.isEmpty()) {
            aktualisieren(array);
        }
    }*/

    public Adjazenzmatrix (ArrayListSearchID<IDBase> array) {
        aktualisieren(array);  
        
    }
    
    
    /**
     * Diese Methode setzt die Matrix zurück auf null und lässt dann die
     * Adjazenzmatrix des Workflownetzes auf Basis des EingabeArrays erstellen.
     * Die Methode teilt zugleich auch die Basisdaten in Stellen/Transitions und
     * Pfeilen, dabei werden Stellen und Transitions gezählt.
     *
     * @since 1.0
     *
     * @param basisDatenArray EingabeArray mit dem Workflownetz "Basisdaten"
     *
     * @return Boolean  True: es wurde etwas verändert. 
     *                  False: es wurde nur die alte Matrix gelöscht.
     */
    public final boolean aktualisieren(ArrayListSearchID<IDBase> basisDatenArray) {
        
    // Rückgabewert ist true, wenn basisDatenArray nicht leer ist.
        if (basisDatenArray.isEmpty()){
            return false;
        }
        
        // Setzt alle Werte der "alten" Matrix auf null
        zuruecksetzen();
        
        //Init Hilfsvariablen
        this.gesamtListe = basisDatenArray;
        int i = 0;

        // Sortiere Array nach S/T/A 
        while (i < basisDatenArray.size()) {
            if (basisDatenArray.get(i) instanceof Stellen) {
                stellenZaehler++;
            }
            if (basisDatenArray.get(i) instanceof Transition) {
                transitionZaehler++;
            }
            if (basisDatenArray.get(i) instanceof Arc) {
                arcListe.add(basisDatenArray.get(i));
            }

            //Gehe zum nächsten Objekt
            i++;
        }

        // Evtl. für spätere Versionen derzeit eig. unnötig
        gesamtZaehler = stellenZaehler + transitionZaehler;

        // Erstelle neue Matrix (Mnn(true,false))
        this.matrix = initalisiereMatrix(gesamtZaehler);

        //Befüllt Matrix auf Grundlage der arcListe, BasisDaten werden gebraucht
        //um InterneIDs der Source und Targets zu ermitteln
        fuelleMatrix(arcListe, basisDatenArray);

        return true;

    }

    /**
     * Die Methode füllt die Matrix mit den Verbindungen des Workflownetzes Dazu
     * benutzt sie das BasisDatenArray und das arcArray. Die Methode durchläuft
     * das arcArray und "fragt" seine Source- und Targeteinträge nach deren
     * interner ID, dann setzt die Methode an entsprechender Stelle in der
     * Matrix den Eintrag auf true (Es ex. ein Weg).
     *
     * @since 1.0
     * 
     * @param arcList   ArrayListSearchID mit allen gerichteten Graphen
     *    
     * @param basisdaten     ArrayListSearchID mit den Basisdaten
     * 
     * @see ArrayListSearchID
     */
    private void fuelleMatrix(ArrayListSearchID<Arc> arcList, ArrayListSearchID<IDBase> basisdaten ){
        
        //Hilfsvariable while-Schleife
        int i = 0;
        
        //Durchlaufe alle Pfeile in der Liste
        while (i < arcList.size()) {

            //Hol aktuellen Pfeil aus der Liste
            Arc arc = (Arc) arcList.get(i);
            
            //Suche Source und Target in Basisdaten und ermittle interner ID´s
            int sourceInt = basisdaten.searchID(arc.getSource()).getInterneID();
            int targetInt = basisdaten.searchID(arc.getTarget()).getInterneID();

            System.out.println(sourceInt +" "+ targetInt);
            //Setzte Adjazenmatrix an passender Stelle auf true
            //(Es gibt einen Weg von Source nach Target)
            this.matrix[sourceInt][targetInt] = true;

            //Nächstes Objekt
            i++;
        }
    }
    
    /**
     * Diese Methode erzeugt eine "leere" (false-Einträge) Adjazenmatrix 
     * gewünschter Größe.
     *    
     * @since 1.0
     * 
     * @param groesse   int Gibt die Größe der Adjazenzmatrix vor (n x n).
     * 
     * @return boolean[][]  Dies ist die Adjazenzmatrix in der gewünschten Größe.
     *                      Initalisiert wurden alle Felde mit false.
     */
    private boolean[][] initalisiereMatrix(int groesse) {
        boolean[][] tempMatrix = new boolean[groesse][groesse];
        for (int g = 0; g < groesse; g++) {
            for (int j = 0; j < groesse; j++) {
                tempMatrix[g][j] = false;
            }
        }
        return tempMatrix;
    }

    /**
     *  Methode setzt alle Parameter auf zurück auf null.     * 
     *
     *  @since 1.0
     */
    private void zuruecksetzen() {
        this.stellenZaehler = 0;
        this.transitionZaehler = 0;
        this.gesamtZaehler = 0;
        this.arcListe = new ArrayListSearchID<>();
        this.matrix = null;
    }

    /**
     * Diese Methode sucht mögliche Anfangsstellen auf Basis der Adjazenzmatrix.
     * Wenn es keinen Pfeile zur Stelle gibt, wird die Stelle vorgemerkt als
     * mögliche Anfangsstelle. Sollte es aus div. Gründen keine geben übergebe
     * eine leere Liste.
     *
     * @since 1.0
     * 
     * @return ArrayList Gibt eine Liste möglicher Anfangsstellen zurück. 
     * 
     * @see java.util.ArrayList
     */    
    public ArrayList getAnfang() {

        //Erstelle leere ArrayListe
        ArrayList moeglicheAnfangsstellen = new ArrayList();
        
        //Wenn es überhaupt Daten gibt prüfe diese, ansonsten gib leere Liste
        if (this.gesamtZaehler <= 0) {
            return moeglicheAnfangsstellen;
        } else {
            //Hilfsvariable 
            boolean flag;

            //Finde Stellen/Transitions ohne eigehende Pfeile
            for (int i = 0; i < this.gesamtZaehler; i++) {
                flag = false;
                for (int j = 0; j < this.gesamtZaehler; j++) {
                    //Wenn es eingehenden Pfeile gibt, setzte flag
                    if ((this.matrix[j][i])) {
                        flag = true;
                    }
                }
                
                // Wenn keine eigehende Pfeile und Objekt ist eine Stelle
                if (!flag && (gesamtListe.getWithInternID(i) instanceof Stellen)) {
                   
                    //Füge es als mögliche Anfangsstelle hinzu
                    moeglicheAnfangsstellen.add(gesamtListe.getWithInternID(i));
                }
            }
        }

        return moeglicheAnfangsstellen;
    }

    /**
     * Diese Methode sucht mögliche Anfangsstellen auf Basis der Adjazenzmatrix.
     * Wenn es keinen Pfeile zur Stelle gibt, wird die Stelle vorgemerkt als
     * mögliche Anfangsstelle. Sollte es aus div. Gründen keine geben übergebe
     * eine leere Liste.
     *
     * @since 1.0
     * 
     * @return ArrayList Gibt eine Liste möglicher Anfangsstellen zurück. 
     * 
     * @see java.util.ArrayList
     */      
    public ArrayList getEnde() {

        //Erstelle leere ArrayListe
        ArrayList moeglicheEndstellen = new ArrayList();
        
        //Wenn es überhaupt Daten gibt prüfe diese, ansonsten gib leere Liste
        if (this.gesamtZaehler <= 0) {
            return moeglicheEndstellen;
        } else {
            //Hilfsvariable 
            boolean flag;

            //Finde Stellen/Transitions ohne ausgehende Pfeile
            for (int i = 0; i < this.gesamtZaehler; i++) {
                flag = false;
                for (int j = 0; j < this.gesamtZaehler; j++) {
                    //Wenn es ausgehende Pfeile gibt, setzte flag
                    if ((this.matrix[i][j])) {
                        flag = true;
                    }
                }
                // Wenn keine ausgehenden Pfeile und Objekt ist eine Stelle
                if (!flag && (gesamtListe.getWithInternID(i) instanceof Stellen)) {
                    
                    //Füge es als mögliche Endstelle hinzu
                    moeglicheEndstellen.add(gesamtListe.getWithInternID(i));
                }
            }
        }

        return moeglicheEndstellen;
    }

    /**
     * Diese Methode überprüft ein bestehendes Netz (Art unbekannt) auf vorhanden
     * sein eines Workflownetzes. Dazu wird das Netz mithilfe der Adjazenzmatrix 
     * einmal Vorwärts und einmal Rückwärts durchlaufen. <br>
     * <br>
     * Es wird geprüft:<br> 1. Gibt es eine Anfangsstelle und eine Endstelle?<br>
     *                  2.a. Sind alle Stellen/Transitions von der Anfangsstelle
     *                       bzw. Endstelle erreichbar?<br>
     *                  2.b. Wurden dabei alle Stellen/Transitions "besucht"?<br>
     *                  3. Gibt es Sackgassen?<br>
     * <br>
     * Hilfsmethoden:<br>   _pruefeWorkflownetzVorwaerts<br>
     *                      _pruefeWorkflownetzRuckwaerts<br>
     * 
     * @since 1.0
     * 
     * @return boolean  True: Es handelt sich um ein Workflownetz
     *                  False: Es ist kein Workflownetz
     *
     * @throws de.BenediktKurth.Exceptions.WorkflownetzException Es wird zu
     *          jeder Exception ein entsprechende Nachricht mitgegeben.
     * 
     * @see java.util.ArrayList
     */
    public boolean pruefeWorkflownetz() throws WorkflownetzException{

        //Bereitsstellen von allen benötigten Anfangsbedingungen
        besuchtePunkteHin = new ArrayList<>();
        besuchtePunkteHer = new ArrayList<>();
        ArrayList anfangsstelle = getAnfang();
        ArrayList endstelle = getEnde();
        
        //Prüfe: Keine Anfangsstelle?
        if (anfangsstelle.isEmpty()) {
            throw new WorkflownetzException("Es existiert keine Anfangsstelle.");
        }
        
        //Prüfe: Gibt es mehrere Anfangsstellen?
         if (anfangsstelle.size() > 1) {
            Integer intTemp = anfangsstelle.size();
            String stringTemp = intTemp.toString();
            throw new WorkflownetzException("Es existieren " + stringTemp + " Anfangsstellen.");
        }  
         
        //Prüfe: Keine Endstelle?
        if (endstelle.isEmpty()) {
            throw new WorkflownetzException("Es existiert keine Endstelle.");
        }
        
        //Prüfe: Gibt es mehrere Endstellen?
         if (endstelle.size() > 1) {
            Integer intTemp = endstelle.size();
            String stringTemp = intTemp.toString();
            throw new WorkflownetzException("Es existieren " + stringTemp + " Endstellen.");
        } 
        
        //Es gibt nur eine Anfangsstelle und ein Endstelle!
        
        Stellen anfang = (Stellen)anfangsstelle.get(0);
        Stellen ende = (Stellen)endstelle.get(0);

        //Ermittlung Interner ID der Anfangsstelle und Endstelle
        int inIdAnfang = anfang.getInterneID();
        int inIdEnde = ende.getInterneID();

        //Prüfe ob alle Objekte auf einem gerichteten Graphen liegen und....
        boolean hin = _pruefeWorkflownetzVorwaerts(inIdAnfang, inIdEnde, besuchtePunkteHin);
        boolean zurueck = _pruefeWorkflownetzRuckwaerts(inIdAnfang, inIdEnde, besuchtePunkteHer);
        
        if (!hin || !zurueck){
            throw new WorkflownetzException("Es gibt eine Sackgasse im Workflownetz.");
        }
        
        //...wurden alle Objekte auf dem Hinweg und Rückweg besucht?
        hin = (hin && besuchtePunkteHin.size() == this.gesamtZaehler);
        zurueck = (zurueck && besuchtePunkteHer.size() == this.gesamtZaehler);

        if (!hin || !zurueck){
            throw new WorkflownetzException("Es liegen nicht alle Objekte auf einem gerichteten Graphen.");
        }
        
        return (hin && zurueck);
    }
 
    /**
     * Diese Methode prüft ein Netzwerk (vorwärts) auf Sackgassen und 
     * merkt sich besuchte Objekte.
     *
     * @since 1.0
     *
     * @param anfangsID     Interne ID der Stelle/Transition von der gesucht werden soll.
     * 
     * @param inIdEnde      Interne ID der Endstelle.
     * 
     * @param besuchtePunkte    ArrayList mit allen besuchten Obejkten
     *                          (interne ID).
     * 
     * @return boolean  True: Es gibt keine Sackgassen
     *                  False: Es gibt Sackgassen
     */
    private boolean _pruefeWorkflownetzVorwaerts(int anfangsID, int inIdEnde, ArrayList<Integer> besuchtePunkte) {

        //Sicherung gegen Zyklen -> Endlosschleife
        ListIterator<Integer> iterator = besuchtePunkte.listIterator();
        while (iterator.hasNext()) {
            int temp = iterator.next();
            if (temp == anfangsID) {

                return true;
            }
        }

        besuchtePunkte.add(anfangsID);

        boolean rueckgabeWert = true;

        //Wenn Anfang = Ende, dann ziel erfüllt
        if (anfangsID == inIdEnde) {
            return true;
        }

        int countNachfolger = 0;

        for (int i = 0; i < this.gesamtZaehler; i++) {
            if (this.matrix[anfangsID][i]) {
                countNachfolger++;
            }
        }

        if (countNachfolger == 0) {
            //Es handelt sich um eine Sackgasse
            return false;
       
        //Es gibt genau einen Nachfolger, dann rufe einmal rekursiv auf    
        } else if (countNachfolger == 1) {
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[anfangsID][i]) {
                    rueckgabeWert = _pruefeWorkflownetzVorwaerts(i, inIdEnde, besuchtePunkte);
                }
            }
            
        //Es gibt n Nachfolger, dann rufe n-mal rekursiv auf      
        } else if (countNachfolger > 1) {
            
            ArrayList<Integer> listeNachfolger = new ArrayList<>();
            ArrayList<Boolean> listePruefungen = new ArrayList<>();
            
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[anfangsID][i]) {
                    listeNachfolger.add(i);
                }
            }

            for (Integer x : listeNachfolger) {
                listePruefungen.add(_pruefeWorkflownetzVorwaerts(x, inIdEnde, besuchtePunkte));
            }

            boolean flag = false;
            for (Boolean x : listePruefungen) {
                if (!x) {
                    flag = true;
                }
            }

            if (flag) {
                return false;
            }
        }

        return rueckgabeWert;
    }

    /**
     * Diese Methode prüft ein Netzwerk (rückwärts) auf Sackgassen und 
     * merkt sich besuchte Objekte.
     *
     * @since 1.0
     *
     * @param inIdAnfang     Interne ID der Anfangsstelle. 
     * 
     * @param anfangsID      Interne ID der Stelle/Transition von der aus gesucht werden soll.
     * 
     * @param besuchtePunkte    ArrayList mit allen besuchten Obejkten
     *                          (interne ID).
     * 
     * @return boolean  True: Es gibt keine Sackgassen
     *                  False: Es gibt Sackgassen
     */
    private boolean _pruefeWorkflownetzRuckwaerts(int inIdAnfang, int anfangsID, ArrayList<Integer> besuchtePunkte) {

        //Sicherung gegen Zyklen -> Endlosschleife
        ListIterator<Integer> iterator = besuchtePunkte.listIterator();
        while (iterator.hasNext()) {
            int temp = iterator.next();
            if (temp == anfangsID) {
 
                return true;
            }
        }

        besuchtePunkte.add(anfangsID);

        boolean rueckgabeWert = true;

        if (anfangsID == inIdAnfang) {
            return true;

        }

        int countVorgaenger = 0;

        for (int i = 0; i < this.gesamtZaehler; i++) {
            if (this.matrix[i][anfangsID]) {
                countVorgaenger++;

            }
        }

        if (countVorgaenger == 0) {

            return false;

        } else if (countVorgaenger == 1) {
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[i][anfangsID]) {
                    rueckgabeWert = _pruefeWorkflownetzRuckwaerts(inIdAnfang, i, besuchtePunkte);
                    //System.out.println("1");
                }
            }

        } else if (countVorgaenger > 1) {

            ArrayList<Integer> tempIntList = new ArrayList<>();
            ArrayList<Boolean> tempBooleanList = new ArrayList<>();
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[i][anfangsID]) {
                    tempIntList.add(i);
                }
            }

            for (Integer x : tempIntList) {
                tempBooleanList.add(_pruefeWorkflownetzRuckwaerts(inIdAnfang, x, besuchtePunkte));
            }

            boolean flag = false;
            for (Boolean x : tempBooleanList) {
                if (!x) {
                    flag = true;

                }
            }

            if (flag) {
                return false;
            }
        }

        return rueckgabeWert;
    }

    /**
     * TEST-Methode
     */
    public void druckeMatrix() {
        for (int i = 0; i < gesamtZaehler; i++) {
            for (int j = 0; j < gesamtZaehler; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
