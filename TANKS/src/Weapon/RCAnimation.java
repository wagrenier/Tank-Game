/****************************************************************
 *  File: RCAnimation.java
 *  Description: This object contains the animation for the RC weapon
 *    History:
 *     Date    04/20/2017
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
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class RCAnimation {
    
    /**Checks if the mine has exploded*/
    private boolean hitSomething = false;
    /**The initial velocity of this weapon*/
    private double initialVelocity = .5;
    /**The initial y position of this weapon*/
    private double currentYPosition;
    /**The yspeed of this weapon*/
    private double yspeed;
    /** the x speed of this weapon*/
    private double xspeed;
    
    /**The explosion animation of this weapon*/
    private ExplosionAnimation explosionAnimation;
    /**The map generation*/
    private MapGeneration mapGeneration;
    /**The weapon*/
    private Weapon weapon;
    /**The weapon animation*/
    private Timeline animationWeapon;
    /**Tank associated with that weapon*/
    private Tanks tank;
    /**The game's pane*/
    private Pane pane;  
    /**The sound played for this weapon*/
    private SoundLib sounds;
    
    /**
     * Constructor
     * @param weapon
     * @param tank
     * @param mapGeneration
     * @param pane
     * @param sounds
     */
    public RCAnimation(Weapon weapon, Tanks tank, MapGeneration mapGeneration, Pane pane, SoundLib sounds){
        this.pane = pane;
        this.tank = tank;
        this.weapon = weapon;
        this.mapGeneration = mapGeneration;
        this.sounds = sounds;
        explosionAnimation = new ExplosionAnimation(weapon, pane);
        setupAnimation(); 
    }
    
    /**Sets up the animation for the RC*/
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
    
    /**
     *
     */
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
