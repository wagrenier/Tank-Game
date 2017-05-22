/****************************************************************
 *  File: HitDetectionMine.java
 *  Description: The hit detection for the mines. Does not explode if the tank is an ally.
 *    History:
 *     Date    04/20/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/

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
    
    /**Checks if the mine has exploded*/
    private boolean hitSomething = false;
    
    /**One of the 4 tanks*/
    private Tanks tanksOne;
    /**One of the 4 tanks*/
    private Tanks tanksTwo;
    /**One of the 4 tanks*/
    private Tanks tanksThree;
    /**One of the 4 tanks*/
    private Tanks tanksFour;
    /**The tank's animation*/
    private TanksAnimation tanksAnimation;
    /**The tank associated with that mine*/
    private Tanks tank;
    /**The explosion animation*/
    private ExplosionAnimation explosionAnimation;
    /**The mine*/
    private Weapon weapon;
    /**The sounds played*/
    private SoundLib sounds;

    /**
     * The constructor of this object
     * @param tanksOne
     * @param tanksTwo
     * @param tanksThree
     * @param tanksFour
     * @param tank
     * @param weapon
     * @param tanksAnimation
     * @param pane
     * @param sounds
     */
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

    /**
     *
     * @return boolean
     */
    public boolean isHitSomething() {
        return hitSomething;
    }

    /**
     *
     * @return Tanks
     */
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
