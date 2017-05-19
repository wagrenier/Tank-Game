/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The hit detection for the mine requires a different object because otherwise 
 * it will prevent the game from going to the next turn
 */
package Weapon;

import Sounds.SoundLib;
import Tanks.Tanks;
import Tanks.TanksAnimation;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 *
 * @author willi
 */
public class HitDetectionMine extends AnimationTimer{
    
    private boolean hitSomething = false;
    private Tanks tanksOne;
    private Tanks tanksTwo;
    private Tanks tanksThree;
    private Tanks tanksFour;
    private TanksAnimation tanksAnimation;
    private Tanks tank;
    private ExplosionAnimation explosionAnimation;
    private Weapon weapon;
    private SoundLib sounds;

    public HitDetectionMine(Tanks tanksOne, Tanks tanksTwo, Tanks tanksThree, Tanks tanksFour, Tanks tank, Weapon weapon, TanksAnimation tanksAnimation, Pane pane, SoundLib sounds) {
        this.tanksOne = tanksOne;
        this.tanksTwo = tanksTwo;
        this.tanksThree = tanksThree;
        this.tanksFour = tanksFour;
        this.tank = tank;
        this.weapon = weapon;
        this.tanksAnimation = tanksAnimation;
        this.sounds = sounds;
        explosionAnimation = new ExplosionAnimation(weapon, pane);
    }

    public boolean isHitSomething() {
        return hitSomething;
    }

    public Tanks getTank() {
        return tank;
    }
    
    @Override
    public void handle(long now){
        if(weapon.getBoundsInParent().intersects(tanksOne.getBoundsInParent()) && tank != tanksOne && tanksOne.isTankAlive()){
           tanksOne.damageDone(weapon.getDamage());
           tanksAnimation.getHud().updateHealth(tanksOne.getLifePoint());
           hitSomething = true;
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksTwo.getBoundsInParent())&& tank != tanksTwo && tanksTwo.isTankAlive()){
           tanksTwo.damageDone(weapon.getDamage());
           tanksAnimation.getHud().updateHealth(tanksTwo.getLifePoint());
           hitSomething = true;
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksThree.getBoundsInParent())&& tank != tanksThree && tanksThree.isTankAlive()){
           tanksThree.damageDone(weapon.getDamage());
           tanksAnimation.getHud().updateHealth(tanksThree.getLifePoint());
           hitSomething = true;
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksFour.getBoundsInParent())&& tank != tanksFour && tanksFour.isTankAlive()){
           tanksFour.damageDone(weapon.getDamage());
           tanksAnimation.getHud().updateHealth(tanksFour.getLifePoint());
           hitSomething = true;
       }
        
        if(hitSomething){
            tanksAnimation.mineExploded();
            explosionAnimation.resetAnimationPosition();
            explosionAnimation.playAnimation(sounds);
            tanksAnimation.getPane().getChildren().remove(weapon);
            tanksAnimation.getMineLocationArrayList().remove(weapon);
            tanksAnimation.getMineHitDetectionArrayList().remove(this);
            this.stop();
        }
    }
}
