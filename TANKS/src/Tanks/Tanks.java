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
 * @author william
 */
public class Tanks extends Circle{
    
    private int lifePoint = 100;
    private final String imagePath;
    private final String imageReversePath;
    private final ImagePattern texturePattern;
    private final ImagePattern texturePatternFlipped;
    private final Image texture;
    private final Image textureFlipped;
    private boolean isImageFlipped = false;
    private final Cannon cannon;
    
    Tanks(String imagePath, String imageReversePath, String imagePathCannon){
        this.setRadius(50);
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
        texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        texturePatternFlipped = new ImagePattern(textureFlipped, 0, 0, 1, 1, true);
        this.setCenterY(-18);
        this.setFill(texturePattern);
        cannon = new Cannon(imagePathCannon);
    }  

    public void flipTexture(){
        this.setFill(texturePatternFlipped);
        isImageFlipped = true;
    }
    
    public void normalTexture(){
        this.setFill(texturePattern);
        isImageFlipped = false; 
    }

    public boolean isIsImageFlipped() {
        return isImageFlipped;
    }

    public Cannon getCannon() {
        return cannon;
    }
}
