/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.BenediktKurth.view;

import de.BenediktKurth.control.MainWindowController;
import javax.swing.JFrame;

/**
 *
 * @author clannick
 */
public class MainWindow extends JFrame{
    
    private MainWindowController controller;
    

    
    public MainWindow (MainWindowController controller){
        super();
        this.controller = controller;
        initCompontens();
        this.setSize(200, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void initCompontens(){
        
        
    }
}
