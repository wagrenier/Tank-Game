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
    private double initialVelocity = -.5;
    private double gravity = .0005;
    private double currentYPosition;
    private double angleLaunched = Math.PI / 3; // angle must be between 0 and 1 included
    private double yspeed = initialVelocity * Math.sin(angleLaunched);
    private double xspeed = -initialVelocity * Math.cos(angleLaunched);
    
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
        this.initialXPosition = tank.getTranslateX();
        this.initialYPosition = tank.getTranslateY();
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
       
        
        weapon.setTranslateX(weapon.getTranslateX() + initialXPosition);
        weapon.setTranslateY(weapon.getTranslateY() + initialYPosition -1);
        //System.out.println(weapon.getTranslateY() + " " + initialYPosition);
        
        animationWeapon =  new Timeline(new KeyFrame(Duration.millis(1), e -> {
            
            currentYPosition = mapGeneration.getY(weapon.getTranslateX());
            weapon.setTranslateY(weapon.getTranslateY() + yspeed);
            weapon.setTranslateX(weapon.getTranslateX() + xspeed); 
            
           // System.out.println("weapon Translate X: " + weapon.getTranslateX() + " Weapon Translate Y: " + weapon.getTranslateY() + " Tank Translate X: " + tank.getTranslateX() + " Tank Translate Y: " + tank.getTranslateY() +" currentYPos: " + currentYPosition + " xspeed: " + xspeed + " yspeed: " + yspeed);
            
            if(weapon.getTranslateX()<= 0 || weapon.getTranslateX() >= 1200){
                xspeed *= -1;  
            }
            if (weapon.getTranslateY() < currentYPosition ){
                yspeed += gravity;
            }
            else{
                yspeed = 0;
                xspeed = 0;
            }
            if(weapon.getTranslateY() > currentYPosition){
                weapon.setTranslateY(currentYPosition);
            } 
            }));
    }
    
    public void launchAnimation(){
        //weapon.setTranslateY(weapon.getTranslateY() + initialYPosition + 1);
        //weapon.setTranslateX(tank.getTranslateX());
        pane.getChildren().add(weapon);
        animationWeapon.setCycleCount(Timeline.INDEFINITE);
        animationWeapon.play();
    }
      
}
