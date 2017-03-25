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
    MainMenu mainMenu = new MainMenu();
    PlayerMenu playerMenu = new PlayerMenu();
    CountryMenu countryMenu = new CountryMenu();
    
    private static int numberOfPlayers;
    private static int playerCount = 0;
    
    private static int paneCount = 0;
    private ArrayList<Pane> paneList = new ArrayList<>();
    
    private ArrayList<Player> playerList = new ArrayList<>();
    
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
            
            for (int i = 0; i < numberOfPlayers; i++){
                playerList.add(new Player());
            }
            
            System.out.println(playerList.size());
        });
        
        countryMenu.getNextBtn().setOnMouseClicked(e -> {
            /*
            *
            *At last player, register last player and switch roots
            *
            */
            if (playerCount == numberOfPlayers - 1){
                registerPlayer();
                System.out.println("Switch Menu");
                
                for (int i = 0; i < playerList.size(); i++){
                    System.out.println(playerList.get(i).toString());
                }
            }
            
            registerPlayer();
            playerCount++;
            countryMenu.resetPane(playerCount + 1);
        });
        
        countryMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            scene.setRoot(paneList.get(paneCount));
            
            //Reset variables for countryMenu
            playerCount = 0;
            playerList.clear();
            countryMenu.resetPane(1);
        });
        
    }
    private void registerPlayer(){
        playerList.get(playerCount).setName(countryMenu.getPlayerName());
        playerList.get(playerCount).setTeam(countryMenu.getCountry());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}