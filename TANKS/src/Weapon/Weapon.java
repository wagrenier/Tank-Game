/****************************************************************
 *  File: Weapon.java
 *  Description: The weapons that destroy the other tanks. Extends a circle so that it can be more easily calculated from the center of circle rather the top left of an ImageView.
 *    History:
 *     Date    03/15/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package Weapon;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author willi
 */
public class Weapon extends Circle{
    
    
    private int indexOfWeapon;
    private int costOfWeapon;
    private int damage;
    private String texturePath;
    private String soundEffectPath;
    private String weaponName;
    private String shotType;
    private Image texture;
    private ImagePattern texturePattern;
    private Media soundEffect;
    private MediaPlayer soundEffectPlayer;
    
    /**
     *
     * @param damage
     * @param texturePath
     * @param indexOfWeapon
     */
    public Weapon(int damage, String texturePath, int indexOfWeapon){
        this.setRadius(25);
        this.texturePath = texturePath;
        texture = new Image(this.texturePath);
        this.damage = damage;
        this.indexOfWeapon = indexOfWeapon;
        texturePattern = new ImagePattern(texture);
        this.setFill(texturePattern);
    }
    
    /**
     *
     * @param damage
     * @param costOfWeapon
     * @param weaponName
     * @param texturePath
     * @param shotType
     * @param indexOfWeapon
     */
    public Weapon(int damage, int costOfWeapon, String weaponName, String texturePath, String shotType, int indexOfWeapon) {
        this.setRadius(25);
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.damage = damage;
        this.shotType = shotType;
        this.weaponName = weaponName;
        texture = new Image(this.texturePath);
        texturePattern = new ImagePattern(texture);
        this.setFill(texturePattern);
        this.indexOfWeapon = indexOfWeapon;
    }

    /**
     *
     * @param costOfWeapon
     * @param weaponName
     * @param texturePath
     * @param soundEffectPath
     * @param indexOfWeapon
     */
    public Weapon(int costOfWeapon, String weaponName, String texturePath, String soundEffectPath, int indexOfWeapon) {
        this.setRadius(25);
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.weaponName = weaponName;        
        texture = new Image(this.texturePath);
        texturePattern = new ImagePattern(texture);
        this.setFill(texturePattern);
        soundEffect = new Media(this.soundEffectPath);
        soundEffectPlayer = new MediaPlayer(this.soundEffect);   
        this.indexOfWeapon = indexOfWeapon;
    }
    
    /**
     *
     * @return
     */
    public String getShotType(){
        return shotType;
    }

    /**
     *
     * @return
     */
    public int getCostOfWeapon() {
        return costOfWeapon;
    }

    /**
     *
     * @param costOfWeapon
     */
    public void setCostOfWeapon(int costOfWeapon) {
        this.costOfWeapon = costOfWeapon;
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
     * @param texture
     */
    public void setTexture(Image texture) {
        this.texture = texture;
    }

    /**
     *
     * @return
     */
    public Media getSoundEffect() {
        return soundEffect;
    }

    /**
     *
     * @param soundEffect
     */
    public void setSoundEffect(Media soundEffect) {
        this.soundEffect = soundEffect;
    }

    /**
     *
     * @return
     */
    public MediaPlayer getSoundEffectPlayer() {
        return soundEffectPlayer;
    }

    /**
     *
     * @param soundEffectPlayer
     */
    public void setSoundEffectPlayer(MediaPlayer soundEffectPlayer) {
        this.soundEffectPlayer = soundEffectPlayer;
    }

    /**
     *
     * @return
     */
    public String getTexturePath() {
        return texturePath;
    }

    /**
     *
     * @return
     */
    public int getDamage() {
        return damage;
    }

    /**
     *
     * @return
     */
    public String getSoundEffectPath() {
        return soundEffectPath;
    }

    /**
     *
     * @return
     */
    public String getWeaponName() {
        return weaponName;
    }

    /**
     *
     * @return
     */
    public int getIndexOfWeapon() {
        return indexOfWeapon;
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
    
    
    
}
