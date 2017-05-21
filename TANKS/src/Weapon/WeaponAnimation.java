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
import javafx.scene.CacheHint;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class WeaponAnimation{
    
    /**
     * An WeaponAnimation Object is created when the weapon is used in the game 
     *    to attack an other player.
     * 
     */
    
    private boolean hitSomething = false;
    private double initialXPosition;
    private double initialYPosition;
    private double initialVelocity = .5;
    private double gravity = .0005;
    private double currentYPosition;
    private double canonAngle;
    private double angleLaunched; // angle must be between 0 and 1 included
    
    private double yspeed;
    private double xspeed;
    
    private ExplosionAnimation explosionAnimation;
    private MapGeneration mapGeneration;
    private Weapon weapon;
    private Timeline animationWeapon;
    private Tanks tank;
    private Pane pane;
    private SoundLib sounds;
    
    public WeaponAnimation(Weapon weapon, Tanks tank, MapGeneration mapGeneration, Pane pane, SoundLib sounds){
        this.pane = pane;
        this.tank = tank;
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        this.sounds = sounds;
        explosionAnimation = new ExplosionAnimation(weapon, pane);
        setupAnimation(); 
        launchAnimation();
    }
    
    public WeaponAnimation(Weapon weapon, Tanks tank, MapGeneration mapGeneration, Pane pane, double initialVelocity, double gravity, SoundLib sounds){
        this.pane = pane;
        this.tank = tank;
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        this.initialVelocity = initialVelocity;
        this.gravity = gravity;
        this.sounds = sounds;
        explosionAnimation = new ExplosionAnimation(weapon, pane);
        setupAnimation(); 
        //launchAnimation();
    }
    
    public WeaponAnimation(Weapon weapon, MapGeneration mapGeneration, Pane pane, SoundLib sounds){
        this.pane = pane;
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        this.sounds = sounds;
        explosionAnimation = new ExplosionAnimation(weapon, pane);
    }
    
    private void setupAnimation(){
       
        
        
        
        canonAngle = tank.getCannon().getCanonAngle();
        
        
        
        if(tank.isIsImageFlipped()){
            canonAngle = Math.PI - canonAngle;
        }
        
        
        
        angleLaunched = mapGeneration.derivativeFunction(tank.getTranslateX()) - canonAngle;
        
        this.initialXPosition = tank.getTranslateX() + (50 * Math.cos(angleLaunched));
        this.initialYPosition = tank.getTranslateY() + (50 * Math.sin(angleLaunched) - 35);
        
        
        yspeed = initialVelocity * Math.sin(angleLaunched);
        xspeed = initialVelocity * Math.cos(angleLaunched);
        
        weapon.setTranslateX(weapon.getTranslateX() + initialXPosition);
        weapon.setTranslateY(weapon.getTranslateY() + initialYPosition - 1);
        
        
        weapon.setRotate(Math.toDegrees(angleLaunched));
        weapon.setCache(true);
        weapon.setCacheHint(CacheHint.SCALE_AND_ROTATE);
        weapon.setCacheHint(CacheHint.SPEED);
        
        animationWeapon =  new Timeline(new KeyFrame(Duration.millis(1), e -> {
            if(hitSomething){
                explosionAnimation.resetAnimationPosition();
                explosionAnimation.playAnimation(sounds);
                stopAnimation();
            }
            currentYPosition = mapGeneration.getY(weapon.getTranslateX());
            weapon.setTranslateY(weapon.getTranslateY() + yspeed);
            weapon.setTranslateX(weapon.getTranslateX() + xspeed); 
            
            
            if(weapon.getTranslateX()<= 0 || weapon.getTranslateX() >= 1200){
                yspeed = 0;
                xspeed = 0;
                pane.getChildren().remove(weapon);
                explosionAnimation.resetAnimationPosition();
                explosionAnimation.playAnimation(sounds);
                animationWeapon.stop();
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
                xspeed = 0;
                explosionAnimation.resetAnimationPosition();
                explosionAnimation.playAnimation(sounds);
                animationWeapon.stop();
            }
            if(weapon.getTranslateY() > currentYPosition){
                weapon.setTranslateY(currentYPosition);
            } 
            if(tank.isIsImageFlipped()){
            weapon.setRotate(projectileRotationReverse() + 90);
        }
            else
            weapon.setRotate(projectileRotation());
            }));
        
        
    }
    
    public void launchAnimation(){
        
        
        pane.getChildren().add(weapon);
        animationWeapon.setCycleCount(10000);
        animationWeapon.play();
        
        
        animationWeapon.setOnFinished(e ->{
            pane.getChildren().removeAll(weapon);
            animationWeapon.stop();
        });
            
    }
    
    private double projectileRotationReverse(){
        return Math.toDegrees(Math.acos((yspeed / Math.sqrt(Math.pow((yspeed), 2) + Math.pow(xspeed, 2)))));
    }
    
    private double projectileRotation(){
        return Math.toDegrees(Math.asin((yspeed / Math.sqrt(Math.pow((yspeed), 2) + Math.pow(xspeed, 2)))));
    }
    
    public void stopAnimation(){
        animationWeapon.stop();
        pane.getChildren().removeAll(weapon);
    }
    
    public void removeWeaponFromPane(){
        pane.getChildren().removeAll(weapon);
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

    public double getInitialXPosition() {
        return initialXPosition;
    }

    public void setInitialXPosition(double initialXPosition) {
        this.initialXPosition = initialXPosition;
    }

    public double getInitialYPosition() {
        return initialYPosition;
    }

    public void setInitialYPosition(double initialYPosition) {
        this.initialYPosition = initialYPosition;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getCurrentYPosition() {
        return currentYPosition;
    }

    public void setCurrentYPosition(double currentYPosition) {
        this.currentYPosition = currentYPosition;
    }

    public double getCanonAngle() {
        return canonAngle;
    }

    public void setCanonAngle(double canonAngle) {
        this.canonAngle = canonAngle;
    }

    public double getAngleLaunched() {
        return angleLaunched;
    }

    public void setAngleLaunched(double angleLaunched) {
        this.angleLaunched = angleLaunched;
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
