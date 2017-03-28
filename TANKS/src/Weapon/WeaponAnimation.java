/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import MapGeneration.MapGeneration;
import Tanks.Tanks;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class WeaponAnimation {
    
    /**
     * An WeaponAnimation Object is created when the weapon is used in the game 
     *    to attack an other player.
     * 
     */
    
    
    /**
     * d = distance
     * v = speed at which the projectile is launched
     * g = gravity
     * yi = initial y position (obtained from the location of the tank)
     * 
     */
    
    
    /**
     * 
     * Formula for the diatance
     * d =  (v * cosθ / g) * (v * sinθ + sqrt((vsinθ) ^ 2 + 2 * g * yi))
     * 
     * Formula for the time
     * t = d / (v * cos(θ))
     * 
     * Formula for the x position of the projectile, (will not be used)
     * x = xi + v * cos(θ) * t
     * 
     * Formula for the speed in X
     * Vxi = Vxf
     * 
     * Formula for the y position of the projectile
     * y = yi + x * Tan(θ) - ( (g * x ^ 2) / (2 * (v * cos(θ)) ^ 2)
     * 
     * Formula for the speed in Y
     * Vy = v * sin(θ) - ((g * x) / (v * cos(θ)))
     * 
     */
    
    
    private double initialXPosition;
    private double initialYPosition;
    private double yspeed = 1;
    private double xspeed = 1;
    private double gravity = 0.05;
    private double currentYPosition;
    
    private double time;
    
    MapGeneration mapGeneration;
    Weapon weapon;
    Timeline animationWeapon;
    Tanks tank;
    Pane pane;
    
    public WeaponAnimation(Weapon weapon, Tanks tank, MapGeneration mapGeneration, Pane pane){
        this.pane = pane;
        this.tank = tank;
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        this.initialXPosition = this.tank.getTranslateX();
        this.initialYPosition = this.tank.getTranslateY();
        setupAnimation();
    }
    
    public WeaponAnimation(Weapon weapon, MapGeneration mapGeneration, Pane pane){
        this.pane = pane;
        
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        //this.initialXPosition = this.tank.getTranslateX();
        //this.initialYPosition = this.tank.getTranslateY();
        setupAnimation();
    }
    
    private void setupAnimation(){
        
        
        animationWeapon =  new Timeline(new KeyFrame(Duration.millis(1), e -> {
            
            
            weapon.setRotate(50 * mapGeneration.derivativeFunction(weapon.getTranslateX()));
        
        currentYPosition = mapGeneration.getY(weapon.getTranslateX());
            weapon.setTranslateY(weapon.getTranslateY() + yspeed);
            weapon.setTranslateX((weapon.getTranslateX() + xspeed)); 
            
            
            
            if(weapon.getTranslateX()<= 0 || weapon.getTranslateX() >= 1200){
                xspeed *= -1;
            }
            
            
            if (weapon.getTranslateY() < currentYPosition ){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(weapon.getTranslateY() > currentYPosition){
                weapon.setTranslateY(currentYPosition);
            } 
            
            }));
        
        
    }
    
    public void launchAnimation(){
        weapon.setTranslateX(tank.getTranslateX());
        pane.getChildren().add(weapon);
        
        animationWeapon.setCycleCount(Timeline.INDEFINITE);
        animationWeapon.play();
    }
    
    
    
}
