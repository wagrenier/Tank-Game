/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
package UI;

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
    
    /**Sets the number of players in the game*/
    private static int numberOfPlayers;
    /**Sets the count of players that have selected a tank to play with*/
    private static int playerCount = 0;
    /**The index of the current pane being displayed at the current time*/
    private static int paneCount = 0;
    
    /**The sounds of the menu*/
    private SoundLib sounds = new SoundLib();
    
    /**The GamePane of the game, containing all the crucial elements of the game*/
    private GamePane gamePane;
    /**The load function, which loads a previous game from a binary file*/
    private LoadFunction load;
    /**The BorderPane containing the hud and the GamePane*/
    private BorderPane borderPane = new BorderPane();
    /**The MainMenu pane*/
    private MainMenu mainMenu = new MainMenu(sounds);
    /**The PlayerMenu Pane*/
    private PlayerMenu playerMenu = new PlayerMenu(sounds);
    /**The CountryMenu Pane*/
    private CountryMenu countryMenu = new CountryMenu(sounds);
    /**The MapMenu Pane*/
    private MapMenu mapMenu = new MapMenu(sounds);
    /**The array list containing all the panes*/
    private ArrayList<Pane> paneList = new ArrayList<>();
    /**The array list containing all the players*/
    private ArrayList<Player> playerList = new ArrayList<>();
    /**The background of the chosen map*/
    private Background chosenMap;
    
    /**
     * Default no-arg constructor
     */
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
            resetLoad();
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
    
    /**Resets a previous game from a save in a binary file*/
    private void resetLoad(){
        load = new LoadFunction();
        
        if(load.isSuccessfulLoad()){
            this.getChildren().clear();
            this.setMinSize(1200, 950);
            this.setMaxSize(1200, 950);
            
            
            
            
            
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
            
            gamePane.setFocusTraversable(true);
            this.getChildren().add(borderPane);
            this.autosize();
        }
        
        else if(!load.isSuccessfulLoad()){
            
    }
            
    }
    
    /**Sets the image for the cursor for the Scene*/
    private void cursorSetting(Pane pane){
        pane.setCursor(new ImageCursor(new Image("Texture/Cursor/Cursor.png")));
    }
    
    /**Registers and creates a new player and puts it into the ArrayList of players*/
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

    /**
     *
     * @return
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     *
     * @return
     */
    public PlayerMenu getPlayerMenu() {
        return playerMenu;
    }

    /**
     *
     * @return
     */
    public CountryMenu getCountryMenu() {
        return countryMenu;
    }

    /**
     *
     * @return
     */
    public MapMenu getMapMenu() {
        return mapMenu;
    }

    /**
     *
     * @return
     */
    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     *
     * @return
     */
    public static int getPlayerCount() {
        return playerCount;
    }

    /**
     *
     * @return
     */
    public static int getPaneCount() {
        return paneCount;
    }

    /**
     *
     * @return
     */
    public ArrayList<Pane> getPaneList() {
        return paneList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    } 

    /**
     *
     * @return
     */
    public SoundLib getSounds() {
        return sounds;
    }

    /**
     *
     * @param sounds
     */
    public void setSounds(SoundLib sounds) {
        this.sounds = sounds;
    }

    /**
     *
     * @return
     */
    public GamePane getGamePane() {
        return gamePane;
    }

    /**
     *
     * @param gamePane
     */
    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    /**
     *
     * @return
     */
    public LoadFunction getLoad() {
        return load;
    }

    /**
     *
     * @param load
     */
    public void setLoad(LoadFunction load) {
        this.load = load;
    }

    /**
     *
     * @return
     */
    public BorderPane getBorderPane() {
        return borderPane;
    }

    /**
     *
     * @param borderPane
     */
    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    /**
     *
     * @return
     */
    public Background getChosenMap() {
        return chosenMap;
    }

    /**
     *
     * @param chosenMap
     */
    public void setChosenMap(Background chosenMap) {
        this.chosenMap = chosenMap;
    }
    
    
}