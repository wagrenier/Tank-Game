/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Cedrik Dubois
 */
public class Movement extends Application {
    Circle circle = new Circle(15);
    
    double xspeed = 0;
    double yspeed = 0;
    double gravity = 0.0005;
    double y;
    
    Rectangle rect;
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setTranslateX(50);
        circle.setTranslateY(200);
        
        for (int i = 0; i < 1200; i++){
            rect = new Rectangle();
            rect.setTranslateX(i);
            rect.setTranslateY(getY(i));
            rect.setWidth(0.5);
            rect.setHeight(800 - getY(i));
            rect.setFill(Color.GREEN);
            rect.setStroke(Color.GREEN);
            pane.getChildren().add(rect);
            
        }
        
        pane.getChildren().add(circle);
        
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            pane.setOnKeyPressed(x -> {
                switch (x.getCode()){
                    case LEFT: {
                        xspeed = -0.25;
                    }break;
                    case RIGHT: {
                        xspeed = 0.25;
                    }break;
                    case UP: {
                        System.out.println("up");
                        yspeed = -0.5;
                    }break;
                    
                }
            });
            y = getY(circle.getTranslateX());
            
            circle.setTranslateY(circle.getTranslateY() + yspeed);
            circle.setTranslateX(circle.getTranslateX() + xspeed);
            if (circle.getTranslateY() < y){
                yspeed += gravity;
            }
            else
                yspeed = 0;
            
            if(circle.getTranslateY()> y){
                circle.setTranslateY(y);
            }
            
            
            
            
            
        }));
        
        
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        
        
        
        primaryStage.setScene(new Scene(pane, 1200, 800));
        primaryStage.show();
        
        pane.requestFocus();
    }
    
    public int getY(double x){
        double a = 5.464 * Math.pow(10, -9);
        double b = -0.000013308;
        double c = 0.01009;
        double d = -2.5108;
        double f = 647.7;
        
        int y = (int)((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + (d * x) + f);
        return y;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
