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
    
    /**If the tank is still alive*/
    private boolean isTankAlive = false; //If the tank is still alive
    /**If this tank is controlled by an AI*/
    private boolean isAI = false; 
    /**If the texture of the tank is flipped*/
    private boolean isImageFlipped = false; 
    /**The index of this tank's team*/
    private int team; 
    /**The amount of shield of this tank, 100=100% of damage being taken, 90=90% damage taken,...*/
    private int shield = 100;
    /**The type of shield active*/
    private char shieldType = 'd';
    /**The amount of armour available for this tank, max is 100*/
    private int armour = 0; 
    /**The life points of the tank, once it reaches 0, the tank is considered as 'dead'*/
    private int lifePoint = 100; 
    
    /**The velocity in x of the tank*/
    private double xSpeed; 
    /**The velocity in y of the tank*/
    private double ySpeed; 
    /**The tanks velocity (represented as a vector)*/
    private double Velocity = 0; 
    /**The y location of the tank*/
    private double y; 
    /**The maximum number that a tank can move per turn. The limit is 500*/
    private double maxPixelMove = 100; 
    
    /**The path of Image*/
    private String imagePath;
    /**The path of Image*/
    private String imageReversePath;
    /**The Image of texture*/
    private Image texture;
    /**The Image of texture*/
    private Image textureFlipped;
    /**The cannon associated with this tank*/
    private Cannon cannon;
    
    /**The image pattern of the texture*/
    private ImagePattern texturePattern;
    /**The image pattern of the texture*/
    private ImagePattern texturePatternFlipped;
    
    /**Constructor*/
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
    
    /**Constructor*/
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

    /**
     *Flips the texture
     */
    public void flipTexture(){
        this.setFill(texturePatternFlipped);
      // this.setImage(textureFlipped);
        cannon.flipTexture();
        isImageFlipped = true;
    }
    
    /**
     * Puts the normal texture
     */
    public void normalTexture(){
        this.setFill(texturePattern);
        //this.setImage(texture);
        cannon.normalTexture();
        isImageFlipped = false; 
    }
    
    /**
     * The damage done to the tank
     * @param damage
     */
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
    
    /**
     * Restore life points to the tank
     * @param lifeToRestore
     * @return
     */
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
    
    /**
     * Set the velocity of the tank
     * @param mapGen
     */
    public void setVelocities(MapGeneration mapGen){
        this.setxSpeed(Velocity * Math.cos(mapGen.derivativeFunction(this.getTranslateX())));
        this.setySpeed(Velocity * Math.sin(mapGen.derivativeFunction(this.getTranslateY())));
    }

    /**
     *
     * @return
     */
    public int getLifePoint() {
        return lifePoint;
    }

    /**
     *
     * @return
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     *
     * @return
     */
    public String getImageReversePath() {
        return imageReversePath;
    }

    /**
     *
     * @return
     */
    public Image getTexture() {
        return texture;
    }

    /**
     *
     * @return
     */
    public Image getTextureFlipped() {
        return textureFlipped;
    }

    /**
     *
     * @return
     */
    public boolean isTankAlive() {
        return isTankAlive;
    }

    /**
     *
     * @param isTankAlive
     */
    public void setIsTankAlive(boolean isTankAlive) {
        this.isTankAlive = isTankAlive;
    }

    /**
     *
     * @return
     */
    public double getxSpeed() {
        return xSpeed;
    }

    /**
     *
     * @param xSpeed
     */
    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     *
     * @return
     */
    public double getySpeed() {
        return ySpeed;
    }

    /**
     *
     * @param ySpeed
     */
    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }
    
    /**
     *
     * @return
     */
    public boolean isIsImageFlipped() {
        return isImageFlipped;
    }

    /**
     *
     * @return
     */
    public Cannon getCannon() {
        return cannon;
    }

    /**
     *
     * @return
     */
    public double getYTanks() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setYTanks(double y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public int getTeam() {
        return team;
    }

    /**
     *
     * @return
     */
    public boolean isIsAI() {
        return isAI;
    }

    /**
     *
     * @param isAI
     */
    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    /**
     *
     * @param lifePoint
     */
    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }
    
    /**
     *
     * @param maxPixelMove
     */
    public void setMaxPixelMove(double maxPixelMove){
        this.maxPixelMove += maxPixelMove;
    }

    /**
     *
     * @return
     */
    public double getMaxPixelMove() {
        return maxPixelMove;
    }

    /**
     *
     * @param shield
     */
    public void setShield(int shield) {
        this.shield = shield;
    }

    /**
     *
     * @return
     */
    public int getShield() {
        return shield;
    }
    
    /**
     *
     * @return
     */
    public int getArmour() {
        return armour;
    }

    /**
     *
     * @param armour
     */
    public void setArmour(int armour) {
        this.armour = armour;
    }

    /**
     *
     * @return
     */
    public double getVelocity() {
        return Velocity;
    }

    /**
     *
     * @param Velocity
     */
    public void setVelocity(double Velocity) {
        this.Velocity = Velocity;
    }
    
    /**
     *
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    public ImagePattern getTexturePattern() {
        return texturePattern;
    }

    /**
     *
     * @param texturePattern
     */
    public void setTexturePattern(ImagePattern texturePattern) {
        this.texturePattern = texturePattern;
    }

    /**
     *
     * @return
     */
    public ImagePattern getTexturePatternFlipped() {
        return texturePatternFlipped;
    }

    /**
     *
     * @param texturePatternFlipped
     */
    public void setTexturePatternFlipped(ImagePattern texturePatternFlipped) {
        this.texturePatternFlipped = texturePatternFlipped;
    }
    
    public void setShieldType(char var){
        this.shieldType = var;
    }
    
    public char getShieldType(){
        return this.shieldType;
    }
}
