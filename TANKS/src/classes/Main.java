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
public class Main extends Application {
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        GamePane pane = new GamePane();
        Scene scene = new Scene(pane, 1200, 800);
        
       /*
        scene.setOnDragDetected(e ->{
            
            
           width = pane.getWidth();
           //System.out.println(width);
           height = pane.getHeight();
           ratio = 1200 / width;
           ratioHeight = 800 / height;
           
           pane.setScaleX(ratio);
           pane.setScaleY(ratioHeight);
           //pane.resize(width, height);
           
           //ratioHeight = 800 / height;
           //pane.resize(width, height);
           //pane.setLayoutX(pane.getTranslateX() * ratio);
           //pane.setLayoutY(pane.getTranslateY() * ratio);
            
            /**
            pie.stop();
            pane.getChildren().clear();
            pie.getChildren().clear();
            paneSetup(pane);
            movingBallSetup(pane);
            
            
            
        });
        */
       
       
       
        //primaryStage.setFullScreen(true);
        //primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
       primaryStage.setResizable(false);
        
        
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1200);
        
        primaryStage.setMaxWidth(1200);
        primaryStage.setMaxHeight(800);
        pane.requestFocus();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
}
