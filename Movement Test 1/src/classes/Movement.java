/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Cedrik Dubois
 */
public class Movement extends Application {
    
    MapGeneration map = new MapGeneration();
    Rectangle rect;
    Thread lol;
    double xspeed = 0;
    double yspeed = 0;
    double xspeed2 = 0;
    double yspeed2 = 0;
    double gravity = 0.0005;
    double y;
    double y2;
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        
        paneSetup(pane);
        
        primaryStage.setScene(new Scene(pane, 1200, 800));
        primaryStage.show();
        
        pane.requestFocus();
    }
    
    public void paneSetup(Pane pane){
        Group group = new Group();
        frontGroundSetup(pane);
        backGroundSetup(pane);
        MovingBall ballOne = new MovingBall(pane, 1);    
        MovingBall ballTwo = new MovingBall(pane, 2);
        
         Timeline animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                
                
                
                switch (x.getCode()){
                    
                    
                    case LEFT: {
                        if(xspeed > -.5)
                        xspeed -= 0.25;
                    }break;
                    
                    case RIGHT: {
                        if(xspeed < .5)
                        xspeed += 0.25;
                        System.out.println("HI");
                    }break;
                    
                    case UP: {
                        if(yspeed == 0){
                        //System.out.println("up");
                        yspeed = -0.5;
                        }
                    }break;
                case A: {
                        if(xspeed2 > -.5)
                        xspeed2 -= 0.25;
                    }break;
                    
                    case D: {
                        if(xspeed2 < .5)
                        xspeed2 += 0.25;
                        System.out.println("HI");
                    }break;
                    
                    case W: {
                        if(yspeed2 == 0){
                        //System.out.println("up");
                        yspeed2 = -0.5;
                        }
                    }break;
                
                }    
            });
            
            
            
            
            
            y = ballOne.getY(ballOne.getTranslateX());
            
            ballOne.setTranslateY(ballOne.getTranslateY() + yspeed);
            ballOne.setTranslateX(ballOne.getTranslateX() + xspeed);
            
            
            if(ballOne.getTranslateX()<= 0 || ballOne.getTranslateX() >= 1200){
                xspeed *= -1;
            }
            
            
            if (ballOne.getTranslateY() < y){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(ballOne.getTranslateY()> y){
                ballOne.setTranslateY(y);
            } 
        }));
         
         Timeline animationTwo = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                
                
                
                switch (x.getCode()){
                    
                    case LEFT: {
                        if(xspeed > -.5)
                        xspeed -= 0.25;
                    }break;
                    
                    case RIGHT: {
                        if(xspeed < .5)
                        xspeed += 0.25;
                        System.out.println("HI");
                    }break;
                    
                    case UP: {
                        if(yspeed == 0){
                        //System.out.println("up");
                        yspeed = -0.5;
                        }
                    }break;
                    
                    case A: {
                        if(xspeed2 > -.5)
                        xspeed2 -= 0.25;
                    }break;
                    
                    case D: {
                        if(xspeed2 < .5)
                        xspeed2 += 0.25;
                        System.out.println("HI");
                    }break;
                    
                    case W: {
                        if(yspeed2 == 0){
                        //System.out.println("up");
                        yspeed2 = -0.5;
                        }
                    }break;
                
                
                }    
            });
            
            
            
            
            
            y2 = ballTwo.getY(ballTwo.getTranslateX());
            
            ballTwo.setTranslateY(ballTwo.getTranslateY() + yspeed2);
            ballTwo.setTranslateX(ballTwo.getTranslateX() + xspeed2);
            
            
            if(ballTwo.getTranslateX()<= 0 || ballTwo.getTranslateX() >= 1200){
                xspeed2 *= -1;
            }
            
            
            if (ballTwo.getTranslateY() < y2){
                yspeed2 += gravity;
            }
            else
                yspeed2 = 0;
            
            if(ballTwo.getTranslateY()> y2){
                ballTwo.setTranslateY(y2);
            } 
        }));
         
         //group.getChildren().addAll(ballOne, ballTwo);
         //animation.setCycleCount(Timeline.INDEFINITE);
         //animationTwo.setCycleCount(Timeline.INDEFINITE);
         
         ParallelTransition pie = new ParallelTransition(animation, animationTwo);
         //pie.getChildren().addAll(animation, animationTwo);
         pie.setCycleCount(Timeline.INDEFINITE);
         pie.play();
         
         //pane.getChildren().add(pie);
         pane.getChildren().addAll(ballOne, ballTwo);
         
        
        /*
        ThreadTest one = new ThreadTest(pane, 1);
        ThreadTest ama = new ThreadTest(pane, 2);
        
        Thread fun = new Thread(ama, "AWGFTYUI");
        lol = new Thread(one, "Thread 3");
        lol.start();
        fun.start();
        
        /*
        MovingBall ballOne = new MovingBall(pane, 1);    
        MovingBall ballTwo = new MovingBall(pane, 2);
        ballOne.playAnimation();
        ballTwo.playAnimation();
       // 
        //ballOne.start();
        //ballTwo.start();
        /*
        Thread one = new Thread(ballOne, "Thread One");
        Thread two = new Thread(ballTwo, "Thread Two");
        one.start();
        two.start();
        */
        
    }
    
    public void backGroundSetup(Pane pane){
        BackgroundImage myBI= new BackgroundImage(new Image("Pictures/CloudBackground.JPG",1200,800,false,true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        pane.setBackground(new Background(myBI));
    }
    
   public void frontGroundSetup(Pane pane){
       for (int i = 0; i < 1200; i++){
            rect = new Rectangle();
            rect.setTranslateX(i);
            rect.setTranslateY(map.getY(i));
            rect.setWidth(0.5);
            rect.setHeight(800 - map.getY(i));
            rect.setFill(Color.GREEN);
            rect.setStroke(Color.GREEN);
            pane.getChildren().add(rect);
            
        }
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
