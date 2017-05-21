/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
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
 * @author Cedrik Dubois
 */
public class Main extends Application {
    
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}
