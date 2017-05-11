
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import java.io.Serializable;
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
        if (color.equals("red-bar")){
            getStyleClass().remove("green-bar");
            getStyleClass().add(color);
        }
        else if (color.equals("green-bar")){
            getStyleClass().remove("red-bar");
            getStyleClass().add("green-bar");
        }
    }
    
    public void setBorderColor(String color){
        getStyleClass().add(color);
    }
    
  
}
