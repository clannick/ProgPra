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
public class XXXListBaum {
    
    private XXXListBaum parents;
    private IDBase value;
    private ArrayList children;
    

    public XXXListBaum(IDBase value){
        this.parents = null;
        this.value = value;
        this.children = null;
   
    
    }

    public XXXListBaum addChild(IDBase value){
        XXXListBaum neuesKind = new XXXListBaum(value);
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
        
        XXXListBaum temp = (XXXListBaum)children.get(counter);
        while (!temp.getValue().equals(value)){
            
            temp = (XXXListBaum)children.get(++counter);
        }
        
    }
    
    public XXXListBaum getRoot(){
        XXXListBaum temp = this;
        while (temp.parents != null) {
            temp = temp.parents;
        }
        return temp;
    }
    
    public IDBase getValue(){
        return this.value;
    }
    
}


