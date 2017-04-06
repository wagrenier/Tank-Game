/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import MapGeneration.MapGeneration;
import Weapon.Weapon;
import Weapon.WeaponAnimation;
import Weapon.WeaponManager;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class TanksAnimation {
    
    double width = 1200;
    
    //Variables for tank 1
    private double xspeed = 0;
    private double yspeed = 0;
    private double y;
    final String  pathForTextureTankOne = "Texture/Tanks/Canada/Body/Red_Tank_(100x100).png";
    final String pathForTextureFlippedTankOne = "Texture/Tanks/Canada/Body/Red_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonOne = "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100).png";
    
    
    
    //Variables for tank 2
    private double xspeed2 = 0;
    private double yspeed2 = 0;
    private double y2;
    final String pathForTextureTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_(100x100).png";
    final String pathForTextureFlippedTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonTwo = "Texture/Tanks/China/Cannon/Yellow_Cannon_(100x100).png";
    
    
    //Variables for tanks 3
    private double xspeed3 = 0;
    private double yspeed3 = 0;
    private double y3;
    final String pathForTextureTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_(100x100).png";
    final String pathForTextureFlippedTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonThree = "Texture/Tanks/NorthKorea/Cannon/Blue_Cannon_(100x100).png";
    
    
    //Variables for tank 4
    private double xspeed4 = 0;
    private double yspeed4 = 0;
    private double y4;
    final String pathForTextureTankFour = "Texture/Tanks/USA/Body/Green_Tank_(100x100).png";
    final String pathForTextureFlippedTankFour = "Texture/Tanks/USA/Body/Green_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonFour = "Texture/Tanks/USA/Cannon/Green_Cannon_(100x100).png";
    
    private double gravity = 0.05;
    
    private int numOfPlayer;
    private final Tanks tanksOne;
    private final Tanks tanksTwo;
    private final Tanks tanksThree;
    private final Tanks tanksFour;
    private Timeline animation;
    private Timeline animation2;
    private Timeline animation3;
    private Timeline animation4;
    private Timeline progressBarAnimationOne;
    private Timeline progressBarAnimationTwo;
    private Timeline progressBarAnimationThree;
    private Timeline progressBarAnimationFour;
    private Pane pane;
    private MapGeneration mapGeneration;
    WeaponManager weaponManager;
    ProgressBar barOne = new ProgressBar(0);
    ProgressBar barTwo = new ProgressBar(0);
    ProgressBar barThree = new ProgressBar(0);
    ProgressBar barFour = new ProgressBar(0);
    
    public TanksAnimation(MapGeneration mapGeneration, Pane pane, int numOfPlayer) {
        tanksOne = new Tanks(pathForTextureTankOne, pathForTextureFlippedTankOne, pathForTextureCannonOne, "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100)_Flipped.png");
        tanksTwo = new Tanks(pathForTextureTankTwo, pathForTextureFlippedTankTwo, pathForTextureCannonTwo);
        tanksThree = new Tanks(pathForTextureTankThree, pathForTextureFlippedTankThree, pathForTextureCannonThree);
        tanksFour = new Tanks(pathForTextureTankFour, pathForTextureFlippedTankFour, pathForTextureCannonFour);
        this.numOfPlayer = numOfPlayer;     
        this.mapGeneration = mapGeneration;
        this.pane = pane;
        
        tanksOne.setCenterY(-23);
        tanksThree.setCenterY(-21);
        
        weaponManager = new WeaponManager();
        
        progressBarAnimationOne = progressBarInitialSetup(barOne);
        progressBarAnimationTwo = progressBarInitialSetup(barTwo);
        progressBarAnimationThree = progressBarInitialSetup(barThree);
        progressBarAnimationFour = progressBarInitialSetup(barFour);
        setupTanksPlayer();
        pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());
            });
    }
    
    private Timeline progressBarInitialSetup(ProgressBar bar){
        bar.setPrefSize(200, 24);

        Timeline progressBarAnimation = new Timeline(
        new KeyFrame(
                Duration.ZERO,       
                new KeyValue(bar.progressProperty(), 0)
        ),
        new KeyFrame(
                Duration.seconds(2), 
                new KeyValue(bar.progressProperty(), 1)
        )
    );
        
        progressBarAnimation.setCycleCount(Timeline.INDEFINITE);
        return progressBarAnimation;
    }
    
    private void progressBarInGameAnimationPlay(Tanks tank, Timeline progressBarAnimation, ProgressBar bar){
        if(progressBarAnimation.getStatus().compareTo(RUNNING) == 0){
            progressBarInGameAnimationStop(tank, progressBarAnimation, bar);
        }
        
        else{
            bar.setTranslateX(tank.getTranslateX() - 50);
            bar.setTranslateY(tank.getTranslateY() - 100);
            pane.getChildren().add(bar);
            progressBarAnimation.setAutoReverse(true);
            progressBarAnimation.playFromStart();
        }
       
        
    }
    
    private void progressBarInGameAnimationStop(Tanks tank, Timeline progressBarAnimation, ProgressBar bar){
        weaponSetup(tank, bar.getProgress());
        progressBarAnimation.stop();
        pane.getChildren().remove(bar);
    }
    
    private void setupTanksPlayer(){
        switch(this.numOfPlayer){
            case 2: setupAnimationForTwoTanks(); break;
            case 3: setupAnimationForThreeTanks(); break;
            case 4: setupAnimationForFourTanks(); break;
        }
    }
    
    private void setupAnimationForTwoTanks(){
        
        animationForTankOne();
        animationForTanksTwo();
        
        pane.getChildren().add(tanksOne.getCannon());
        pane.getChildren().add(tanksTwo.getCannon());
        
        pane.getChildren().add(tanksOne);
        pane.getChildren().add(tanksTwo);
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        animation2.setCycleCount(Timeline.INDEFINITE);
        animation2.play();         
    }
    
    private void setupAnimationForThreeTanks(){
        
        
        animationForTankOne();
        animationForTanksTwo();
        animationForTankThree();
         
        pane.getChildren().add(tanksOne.getCannon());
        pane.getChildren().add(tanksTwo.getCannon());
        pane.getChildren().add(tanksThree.getCannon());
         
        pane.getChildren().add(tanksOne);
        pane.getChildren().add(tanksTwo);
        pane.getChildren().add(tanksThree);
        
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        animation2.setCycleCount(Timeline.INDEFINITE);
        animation2.play();
        
        animation3.setCycleCount(Timeline.INDEFINITE);
        animation3.play();
        
                
    }
    
    private void setupAnimationForFourTanks(){
        animationForTankOne();
        animationForTanksTwo();
        animationForTankThree();
        animationForTankFour();
        
        pane.getChildren().add(tanksOne.getCannon());
        pane.getChildren().add(tanksTwo.getCannon());
        pane.getChildren().add(tanksThree.getCannon());
        pane.getChildren().add(tanksFour.getCannon());
        
        pane.getChildren().add(tanksOne);
        pane.getChildren().add(tanksTwo);
        pane.getChildren().add(tanksThree);
        pane.getChildren().add(tanksFour);
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        animation2.setCycleCount(Timeline.INDEFINITE);
        animation2.play();
        
        animation3.setCycleCount(Timeline.INDEFINITE);
        animation3.play();
        
        animation4.setCycleCount(Timeline.INDEFINITE);
        animation4.play();
         
         
    
                
    }
    
    private void animationForTankOne(){
        tanksOne.setCenterY(-23);
        tanksOne.setTranslateX(300);
        tanksOne.setTranslateY(mapGeneration.getY(300));
        tanksOne.getCannon().setTranslateY(tanksOne.getTranslateY() + yspeed - 35);
        
        
        
        animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            tanksOne.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksOne.getTranslateX())));
            
            if(progressBarAnimationOne.getStatus().compareTo(RUNNING) == 0){
            xspeed = 0;
            yspeed = 0;
        }
            
        y = mapGeneration.getY(tanksOne.getTranslateX());
            tanksOne.setTranslateY(tanksOne.getTranslateY() + yspeed);
            tanksOne.setTranslateX((tanksOne.getTranslateX() + xspeed)); 
            
            
            if(xspeed < 0){
                tanksOne.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksOne.getTranslateX())) + Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                tanksOne.getCannon().setTranslateY(tanksOne.getTranslateY() + yspeed - 35);
                tanksOne.getCannon().setTranslateX((tanksOne.getTranslateX() + xspeed)); 
            }
            
            else if(xspeed == 0){
                if(tanksOne.isIsImageFlipped()){
                    tanksOne.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksOne.getTranslateX())) + Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                    tanksOne.getCannon().setTranslateY(tanksOne.getTranslateY() + yspeed - 35);
                    tanksOne.getCannon().setTranslateX((tanksOne.getTranslateX() + xspeed)); 
                }
                else{
                    tanksOne.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksOne.getTranslateX())) - Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                    tanksOne.getCannon().setTranslateY(tanksOne.getTranslateY() + yspeed - 35);
                    tanksOne.getCannon().setTranslateX((tanksOne.getTranslateX() + xspeed)); 
                }
            }
            
            else if(xspeed > 0){
                tanksOne.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksOne.getTranslateX())) - Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                tanksOne.getCannon().setTranslateY(tanksOne.getTranslateY() + yspeed - 35);
                tanksOne.getCannon().setTranslateX((tanksOne.getTranslateX() + xspeed)); 
            }
            
            
            
            
            
            if(tanksOne.getTranslateX()<= 0 || tanksOne.getTranslateX() >= 1200){
                if(tanksOne.getTranslateX()<= 0){
                    tanksOne.normalTexture();
                    tanksOne.getCannon().normalTexture();
                }
                
                else{
                    tanksOne.flipTexture();
                    tanksOne.getCannon().flipTexture();
                }
                xspeed *= -1;
            }
            
            
            if (tanksOne.getTranslateY() < y ){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(tanksOne.getTranslateY() > y){
                tanksOne.setTranslateY(y);
            } 
            
            
            }));
        
    }
    
    private void animationForTanksTwo(){
        
        tanksTwo.setTranslateX(500);
        tanksTwo.setTranslateY(mapGeneration.getY(500));
       // tanksTwo.getCannon().setTranslateY(tanksTwo.getTranslateY());
        //tanksTwo.getCannon().setTranslateY(tanksTwo.getTranslateY());
        //tanksTwo.getCannon().setTranslateX((tanksTwo.getTranslateX())); 
        //tanksTwo.setCenterX(width);
        
        tanksTwo.getCannon().setTranslateX(500);
        tanksTwo.getCannon().setTranslateY(mapGeneration.getY(500));
        tanksTwo.getCannon().setCenterY(-35);
        animation2 = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            
            if(progressBarAnimationTwo.getStatus().compareTo(RUNNING) == 0){
            xspeed2 = 0;
            yspeed2 = 0;
        }
        
        tanksTwo.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksTwo.getTranslateX())));
            y2 = mapGeneration.getY(tanksTwo.getTranslateX()) ;
            
            tanksTwo.setTranslateY(tanksTwo.getTranslateY() + yspeed2);
            tanksTwo.setTranslateX(tanksTwo.getTranslateX() + xspeed2);
            tanksTwo.getCannon().setTranslateY(tanksTwo.getTranslateY());
            tanksTwo.getCannon().setTranslateX((tanksTwo.getTranslateX())); 
            
            if(xspeed2 < 0){
                tanksTwo.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksTwo.getTranslateX())) + Math.toDegrees(tanksTwo.getCannon().getCanonAngle()) - 180);
                tanksTwo.getCannon().setCenterY(-30);
                
            }
            
            else if(xspeed2 == 0){
                if(tanksTwo.isIsImageFlipped()){
                    tanksTwo.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksTwo.getTranslateX())) + Math.toDegrees(tanksTwo.getCannon().getCanonAngle()) - 180);
                    tanksTwo.getCannon().setCenterY(-30);
                    
                    
                    
                }
                else{
                    tanksTwo.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksTwo.getTranslateX())) - Math.toDegrees(tanksTwo.getCannon().getCanonAngle()));
                    tanksTwo.getCannon().setCenterY(-35);
                    
                    
                }
            }
            
            else if(xspeed2 > 0){
                tanksTwo.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksTwo.getTranslateX())) - Math.toDegrees(tanksTwo.getCannon().getCanonAngle()));
                tanksTwo.getCannon().setCenterY(-35);
                
                
            }
            
            
            
            
            
            if(tanksTwo.getTranslateX()<= 0 || tanksTwo.getTranslateX() >= width){
                if(tanksTwo.getTranslateX()<= 0){
                    tanksTwo.normalTexture();
                }
                
                else{
                    tanksTwo.flipTexture();
                }
                xspeed2 *= -1;
            }
            
            
            if (tanksTwo.getTranslateY() < y2){
                yspeed2 += gravity;
            }
            else
                yspeed2 = 0;
            
            if(tanksTwo.getTranslateY() > y2){
                tanksTwo.getCannon().setTranslateY(y2);
                tanksTwo.setTranslateY(y2);
            } 
            
            
            
            }));
    }
    
    private void animationForTankThree(){
        
        tanksThree.setTranslateX(700);
        tanksThree.setTranslateY(mapGeneration.getY(700));
        tanksThree.getCannon().setTranslateY(tanksThree.getTranslateY() + yspeed3 - 35);
        
        animation3 = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            
            if(progressBarAnimationThree.getStatus().compareTo(RUNNING) == 0){
            xspeed3 = 0;
            yspeed3 = 0;
        }
        tanksThree.setRotate(50 * mapGeneration.derivativeFunction(tanksThree.getTranslateX()));
        y3 = mapGeneration.getY(tanksThree.getTranslateX()) ;
            
            tanksThree.setTranslateY(tanksThree.getTranslateY() + yspeed3);
            tanksThree.setTranslateX(tanksThree.getTranslateX() + xspeed3);
            
            if(xspeed3 < 0){
                tanksThree.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksThree.getTranslateX())) + Math.toDegrees(tanksThree.getCannon().getCanonAngle()) - 180);
                tanksThree.getCannon().setTranslateY(tanksThree.getTranslateY() + yspeed3 - 30);
            }
            
            else if(xspeed3 == 0){
                if(tanksThree.isIsImageFlipped()){
                    tanksThree.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksThree.getTranslateX())) + Math.toDegrees(tanksThree.getCannon().getCanonAngle()) - 180);
                    tanksThree.getCannon().setTranslateY(tanksThree.getTranslateY() + yspeed3 - 30);
                }
                else{
                    tanksThree.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksThree.getTranslateX())) - Math.toDegrees(tanksThree.getCannon().getCanonAngle()));
                    tanksThree.getCannon().setTranslateY(tanksThree.getTranslateY() + yspeed3 - 35);
                }
            }
            
            else if(xspeed3 > 0){
                tanksThree.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksThree.getTranslateX())) - Math.toDegrees(tanksThree.getCannon().getCanonAngle()));
                tanksThree.getCannon().setTranslateY(tanksThree.getTranslateY() + yspeed3 - 35);
            }
            
            
            tanksThree.getCannon().setTranslateX((tanksThree.getTranslateX() + xspeed3)); 
            
            
            if(tanksThree.getTranslateX()<= 0 || tanksThree.getTranslateX() >= width){
                if(tanksThree.getTranslateX()<= 0){
                    tanksThree.normalTexture();
                }
                
                else{
                    tanksThree.flipTexture();
                }
                //System.out.println("BOB " + tanksThree.getTranslateX() + " width: " + width);
                xspeed3 *= -1;
            }
            
            
            if (tanksThree.getTranslateY() < y3){
                yspeed3 += gravity;
            }
            else
                yspeed3 = 0;
            
            if(tanksThree.getTranslateY() > y3){
                tanksThree.setTranslateY(y3);
            } 
            
            }));
    }
    
    private void animationForTankFour(){
        
        tanksFour.setTranslateX(100);
        tanksFour.setTranslateY(mapGeneration.getY(100));
        tanksFour.getCannon().setTranslateY(tanksFour.getTranslateY() + yspeed4 - 30);
        
        animation4 = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            
            
            if(progressBarAnimationFour.getStatus().compareTo(RUNNING) == 0){
            xspeed4 = 0;
            yspeed4 = 0;
        }
            
        
        tanksFour.setRotate(50 * mapGeneration.derivativeFunction(tanksFour.getTranslateX()));
        y4 = mapGeneration.getY(tanksFour.getTranslateX()) ;
            
            tanksFour.setTranslateY(tanksFour.getTranslateY() + yspeed4);
            tanksFour.setTranslateX(tanksFour.getTranslateX() + xspeed4);
            
            if(xspeed4 < 0){
                tanksFour.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksFour.getTranslateX())) + Math.toDegrees(tanksFour.getCannon().getCanonAngle()) - 180);
                tanksFour.getCannon().setTranslateY(tanksFour.getTranslateY() + yspeed4 - 30);
                tanksFour.getCannon().setTranslateX(tanksFour.getTranslateX() + xspeed4 + 5); 
            }
            
            else if(xspeed4 == 0){
                if(tanksFour.isIsImageFlipped()){
                    tanksFour.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksFour.getTranslateX())) + Math.toDegrees(tanksFour.getCannon().getCanonAngle()) - 180);
                    tanksFour.getCannon().setTranslateY(tanksFour.getTranslateY() + yspeed4 - 30);
                    tanksFour.getCannon().setTranslateX(tanksFour.getTranslateX() + xspeed4 + 5); 
                }
                else{
                    tanksFour.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksFour.getTranslateX())) - Math.toDegrees(tanksFour.getCannon().getCanonAngle()));
                    tanksFour.getCannon().setTranslateY(tanksFour.getTranslateY() + yspeed4 - 30);
                    tanksFour.getCannon().setTranslateX(tanksFour.getTranslateX() + xspeed4 + 10); 
                }
            }
            
            else if(xspeed4 > 0){
                tanksFour.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksFour.getTranslateX())) - Math.toDegrees(tanksFour.getCannon().getCanonAngle()));
                tanksFour.getCannon().setTranslateY(tanksFour.getTranslateY() + yspeed4 - 30);
                tanksFour.getCannon().setTranslateX(tanksFour.getTranslateX() + xspeed4 + 10); 
            }
            
            
            //tanksFour.getCannon().setTranslateX(tanksFour.getTranslateX() + xspeed4); 
            
            
            if(tanksFour.getTranslateX()<= 0 || tanksFour.getTranslateX() >= width){
                if(tanksFour.getTranslateX()<= 0){
                    tanksFour.normalTexture();
                }
                
                else{
                    tanksFour.flipTexture();
                }
                //System.out.println("BOB " + tanksFour.getTranslateX() + " width: " + width);
                xspeed4 *= -1;
            }
            
            
            if (tanksFour.getTranslateY() < y4){
                yspeed4 += gravity;
            }
            else
                yspeed4 = 0;
            
            if(tanksFour.getTranslateY() > y4){
                tanksFour.setTranslateY(y4);
            } 
            
            }));
    }
    
    public void weaponSetup(Tanks tank, double x){
        Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(((int)(Math.random() * 9))).getTexturePath());        
        
        new WeaponAnimation(weapon, tank, mapGeneration, pane, x);
    }
    
    public void keyPressed(KeyCode x){
        
         
        switch (x){
            
            
            //Controls for player 1
            case SPACE: {
                progressBarInGameAnimationPlay(tanksOne, progressBarAnimationOne, barOne);
            }break;
                    
                    
                case LEFT: {
                    
                    if(xspeed == 0){
                            xspeed -= 0.1;
                            //tanksOne.setRotate(90);
                            
                            tanksOne.flipTexture();
                            tanksOne.getCannon().flipTexture();
                            //tanksOne.getCannon().setRotate(180 - Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                        }
                    else if(xspeed > -.1)
                        xspeed -= 0.1;
                        
                        
                    }break;
                    
                case RIGHT: {
                    
                    if(xspeed == 0){
                            xspeed += 0.1;
                            tanksOne.normalTexture();
                            tanksOne.getCannon().normalTexture();
                            //tanksOne.getCannon().setRotate(180 + Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                            //cannon.setRotate(180 - Math.toDegrees(cannon.getCanonAngle()));
                        }
                    else if(xspeed < .1)
                        xspeed += 0.1;
                    }break;
                    
                case UP: {
                        tanksOne.getCannon().higherAngle();
                        //tanksOne.updateSomething();
                    }break;
                    
                case DOWN: {
                    tanksOne.getCannon().lowerAngle();
                    //tanksOne.updateSomething();break;
                }break;        
                
                
                
                
                  //Controls for player 2  
                
                case E: {
                progressBarInGameAnimationPlay(tanksTwo, progressBarAnimationTwo, barTwo);
            }break;
            
                case A: {
                    
                    if(xspeed2 == 0){
                            xspeed2 -= 0.1;
                            tanksTwo.flipTexture();
                        }
                    else if(xspeed2 > -.1)
                        xspeed2 -= 0.1;
                    }break;
                    
                case D: {
                    
                    if(xspeed2 == 0){
                            xspeed2 += 0.1;
                            tanksTwo.normalTexture();
                        }
                    
                    else if(xspeed2 < .1)
                        xspeed2 += 0.1;
                        //System.out.println("HI");
                    }break;
                    
                case W: {
                        tanksTwo.getCannon().higherAngle();
                    }break;
                    
                case S: {
                    tanksTwo.getCannon().lowerAngle();
                }break;
                    
                    
                
                
                    //Controls for player 3
                case O: {
                progressBarInGameAnimationPlay(tanksThree, progressBarAnimationThree, barThree);
            }break;
            
                case J: {
                    if(xspeed3 == 0){
                            xspeed3 -= 0.1;
                            tanksThree.flipTexture();
                        }
                    
                    else if(xspeed3 > -.1)
                        xspeed3 -= 0.1;
                    }break;
                    
                case L: {
                    if(xspeed3 == 0){
                            xspeed3 += 0.1;
                            tanksThree.normalTexture();
                        }
                    else if(xspeed3 < .1)
                        xspeed3 += 0.1;
                        //System.out.println("HI");
                    }break;
                    
                case I: {
                        tanksThree.getCannon().higherAngle();
                        
                    }break;
                    
                    
                case K:{
                    tanksThree.getCannon().lowerAngle();
                }break;
                    
                    
                
                
                    //Controls for player 4
                case Y: {
                progressBarInGameAnimationPlay(tanksFour, progressBarAnimationFour, barFour);
            }break;
                case F: {
                    if(xspeed4 == 0){
                            xspeed4 -= 0.1;
                            tanksFour.flipTexture();
                        }
                       else if(xspeed4 > -.1)
                        xspeed4 -= 0.1;
                    }break;
                    
                case H: {
                    if(xspeed4 == 0){
                            xspeed4 += 0.1;
                            tanksFour.normalTexture();
                        }
                        if(xspeed4 < .1)
                        xspeed4 += 0.1;
                        //System.out.println("HI");
                    }break;
                    
                case T: {
                        tanksFour.getCannon().higherAngle();
                    }break;
                    
                case G:{
                    tanksFour.getCannon().lowerAngle();
                }
                
        }
    }
    
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getXspeed() {
        return xspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public double getYspeed() {
        return yspeed;
    }

    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getXspeed2() {
        return xspeed2;
    }

    public void setXspeed2(double xspeed2) {
        this.xspeed2 = xspeed2;
    }

    public double getYspeed2() {
        return yspeed2;
    }

    public void setYspeed2(double yspeed2) {
        this.yspeed2 = yspeed2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getXspeed3() {
        return xspeed3;
    }

    public void setXspeed3(double xspeed3) {
        this.xspeed3 = xspeed3;
    }

    public double getYspeed3() {
        return yspeed3;
    }

    public void setYspeed3(double yspeed3) {
        this.yspeed3 = yspeed3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getXspeed4() {
        return xspeed4;
    }

    public void setXspeed4(double xspeed4) {
        this.xspeed4 = xspeed4;
    }

    public double getYspeed4() {
        return yspeed4;
    }

    public void setYspeed4(double yspeed4) {
        this.yspeed4 = yspeed4;
    }

    public double getY4() {
        return y4;
    }

    public void setY4(double y4) {
        this.y4 = y4;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public Timeline getAnimation() {
        return animation;
    }

    public void setAnimation(Timeline animation) {
        this.animation = animation;
    }

    public Timeline getAnimation2() {
        return animation2;
    }

    public void setAnimation2(Timeline animation2) {
        this.animation2 = animation2;
    }

    public Timeline getAnimation3() {
        return animation3;
    }

    public void setAnimation3(Timeline animation3) {
        this.animation3 = animation3;
    }

    public Timeline getAnimation4() {
        return animation4;
    }

    public void setAnimation4(Timeline animation4) {
        this.animation4 = animation4;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    public void setMapGeneration(MapGeneration mapGeneration) {
        this.mapGeneration = mapGeneration;
    }
    
    public Tanks getTanksOne() {
        return tanksOne;
    }

    public Tanks getTanksTwo() {
        return tanksTwo;
    }

    public Tanks getTanksThree() {
        return tanksThree;
    }

    public Tanks getTanksFour() {
        return tanksFour;
    }  
}