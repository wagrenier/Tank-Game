/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

/**
 *
 * @author Cedrik Dubois
 */
public class Main extends Application {
    MainMenu mainMenu = new MainMenu();
    PlayerMenu playerMenu = new PlayerMenu();
    CountryMenu countryMenu = new CountryMenu();
    
    private static int numberOfPlayers;
    private static int playerCount = 1;
    
    private static int paneCount = 0;
    private ArrayList<Pane> paneList = new ArrayList<>();
    
    @Override
    public void start(Stage stage) {
        
        paneList.add(mainMenu);
        paneList.add(playerMenu);
        paneList.add(countryMenu);
        
        Scene scene = new Scene(mainMenu);
        stage.setScene(scene);
        
        stage.show();
        
        
        mainMenu.getPlayBtn().setOnMouseClicked(e -> {
            paneCount++;
            scene.setRoot(paneList.get(paneCount));
        });
        
        playerMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            scene.setRoot(paneList.get(paneCount));
        });
        
        playerMenu.getNextBtn().setOnMouseClicked(e -> {
            paneCount++;
            scene.setRoot(paneList.get(paneCount));
            numberOfPlayers = playerMenu.getNumberOfPlayers();
        });
        /*
        countryMenu.getNextBtn().setOnMouseClicked(e -> {
            if (playerCount <= numberOfPlayers){
                countryMenu.resetPane(playerCount);
                playerCount++;
            }
            else{
                System.out.println("Map Selection Menu");
            }
        });
        */
        countryMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            scene.setRoot(paneList.get(paneCount));
        });
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
