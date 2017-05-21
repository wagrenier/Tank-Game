/****************************************************************
 *  File: Tanks.java
 *  Description: This object is what the player will identify as. There are 4 different tanks available (each of a different country) to play as. Each tanks have their own cannon.
 *    History:
 *     Date    03/10/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package Tanks;

import MapGeneration.MapGeneration;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author william
 */
public class Tanks extends Circle{
    
    private boolean isTankAlive = false; //If the tank is still alive
    private boolean isAI = false; //If this tank is controlled by an AI
    private boolean isImageFlipped = false; //If the texture of the tank is flipped
    
    private int team; //THe index of this tank's team
    private int shield = 100; //The amount of shield of this tank, 100=100% of damage being taken, 90=90% damage taken,...
    private int armour = 0; //The amount of armour available for this tank, max is 100
    private int lifePoint = 100; //The life points of the tank, once it reaches 0, the tank is considered as 'dead'
    
    private double xSpeed; //The velocity in x of the tank
    private double ySpeed; //The velocity in y of the tank
    private double Velocity = 0; //The tanks velocity (represented as a vector)
    private double y; //The y location of the tank
    private double maxPixelMove = 100; //The maximum number that a tank can move per turn. The limit is 500
    
    
    private String imagePath;
    private String imageReversePath;
    private Image texture;
    private Image textureFlipped;
    
    private Cannon cannon;//The cannon associated with this tank
    
    
    private ImagePattern texturePattern;
    private ImagePattern texturePatternFlipped;
    
    Tanks(String imagePath, String imageReversePath, String imagePathCannon, int team){
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
        texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        texturePatternFlipped = new ImagePattern(textureFlipped, 0, 0, 1, 1, true);
        //this.setCenterY(-18);
        this.setFill(texturePattern);
        //this.setImage(texture);
        cannon = new Cannon(imagePathCannon);
        this.team = team;
    }  
    
    Tanks(String imagePath, String imageReversePath, String imagePathCannon, String reverse, int team){
        this.setRadius(50);
        this.imageReversePath = imageReversePath;
        this.imagePath = imagePath;
        texture = new Image(this.imagePath);
        textureFlipped = new Image(this.imageReversePath);
       // this.setImage(texture);
       texturePattern = new ImagePattern(texture, 0, 0, 1, 1, true);
        texturePatternFlipped = new ImagePattern(textureFlipped, 0, 0, 1, 1, true);
        //this.setCenterY(-18);
        this.setFill(texturePattern);
        cannon = new Cannon(imagePathCannon, reverse);
        this.team = team;
        this.setPickOnBounds(true);
    } 

    public void flipTexture(){
        this.setFill(texturePatternFlipped);
      // this.setImage(textureFlipped);
        cannon.flipTexture();
        isImageFlipped = true;
    }
    
    public void normalTexture(){
        this.setFill(texturePattern);
        //this.setImage(texture);
        cannon.normalTexture();
        isImageFlipped = false; 
    }
    
    public void damageDone(int damage){
        if(armour > 0){
            int remainingArmour = (int)((shield / 100.0) * damage) - armour;
            if(remainingArmour > 0){
                lifePoint -= remainingArmour;
            }
            else if(remainingArmour >= 0){
                armour -= ((shield / 100.0) * damage);
            }
        }
        else {
            lifePoint -= ((shield / 100.0) * damage);
        }
        
        if(lifePoint < 5){
            isTankAlive = false;
        }
        System.out.println(lifePoint);
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
    
    public void setVelocities(MapGeneration mapGen){
        this.setxSpeed(Velocity * Math.cos(mapGen.derivativeFunction(this.getTranslateX())));
        this.setySpeed(Velocity * Math.sin(mapGen.derivativeFunction(this.getTranslateY())));
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
    
    public boolean isIsImageFlipped() {
        return isImageFlipped;
    }

    public Cannon getCannon() {
        return cannon;
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
        this.maxPixelMove += maxPixelMove;
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

    public double getVelocity() {
        return Velocity;
    }

    public void setVelocity(double Velocity) {
        this.Velocity = Velocity;
    }
    
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ImagePattern getTexturePattern() {
        return texturePattern;
    }

    public void setTexturePattern(ImagePattern texturePattern) {
        this.texturePattern = texturePattern;
    }

    public ImagePattern getTexturePatternFlipped() {
        return texturePatternFlipped;
    }

    public void setTexturePatternFlipped(ImagePattern texturePatternFlipped) {
        this.texturePatternFlipped = texturePatternFlipped;
    }
}
