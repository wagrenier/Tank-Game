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

import Tanks.Tanks;
import javafx.animation.AnimationTimer;

/**
 *
 * @author willi
 */
public class HitDetectionMine extends AnimationTimer{
    
    private boolean hitSomething = false;
    private final Tanks tanksOne;
    private final Tanks tanksTwo;
    private final Tanks tanksThree;
    private final Tanks tanksFour;
    
    private Tanks tank;
    
    Weapon weapon;

    public HitDetectionMine(Tanks tanksOne, Tanks tanksTwo, Tanks tanksThree, Tanks tanksFour, Tanks tank, Weapon weapon) {
        this.tanksOne = tanksOne;
        this.tanksTwo = tanksTwo;
        this.tanksThree = tanksThree;
        this.tanksFour = tanksFour;
        this.tank = tank;
        this.weapon = weapon;
    }

    public boolean isHitSomething() {
        return hitSomething;
    }
    
    @Override
    public void handle(long now){
        if(weapon.getBoundsInParent().intersects(tanksOne.getBoundsInParent()) && tank != tanksOne && tanksOne.isTankAlive()){
           tanksOne.damageDone(weapon.getDamage());
           hitSomething = true;
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksTwo.getBoundsInParent())&& tank != tanksTwo && tanksTwo.isTankAlive()){
           tanksTwo.damageDone(weapon.getDamage());
           hitSomething = true;
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksThree.getBoundsInParent())&& tank != tanksThree && tanksThree.isTankAlive()){
           tanksThree.damageDone(weapon.getDamage());
           hitSomething = true;
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksFour.getBoundsInParent())&& tank != tanksFour && tanksFour.isTankAlive()){
           tanksFour.damageDone(weapon.getDamage());
           hitSomething = true;
       }
    }
}
