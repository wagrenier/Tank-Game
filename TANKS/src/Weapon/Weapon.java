/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private int indexOfWeapon;
   
    
    public Weapon(int damage, String texturePath, int indexOfWeapon){
        this.setRadius(25);
        this.texturePath = texturePath;
        texture = new Image(this.texturePath);
        //this.setImage(texture);
        this.damage = damage;
        this.indexOfWeapon = indexOfWeapon;
        texturePattern = new ImagePattern(texture);
        this.setFill(texturePattern);
        //this.setLayoutX(50);
        //this.setLayoutY(50);
    }
    
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
        //this.setImage(texture);
        this.setFill(texturePattern);
        this.indexOfWeapon = indexOfWeapon;
        //this.setLayoutX(50);
        //this.setLayoutY(50);
    }

    public Weapon(int costOfWeapon, String weaponName, String texturePath, String soundEffectPath, int indexOfWeapon) {
        this.setRadius(25);
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.weaponName = weaponName;        
        texture = new Image(this.texturePath);
        texturePattern = new ImagePattern(texture);
        this.setFill(texturePattern);
        //this.setImage(texture);
        soundEffect = new Media(this.soundEffectPath);
        soundEffectPlayer = new MediaPlayer(this.soundEffect);   
        this.indexOfWeapon = indexOfWeapon;
        //this.setLayoutX(50);
        //this.setLayoutY(50);
    }
    
    public String getShotType(){
        return shotType;
    }

    public int getCostOfWeapon() {
        return costOfWeapon;
    }

    public void setCostOfWeapon(int costOfWeapon) {
        this.costOfWeapon = costOfWeapon;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public Media getSoundEffect() {
        return soundEffect;
    }

    public void setSoundEffect(Media soundEffect) {
        this.soundEffect = soundEffect;
    }

    public MediaPlayer getSoundEffectPlayer() {
        return soundEffectPlayer;
    }

    public void setSoundEffectPlayer(MediaPlayer soundEffectPlayer) {
        this.soundEffectPlayer = soundEffectPlayer;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public int getDamage() {
        return damage;
    }

    public String getSoundEffectPath() {
        return soundEffectPath;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getIndexOfWeapon() {
        return indexOfWeapon;
    }
    
    
}
