/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class TanksAnimation {
    
    double width = 1200;
    double xspeed = 0;
    double yspeed = 0;
    double xspeed2 = 0;
    double yspeed2 = 0;
    double gravity = 0.0005;
    double y;
    double y2;
    int numOfPlayer;
    Tanks tanksOne;
    Tanks tanksTwo;
    Tanks tanksThree;
    Tanks tanksFour;
    Timeline animation;
    Timeline animation2;
    Timeline animation3;
    Timeline animation4;
    Pane pane;
    MapGeneration mapGeneration;
    
    
    public TanksAnimation(MapGeneration mapGeneration, Pane pane, int numOfPlayer) {
        tanksOne = new Tanks("Texture/Tanks/Canada/Body/Red_Tank_(100x100).png", "Texture/Tanks/Canada/Body/Red_Tank_Flipped.png");
        tanksTwo = new Tanks("Texture/Tanks/China/Body/Yellow_Tank.png", "Texture/Tanks/China/Body/Yellow_Tank_Flipped.png");
        tanksThree = new Tanks("Texture/Tanks/NorthKorea/Body/Blue_Tank.png", "Texture/Tanks/NorthKorea/Body/Blue_Tank_Flipped.png");
        tanksFour = new Tanks("Texture/Tanks/USA/Body/Green_Tank.png", "Texture/Tanks/USA/Body/Green_Tank_Flipped.png");
        this.numOfPlayer = numOfPlayer;     
        this.mapGeneration = mapGeneration;
        this.pane = pane;
        
        setupAnimationForTwoTanks();
        
    }
    
    
        private void setupAnimationForTwoTanks(){
        
        animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());
            });
            
            y = mapGeneration.getY(tanksOne.getTranslateX());
            tanksOne.setTranslateY(tanksOne.getTranslateY() + yspeed);
            tanksOne.setTranslateX((tanksOne.getTranslateX() + xspeed)); 
            
            //setTranslateX(v);
            
            //System.out.println(width * ratio + " xspeed: " + xspeed + " translateX: " + getTranslateX());
            
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
            
            //Rotate rotate = new Rotate(points[((int)tanksOne.getTranslateX())].angle(tanksOne.getTranslateX(), tanksOne.getTranslateY()));
            tanksOne.setRotate(mapGeneration.derivativeFunction(tanksOne.getTranslateX()));
            
            }));
        
        
         animation2 = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());  
            });
            
            
            
            y2 = mapGeneration.getY(tanksTwo.getTranslateX()) ;
            
            tanksTwo.setTranslateY(tanksTwo.getTranslateY() + yspeed2);
            tanksTwo.setTranslateX(tanksTwo.getTranslateX() + xspeed2);
            
            
            if(tanksTwo.getTranslateX()<= 0 || tanksTwo.getTranslateX() >= width){
                
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
        
        pane.getChildren().add(tanksOne);
        pane.getChildren().add(tanksTwo);
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        animation2.setCycleCount(Timeline.INDEFINITE);
        animation2.play();
         
         
    
                
    }
    
     public void keyPressed(KeyCode x){
        
         
        switch (x){
                    
                    
                    case LEFT: {
                        if(xspeed > -.1)
                        xspeed -= 0.1;
                    }break;
                    
                    case RIGHT: {
                        if(xspeed < .1)
                        xspeed += 0.1;
                        //System.out.println("HI");
                    }break;
                    
                    case UP: {
                        if(yspeed == 0){
                        //System.out.println("up");
                        yspeed = -0.5;
                        }
                    }break;
                    
                        
                case A: {
                        if(xspeed2 > -.1)
                        xspeed2 -= 0.1;
                    }break;
                    
                    case D: {
                        if(xspeed2 < .1)
                        xspeed2 += 0.1;
                        //System.out.println("HI");
                    }break;
                    
                    case W: {
                        if(yspeed2 == 0){
                        //System.out.println("up");
                        yspeed2 = -0.5;
                        }
                    }break;
                
        }
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
