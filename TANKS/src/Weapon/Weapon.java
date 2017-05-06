/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import MapGeneration.MapGeneration;
import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author willi
 */
public class Weapon extends ImageView{
    
    private int costOfWeapon;
    private int damage;
    private String texturePath;
    private String soundEffectPath;
    private String weaponName;
    private String shotType;
    private Image texture;
    private Media soundEffect;
    private MediaPlayer soundEffectPlayer;
    
   
    
    public Weapon(int damage, String texturePath){
        this.texturePath = texturePath;
        texture = new Image(this.texturePath);
        this.setImage(texture);
        this.damage = damage;
        //this.setLayoutX(50);
        //this.setLayoutY(50);
    }
    
    public Weapon(int damage, int costOfWeapon, String weaponName, String texturePath, String shotType) {
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.damage = damage;
        this.shotType = shotType;
        this.weaponName = weaponName;
        texture = new Image(this.texturePath);
        this.setImage(texture);
        //this.setLayoutX(50);
        //this.setLayoutY(50);
    }

    public Weapon(int costOfWeapon, String weaponName, String texturePath, String soundEffectPath) {
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.weaponName = weaponName;        
        texture = new Image(this.texturePath);
        this.setImage(texture);
        soundEffect = new Media(this.soundEffectPath);
        soundEffectPlayer = new MediaPlayer(this.soundEffect);   
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
    
    
}
