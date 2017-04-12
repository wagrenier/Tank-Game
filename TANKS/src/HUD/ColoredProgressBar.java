/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import javafx.scene.control.ProgressBar;

/**
 *
 * @author Cedrik Dubois
 */
public class ColoredProgressBar extends ProgressBar{
    
    ColoredProgressBar(String styleClass, double progress) {
        super(progress);
        getStyleClass().add(styleClass);
    }
    
    public void setColor(String color){
        //getStyleClass().clear();
        getStyleClass().add(color);
    }
    
  
}