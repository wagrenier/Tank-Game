/****************************************************************
 *  File: WeaponAnimation.java
 *  Description: The object containing the animation of the weapon. The animation has a limited time of 15 seconds in the pane to prevent a memory leak and a slow down of the cpu for too many animations.
 *    History:
 *     Date    03/18/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
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
    
    /**
     *
     * @param weapon
     * @param tank
     * @param mapGeneration
     * @param pane
     * @param sounds
     */
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
    
    /**
     *
     * @param weapon
     * @param tank
     * @param mapGeneration
     * @param pane
     * @param initialVelocity
     * @param gravity
     * @param sounds
     */
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
    
    /**
     *
     * @param weapon
     * @param mapGeneration
     * @param pane
     * @param sounds
     */
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
    
    /**
     *
     */
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
    
    /**
     *
     */
    public void stopAnimation(){
        animationWeapon.stop();
        pane.getChildren().removeAll(weapon);
    }
    
    /**
     *
     */
    public void removeWeaponFromPane(){
        pane.getChildren().removeAll(weapon);
    }

    /**
     *
     * @return
     */
    public double getInitialVelocity() {
        return initialVelocity;
    }

    /**
     *
     * @param initialVelocity
     */
    public void setInitialVelocity(double initialVelocity) {
        this.initialVelocity = initialVelocity;
    }

    /**
     *
     * @return
     */
    public Timeline getAnimationWeapon() {
        return animationWeapon;
    }

    /**
     *
     * @return
     */
    public Tanks getTank() {
        return tank;
    }

    /**
     *
     * @return
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     *
     * @return
     */
    public boolean isHitSomething() {
        return hitSomething;
    }

    /**
     *
     * @param hitSomething
     */
    public void setHitSomething(boolean hitSomething) {
        this.hitSomething = hitSomething;
    }

    /**
     *
     * @return
     */
    public double getInitialXPosition() {
        return initialXPosition;
    }

    /**
     *
     * @param initialXPosition
     */
    public void setInitialXPosition(double initialXPosition) {
        this.initialXPosition = initialXPosition;
    }

    /**
     *
     * @return
     */
    public double getInitialYPosition() {
        return initialYPosition;
    }

    /**
     *
     * @param initialYPosition
     */
    public void setInitialYPosition(double initialYPosition) {
        this.initialYPosition = initialYPosition;
    }

    /**
     *
     * @return
     */
    public double getGravity() {
        return gravity;
    }

    /**
     *
     * @param gravity
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    /**
     *
     * @return
     */
    public double getCurrentYPosition() {
        return currentYPosition;
    }

    /**
     *
     * @param currentYPosition
     */
    public void setCurrentYPosition(double currentYPosition) {
        this.currentYPosition = currentYPosition;
    }

    /**
     *
     * @return
     */
    public double getCanonAngle() {
        return canonAngle;
    }

    /**
     *
     * @param canonAngle
     */
    public void setCanonAngle(double canonAngle) {
        this.canonAngle = canonAngle;
    }

    /**
     *
     * @return
     */
    public double getAngleLaunched() {
        return angleLaunched;
    }

    /**
     *
     * @param angleLaunched
     */
    public void setAngleLaunched(double angleLaunched) {
        this.angleLaunched = angleLaunched;
    }

    /**
     *
     * @return
     */
    public double getYspeed() {
        return yspeed;
    }

    /**
     *
     * @param yspeed
     */
    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }

    /**
     *
     * @return
     */
    public double getXspeed() {
        return xspeed;
    }

    /**
     *
     * @param xspeed
     */
    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    /**
     *
     * @return
     */
    public ExplosionAnimation getExplosionAnimation() {
        return explosionAnimation;
    }

    /**
     *
     * @param explosionAnimation
     */
    public void setExplosionAnimation(ExplosionAnimation explosionAnimation) {
        this.explosionAnimation = explosionAnimation;
    }

    /**
     *
     * @return
     */
    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    /**
     *
     * @param mapGeneration
     */
    public void setMapGeneration(MapGeneration mapGeneration) {
        this.mapGeneration = mapGeneration;
    }

    /**
     *
     * @return
     */
    public Pane getPane() {
        return pane;
    }

    /**
     *
     * @param pane
     */
    public void setPane(Pane pane) {
        this.pane = pane;
    }

    /**
     *
     * @return
     */
    public SoundLib getSounds() {
        return sounds;
    }

    /**
     *
     * @param sounds
     */
    public void setSounds(SoundLib sounds) {
        this.sounds = sounds;
    }
    
    
    
}
