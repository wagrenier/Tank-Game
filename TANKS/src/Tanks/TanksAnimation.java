/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import GamePane.GamePane;
import Tanks.Tanks;
import MapGeneration.MapGeneration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    String pathForTextureTankOne = "Texture/Tanks/Canada/Body/Red_Tank_(100x100).png";
    String pathForTextureFlippedTankOne = "Texture/Tanks/Canada/Body/Red_Tank_Flipped_(100x100).png";
    String pathForTextureCannonOne = "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100).png";
    String pathForTextureFlippedCannonOne = "Texture/Tanks/Canada/Cannon/Red_Cannon_Flipped_(100x100).png";
    
    //Variables for tank 2
    private double xspeed2 = 0;
    private double yspeed2 = 0;
    private double y2;
    String pathForTextureTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_(100x100).png";
    String pathForTextureFlippedTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_Flipped_(100x100).png";
    String pathForTextureCannonTwo = "Texture/Tanks/China/Cannon/Yellow_Cannon_(100x100).png";
    String pathForTextureFlippedCannonTwo = "Texture/Tanks/China/Cannon/Yellow_Cannon_Flipped_(100x100).png";
    
    //Variables for tanks 3
    private double xspeed3 = 0;
    private double yspeed3 = 0;
    private double y3;
    String pathForTextureTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_(100x100).png";
    String pathForTextureFlippedTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_Flipped_(100x100).png";
    String pathForTextureCannonThree = "Texture/Tanks/NorthKorea/Cannon/Blue_Cannon_(100x100).png";
    String pathForTextureFlippedCannonThree = "Texture/Tanks/NorthKorea/Cannon/Blue_Cannon_Flipped_(100x100).png";
    
    //Variables for tank 4
    private double xspeed4 = 0;
    private double yspeed4 = 0;
    private double y4;
    String pathForTextureTankFour = "Texture/Tanks/USA/Body/Green_Tank_(100x100).png";
    String pathForTextureFlippedTankFour = "Texture/Tanks/USA/Body/Green_Tank_Flipped_(100x100).png";
    String pathForTextureCannonFour = "Texture/Tanks/USA/Cannon/Green_Cannon_(100x100).png";
    String pathForTextureFlippedCannonFour = "Texture/Tanks/USA/Cannon/Green_Cannon_Flipped_(100x100).png";
    
    
    private double gravity = 0.05;
    
    private int numOfPlayer;
    private Tanks tanksOne;
    private Tanks tanksTwo;
    private Tanks tanksThree;
    private Tanks tanksFour;
    private Timeline animation;
    private Timeline animation2;
    private Timeline animation3;
    private Timeline animation4;
    private Pane pane;
    private MapGeneration mapGeneration;
    
    
    public TanksAnimation(MapGeneration mapGeneration, Pane pane, int numOfPlayer) {
        tanksOne = new Tanks(pathForTextureTankOne, pathForTextureFlippedTankOne, pathForTextureCannonOne, pathForTextureFlippedCannonOne);
        tanksTwo = new Tanks(pathForTextureTankTwo, pathForTextureFlippedTankTwo, pathForTextureCannonTwo, pathForTextureFlippedCannonTwo);
        tanksThree = new Tanks(pathForTextureTankThree, pathForTextureFlippedTankThree, pathForTextureCannonThree, pathForTextureFlippedCannonThree);
        tanksFour = new Tanks(pathForTextureTankFour, pathForTextureFlippedTankFour, pathForTextureCannonFour, pathForTextureFlippedCannonFour);
        this.numOfPlayer = numOfPlayer;     
        this.mapGeneration = mapGeneration;
        this.pane = pane;
        
        tanksOne.setCenterY(-23);
        tanksThree.setCenterY(-21);
        setupTanksPlayer();
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
         
         //tanksOne.setRotate(20);
         //tanksTwo.setRotate(20);
    
                
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
        
        animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());
            });
            
            
            
            
            
            
        //tanksOne.setRotate(100 * mapGeneration.derivativeFunction(tanksOne.getTranslateX()));
        //tanksOne.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksOne.getTranslateX())));
        
        y = mapGeneration.getY(tanksOne.getTranslateX());
            tanksOne.setTranslateY(tanksOne.getTranslateY() + yspeed);
            tanksOne.setTranslateX((tanksOne.getTranslateX() + xspeed)); 
            
            
            
            if(tanksOne.getTranslateX()<= 0 || tanksOne.getTranslateX() >= 1200){
                if(tanksOne.getTranslateX()<= 0){
                    tanksOne.normalTexture();
                }
                
                else{
                    tanksOne.flipTexture();
                }
                xspeed *= -1;
                
               // System.out.println("BOB");
            }
            
            
            if (tanksOne.getTranslateY() < y ){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(tanksOne.getTranslateY() > y){
                tanksOne.setTranslateY(y);
            } 
            
            tanksOne.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanksOne.getTranslateX())));
            }));
    }
    
    private void animationForTanksTwo(){
        
        animation2 = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());  
            });
            
            
            
            
        
        tanksTwo.setRotate(50 * mapGeneration.derivativeFunction(tanksTwo.getTranslateX()));
           
            
            
            y2 = mapGeneration.getY(tanksTwo.getTranslateX()) ;
            
            tanksTwo.setTranslateY(tanksTwo.getTranslateY() + yspeed2);
            tanksTwo.setTranslateX(tanksTwo.getTranslateX() + xspeed2);
            
            
            if(tanksTwo.getTranslateX()<= 0 || tanksTwo.getTranslateX() >= width){
                if(tanksTwo.getTranslateX()<= 0){
                    tanksTwo.normalTexture();
                }
                
                else{
                    tanksTwo.flipTexture();
                }
                //System.out.println("BOB " + tanksTwo.getTranslateX() + " width: " + width);
                xspeed2 *= -1;
            }
            
            
            if (tanksTwo.getTranslateY() < y2){
                yspeed2 += gravity;
            }
            else
                yspeed2 = 0;
            
            if(tanksTwo.getTranslateY() > y2){
                tanksTwo.setTranslateY(y2);
            } 
            
            }));
    }
    
    private void animationForTankThree(){
        
        animation3 = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());  
            });
            
            
            
            
        
        tanksThree.setRotate(50 * mapGeneration.derivativeFunction(tanksThree.getTranslateX()));
        y3 = mapGeneration.getY(tanksThree.getTranslateX()) ;
            
            tanksThree.setTranslateY(tanksThree.getTranslateY() + yspeed3);
            tanksThree.setTranslateX(tanksThree.getTranslateX() + xspeed3);
            
            
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
        
        animation4 = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());  
            });
            
            
            
            
        
        tanksFour.setRotate(50 * mapGeneration.derivativeFunction(tanksFour.getTranslateX()));
        y4 = mapGeneration.getY(tanksFour.getTranslateX()) ;
            
            tanksFour.setTranslateY(tanksFour.getTranslateY() + yspeed4);
            tanksFour.setTranslateX(tanksFour.getTranslateX() + xspeed4);
            
            
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
    
    public void keyPressed(KeyCode x){
        
         
        switch (x){
            
            case SPACE: {
                ((GamePane)pane).weaponSetup(pane);
            }break;
                    
                    //Controls for player 1
                case LEFT: {
                    
                    if(xspeed == 0){
                            xspeed -= 0.1;
                            tanksOne.flipTexture();
                        }
                    else if(xspeed > -.1)
                        xspeed -= 0.1;
                        
                        
                    }break;
                    
                case RIGHT: {
                    
                    if(xspeed == 0){
                            xspeed += 0.1;
                            tanksOne.normalTexture();
                        }
                    else if(xspeed < .1)
                        xspeed += 0.1;
                        //System.out.println("HI");
                        
                    }break;
                    
                case UP: {
                        tanksOne.getCannon().higherAngle();
                        tanksOne.updateSomething();
                    }break;
                    
                case DOWN: {
                    tanksOne.getCannon().lowerAngle();
                    tanksOne.updateSomething();break;
                }        
                  //Controls for player 2  
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
                        if(yspeed2 == 0){
                        //System.out.println("up");
                        yspeed2 = -0.5;
                        }
                    }break;
                    
                    
                    //Controls for player 3
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
                        if(yspeed3 == 0){
                        //System.out.println("up");
                        yspeed3 = -0.5;
                        }
                    }break;
                    
                    
                    //Controls for player 4
                case V: {
                    if(xspeed4 == 0){
                            xspeed4 -= 0.1;
                            tanksFour.flipTexture();
                        }
                       else if(xspeed4 > -.1)
                        xspeed4 -= 0.1;
                    }break;
                    
                case B: {
                    if(xspeed4 == 0){
                            xspeed4 += 0.1;
                            tanksFour.normalTexture();
                        }
                        if(xspeed4 < .1)
                        xspeed4 += 0.1;
                        //System.out.println("HI");
                    }break;
                    
                case G: {
                        if(yspeed4 == 0){
                        //System.out.println("up");
                        yspeed4 = -0.5;
                        }
                    }break;
                
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
