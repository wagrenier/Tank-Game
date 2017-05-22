/****************************************************************
 *  File: ExplosionAnimation
 *  Description: Animates an explosion once a weapon animation is completed
 *    History:
 *     Date    05/10/2006 
 *     ---------- ---------- ----------------------------
 *  Author     Cedrik Dubois     
 *
 ****************************************************************/
package Weapon;

import Sounds.SoundLib;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * Animates an explosion once a weapon animation is completed
 */
public class ExplosionAnimation {
    /**Index of the current frame*/
    private int frameIndex = 0;
    /**Current Image being displayed*/
    private Image frame1 = new Image("Explosion/Explosion Animation/Frame 1.png");
    /**Current Image being displayed*/
    private Image frame2 = new Image("Explosion/Explosion Animation/Frame 2.png");
    /**Current Image being displayed*/
    private Image frame3 = new Image("Explosion/Explosion Animation/Frame 3.png");
    /**Current Image being displayed*/
    private Image frame4 = new Image("Explosion/Explosion Animation/Frame 4.png");
    /**Current Image being displayed*/
    private Image frame5 = new Image("Explosion/Explosion Animation/Frame 5.png");
    /**Current Image being displayed*/
    private Image frame6 = new Image("Explosion/Explosion Animation/Frame 6.png");
    /**Current Image being displayed*/
    private Image frame7 = new Image("Explosion/Explosion Animation/Frame 7.png");
    /**Current Image being displayed*/
    private Image frame8 = new Image("Explosion/Explosion Animation/Frame 8.png");
    /**Current Image being displayed*/
    private Image frame9 = new Image("Explosion/Explosion Animation/Frame 9.png");
    /**Current Image being displayed*/
    private Image frame10 = new Image("Explosion/Explosion Animation/Frame 10.png");
    /**Current Image being displayed*/
    private Image frame11 = new Image("Explosion/Explosion Animation/Frame 11.png");
    /**Current Image being displayed*/
    private Image frame12 = new Image("Explosion/Explosion Animation/Frame 12.png");
    /**Current Image being displayed*/
    private Image frame0 = new Image("Explosion/Explosion Animation/Frame 13.png");
    /**ImageView that holds the current frame of animation*/
    private ImageView explosion;
    /**Array that contains the 13 frames of animation*/
    private Image[] frames = new Image[13];
    /**The animation*/
    private Timeline animation;
    /**The weapon associated with that animation*/
    private Weapon weapon;
    
    /**
     * Constructor for the ExplosionAnimation object
     * @param weapon
     * @param pane
     */
    public ExplosionAnimation(Weapon weapon, Pane pane){
        
        
        this.weapon = weapon;
        frames[0] = frame0;
        frames[1] = frame1;
        frames[2] = frame2;
        frames[3] = frame3;
        frames[4] = frame4;
        frames[5] = frame5;
        frames[6] = frame6;
        frames[7] = frame7;
        frames[8] = frame8;
        frames[9] = frame9;
        frames[10] = frame10;
        frames[11] = frame11;
        frames[12] = frame12;
        
        explosion = new ImageView(frames[frameIndex]);
        explosion.setFitHeight(100);
        explosion.setFitWidth(100);
        
        
        pane.getChildren().add(explosion);
        
        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            if (frameIndex == 12)
                frameIndex = 0;
            else
                frameIndex++;
            
            explosion.setImage(frames[frameIndex]);
        }));
        animation.setCycleCount(13);
    }
    
    /**
     *Resets the position of the animation
     */
    public void resetAnimationPosition(){
        explosion.setTranslateX(weapon.getTranslateX() - 50);
        explosion.setTranslateY(weapon.getTranslateY() - 50);
    }
    
    /**
     * plays the explosion sound
     * @param sounds
     */
    public void playAnimation(SoundLib sounds){
        if (sounds.isSoundPlaying()){
            sounds.getExplosion().seek(Duration.ZERO);
            sounds.getExplosion().play();
        }
        animation.play();
        
    }

    /**
     *
     * @return Timeline
     */
    public Timeline getAnimation() {
        return animation;
    }
    
    
    
}
