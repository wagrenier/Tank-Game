/****************************************************************
 *  File: Main.java
 *  Description: The main class of the program. Launches the application Tanks
 *  Program Description: Tanks is a single/multi player game that offers a vast array of weapons and items to defeat the opponent. 
 *    History:
 *     Date    02/15/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier & Cedrik Dubois          
 *
 ****************************************************************/
package classes;

import UI.MainMenuManagerPane;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * The main class of the program. Launches the application Tanks
 */
public class Main extends Application {
    /**Method that launches the app*/
    @Override
    public void start(Stage primaryStage) {
        
        MainMenuManagerPane mainMenuManagerPane = new MainMenuManagerPane();
        
        Scene scene = new Scene(mainMenuManagerPane, 1200, 950);
        
        primaryStage.setScene(scene);
        
        
        primaryStage.show();
        
        Image icon = new Image("Texture/Icons/icon.png");
        
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Tanks");
        primaryStage.setMinHeight(750);
        primaryStage.setMinWidth(1200);

        primaryStage.setResizable(false);

        primaryStage.sizeToScene(); 
    }
    
    /**
     * Method that launches the program
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    } 
}
