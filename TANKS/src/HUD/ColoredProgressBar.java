/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
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
    
    /**
     *
     * @param color
     */
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
    
    /**
     *
     * @param color
     */
    public void setBorderColor(String color){
        getStyleClass().add(color);
    }
    
}
