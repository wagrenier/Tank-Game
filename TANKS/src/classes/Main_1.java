/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

/**
 *
 * @author Cedrik Dubois
 */
public class Main_1 extends Application {
    
    @Override
    public void start(Stage stage) {
        
        MainMenuManagerPane mainMenu = new MainMenuManagerPane();
        stage.setScene(mainMenu.getThisScene());
        
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}