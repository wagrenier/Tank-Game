/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
package Weapon;

import MapGeneration.MapGeneration;
import Sounds.SoundLib;
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
    private double initialVelocity = .5;
    private double currentYPosition;
    private double yspeed;
    private double xspeed;
    
    private ExplosionAnimation explosionAnimation;
    private MapGeneration mapGeneration;
    private Weapon weapon;
    private Timeline animationWeapon;
    private Tanks tank;
    private Pane pane;  
    private SoundLib sounds;
    
    public RCAnimation(Weapon weapon, Tanks tank, MapGeneration mapGeneration, Pane pane, SoundLib sounds){
        this.pane = pane;
        this.tank = tank;
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        this.sounds = sounds;
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
                explosionAnimation.playAnimation(sounds);
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
                yspeed += mapGeneration.getGravity();
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

    public double getCurrentYPosition() {
        return currentYPosition;
    }

    public void setCurrentYPosition(double currentYPosition) {
        this.currentYPosition = currentYPosition;
    }

    public double getYspeed() {
        return yspeed;
    }

    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }

    public double getXspeed() {
        return xspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public ExplosionAnimation getExplosionAnimation() {
        return explosionAnimation;
    }

    public void setExplosionAnimation(ExplosionAnimation explosionAnimation) {
        this.explosionAnimation = explosionAnimation;
    }

    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    public void setMapGeneration(MapGeneration mapGeneration) {
        this.mapGeneration = mapGeneration;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public SoundLib getSounds() {
        return sounds;
    }

    public void setSounds(SoundLib sounds) {
        this.sounds = sounds;
    }
    
    
}
