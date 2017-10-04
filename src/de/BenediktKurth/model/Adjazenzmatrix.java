/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.BenediktKurth.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author clannick
 */
public class Adjazenzmatrix {
    
    private boolean[][] matrix;
    private int gesamtCounter;
    private int stellenCounter;
    private int transitionCounter;
    private ArrayListSearchID arcListe;
    private ArrayListSearchID gesamtListe;
    
    
    
    public Adjazenzmatrix(ArrayListSearchID array){
        if (array != null){
            initComponents(array);
            
        }
        
    }
        

    public void printMatrix(){
        for (int i = 0 ; i < gesamtCounter; i++){
            for (int j = 0 ; j < gesamtCounter; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void initComponents(ArrayListSearchID array){
        aktualisieren(array);        
    } 
    
    /**
     * 
     * @param array
     * @return Boolean  true -> es wurde etwas verändert; false -> es wurde nichts
     *                          geändert
     */
    public boolean aktualisieren(ArrayListSearchID array){
        Boolean rueckgabeWert = false;
           
        reset();
        this.gesamtListe = array;
        int i = 0;
        
        while (i < array.size()){
            if (array.get(i) instanceof Stellen){
                stellenCounter++;
            }
            if (array.get(i) instanceof Transition){
                transitionCounter++;
            }
            if (array.get(i) instanceof Arc){
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
    
    private void fuelleMatrix(ArrayListSearchID arcList, ArrayListSearchID array){
        int i = 0;
        while (i < arcList.size()){
            
            Arc temp = (Arc)arcList.get(i);
            String sourceString = temp.getSource();
            String targetString = temp.getTarget();
            IDBase sourceIDBase = (IDBase)array.searchID(sourceString);
            IDBase targetIDBase = (IDBase)array.searchID(targetString);
            int sourceInt = sourceIDBase.getInterneID();
            int targetInt = targetIDBase.getInterneID();
            
            this.matrix[sourceInt][targetInt] = true;
            
            i++;
        }        
    }
    
    private boolean[][] initalisiereMatrix(int groesse){
        boolean[][] tempMatrix = new boolean[groesse][groesse];
        for (int g = 0 ; g < groesse; g++){
            for (int j = 0 ; j < groesse; j++){
                tempMatrix[g][j] = false;
            }
        }
        return tempMatrix;
    }
    
    private void reset(){
        this.stellenCounter = 0;
        this.transitionCounter = 0;
        this.gesamtCounter = 0;
        this.arcListe = new ArrayListSearchID();
        this.matrix = null;
    }
    
    public Stellen getAnfang(){
        
        Stellen rueckgabeWert = null;
        
        if  (this.gesamtCounter <= 0){
           return rueckgabeWert;
        } else {
            int moeglicheAnfaenge = 0;
            
            IDBase temp = null;
            boolean flag;
            
            
            for (int i = 0; i < this.gesamtCounter; i++){
                flag = false;
                for (int j = 0; j < this.gesamtCounter; j++){
                    if ((this.matrix[j][i])){
                        flag = true;
                    }
                }
                if (!flag) {
                    moeglicheAnfaenge++;
                    temp = gesamtListe.getWithInternID(i);
                }
            }
            
            if ((moeglicheAnfaenge == 1) && 
                (temp instanceof Stellen)){
                return (Stellen)temp;
                
            }   else if (moeglicheAnfaenge > 1){
                    //throw new ZuVieleAnfaengeException();
            }
            
        }

        return rueckgabeWert;
    }

    public Stellen getEnde(){
        
        Stellen rueckgabeWert = null;
        
        if  (this.gesamtCounter <= 0){
           return rueckgabeWert;
        } else {
            int moeglicheAnfaenge = 0;
            
            IDBase temp = null;
            boolean flag;
            
            
            for (int i = 0; i < this.gesamtCounter; i++){
                flag = false;
                for (int j = 0; j < this.gesamtCounter; j++){
                    if ((this.matrix[i][j])){
                        flag = true;
                    }
                }
                if (!flag) {
                    moeglicheAnfaenge++;
                    temp = gesamtListe.getWithInternID(i);
                }
            }
            
            if ((moeglicheAnfaenge == 1) && 
                (temp instanceof Stellen)){
                return (Stellen)temp;
                
            }   else if (moeglicheAnfaenge > 1){
                    //throw new ZuVieleEndenException();
            }
            
        }
   
        return rueckgabeWert;
    }
    
    public boolean pruefeWorkflownetz(){
        boolean rueckgabeWert = false;
        Stellen anfang = this.getAnfang();
        Stellen ende = this.getEnde();
        
        int inIdAnfang = anfang.getInterneID();
        int inIdEnde = ende.getInterneID();
        
        rueckgabeWert = _pruefeWorkflownetz(inIdAnfang, inIdEnde, 0);
        
        return rueckgabeWert;
    }
    
    private boolean _pruefeWorkflownetz(int inIdAnfang, int inIdEnde, int rekTiefe){
        
        if (rekTiefe++ > this.gesamtCounter){
            // throw new WorkflownetzZyklusGefunden
            return false;
        }
        
        boolean rueckgabeWert = true;
            
        if (inIdAnfang == inIdEnde){
            System.out.println("Ende");
            return true;
            
        }
        
        int countNachfolger = 0;
        
        for (int i = 0 ; i < this.gesamtCounter; i++){
            if (this.matrix[inIdAnfang][i]){
               countNachfolger++;
            }
        }
        
        if (countNachfolger == 1) {
            for (int i = 0 ; i < this.gesamtCounter; i++){
                if (this.matrix[inIdAnfang][i]){
                   rueckgabeWert = _pruefeWorkflownetz(i, inIdEnde, rekTiefe);
                   System.out.println("1");
                }
            }
        } else if (countNachfolger > 1) {
            System.out.println("Mehr als eins");
            ArrayList<Integer> tempIntList = new ArrayList<>();
            ArrayList<Boolean> tempBooleanList = new ArrayList<>();
            for (int i = 0 ; i < this.gesamtCounter; i++){
                if (this.matrix[inIdAnfang][i]){
                   tempIntList.add(i);
                }
            }
            
            for (Integer x: tempIntList){
                tempBooleanList.add(_pruefeWorkflownetz(x, inIdEnde, rekTiefe));
            }
            
            boolean flag = false;
            for (Boolean x: tempBooleanList){
                if (!x){
                   flag = true; 
                }
            }
            
            if (flag){
                return false;
            }
        }  
        
        return rueckgabeWert;
    }
}
