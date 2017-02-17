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
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
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
    double width = 1200;
    double height = 800;
    double ratio = 1200 / width;
    Rectangle rect;
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
        
        primaryStage.setTitle("Tanks");
        
        movingBallSetup(pane);
        
        Scene scene = new Scene(pane, width, height);
        
        scene.setOnDragDetected(e ->{
           width = pane.getWidth();
           height = pane.getHeight();
           ratio = 1200 / width;
            paneSetup(pane);
            xspeed = 0;
            //pane.getChildren().clear();
            //pie.getChildren().clear();
            //paneSetup(pane);
            pane.resize(pane.getWidth(), pane.getHeight());
            
            
        });
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //primaryStage.setResizable(false);
        
        
        pane.requestFocus();
    }
    
    public void paneSetup(Pane pane){
        // pane.getChildren().clear();
        //movingBallSetup(pane);
        
        frontGroundSetup(pane);
        backGroundSetup(pane);
        //movingBallSetup(pane);
        
    }
    
    public void movingBallSetup(Pane pane){
        
        MovingBall ballOne = new MovingBall(pane, 1);    
        MovingBall ballTwo = new MovingBall(pane, 2);
        
        
         Timeline animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());
            });
            
            //rect.setTranslateX(i / ratio);
            double x = ballOne.getTranslateX();
            double v = ((x + xspeed) / ratio);
            y = ballOne.getY(ballOne.getTranslateX(), ratio);
            ballOne.setTranslateY(ballOne.getTranslateY() + yspeed);
            ballOne.setTranslateX((x + xspeed));
           
            
            //System.out.println(ratio + " xspeed: " + xspeed + " translateX: " + ballOne.getTranslateX() + " Sum: " + (x + xspeed) / ratio );
            
            if(ballOne.getTranslateX()<= 0 || ballOne.getTranslateX() >= width){
                xspeed *= -1;
                System.out.println("BOB");
            }
            
            
            if (ballOne.getTranslateY() < y ){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(ballOne.getTranslateY() > y){
                ballOne.setTranslateY(y);
            } 
            
            }));
         

         Timeline animationTwo = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode());  
            });
            
            y2 = ballTwo.getY(ballTwo.getTranslateX()) ;
            
            ballTwo.setTranslateY(ballTwo.getTranslateY() + yspeed2);
            ballTwo.setTranslateX(ballTwo.getTranslateX() + xspeed2);
            
            
            if(ballTwo.getTranslateX()<= 0 || ballTwo.getTranslateX() >= width){
                
                //System.out.println("BOB " + ballTwo.getTranslateX() + " width: " + width);
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
         
         
         
        ParallelTransition pie = new ParallelTransition(animation, animationTwo);
         //pie.getChildren().addAll(animation, animationTwo);
         pie.setCycleCount(Timeline.INDEFINITE);
         pie.play();
         
         
         pane.getChildren().addAll(ballOne, ballTwo);
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
                
                } 
    }
    
    public void backGroundSetup(Pane pane){
        
        BackgroundImage myBI= new BackgroundImage(new Image("Pictures/CloudBackground.JPG", width, height, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        pane.setBackground(new Background(myBI));
    }
    
   public void frontGroundSetup(Pane pane){
       ratio = 1200 / width;
       for (int i = 0; i < width; i++){
            rect = new Rectangle();
            rect.setTranslateX(i / ratio);
            rect.setTranslateY(MapGeneration.getY(i, ratio));
            rect.setWidth(0.5);
            rect.setHeight(height - MapGeneration.getY(i, ratio));
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
