/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author willi
 */
public class Cannon extends Circle{
    
    private double canonAngle = 0;
    private final String imagePath;
    private final ImagePattern texturePattern;
    private final Image texture;
    private boolean isImageFlipped = false;
    
    Cannon(String imagePath){
        this.setRadius(50);
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        this.setFill(texturePattern);
    }  
    
    public void flipTexture(){
        isImageFlipped = true;
    }
    
    public void normalTexture(){
        this.setFill(texturePattern);
        isImageFlipped = false;
    }

    public boolean isIsImageFlipped() {
        return isImageFlipped;
    }
    
    public void lowerAngle(){
       
        if(canonAngle > 0){
        canonAngle -= Math.PI / 25;
        System.out.println(canonAngle);//this.setRotate(-Math.toDegrees(getCanonAngle()));
        }
    }
    
    public void higherAngle(){
        
        if(canonAngle < (0.879645943005142)){
        canonAngle += Math.PI / 25;
        
        //this.setRotate(Math.toDegrees(getCanonAngle()));
        }
        
    }
    
    public double getCanonAngle() {
        return canonAngle;
    }
     
}
