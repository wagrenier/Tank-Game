/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author willi
 */
public class Cannon extends ImageView{
    
    private double canonAngle = 0;
    private String imagePath;
    private String imageReversePath;
    private ImagePattern texturePattern;
    
    private ImagePattern texturePatternFlipped;
    private Image texture;
    private Image textureFlipped;
    private boolean isImageFlipped = false;
    
    Cannon(String imagePath, String imageReversePath){
        //this.setRadius(50);
        
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
        
        //texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        //texturePatternFlipped = new ImagePattern(textureFlipped, 0, 0, 1, 1, true);
        
        //ImageView imageView = new ImageView(texture);
        
        this.setImage(texture);
        
        
        //this.setFill(texturePattern);
        //this.setRotate(20);
        //setStroke(Color.RED);
        //setTranslateX(50);
        //setTranslateY(200);
    }  
    
    Cannon(String imagePath){
        //this.setRadius(50);
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        //this.setFill(texturePattern);
        
        this.imageReversePath = imagePath;
        textureFlipped = new Image(this.imagePath);
        texturePatternFlipped = new ImagePattern(texture, 0, 0, 1, 1, true);
    }  
    
    public void flipTexture(){
        //this.setFill(texturePatternFlipped);
        this.setImage(textureFlipped);
        isImageFlipped = true;
    }
    
    public void normalTexture(){
        //this.setFill(texturePattern);
        this.setImage(texture);
        isImageFlipped = false;
    }

    public boolean isIsImageFlipped() {
        return isImageFlipped;
    }
    
    public void lowerAngle(){
       
        if(canonAngle > 0){
        canonAngle -= Math.PI / 25;
        }
    }
    
    public void higherAngle(){
        
        if(canonAngle < (0.879645943005142)){
        canonAngle += Math.PI / 25;
        
        //this.setRotate(Math.toDegrees(getCanonAngle()));
        }
        
    }
    
    public void setAICannonAngle(double angle){
        canonAngle = angle;
    }
    
    public double getCanonAngle() {
        return canonAngle;
    }
     
}
