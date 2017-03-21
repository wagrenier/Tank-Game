/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author william
 */
public class Tanks extends Rectangle{
    
    
    
    Tanks(){
        //this.setRadius(15);
        Image texture = new Image("Texture/Tanks/Canada/Body/Red_Tank.png", 1200, 800, false, false);
        
        this.setFill(new ImagePattern(texture, 0, 0, 1, 1, true));
        //this.setLayoutX(100);
        //this.setLayoutY(100);
        this.setWidth(1200);
        this.setHeight(800);
        //setFill(Color.BLACK);
        //setStroke(Color.BLACK);
        setTranslateX(50);
        setTranslateY(200);
    }  
}
