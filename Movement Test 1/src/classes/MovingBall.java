/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author 1530178
 */
public class MovingBall extends Circle implements Runnable {
    //Circle circle = new Circle(15);
    double xspeed = 0;
    double yspeed = 0;
    double gravity = 0.0005;
    double y;
    int v;
    Timeline animation;
    Pane pane;
    Thread t;
    
    MovingBall(Pane pane, int v){
        this.v = v;
        this.pane = pane;
        this.setRadius(15);
    }
    
    private void movingBallSetup(Pane pane){
        setFill(Color.BLACK);
        setStroke(Color.BLACK);
        setTranslateX(50 * v);
        setTranslateY(200);
        
        animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                
                if(v == 1 ){
                switch (x.getCode()){
                    
                    
                    case A: {
                        if(xspeed > -.5)
                        xspeed -= 0.25;
                    }break;
                    
                    case D: {
                        if(xspeed < .5)
                        xspeed += 0.25;
                    }break;
                    
                    case W: {
                        if(yspeed == 0){
                        //System.out.println("up");
                        yspeed = -0.5;
                        }
                    }break;
                
                }
                }
                
                else if(v == 2 ){
                switch (x.getCode()){
                    
                    
                    case LEFT: {
                        if(xspeed > -.5)
                        xspeed -= 0.25;
                    }break;
                    
                    case RIGHT: {
                        if(xspeed < .5)
                        xspeed += 0.25;
                    }break;
                    
                    case UP: {
                        if(yspeed == 0){
                        //System.out.println("up");
                        yspeed = -0.5;
                        }
                    }break;
                }
                
                }    
            });
            
            
            
            
            
            y = getY(getTranslateX());
            
            setTranslateY(getTranslateY() + yspeed);
            setTranslateX(getTranslateX() + xspeed);
            
            
            if(getTranslateX()<= 0 || getTranslateX() >= 1200){
                xspeed *= -1;
            }
            
            
            if (getTranslateY() < y){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(getTranslateY()> y){
                setTranslateY(y);
            } 
        }));
        
        
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        //pane.getChildren().add(this);
    }
    
    /*
    public Circle getCircle(){
        return circle;
    }
    */
    
    public int getY(double x){
        double a = 5.464 * Math.pow(10, -9);
        double b = -0.000013308;
        double c = 0.01009;
        double d = -2.5108;
        double f = 647.7;
        
        int y = (int)((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + (d * x) + f);
        return y;
    }

    @Override
    public void run() {
        movingBallSetup(pane);
            
        
    }
    
    public void start () {
      
         t = new Thread (this, "THread" + v);
         t.start ();
      
   }
    
   
    
}
