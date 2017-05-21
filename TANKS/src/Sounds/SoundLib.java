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
 *
 * @author Cedrik Dubois
 */
public class SoundLib {
    
    //A list of all mediaPlayers of every sound used in the game
    //Class will contain getter methods to retrieve said sounds.
    
    private boolean backgroundMusicPlaying = false;
    
    private MediaPlayer backgroundMusic = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Background Music.mp3").toURI().toString()));
    private MediaPlayer tankShot = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Tank Shot.mp3").toURI().toString()));
    private MediaPlayer explosion = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Explosion.mp3").toURI().toString()));
    private MediaPlayer repair = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Repair.mp3").toURI().toString()));
    private MediaPlayer btnClicked = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Button Clicked.mp3").toURI().toString()));
    private MediaPlayer save = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Save.mp3").toURI().toString()));
    private MediaPlayer failSave = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Fail Save.mp3").toURI().toString()));
    private MediaPlayer bought = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Bought Sound.mp3").toURI().toString()));
    private MediaPlayer error = new MediaPlayer(new Media(new File("src/Sounds/Sounds/Error.mp3").toURI().toString()));
    
    /**
     *
     */
    public SoundLib(){
        
    }
    
    /**
     *
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
