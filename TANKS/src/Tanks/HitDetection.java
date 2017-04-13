/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import GamePane.GamePane;
import HUD.HUD;
import MapGeneration.MapGeneration;
import Weapon.Weapon;
import Weapon.WeaponAnimation;
import Weapon.WeaponManager;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;


/**
 *
 * @author willi
 */
public class HitDetection extends Thread{
    
    
    private HUD hud;
    
    
    private final Tanks tanksOne;
    private final Tanks tanksTwo;
    private final Tanks tanksThree;
    private final Tanks tanksFour;
    
    //Variable for the tanks' animation
    private Timeline animation;
    private Timeline animation2;
    private Timeline animation3;
    private Timeline animation4;
    
    //Pane of the game
    private GamePane pane;
    
    private Tanks tank;
    
    WeaponAnimation weaponAnimation;
    Weapon weapon;

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
    
    
    
    public void run(){
        
        while(weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0){
        //System.out.println("Hey");
        if(weapon.getBoundsInParent().intersects(tanksOne.getBoundsInParent()) && tank != tanksOne && tanksOne.isTankAlive()){
           //System.out.println(weapon.getDamage());
           tanksOne.damageDone(weapon.getDamage());
           System.out.println(tanksOne.getLifePoint());
           hud.updateHealth(tanksOne.getLifePoint());
           
           //weaponAnimation.removeWeaponFromPane();
           if(!tanksOne.isTankAlive()){
               animation.stop();
               //pane.getChildren().remove(tanksOne);
               //pane.getChildren().remove(tanksOne.getCannon());
           }
           
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksTwo.getBoundsInParent())&& tank != tanksTwo && tanksTwo.isTankAlive()){
           //System.out.println(weapon.getDamage());
           tanksTwo.damageDone(weapon.getDamage());
           System.out.println(tanksTwo.getLifePoint());
           hud.updateHealth(tank.getLifePoint());
           
           //weaponAnimation.removeWeaponFromPane();
           if(!tanksTwo.isTankAlive()){
               animation2.stop();
              // pane.getChildren().remove(tanksTwo);
               //pane.getChildren().remove(tanksTwo.getCannon());
           }
           
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksThree.getBoundsInParent())&& tank != tanksThree && tanksThree.isTankAlive()){
           //System.out.println(weapon.getDamage());
           tanksThree.damageDone(weapon.getDamage());
           System.out.println(tanksThree.getLifePoint());
           //hud.updateHealth(tank.getLifePoint());
           
          // weaponAnimation.removeWeaponFromPane();
           if(!tanksThree.isTankAlive()){
               animation3.stop();
              // pane.getChildren().remove(tanksThree);
               //pane.getChildren().remove(tanksThree.getCannon());
           }
           
       }
        
        else if(weapon.getBoundsInParent().intersects(tanksFour.getBoundsInParent())&& tank != tanksFour && tanksFour.isTankAlive()){
           //System.out.println(weapon.getDamage());
           tanksFour.damageDone(weapon.getDamage());
           System.out.println(tanksFour.getLifePoint());
           //hud.updateHealth(tank.getLifePoint());
           
          // weaponAnimation.removeWeaponFromPane();
           if(!tanksFour.isTankAlive()){
               animation4.stop();
              // pane.getChildren().remove(tanksFour);
               //pane.getChildren().remove(tanksFour.getCannon());
           }
           
       }
        }
        
        this.stop();
    }
}
