package de.BenediktKurth.model;

import de.BenediktKurth.Exceptions.KeineEndstelleException;
import de.BenediktKurth.Exceptions.SackgasseException;
import de.BenediktKurth.Exceptions.KeineAnfangsstelleException;
import de.BenediktKurth.Exceptions.ZuVieleEndstellenException;
import de.BenediktKurth.Exceptions.ZuVieleAnfangsstellenException;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *  Diese Klasse stellt eine Adjazenzmatrix und ein dazugehörige Prüfung dar.
 *  Erste Aufgabe der Klasse ist die Erstellung einer Adjazenzmatrix.
 *  Zweite Aufgabe besteht aus einer Prüfung, ob alle Obejekt des Workflownetzes
 *  auf einem gerichteten Graphen zw. Anfang und Ende liegen.
 * 
 *  Klasse wirft keine Exceptions.
 * 
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
 */
public class Adjazenzmatrix {

    /**
     * Stellt die eigentliche Adjazenzmatrix dar.
     */
    private boolean[][] matrix;
    
    /**
     * Zähler für alle Stellen und Transitions im Workflownetz
     */
    private int gesamtZaehler;
    
    /**
     * Zähler für alle Stellen im Workflownetz
     */    
    private int stellenZaehler;
    
    /**
     * Zähler für alle Transitions im Workflownetz
     */    
    private int transitionZaehler;
    
    /**
     * Typsichere ArrayList auf IDBase-Objekts, diese speziel für alle Pfeile
     */
    private ArrayListSearchID arcListe;
    
    /**
     * Typsichere ArrayList auf IDBase-Objekts, enthält die Basisdaten des
     * Workflownetzes
     */
    private ArrayListSearchID gesamtListe;
    
    /**
     * Hilfsliste (Rekursion) Liste mit allen besuchten Knoten auf dem Hinweg
     * (Vom Anfang zum Ende)
     */
    private ArrayList<Integer> besuchtePunkteHin;
    
    /**
     * Hilfsliste (Rekursion) Liste mit allen besuchten Knoten auf dem Rückweg
     * (Vom Ende zum Anfang)
     */
    private ArrayList<Integer> besuchtePunkteHer;

    public Adjazenzmatrix(ArrayListSearchID array) {
        if (array != null) {
            aktualisieren(array);

        }

    }

    /**
     * Diese Methode setzt die Matrix zurück auf null und lässt dann die
     * Adjazenzmatrix des Workflownetzes auf Basis des EingabeArrays erstellen.
     * Die Methode teilt zugleich auch die Basisdaten in Stellen/Transitions und
     * Pfeilen, dabei werden Stellen und Transitions gezählt.
     *
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @param basisDatenArray EingabeArray mit dem Workflownetz "Basisdaten"
     *
     *
     * @return Boolean true -> es wurde etwas verändert; false -> es wurde nur
     * die alte Matrix gelöscht.
     */
    public final boolean aktualisieren(ArrayListSearchID basisDatenArray) {

        Boolean rueckgabeWert = (basisDatenArray.size() > 0);

        zuruecksetzen();

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

            i++;
        }

        // Evtl. für spätere Versionen derzeit eig. unnötig
        gesamtZaehler = stellenZaehler + transitionZaehler;

        // Erstelle neue Matrix (Mnn(true,false))
        this.matrix = initalisiereMatrix(gesamtZaehler);

        fuelleMatrix(arcListe, basisDatenArray);

        return rueckgabeWert;

    }

    /**
     * Die Methode füllt die Matrix mit den Verbindungen des Workflownetzes Dazu
     * benutzt sie das BasisDatenArray und das arcArray. Die Methode durchläuft
     * das arcArray und "fragt" seine Source- und Targeteinträge nach deren
     * interner ID, dann setzt die Methode an entsprechender Stelle in der
     * Matrix den Eintrag auf true (Es ex. ein Weg).
     *
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @param arcList
     * 
     * @param array
     */
    private void fuelleMatrix(ArrayListSearchID arcList, ArrayListSearchID array) {
        int i = 0;
        while (i < arcList.size()) {

            Arc temp = (Arc) arcList.get(i);
            String sourceString = temp.getSource();
            String targetString = temp.getTarget();
            IDBase sourceIDBase = (IDBase) array.searchID(sourceString);
            IDBase targetIDBase = (IDBase) array.searchID(targetString);
            int sourceInt = sourceIDBase.getInterneID();
            int targetInt = targetIDBase.getInterneID();

            this.matrix[sourceInt][targetInt] = true;

            i++;
        }
    }
    
    /**
     * Diese Methode
     *    
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @param groesse
     * 
     * @return 
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
     *  Methode
     * 
     *  @author Benedikt Kurth
     *
     *  @since 1.0
     *
     *  @version 1.0
     * 
     */
    private void zuruecksetzen() {
        this.stellenZaehler = 0;
        this.transitionZaehler = 0;
        this.gesamtZaehler = 0;
        this.arcListe = new ArrayListSearchID();
        this.matrix = null;
    }

    /**
     * Diese Methode
     *    
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @return 
     */    
    public ArrayList getAnfang() {

        ArrayList moeglicheAnfangsstellen = new ArrayList();
        

        if (this.gesamtZaehler <= 0) {
            return moeglicheAnfangsstellen;
        } else {
            boolean flag;

            for (int i = 0; i < this.gesamtZaehler; i++) {
                flag = false;
                for (int j = 0; j < this.gesamtZaehler; j++) {
                    if ((this.matrix[j][i])) {
                        flag = true;
                    }
                }
                if (!flag) {
                   
                    moeglicheAnfangsstellen.add(gesamtListe.getWithInternID(i));
                }
            }
        }

        return moeglicheAnfangsstellen;
    }

    /**
     * Diese Methode
     *    
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @return 
     */     
    public ArrayList getEnde() {

        ArrayList moeglicheEndstellen = null;

        if (this.gesamtZaehler <= 0) {
            return moeglicheEndstellen;
        } else {
        

            
            boolean flag;

            for (int i = 0; i < this.gesamtZaehler; i++) {
                flag = false;
                for (int j = 0; j < this.gesamtZaehler; j++) {
                    if ((this.matrix[i][j])) {
                        flag = true;
                    }
                }
                if (!flag) {
                    //Es kann kein Nullpointer übergeben werden da i ex.
                    //und damit auch das Objekt existiert.
                    moeglicheEndstellen.add(gesamtListe.getWithInternID(i));
                }
            }
        }

        return moeglicheEndstellen;
    }

    /**
     * Diese Methode überprüft ein bestehendes Netz (Art unbekannt) auf vorhanden
     * sein eines Workflownetzes. Dazu wird das Netz mithilfe der Adjazenzmatrix 
     * einmal Vorwärts und einmal Rückwärts durchlaufen. 
     * 
     * Es wird geprüft: 1. Gibt es eine Anfangsstelle und eine Endstelle?
     *                  2.a. Sind alle Stellen/Transitions von der Anfangsstelle
     *                       bzw. Endstelle erreichbar?
     *                  2.b. Wurden dabei alle Stellen/Transitions "besucht"?
     *                  3. Gibt es Sackgassen?
     * 
     * Hilfsmethoden:   _pruefeWorkflownetzVorwaerts
     *                  _pruefeWorkflownetzRuckwaerts
     * 
     * @author Benedikt Kurth
     * 
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @throws de.BenediktKurth.Exceptions.KeineAnfangsstelleException
     * @throws de.BenediktKurth.Exceptions.ZuVieleAnfangsstellenException
     * @throws de.BenediktKurth.Exceptions.KeineEndstelleException
     * @throws de.BenediktKurth.Exceptions.ZuVieleEndstellenException
     * @throws de.BenediktKurth.Exceptions.SackgasseException
     * 
     * @return boolean  True ->     Es handelt sich um ein Workflownetz
     *                  False ->    Es ist kein Workflownetz
     */
    public boolean pruefeWorkflownetz() throws  KeineAnfangsstelleException,
                                                ZuVieleAnfangsstellenException,
                                                KeineEndstelleException,
                                                ZuVieleEndstellenException,
                                                SackgasseException{

        //Bereitsstellen von allen benötigten Anfangsbedingungen
        besuchtePunkteHin = new ArrayList<>();
        besuchtePunkteHer = new ArrayList<>();
        ArrayList anfangsstelle = getAnfang();
        ArrayList endstelle = getEnde();
        
        //Prüfe: Keine Anfangsstelle?
        if (anfangsstelle.isEmpty()) {
            throw new KeineAnfangsstelleException();
        }
        
        //Prüfe: Gibt es mehrere Anfangsstellen?
         if (anfangsstelle.size() > 1) {
            Integer intTemp = anfangsstelle.size();
            String stringTemp = intTemp.toString();
            throw new ZuVieleAnfangsstellenException(stringTemp);
        }  
         
        //Prüfe: Keine Endstelle?
        if (endstelle.isEmpty()) {
            throw new KeineEndstelleException();
        }
        
        //Prüfe: Gibt es mehrere Endstellen?
         if (endstelle.size() > 1) {
            Integer intTemp = endstelle.size();
            String stringTemp = intTemp.toString();
            throw new ZuVieleEndstellenException(stringTemp);
        } 
        
        //Es gibt nur eine Anfangsstelle und ein Endstelle!
        
        Stellen anfang = (Stellen)anfangsstelle.get(anfangsstelle.size());
        Stellen ende = (Stellen)endstelle.get(endstelle.size());

        //Ermittlung Interner ID der Anfangsstelle und Endstelle
        int inIdAnfang = anfang.getInterneID();
        int inIdEnde = ende.getInterneID();

        //Prüfe ob alle Objekte auf einem gerichteten Graphen liegen und....
        boolean hin = _pruefeWorkflownetzVorwaerts(inIdAnfang, inIdEnde, besuchtePunkteHin);
        boolean zurueck = _pruefeWorkflownetzRuckwaerts(inIdAnfang, inIdEnde, besuchtePunkteHer);
        
        if (!hin || !zurueck){
            throw new SackgasseException();
        }
        
        //...wurden alle Objekte auf dem Hinweg und Rückweg besucht?
        hin = (hin && besuchtePunkteHin.size() == this.gesamtZaehler);
        zurueck = (zurueck && besuchtePunkteHer.size() == this.gesamtZaehler);

        return (hin && zurueck);
    }
 
    /**
     * Diese Methode
     *    
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @return 
     *  
     */
    private boolean _pruefeWorkflownetzVorwaerts(int inIdAnfang, int inIdEnde, ArrayList<Integer> besuchtePunkte) {

        //Sicherung gegen Zyklen -> Endlosschleife
        ListIterator<Integer> iterator = besuchtePunkte.listIterator();
        while (iterator.hasNext()) {
            int temp = iterator.next();
            if (temp == inIdAnfang) {

                return true;

            }
        }

        besuchtePunkte.add(inIdAnfang);

        boolean rueckgabeWert = true;

        if (inIdAnfang == inIdEnde) {
            return true;

        }

        int countNachfolger = 0;

        for (int i = 0; i < this.gesamtZaehler; i++) {
            if (this.matrix[inIdAnfang][i]) {
                countNachfolger++;
            }
        }

        if (countNachfolger == 0) {
            //throw new WorkflownetzDeadEndException();
            System.out.println("Fehler");
            return false;
        } else if (countNachfolger == 1) {
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[inIdAnfang][i]) {
                    rueckgabeWert = _pruefeWorkflownetzVorwaerts(i, inIdEnde, besuchtePunkte);

                }
            }
        } else if (countNachfolger > 1) {
            ArrayList<Integer> tempIntList = new ArrayList<>();
            ArrayList<Boolean> tempBooleanList = new ArrayList<>();
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[inIdAnfang][i]) {
                    tempIntList.add(i);
                }
            }

            for (Integer x : tempIntList) {
                tempBooleanList.add(_pruefeWorkflownetzVorwaerts(x, inIdEnde, besuchtePunkte));
            }

            boolean flag = false;
            for (Boolean x : tempBooleanList) {
                if (!x) {
                    flag = true;
                }
            }

            if (flag) {
                System.out.println("Ein Weg versperrt, hin");
                return false;
            }
        }

        return rueckgabeWert;
    }

    /**
     * Diese Methode
     *    
     * @author Benedikt Kurth
     *
     * @since 1.0
     *
     * @version 1.0
     * 
     * @return 
     *  
     */
    private boolean _pruefeWorkflownetzRuckwaerts(int inIdAnfang, int inIdEnde, ArrayList<Integer> besuchtePunkte) {

        //Sicherung gegen Zyklen -> Endlosschleife
        ListIterator<Integer> iterator = besuchtePunkte.listIterator();
        while (iterator.hasNext()) {
            int temp = iterator.next();
            if (temp == inIdEnde) {
                System.out.println("Zyklus her");
                return true;
            }
        }

        besuchtePunkte.add(inIdEnde);

        boolean rueckgabeWert = true;

        if (inIdEnde == inIdAnfang) {
            return true;

        }

        int countVorgaenger = 0;

        for (int i = 0; i < this.gesamtZaehler; i++) {
            if (this.matrix[i][inIdEnde]) {
                countVorgaenger++;

            }
        }

        if (countVorgaenger == 0) {
            //throw new WorkflownetzDeadEndException();
            System.out.println("Fehler");
            return false;

        } else if (countVorgaenger == 1) {
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[i][inIdEnde]) {
                    rueckgabeWert = _pruefeWorkflownetzRuckwaerts(inIdAnfang, i, besuchtePunkte);
                    //System.out.println("1");
                }
            }

        } else if (countVorgaenger > 1) {

            ArrayList<Integer> tempIntList = new ArrayList<>();
            ArrayList<Boolean> tempBooleanList = new ArrayList<>();
            for (int i = 0; i < this.gesamtZaehler; i++) {
                if (this.matrix[i][inIdEnde]) {
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
