/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import PlayerSettings.Player;
import GamePane.GamePane;
import LoadFunction.LoadFunction;
import Sounds.SoundLib;
import java.util.ArrayList;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class MainMenuManagerPane extends Pane{
    
    
    private static int numberOfPlayers;
    private static int playerCount = 0;
    private static int paneCount = 0;
    
    private SoundLib sounds = new SoundLib();
    private GamePane gamePane;
    private LoadFunction load;
    private BorderPane borderPane = new BorderPane();
    private MainMenu mainMenu = new MainMenu(sounds);
    private PlayerMenu playerMenu = new PlayerMenu(sounds);
    private CountryMenu countryMenu = new CountryMenu(sounds);
    private MapMenu mapMenu = new MapMenu(sounds);
    private ArrayList<Pane> paneList = new ArrayList<>();
    private ArrayList<Player> playerList = new ArrayList<>();
    private Background chosenMap;
    
    public MainMenuManagerPane() {
        paneList.add(mainMenu);
        paneList.add(playerMenu);
        paneList.add(countryMenu);
        paneList.add(mapMenu);
        
        //start background music and set on loop
        sounds.getBackgroundMusic().setOnEndOfMedia(new Runnable() {
            public void run() {
                sounds.getBackgroundMusic().seek(Duration.ZERO);
            }
        });
        sounds.getBackgroundMusic().play();
        sounds.setSoundPlaying(true);
        
        
        
        
        this.getChildren().add(mainMenu);
        cursorSetting(this);
        
        mainMenu.getLoadBtn().setOnMouseClicked(e -> {
            
            this.getChildren().clear();
            resetLoad();
            //gamePane.getTanksAnimation().setIndexOfCurrentPlayerTurn(currentTurn[0]);
            this.setMinSize(1200, 950);
            this.setMaxSize(1200, 950);
            
            
            
            gamePane.setFocusTraversable(true);
            this.getChildren().add(borderPane);
            this.autosize();
        });
        
        mainMenu.getPlayBtn().setOnMouseClicked(e -> {
            paneCount++;
            this.getChildren().add(paneList.get(paneCount));
            cursorSetting(paneList.get(paneCount));
            //scene.setRoot(paneList.get(paneCount));
        });
        
        playerMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            this.getChildren().clear();
            this.getChildren().add(paneList.get(paneCount));
            cursorSetting(paneList.get(paneCount));
           // scene.setRoot(paneList.get(paneCount));
        });
        
        playerMenu.getNextBtn().setOnMouseClicked(e -> {
            paneCount++;
            this.getChildren().clear();
            this.getChildren().add(paneList.get(paneCount));
            //scene.setRoot(paneList.get(paneCount));
            cursorSetting(paneList.get(paneCount));
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
                this.getChildren().clear();
                this.getChildren().add(paneList.get(paneCount));
                cursorSetting(paneList.get(paneCount));
                //scene.setRoot(paneList.get(paneCount));
                mapMenu.setPlayerList(playerList);
                mapMenu.setPlayers();
                
                
            }
            
            registerPlayer(false);
            playerCount++;
            countryMenu.refreshPane(playerCount + 1);
        });
        
        countryMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            this.getChildren().clear();
            this.getChildren().add(paneList.get(paneCount));
            //scene.setRoot(paneList.get(paneCount));
            cursorSetting(paneList.get(paneCount));
            //Reset variables for countryMenu
            playerCount = 0;
            playerList.clear();
            countryMenu.refreshPane(1);
            countryMenu.resetPane();
        });
        
        mapMenu.getPlayBtn().setOnMouseReleased(e ->{
            mapMenu.getPlayBtn().setImage(mapMenu.getPlayBtnHover());
            this.getChildren().clear();
            
            this.setMinSize(1200, 950);
            this.setMaxSize(1200, 950);
            
            
            
            gamePane = new GamePane(numberOfPlayers, playerList, mapMenu.getChosenMap(), sounds);
            gamePane.setPlayerArrayList(playerList);
            
            
            
            borderPane.setCenter(gamePane);
            borderPane.setTop(gamePane.getHUD());
            cursorSetting(paneList.get(paneCount));
            gamePane.setFocusTraversable(true);
            this.getChildren().add(borderPane);
            this.autosize();
            System.out.println(this.getMinWidth() + ", " + this.getMinHeight());
        });
        
        mapMenu.getBackBtn().setOnMouseClicked(e -> {
            paneCount--;
            paneCount--;
            this.getChildren().clear();
            this.getChildren().add(paneList.get(paneCount));
            //scene.setRoot(paneList.get(paneCount));
            
            //Reset variables for mapMenu
            mapMenu.resetPane();
            cursorSetting(paneList.get(paneCount));
            //Reset variables for countryMenu
            playerCount = 0;
            playerList.clear();
            countryMenu.refreshPane(1);
            countryMenu.resetPane();
        });
    }
    
    private void resetLoad(){
        load = new LoadFunction();
            double[][] array = load.getTanksArray();
            boolean[] array2 = load.getDirection();
            boolean[] dead = load.getDead();
            int[] tanksHP = load.getTanksHP();
            int[] currentTurn = load.getIndexOfCurrentPlayerTurn();
            double[] maxPix = load.getMaxPixMove();
            int[] numOfTurns = load.getNumOfTurnArray();
            gamePane = new GamePane(load.getGamePane().getPlayerArrayList().size(), load.getGamePane().getPlayerArrayList(), load.getGamePane().getMapGeneration(), currentTurn[0], sounds);
            //gamePane.setTanksAnimation(load.getTanksAnimation());
            borderPane.setCenter(gamePane);
            borderPane.setTop(gamePane.getHUD());
            
            gamePane.getTanksAnimation().resetMaxPixelMove(maxPix);
            
            gamePane.getTanksAnimation().resetTankPositionSave(array);
            gamePane.getTanksAnimation().resetTankOrientationSave(array2);
            gamePane.getTanksAnimation().resetTankHPSave(tanksHP);
            gamePane.getTanksAnimation().resetWhoIsDead(dead);
            gamePane.getGameLoop().resetNumOfTurn(numOfTurns);
            if(load.isMinePlaced()){
                double[][] mineLocation = load.getMineLocation();
                gamePane.getTanksAnimation().resetMineLocation(mineLocation);
            }
    }
    
    private void cursorSetting(Pane pane){
        pane.setCursor(new ImageCursor(new Image("Texture/Cursor/Cursor.png")));
    }
    
    private void registerPlayer(boolean lastPlayer){
        
        playerList.get(playerCount).setName(countryMenu.getPlayerName());
        playerList.get(playerCount).setTeam(countryMenu.getCountry());
        if (countryMenu.isAI())
            playerList.get(playerCount).setIsAI(true);
        else
            playerList.get(playerCount).setIsAI(false);
        
        if (!lastPlayer)
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

    public SoundLib getSounds() {
        return sounds;
    }

    public void setSounds(SoundLib sounds) {
        this.sounds = sounds;
    }

    public GamePane getGamePane() {
        return gamePane;
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public LoadFunction getLoad() {
        return load;
    }

    public void setLoad(LoadFunction load) {
        this.load = load;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Background getChosenMap() {
        return chosenMap;
    }

    public void setChosenMap(Background chosenMap) {
        this.chosenMap = chosenMap;
    }
    
    
    
}