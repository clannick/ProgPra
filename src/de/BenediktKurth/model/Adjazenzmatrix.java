package de.BenediktKurth.model;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author Benedikt Kurth
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
public class Adjazenzmatrix {

    private boolean[][] matrix;
    private int gesamtCounter;
    private int stellenCounter;
    private int transitionCounter;
    private ArrayListSearchID arcListe;
    private ArrayListSearchID gesamtListe;
    private ArrayList<Integer> besuchtePunkteHin;
    private ArrayList<Integer> besuchtePunkteHer;

    public Adjazenzmatrix(ArrayListSearchID array) {
        if (array != null) {
            initComponents(array);

        }

    }

    public void printMatrix() {
        for (int i = 0; i < gesamtCounter; i++) {
            for (int j = 0; j < gesamtCounter; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void initComponents(ArrayListSearchID array) {
        aktualisieren(array);
    }

    /**
     *
     * @param array
     * @return Boolean true -> es wurde etwas verändert; false -> es wurde
     * nichts geändert
     */
    public boolean aktualisieren(ArrayListSearchID array) {
        Boolean rueckgabeWert = false;

        reset();
        this.gesamtListe = array;
        int i = 0;

        while (i < array.size()) {
            if (array.get(i) instanceof Stellen) {
                stellenCounter++;
            }
            if (array.get(i) instanceof Transition) {
                transitionCounter++;
            }
            if (array.get(i) instanceof Arc) {
                arcListe.add(array.get(i));
            }
            rueckgabeWert = true;
            i++;
        }

        gesamtCounter = stellenCounter + transitionCounter;

        this.matrix = initalisiereMatrix(gesamtCounter);

        fuelleMatrix(arcListe, array);

        return rueckgabeWert;

    }

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

    private boolean[][] initalisiereMatrix(int groesse) {
        boolean[][] tempMatrix = new boolean[groesse][groesse];
        for (int g = 0; g < groesse; g++) {
            for (int j = 0; j < groesse; j++) {
                tempMatrix[g][j] = false;
            }
        }
        return tempMatrix;
    }

    private void reset() {
        this.stellenCounter = 0;
        this.transitionCounter = 0;
        this.gesamtCounter = 0;
        this.arcListe = new ArrayListSearchID();
        this.matrix = null;
    }

    public Stellen getAnfang() {

        Stellen rueckgabeWert = null;

        if (this.gesamtCounter <= 0) {
            return rueckgabeWert;
        } else {
            int moeglicheAnfaenge = 0;

            IDBase temp = null;
            boolean flag;

            for (int i = 0; i < this.gesamtCounter; i++) {
                flag = false;
                for (int j = 0; j < this.gesamtCounter; j++) {
                    if ((this.matrix[j][i])) {
                        flag = true;
                    }
                }
                if (!flag) {
                    moeglicheAnfaenge++;
                    temp = gesamtListe.getWithInternID(i);
                }
            }

            if ((moeglicheAnfaenge == 1)
                    && (temp instanceof Stellen)) {
                return (Stellen) temp;

            } else if (moeglicheAnfaenge > 1) {
                //throw new ZuVieleAnfaengeException();
            }

        }

        return rueckgabeWert;
    }

    public Stellen getEnde() {

        Stellen rueckgabeWert = null;

        if (this.gesamtCounter <= 0) {
            return rueckgabeWert;
        } else {
            int moeglicheAnfaenge = 0;

            IDBase temp = null;
            boolean flag;

            for (int i = 0; i < this.gesamtCounter; i++) {
                flag = false;
                for (int j = 0; j < this.gesamtCounter; j++) {
                    if ((this.matrix[i][j])) {
                        flag = true;
                    }
                }
                if (!flag) {
                    moeglicheAnfaenge++;
                    temp = gesamtListe.getWithInternID(i);
                }
            }

            if ((moeglicheAnfaenge == 1)
                    && (temp instanceof Stellen)) {
                return (Stellen) temp;

            } else if (moeglicheAnfaenge > 1) {
                //throw new ZuVieleEndenException();
            }

        }

        return rueckgabeWert;
    }

    public boolean pruefeWorkflownetz() {
        
        besuchtePunkteHin = new ArrayList<>();
        besuchtePunkteHer = new ArrayList<>();
        Stellen anfang = this.getAnfang();
        Stellen ende = this.getEnde();

        if ((anfang == null) || (ende == null)) {
            System.out.println("Kein Anfang oder kein Ende");
            return false;
        }

        int inIdAnfang = anfang.getInterneID();
        int inIdEnde = ende.getInterneID();

        boolean hin = _pruefeWorkflownetzVorwaerts(inIdAnfang, inIdEnde, besuchtePunkteHin);
        hin = ( hin && besuchtePunkteHin.size() == this.gesamtCounter);

        System.out.println(besuchtePunkteHin.size() + " " + this.gesamtCounter);
        
        boolean zurueck = _pruefeWorkflownetzRuckwaerts(inIdAnfang, inIdEnde, besuchtePunkteHer);
        zurueck = ( zurueck && besuchtePunkteHer.size() == this.gesamtCounter);

        System.out.println(besuchtePunkteHer.size() + " " + this.gesamtCounter);
        
        return (hin && zurueck);
    }

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

        for (int i = 0; i < this.gesamtCounter; i++) {
            if (this.matrix[inIdAnfang][i]) {
                countNachfolger++;
            }
        }

        if (countNachfolger == 0) {
            //throw new WorkflownetzDeadEndException();
            System.out.println("Fehler");
            return false;
        } else if (countNachfolger == 1) {
            for (int i = 0; i < this.gesamtCounter; i++) {
                if (this.matrix[inIdAnfang][i]) {
                    rueckgabeWert = _pruefeWorkflownetzVorwaerts(i, inIdEnde, besuchtePunkte);
                    
                }
            }
        } else if (countNachfolger > 1) {
            ArrayList<Integer> tempIntList = new ArrayList<>();
            ArrayList<Boolean> tempBooleanList = new ArrayList<>();
            for (int i = 0; i < this.gesamtCounter; i++) {
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

        for (int i = 0; i < this.gesamtCounter; i++) {
            if (this.matrix[i][inIdEnde]) {
                countVorgaenger++;
                
            }
        }
        

        if (countVorgaenger == 0) {
            //throw new WorkflownetzDeadEndException();
            System.out.println("Fehler");
            return false;

        } else if (countVorgaenger == 1) {
            for (int i = 0; i < this.gesamtCounter; i++) {
                if (this.matrix[i][inIdEnde]) {
                    rueckgabeWert = _pruefeWorkflownetzRuckwaerts(inIdAnfang, i, besuchtePunkte);
                    //System.out.println("1");
                }
            }

        } else if (countVorgaenger > 1) {
            
            ArrayList<Integer> tempIntList = new ArrayList<>();
            ArrayList<Boolean> tempBooleanList = new ArrayList<>();
            for (int i = 0; i < this.gesamtCounter; i++) {
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
}
