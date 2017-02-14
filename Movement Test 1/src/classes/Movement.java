/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.application.Application;
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

/**
 *
 * @author Cedrik Dubois
 */
public class Movement extends Application {
    
    MapGeneration map = new MapGeneration();
    Rectangle rect;
    
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        
        paneSetup(pane);
        
        primaryStage.setScene(new Scene(pane, 1200, 800));
        primaryStage.show();
        
        pane.requestFocus();
    }
    
    public void paneSetup(Pane pane){
        frontGroundSetup(pane);
        backGroundSetup(pane);
        
        MovingBall ballOne = new MovingBall(pane, 1);
        MovingBall ballTwo = new MovingBall(pane, 2);
                
        pane.getChildren().addAll(ballOne, ballTwo);
        ballOne.start();
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
