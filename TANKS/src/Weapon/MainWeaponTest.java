/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import MapGeneration.MapGeneration;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author willi
 */
public class MainWeaponTest extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        
        Pane pane = new Pane();
        
        
        WeaponManager weaponManager = new WeaponManager();
        MapGeneration mapGeneration = new MapGeneration(200, 200, 500);
        //pane.getChildren().add(weaponManager.getWeaponFromWeaponManager(0));
        WeaponAnimation weaponAnimation = new WeaponAnimation(weaponManager.getWeaponFromWeaponManager(0), mapGeneration, pane);
        Scene scene = new Scene(pane);
        
       
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setResizable(false);
        
        primaryStage.getIcons().add(new Image("Texture/Tanks/USA/Body/Green_Tank_(100x100).png"));
        primaryStage.setTitle("Tanks");
        
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
