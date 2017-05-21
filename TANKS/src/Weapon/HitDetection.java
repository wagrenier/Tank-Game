/****************************************************************
 *  File: HitDetection.java
 *  Description: This object is created when a new weapon is put into the pane. It checks if it collides with another enemy tank to make damage.
 *    History:
 *     Date    04/20/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package Weapon;

import GamePane.GamePane;
import HUD.HUD;
import Tanks.Tanks;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;


/**
 *
 * @author willi
 */
public class HitDetection extends AnimationTimer{
    
    
    private HUD hud;
    
    
    private Tanks tanksOne;
    private Tanks tanksTwo;
    private Tanks tanksThree;
    private Tanks tanksFour;
    
    //Variable for the tanks' animation
    private Timeline animation;
    private Timeline animation2;
    private Timeline animation3;
    private Timeline animation4;
    
    //Pane of the game
    private GamePane pane;
    
    private Tanks tank;
    
    private WeaponAnimation weaponAnimation;
    private Weapon weapon;

    public HitDetection(WeaponAnimation weaponAnimation, HUD hud, Tanks tanksOne, Tanks tanksTwo, Tanks tanksThree, Tanks tanksFour, Tanks tank, Timeline animation, Timeline animation2, Timeline animation3, Timeline animation4, GamePane pane, Weapon weapon) {
        this.hud = hud;
        this.tanksOne = tanksOne;
        this.tanksTwo = tanksTwo;
        this.tanksThree = tanksThree;
        this.tanksFour = tanksFour;
        this.tank = tank;
        this.animation = animation;
        this.animation2 = animation2;
        this.animation3 = animation3;
        this.animation4 = animation4;
        this.weaponAnimation = weaponAnimation;
        this.pane = pane;
        this.weapon = weapon;
    }
    
    public void handle(long now){
        
        if(weapon.getBoundsInParent().intersects(tanksOne.getBoundsInParent()) && tank != tanksOne && tanksOne.isTankAlive()){
           if(!weaponAnimation.isHitSomething()){
           tanksOne.damageDone(weapon.getDamage());
           }
           weaponAnimation.setHitSomething(true);
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksTwo.getBoundsInParent())&& tank != tanksTwo && tanksTwo.isTankAlive()){
           if(!weaponAnimation.isHitSomething()){
           tanksTwo.damageDone(weapon.getDamage());
           }
           weaponAnimation.setHitSomething(true);
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksThree.getBoundsInParent())&& tank != tanksThree && tanksThree.isTankAlive()){
           if(!weaponAnimation.isHitSomething()){
           tanksThree.damageDone(weapon.getDamage());
           }
           weaponAnimation.setHitSomething(true); 
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksFour.getBoundsInParent())&& tank != tanksFour && tanksFour.isTankAlive()){
           if(!weaponAnimation.isHitSomething()){
           tanksFour.damageDone(weapon.getDamage());
           }
           weaponAnimation.setHitSomething(true);  
       }
        if(weaponAnimation.isHitSomething()){
            this.stop();
        }
    }
}
