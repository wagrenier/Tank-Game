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
public class Weapon extends Circle  implements Serializable{
    
    private int costOfWeapon;
    private int damage;
    private String texturePath;
    private String soundEffectPath;
    private String weaponName;
    private  transient Image texture;
    private  transient ImagePattern texturePattern;
    private  transient Media soundEffect;
    private transient MediaPlayer soundEffectPlayer;
    
   
    
    public Weapon(int damage, String texturePath){
        this.texturePath = texturePath;
        //textureView = new ImageView(this.texture);
        texture = new Image(this.texturePath);
        texturePattern = new ImagePattern(texture);
        this.setFill(texturePattern);
        this.damage = damage;
        this.setRadius(25);
    }
    
    public Weapon(int damage, int costOfWeapon, String weaponName, String texturePath) {
        this.setRadius(25);
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.damage = damage;
        
        this.weaponName = weaponName;
        
        texture = new Image(this.texturePath);
        texturePattern = new ImagePattern(this.texture);
        
        
    }

    public Weapon(int costOfWeapon, String weaponName, String texturePath, String soundEffectPath) {
        this.setRadius(25);
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        
        
        this.weaponName = weaponName;
        
        texture = new Image(this.texturePath);
        texturePattern = new ImagePattern(this.texture);
        soundEffect = new Media(this.soundEffectPath);
        soundEffectPlayer = new MediaPlayer(this.soundEffect);
        
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

    public ImagePattern getTextureView() {
        return texturePattern;
    }

    public void setTextureView(ImagePattern texturePattern) {
        this.texturePattern = texturePattern;
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

    public ImagePattern getTexturePattern() {
        return texturePattern;
    }
    
    
}
