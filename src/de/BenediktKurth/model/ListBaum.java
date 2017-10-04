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
public class ListBaum {
    
    private ListBaum parents;
    private IDBase value;
    private ArrayList children;
    

    public ListBaum(IDBase value){
        this.parents = null;
        this.value = value;
        this.children = null;
   
    
    }

    public ListBaum addChild(IDBase value){
        ListBaum neuesKind = new ListBaum(value);
        neuesKind.parents = this;
        neuesKind.children = null;
        children.add(neuesKind);
        
        return neuesKind;
    }
    
    public ArrayList getChildren(){
        return children; 
    }
    
    public void removeChild(IDBase value){
        int counter = 0;
        
        ListBaum temp = (ListBaum)children.get(counter);
        while (!temp.getValue().equals(value)){
            
            temp = (ListBaum)children.get(++counter);
        }
        
    }
    
    public ListBaum getRoot(){
        ListBaum temp = this;
        while (temp.parents != null) {
            temp = temp.parents;
        }
        return temp;
    }
    
    public IDBase getValue(){
        return this.value;
    }
    
}


