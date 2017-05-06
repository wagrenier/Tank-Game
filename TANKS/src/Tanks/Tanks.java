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
 * @author william
 */
public class Tanks extends ImageView{
    
    private double xSpeed;
    private double ySpeed;
    private double y;
    private double maxPixelMove = 100;
    private int lifePoint = 100;
    private boolean isTankAlive = false;
    private boolean isAI = false;
    private final String imagePath;
    private final String imageReversePath;
    private final Image texture;
    private final Image textureFlipped;
    private boolean isImageFlipped = false;
    private final Cannon cannon;
    private int team;
    private int shield = 1;
    private int armour = 0;
    
    Tanks(String imagePath, String imageReversePath, String imagePathCannon, int team){
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
        this.setImage(texture);
        cannon = new Cannon(imagePathCannon);
        this.team = team;
    }  
    
    Tanks(String imagePath, String imageReversePath, String imagePathCannon, String reverse, int team){
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
        this.setImage(texture);
        cannon = new Cannon(imagePathCannon, reverse);
        this.team = team;
    } 

    public void flipTexture(){
       // this.setFill(texturePatternFlipped);
       this.setImage(textureFlipped);
        cannon.flipTexture();
        isImageFlipped = true;
    }
    
    public void normalTexture(){
        //this.setFill(texturePattern);
        this.setImage(texture);
        cannon.normalTexture();
        isImageFlipped = false; 
    }

    public boolean isIsImageFlipped() {
        return isImageFlipped;
    }

    public Cannon getCannon() {
        return cannon;
    }
    
    public void damageDone(int damage){
        if(armour > 0){
            int remainingArmour = (shield * damage) - armour;
            if(remainingArmour > 0){
                lifePoint -= remainingArmour;
            }
            else if(remainingArmour >= 0){
                armour -= (shield * damage);
            }
        }
        else if(lifePoint > 0){
            lifePoint -= (shield * damage);
        }
        
        if(lifePoint < 1){
            isTankAlive = false;
        }
    }
    
    public boolean restoreLifePoints(int lifeToRestore){
        //Return a boolean value to indicate if the item was used
        //True == item was used
        //False == item was not used
        if(lifePoint == 0){
            return false;
        }
        else if(lifePoint == 100){
            return false;
        }
        else if(lifePoint + lifeToRestore > 100){
            lifePoint = 100;
            return true;
        }
        else{
            lifePoint += lifeToRestore;
            return true;
        }
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageReversePath() {
        return imageReversePath;
    }

    public Image getTexture() {
        return texture;
    }

    public Image getTextureFlipped() {
        return textureFlipped;
    }

    public boolean isTankAlive() {
        return isTankAlive;
    }

    public void setIsTankAlive(boolean isTankAlive) {
        this.isTankAlive = isTankAlive;
    }

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getYTanks() {
        return y;
    }

    public void setYTanks(double y) {
        this.y = y;
    }

    public int getTeam() {
        return team;
    }

    public boolean isIsAI() {
        return isAI;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }
    
    public void setMaxPixelMove(double maxPixelMove){
        this.maxPixelMove = maxPixelMove;
    }

    public double getMaxPixelMove() {
        return maxPixelMove;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getShield() {
        return shield;
    }
    
    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }
    
    
    
}
