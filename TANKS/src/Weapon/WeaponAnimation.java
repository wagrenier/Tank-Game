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
    
    private double initialXPosition;
    private double initialYPosition;
    private double yspeed = 1;
    private double xspeed = 1;
    private double gravity = 0.05;
    private double currentYPosition;
    
    private double time;
    
    MapGeneration mapGeneration;
    Weapon weapon;
    Timeline animation;
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
        
        
        animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                
            });
            
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
        
        pane.getChildren().add(weapon);
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    
    
    
}
