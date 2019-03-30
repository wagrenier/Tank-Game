/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
package Sounds;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *A list of all mediaPlayers of every sound used in the game
 * Class will contain getter methods to retrieve said sounds.
 * @author Cedrik Dubois
 */
public class SoundLib {
    
    //A list of all mediaPlayers of every sound used in the game
    //Class will contain getter methods to retrieve said sounds.
    /**Checks if there is currently background music playing*/
    private boolean backgroundMusicPlaying = false;

    /**Plays the sound related to that effect*/
    private MediaPlayer backgroundMusic = new MediaPlayer(new Media(getClass().getResource("Sounds/Background Music.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer tankShot = new MediaPlayer(new Media(getClass().getResource("Sounds/Tank Shot.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer explosion = new MediaPlayer(new Media(getClass().getResource("Sounds/Explosion.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer repair = new MediaPlayer(new Media(getClass().getResource("Sounds/Repair.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer btnClicked = new MediaPlayer(new Media(getClass().getResource("Sounds/Button Clicked.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer save = new MediaPlayer(new Media(getClass().getResource("Sounds/Save.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer failSave = new MediaPlayer(new Media(getClass().getResource("Sounds/Fail Save.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer bought = new MediaPlayer(new Media(getClass().getResource("Sounds/Bought Sound.mp3").toString()));
    /**Plays the sound related to that effect*/
    private MediaPlayer error = new MediaPlayer(new Media(getClass().getResource("Sounds/Error.mp3").toString()));
    
    /**
     *Default no-arg constructor
     */
    public SoundLib(){
        
    }
    
    /**
     *Sets if the background sound is playing
     * @param value
     */
    public void setSoundPlaying(boolean value){
        this.backgroundMusicPlaying = value;
    }
    
    /**
     *
     * @return
     */
    public boolean isSoundPlaying(){
        return this.backgroundMusicPlaying;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getBackgroundMusic(){
        return backgroundMusic;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getTankShot(){
        return tankShot;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getExplosion(){
        return explosion;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getRepair(){
        return repair;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getBtnClicked(){
        return btnClicked;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getSave(){
        return save;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getFailSave(){
        return failSave;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getBought(){
        return bought;
    }
    
    /**
     *
     * @return
     */
    public MediaPlayer getError(){
        return error;
    }
    
    
}
