/****************************************************************
 *  File: Cannon.java
 *  Description: The cannon is split from the tank so that it can rotate separately of the tank. It extends circle so that it is easier to compute its location. 
 *    History:
 *     Date    03/05/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package Tanks;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The cannon is split from the tank so that it can rotate separately of the tank. It extends circle so that it is easier to compute its location. 
 * @author willi
 */
public class Cannon extends Circle{
    /**Indicates whether or not the texture of the cannon has been flipped*/
    private boolean isImageFlipped = false;
    /**The angle of the cannon, maximum is PI/2*/
    private double canonAngle = 0;
    /**Path to the texture*/
    private String imagePath;
    /**Path to the flipped texture*/
    private String imageReversePath;
    /**Image of the texture*/
    private Image texture;
    /**Image of the flipped texture*/
    private Image textureFlipped;
    /**Image of the texture*/
    private ImagePattern texturePattern;
    /**Image of the flipped texture*/
    private ImagePattern texturePatternFlipped;
    
    /**Constructor*/
    Cannon(String imagePath, String imageReversePath){
        this.setRadius(50);
        
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
        
        texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        texturePatternFlipped = new ImagePattern(textureFlipped, 0, 0, 1, 1, true);
        
        //ImageView imageView = new ImageView(texture);
        
        //this.setImage(texture);
        
        this.setFill(texturePattern);
        //this.setRotate(20);
        //setStroke(Color.RED);
        //setTranslateX(50);
        //setTranslateY(200);
    }  
    /**Constructor*/
    Cannon(String imagePath){
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        this.imageReversePath = imagePath;
        textureFlipped = new Image(this.imagePath);
        texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        texturePatternFlipped = new ImagePattern(texture, 0, 0, 1, 1, true);
        this.setFill(texturePattern);
    }  
    
    /**
     *Flips the texture
     */
    public void flipTexture(){
        this.setFill(texturePatternFlipped);
        //this.setImage(textureFlipped);
        isImageFlipped = true;
    }
    
    /**
     * Puts the normal texture
     */
    public void normalTexture(){
        this.setFill(texturePattern);
        //this.setImage(texture);
        isImageFlipped = false;
    }
    
    /**
     * Lowers the cannon's angle
     */
    public void lowerAngle(){
       
        if(canonAngle > 0){
        canonAngle -= Math.PI / 25;
        //this.setTranslateX(this.getTranslateX() + (50 * Math.cos(canonAngle)));
        //this.setTranslateY(this.getTranslateY() + (50 * Math.sin(canonAngle)));
        }
    }
    
    /**
     * Increases the cannon's angle
     */
    public void higherAngle(){
        
        if(canonAngle < (0.879645943005142)){
        canonAngle += Math.PI / 25;
        
        //this.setRotate(Math.toDegrees(getCanonAngle()));
        }
        
    }
    
    /**
     * Sets the angle for the AI
     * @param angle
     */
    public void setAICannonAngle(double angle){
        canonAngle = angle;
    }
    
    /**
     *
     * @return double
     */
    public double getCanonAngle() {
        return canonAngle;
    }
    
    /**
     *
     * @return boolean
     */
    public boolean isIsImageFlipped() {
        return isImageFlipped;
    }
     
}
