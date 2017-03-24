/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author willi
 */
public class MainWeaponTest extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        
        GamePane pane = new GamePane();
        
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
