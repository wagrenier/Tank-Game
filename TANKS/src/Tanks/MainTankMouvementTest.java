/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;



import GamePane.GamePane;
import HUD.HUD;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
;

/**
 *
 * @author Cedrik Dubois
 */ 
public class MainTankMouvementTest extends Application { 
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane gamePane = new BorderPane();
        Scene scene = new Scene(gamePane);
        
        
        GamePane pane = new GamePane();
        
        
        HUD hud = new HUD(scene);
        
        gamePane.setCenter(pane);
        gamePane.setTop(hud);
        
       
        
       
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setResizable(false);
        
        primaryStage.getIcons().add(new Image("Texture/Tanks/USA/Body/Green_Tank_(100x100).png"));
        primaryStage.setTitle("Tanks");
        
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1200);
        primaryStage.setMaxWidth(1200);
        primaryStage.setMaxHeight(950);
        pane.requestFocus();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}
