/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import javafx.scene.layout.Pane;

/**
 *
 * @author Cedrik Dubois
 */
public class HUD extends Pane{
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 150;
    
    public HUD(){
        this.setMinSize(WIDTH, HEIGHT);
        this.setMaxSize(WIDTH, HEIGHT);
    }
}
