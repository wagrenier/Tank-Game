/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;

/**
 *
 * @author willi
 */
public class Weapon extends Circle{
    
    private int costOfWeapon;
    private String texturePath;
    private String soundEffectPath;
    private String weaponName;
    private Image texture;
    private ImageView textureView;
    private Media soundEffect;
    private MediaPlayer soundEffectPlayer;
    private MapGeneration mapGeneration;
    
    
    public Weapon(){
        
    }

    public Weapon(int costOfWeapon, String weaponName, String texturePath, String soundEffectPath, MapGeneration mapGeneration) {
        this.setRadius(25);
        this.costOfWeapon = costOfWeapon;
        this.texturePath = texturePath;
        this.soundEffectPath = soundEffectPath;
        this.mapGeneration = mapGeneration;
        
        this.weaponName = weaponName;
        
        texture = new Image(this.texturePath);
        textureView = new ImageView(this.texture);
        soundEffect = new Media(this.soundEffectPath);
        soundEffectPlayer = new MediaPlayer(this.soundEffect);
        
    }

    public Weapon(int costOfWeapon, Image texture, ImageView textureView, Media soundEffect, MediaPlayer soundEffectPlayer, MapGeneration mapGeneration) {
        this.costOfWeapon = costOfWeapon;
        this.texture = texture;
        this.textureView = textureView;
        this.soundEffect = soundEffect;
        this.soundEffectPlayer = soundEffectPlayer;
        this.mapGeneration = mapGeneration;
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

    public ImageView getTextureView() {
        return textureView;
    }

    public void setTextureView(ImageView textureView) {
        this.textureView = textureView;
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

    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    public void setMapGeneration(MapGeneration mapGeneration) {
        this.mapGeneration = mapGeneration;
    }
    
    
    
}
