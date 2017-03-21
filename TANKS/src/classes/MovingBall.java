/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author 1530178
 */
public class MovingBall extends Circle {
    
    double xspeed = 0;
    double yspeed = 0;
    //double gravity = 0.0005;
    double ratio;
    double y;
    int v;
    Timeline animation;
    Pane pane;
    MapGeneration mapGeneration;
    
        MovingBall(Pane pane, int v, MapGeneration mapGeneration){
        this.v = v;
        this.pane = pane;
        this.setRadius(15);
        this.ratio = 1;
        setFill(Color.BLACK);
        setStroke(Color.BLACK);
        setTranslateX(50 * v);
        setTranslateY(200);
        this.mapGeneration = mapGeneration;
        //movingBallSetup(pane);
    }
    
    
    MovingBall(Pane pane, int v, double ratio){
        this.v = v;
        this.pane = pane;
        this.setRadius(15);
        this.ratio = ratio;
        setFill(Color.BLACK);
        setStroke(Color.BLACK);
        setTranslateX(50 * v);
        setTranslateY(200);
        //movingBallSetup(pane);
    }
    
    private void movingBallSetup(Pane pane){
        /*
        setFill(Color.BLACK);
        setStroke(Color.BLACK);
        setTranslateX(50 * v);
        setTranslateY(200);
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());
            });
            
            //rect.setTranslateX(i / ratio);
            
            
            
            if(ratio < 1){
               setLayoutX(getTranslateX() * ratio);
            
            }
            
            
            
            if(ratio == 1){
                
            }
            
            y = getY(getTranslateX(), ratio);
            setTranslateY(getTranslateY() + yspeed);
            setTranslateX((getTranslateX() + xspeed)); 
            
            //setTranslateX(v);
            
            //System.out.println(ratio + " xspeed: " + xspeed + " translateX: " + getTranslateX() + " Sum: " + (x + xspeed) / ratio );
            
            if(getTranslateX()<= 0 || getTranslateX() >= width * ratio){
                xspeed *= -1;
               // System.out.println("BOB");
            }
            
            
            if (getTranslateY() < y ){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(getTranslateY() > y){
                setTranslateY(y);
            } 
            
            }));
        */
    }
    
    
    public void playAnimation(){
        animation.play();
        pane.getChildren().add(this);
    }
    
    public void keyPressed(KeyCode x){
        
        switch (x){
                    
                    
                    case LEFT: {
                        if(xspeed > -.5)
                        xspeed -= 0.25;
                    }break;
                    
                    case RIGHT: {
                        if(xspeed < .5)
                        xspeed += 0.25;
                        //System.out.println("HI");
                    }break;
                    
                    case UP: {
                        if(yspeed == 0){
                        //System.out.println("up");
                        yspeed = -0.5;
                        }
                    }break;
                    
                    /*
                case A: {
                        if(xspeed2 > -.5)
                        xspeed2 -= 0.25;
                    }break;
                    
                    case D: {
                        if(xspeed2 < .5)
                        xspeed2 += 0.25;
                        //System.out.println("HI");
                    }break;
                    
                    case W: {
                        if(yspeed2 == 0){
                        //System.out.println("up");
                        yspeed2 = -0.5;
                        }
                    }break;
                */
                } 
    }
    

    
    public double getY(double x){
        return mapGeneration.getY(x);
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
    
    
    
}
