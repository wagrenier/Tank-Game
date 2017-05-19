/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author willi
 */
public class ExplosionAnimation {
    private int frameIndex = 0;
    private Image frame1 = new Image("Explosion/Explosion Animation/Frame 1.png");
    private Image frame2 = new Image("Explosion/Explosion Animation/Frame 2.png");
    private Image frame3 = new Image("Explosion/Explosion Animation/Frame 3.png");
    private Image frame4 = new Image("Explosion/Explosion Animation/Frame 4.png");
    private Image frame5 = new Image("Explosion/Explosion Animation/Frame 5.png");
    private Image frame6 = new Image("Explosion/Explosion Animation/Frame 6.png");
    private Image frame7 = new Image("Explosion/Explosion Animation/Frame 7.png");
    private Image frame8 = new Image("Explosion/Explosion Animation/Frame 8.png");
    private Image frame9 = new Image("Explosion/Explosion Animation/Frame 9.png");
    private Image frame10 = new Image("Explosion/Explosion Animation/Frame 10.png");
    private Image frame11 = new Image("Explosion/Explosion Animation/Frame 11.png");
    private Image frame12 = new Image("Explosion/Explosion Animation/Frame 12.png");
    private Image frame0 = new Image("Explosion/Explosion Animation/Frame 13.png");
    
    private ImageView explosion;
    
    private Image[] frames = new Image[13];
    private Timeline animation;
    private Weapon weapon;
    
    
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
    
    public void resetAnimationPosition(){
        explosion.setTranslateX(weapon.getTranslateX() - 50);
        explosion.setTranslateY(weapon.getTranslateY() - 50);
    }
    
    public void playAnimation(SoundLib sounds){
        if (sounds.isSoundPlaying()){
            sounds.getExplosion().seek(Duration.ZERO);
            sounds.getExplosion().play();
        }
        animation.play();
        
    }

    public Timeline getAnimation() {
        return animation;
    }
    
    
    
}
