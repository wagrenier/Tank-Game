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
public class MapMenu extends Pane{
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    
    public MapMenu(){
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);
    }
}
