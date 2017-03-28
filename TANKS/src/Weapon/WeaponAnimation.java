/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import MapGeneration.MapGeneration;
import Tanks.Tanks;
import java.sql.Time;
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
    private double yspeed = -.5;
    private double xspeed = .25;
    private double gravity = .0005;
    private double currentYPosition;
    private double angleLaunched = 1; // angle must be between 0 and 1 included
    private double initialSpeed = Math.sqrt(Math.pow(yspeed, 2) + Math.pow(xspeed, 2));
    private double distance;
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
        //this.initialXPosition = this.tank.getLayoutX();
        this.initialXPosition = 0;
        this.initialYPosition = 0;
       // this.initialYPosition = this.tank.getTranslateY();
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
       // calculateDistance();
        //alculateAnimationTime();
        yspeed = yspeed * Math.toDegrees(Math.sin(Math.toRadians(angleLaunched)));
        weapon.setTranslateX(200);
        weapon.setTranslateY(699);
        animationWeapon =  new Timeline(new KeyFrame(Duration.millis(1), e -> {
            
            
            currentYPosition = mapGeneration.getY(weapon.getTranslateX());
            weapon.setTranslateY(weapon.getTranslateY() + yspeed);
            weapon.setTranslateX((weapon.getTranslateX() + xspeed)); 
            
            System.out.println(weapon.getTranslateX() + " TranslateY: " + weapon.getTranslateY() + " currentYPos: " + currentYPosition);
            
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
            
            /*
        //System.out.println(weapon.getTranslateX() + " " + weapon.getTranslateY());
            
        //weapon.setRotate(25 * mapGeneration.derivativeFunction(weapon.getTranslateX()));
        
        currentYPosition = calculateCurrentYPosition();
        //currentYPosition = mapGeneration.getY(weapon.getTranslateX());
        //System.out.println("yspeed: " + yspeed + " xspeed: " + xspeed + " currentYPosition: " + currentYPosition);
        //mapGeneration.getY(weapon.getTranslateX())
        //weapon.setTranslateY(weapon.getTranslateY() + yspeed);
        weapon.setTranslateY(currentYPosition);
        weapon.setTranslateX((weapon.getTranslateX() + xspeed)); 
            
            System.out.println(weapon.getTranslateX() + " TranslateY: " + weapon.getTranslateY() + " currentYPos: " + currentYPosition);
            
        if(weapon.getTranslateX()<= 0 || weapon.getTranslateX() >= 1200){
            xspeed *= -1;
            }
            
           
        if (weapon.getTranslateY() <  mapGeneration.getY(weapon.getTranslateX())){
                yspeed += gravity;
            }
        else{
            yspeed = 0;
            xspeed = 0;
            currentYPosition = weapon.getTranslateY();
        }
        if(weapon.getTranslateY() > mapGeneration.getY(weapon.getTranslateX())){
                weapon.setTranslateY(currentYPosition);
                //yspeed = 0;
                //xspeed = 0;
            } 
            
*/
            
            }));
        
        
    }
    
    public void launchAnimation(){
        
        //weapon.setTranslateX(tank.getTranslateX());
        pane.getChildren().add(weapon);
        
        animationWeapon.setCycleCount(Timeline.INDEFINITE);
        animationWeapon.play();
    }
    
    
    /*
    private double calculateCurrentYPosition(){
       // y = yi + x * Tan(θ) - ( (g * x ^ 2) / (2 * (v * cos(θ)) ^ 2)
       
       double firstPart = initialYPosition + (weapon.getTranslateX() * Math.toDegrees(Math.tan(Math.toRadians(angleLaunched))));
       //double firstPart = initialYPosition + (weapon.getTranslateX() * Math.tan(angleLaunched));
       double secondPart = gravity * Math.pow(weapon.getTranslateX(), 2);
       double thirdPart = 2 * Math.pow((initialSpeed * Math.toDegrees(Math.cos(Math.toRadians(angleLaunched)))) , 2);
       //double thirdPart = 2 * Math.pow((initialSpeed * Math.cos(angleLaunched)) , 2);
       double fourthPart = secondPart / thirdPart;
       //System.out.println(firstPart - fourthPart);
       return (firstPart - fourthPart);

       //double yVelocity = yspeed - gravity * animationWeapon.getTargetFramerate();
       
       //return yspeed * animationWeapon.getTargetFramerate() - (0.5 * gravity * Math.pow(animationWeapon.getTargetFramerate(), 2));
    }
    
    private void calculateDistance(){
        //d =  (v * cosθ / g) * (v * sinθ + sqrt((vsinθ) ^ 2 + 2 * g * yi)) 
        distance = ((initialSpeed * Math.toDegrees(Math.cos(Math.toRadians(angleLaunched)))) / gravity) * ( initialSpeed * Math.toDegrees(Math.sin(Math.toRadians(angleLaunched))) + Math.sqrt(Math.pow((initialSpeed * Math.toDegrees(Math.sin(Math.toRadians(angleLaunched)))), 2) + (2 * gravity * initialYPosition))); 
                System.out.println(distance);
    }
    
    private void calculateAnimationTime(){
        time = distance / ((initialSpeed * Math.toDegrees(Math.cos(Math.toRadians(angleLaunched)))) / gravity);
        System.out.println(time);
        //t = d / (v * cos(θ))
    }
    */
    
    
}
