/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import GamePane.GamePane;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author willi
 */
public class MainMenuManagerPane{
    
    GamePane gamePane;
    BorderPane borderPane = new BorderPane();
    private MainMenu mainMenu = new MainMenu();
    private PlayerMenu playerMenu = new PlayerMenu();
    private CountryMenu countryMenu = new CountryMenu();
    private MapMenu mapMenu = new MapMenu();
    private Scene scene;
    private static int numberOfPlayers;
    private static int playerCount = 0;
    private boolean gameLaunch = false;
    
    private static int paneCount = 0;
    private ArrayList<Pane> paneList = new ArrayList<>();
    
    private ArrayList<Player> playerList = new ArrayList<>();

    public MainMenuManagerPane() {
        paneList.add(mainMenu);
        paneList.add(playerMenu);
        paneList.add(countryMenu);
        paneList.add(mapMenu);
        
        scene = new Scene(mainMenu);
        
        
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
            
            
        });
        
        countryMenu.getNextBtn().setOnMouseClicked(e -> {
            /*
            *
            *At last player, register last player and switch roots
            *
            */
            if (playerCount == numberOfPlayers - 1){
                registerPlayer(true);
                paneCount++;
                scene.setRoot(paneList.get(paneCount));
                mapMenu.setPlayerList(playerList);
                mapMenu.setPlayers();
                
                
            }
            
            registerPlayer(false);
            playerCount++;
            countryMenu.refreshPane(playerCount + 1);
        });
        
        countryMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            scene.setRoot(paneList.get(paneCount));
            
            //Reset variables for countryMenu
            playerCount = 0;
            playerList.clear();
            countryMenu.refreshPane(1);
            countryMenu.resetPane();
        });
        
        mapMenu.getPlayBtn().setOnMouseReleased(e ->{
            mapMenu.getPlayBtn().setImage(mapMenu.getPlayBtnHover());
            gameLaunch = true;
            
            gamePane = new GamePane();
            
            borderPane.setCenter(gamePane);
            borderPane.setTop(gamePane.getHUD());
            scene.setRoot(borderPane);
            //System.out.println("Launch");
        });
        
        mapMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            paneCount--;
            scene.setRoot(paneList.get(paneCount));
            
            //Reset variables for mapMenu
            mapMenu.resetPane();
            
            //Reset variables for countryMenu
            playerCount = 0;
            playerList.clear();
            countryMenu.refreshPane(1);
            countryMenu.resetPane();
        });
    }
    
    private void registerPlayer(boolean lastplayer){
        
        playerList.get(playerCount).setName(countryMenu.getPlayerName());
        playerList.get(playerCount).setTeam(countryMenu.getCountry());
        if (!lastplayer)
            countryMenu.removeTeam(countryMenu.getTankCount());
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public PlayerMenu getPlayerMenu() {
        return playerMenu;
    }

    public CountryMenu getCountryMenu() {
        return countryMenu;
    }

    public MapMenu getMapMenu() {
        return mapMenu;
    }

    public Scene getThisScene() {
        return scene;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static int getPaneCount() {
        return paneCount;
    }

    public ArrayList<Pane> getPaneList() {
        return paneList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public boolean isGameLaunch() {
        return gameLaunch;
    }
    
    
    
}
