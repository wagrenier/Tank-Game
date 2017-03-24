/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author william
 */
public class Tanks extends Circle{
    
    private int lifePoint = 100;
    private String imagePath;
    private String imageReversePath;
    private ImagePattern texturePattern;
    private ImagePattern texturePatternFlipped;
    private Image texture;
    private Image textureFlipped;
    
    
    Tanks(String imagePath, String imageReversePath){
        this.setRadius(50);
        
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
        
        texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        texturePatternFlipped = new ImagePattern(textureFlipped, 0, 0, 1, 1, true);
        
        //ImageView imageView = new ImageView(texture);
        
        
        this.setCenterY(-18);
        
        this.setFill(texturePattern);
        //this.setRotate(20);
        //setStroke(Color.BLACK);
        //setTranslateX(50);
        //setTranslateY(200);
    }  
    
    public void flipTexture(){
        this.setFill(texturePatternFlipped);
    }
    
    public void normalTexture(){
        this.setFill(texturePattern);
    }
    
    
}
