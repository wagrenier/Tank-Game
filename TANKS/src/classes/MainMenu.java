/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.scene.layout.Pane;

/**
 *
 * @author Cedrik Dubois
 */
public class MainMenu extends Pane{
    
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    
    public MainMenu(){
        this.setMaxHeight(HEIGHT);
        this.setMinHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMinWidth(WIDTH);
        
    }
    
    
}
