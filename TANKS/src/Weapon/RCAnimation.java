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
public class RCAnimation {
    private boolean hitSomething = false;
    private double initialXPosition;
    private double initialYPosition;
    private double initialVelocity = .5;
    private double gravity = .0005;
    private double currentYPosition;
    private double yspeed;
    private double xspeed;
    
    private ExplosionAnimation explosionAnimation;
    private MapGeneration mapGeneration;
    private Weapon weapon;
    private Timeline animationWeapon;
    private Tanks tank;
    private Pane pane;  
    
    public RCAnimation(Weapon weapon, Tanks tank, MapGeneration mapGeneration, Pane pane){
        this.pane = pane;
        this.tank = tank;
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        explosionAnimation = new ExplosionAnimation(weapon, pane);
        setupAnimation(); 
    }
    
    private void setupAnimation(){
       

        xspeed = initialVelocity;
        
        weapon.setTranslateX(tank.getTranslateX());
        weapon.setTranslateY(tank.getTranslateY() - 30);
        
        //weapon.setCenterY(-7);
        
        animationWeapon =  new Timeline(new KeyFrame(Duration.millis(1), e -> {
            if(hitSomething){
                explosionAnimation.resetAnimationPosition();
                explosionAnimation.playAnimation();
                stopAnimation();
            }
            currentYPosition = mapGeneration.getY(weapon.getTranslateX());
            weapon.setTranslateY(weapon.getTranslateY() + yspeed);
            weapon.setTranslateX(weapon.getTranslateX() + xspeed); 
            weapon.setRotate(projectileRotation());
           //System.out.println("weapon Translate X: " + weapon.getTranslateX() + " Weapon Translate Y: " + weapon.getTranslateY() + " Tank Translate X: " + tank.getTranslateX() + " Tank Translate Y: " + tank.getTranslateY() +" currentYPos: " + currentYPosition + " xspeed: " + xspeed + " yspeed: " + yspeed + " rotation angle:" + angleLaunched);
            
            if(weapon.getTranslateX()<= 0 || weapon.getTranslateX() >= 1200){
                xspeed *= -1;
                //add animation explosion
                
            }
            if(weapon.getTranslateY() <= 0){
                yspeed *= -1;
            }
            if (weapon.getTranslateY() < currentYPosition ){
                yspeed += gravity;
            }
            else{
                yspeed = 0;
            }
            if(weapon.getTranslateY() > currentYPosition){
                weapon.setTranslateY(currentYPosition);
            } 
            if(tank.isIsImageFlipped()){
                
        }
            }));  
    }
    
    public void launchAnimation(){
        
        
        pane.getChildren().add(weapon);
        animationWeapon.setCycleCount(5000);
        animationWeapon.play();
        
        
        animationWeapon.setOnFinished(e ->{
            
            pane.getChildren().removeAll(weapon);
            animationWeapon.stop();
           // pane.getChildren().removeAll(bar);
            
        });
            
    }
    
    private double projectileRotation(){
        return Math.toDegrees(mapGeneration.derivativeFunction(weapon.getTranslateX()));
    }

    public double getInitialVelocity() {
        return initialVelocity;
    }

    public void setInitialVelocity(double initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    public Timeline getAnimationWeapon() {
        return animationWeapon;
    }

    public Tanks getTank() {
        return tank;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isHitSomething() {
        return hitSomething;
    }

    public void setHitSomething(boolean hitSomething) {
        this.hitSomething = hitSomething;
    }
    
    public void stopAnimation(){
        animationWeapon.stop();
        pane.getChildren().removeAll(weapon);
    }
    
    public void removeWeaponFromPane(){
        pane.getChildren().removeAll(weapon);
    }
}
