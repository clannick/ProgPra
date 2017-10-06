/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.BenediktKurth.model;

import java.util.ArrayList;

/**
 *
 * @author clannick
 */
public class ArrayListCheckAdd extends ArrayList<Integer>{

    public boolean addCheck(int newValue){
        Integer temp = newValue;
        
        int i = 0;
        
        
        while (i < this.size()){
            if (this.get(i).equals(temp)){
                
                return false;
            }
        }
        this.add(temp);
        return true;
    }
}
