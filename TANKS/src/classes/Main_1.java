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
 * @author Cedrik Dubois
 */
public class Main_1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        MainMenuManagerPane mainMenuManagerPane = new MainMenuManagerPane();
        
        Scene scene = new Scene(mainMenuManagerPane, 1200, 950);
        
        primaryStage.setScene(scene);
        
        
        primaryStage.show();
        
        
        primaryStage.getIcons().add(new Image("Texture/Tanks/USA/Body/Green_Tank_(100x100).png"));
        primaryStage.setTitle("Tanks");
        
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1200);
        //primaryStage.setMaxWidth(1200);
        //primaryStage.setMaxHeight(1110);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}