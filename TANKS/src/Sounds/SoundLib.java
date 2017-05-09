/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sounds;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Cedrik Dubois
 */
public class SoundLib {
    
    //A list of all mediaPlayers of every sound used in the game
    //Class will contain getter methods to retrieve said sounds.
    
    private MediaPlayer backgroundMusic = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Background Music.mp3").toURI().toString()));
    private MediaPlayer tankShot = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Tank Shot.mp3").toURI().toString()));
    private MediaPlayer explosion = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Explosion.mp3").toURI().toString()));
    private MediaPlayer repair = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Repair.mp3").toURI().toString()));
    private MediaPlayer btnClicked = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Button Clicked.mp3").toURI().toString()));
    
    private boolean backgroundMusicPlaying = false;
    
    
    public SoundLib(){
        
    }
    
    public void setSoundPlaying(boolean value){
        this.backgroundMusicPlaying = value;
    }
    
    public boolean isSoundPlaying(){
        return this.backgroundMusicPlaying;
    }
    
    public MediaPlayer getBackgroundMusic(){
        return backgroundMusic;
    }
    
    public MediaPlayer getTankShot(){
        return tankShot;
    }
    
    public MediaPlayer getExplosion(){
        return explosion;
    }
    
    public MediaPlayer getRepair(){
        return repair;
    }
    
    public MediaPlayer getBtnClicked(){
        return btnClicked;
    }
}