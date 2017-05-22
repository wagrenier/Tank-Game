/****************************************************************
 *  File: GamePane.java
 *  Description: This object contains all the necessary elements for the game's visuals. It implements serializable so that it can be written directly in a file. This pane is where the tanks are and most of the game, except the hud. 
 *     Date    03/02/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package GamePane;

import HUD.HUD;
import Tanks.TanksAnimation;
import MapGeneration.MapGeneration;
import Sounds.SoundLib;
import PlayerSettings.Player;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This object contains all the necessary elements for the game's visuals. It implements serializable so that it can be written directly in a file. This pane is where the tanks are and most of the game, except the hud.
 * @author William Adam-Grenier
 */
public class GamePane extends Pane implements Serializable{
    /**The sound effects for the game*/
    private transient SoundLib sounds;
    /**The number of players in this game*/
    private int numOfPlayers;
    /**The index of the map chosen*/
    private int mapIndex;
    /**The width of the pane*/
    private int width = 1200;
    /**The height of the pane*/
    private int height = 800;
    /**The tanks animation of this game*/
    private transient TanksAnimation tanksAnimation;
    /**The map generated for this game*/
    private MapGeneration mapGeneration;
    /**The array list of the players*/
    private ArrayList<Player> playerArrayList = new ArrayList<>();
    /**The animation of the tanks that are actually used for this game*/
    private transient Timeline[] tanksAnimationArrayUsed;
    /**The game's loop, contains all the logic of the game*/
    private transient GameLoop gameLoop;
    
    /**
     * Constructor
     * @param numOfPlayers
     * @param playerArrayList
     * @param mapGeneration
     * @param currentPlayer
     * @param sounds
     */
    public GamePane(int numOfPlayers, ArrayList<Player> playerArrayList, MapGeneration mapGeneration, int currentPlayer, SoundLib sounds){
        this.mapGeneration = mapGeneration;
        this.mapIndex = this.mapGeneration.getMapIndex();
        this.playerArrayList = playerArrayList;
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.numOfPlayers = numOfPlayers;
        this.sounds = sounds;
        mapSetupRestoredMapGeneration(this);
        paneSetup(this, currentPlayer);   
        gameLoop(currentPlayer);
    }
    
    /**
     *Constructor
     * @param numOfPlayers
     * @param playerArrayList
     * @param mapIndex
     * @param sounds
     */
    public GamePane(int numOfPlayers, ArrayList<Player> playerArrayList, int mapIndex, SoundLib sounds){
        this.playerArrayList = playerArrayList;
        this.mapIndex = mapIndex;
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.numOfPlayers = numOfPlayers;
        this.sounds = sounds;
        mapSetupNewMapGeneration(this);
        paneSetup(this, 0);   
        gameLoop(0);
    }
    
    //The game's main loop put in a thread because otherwise the program hangs in here

    /**
     *Initializes the game's loop
     * @param currentPlayer
     */
    public void gameLoop(int currentPlayer){
        gameLoop = new GameLoop(tanksAnimation, tanksAnimationArrayUsed, tanksAnimation.getTanksArrayUsed(), currentPlayer);
        gameLoop.start();
    }
    
    /**
     *Sets up the pane of the game
     * @param pane
     * @param currentPlayer
     */
    public void paneSetup(GamePane pane, int currentPlayer){
        tanksSetup(pane, currentPlayer);
    }
    
    /**
     *Generates a new map for the game
     * @param pane
     */
    public void mapSetupNewMapGeneration(GamePane pane){
        switch(mapIndex){
            case 0:{
                //Desert Map
                this.mapGeneration = new MapGeneration(500, 100, 500, 0.0005, 0);
                frontGroundSetup(pane, "Pictures/Frontgrounds/Desert Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Desert Background.png");
            }break;
            
            case 1:{
                //Mountain Map
                this.mapGeneration = new MapGeneration(600, 125, 500, 0.0005, 1);
                frontGroundSetup(pane, "Pictures/Frontgrounds/Mountain Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Mountain Background.png");
            }break;
            
            case 2:{
                //Space Map
                this.mapGeneration = new MapGeneration(600, 75, 600, 0.0001, 2);
                frontGroundSetup(pane, "Pictures/Frontgrounds/Space Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Space Background.png");
            }break;
                     
            case 3:{

                //Snow Map
                this.mapGeneration = new MapGeneration(600, 50, 500, 0.0005, 3);
                frontGroundSetup(pane, "Pictures/Frontgrounds/Snow Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Snow Background.png");
            }break;
        }
        
    }
    
    /**
     *Restores a map from a previous game/save
     * @param pane
     */
    public void mapSetupRestoredMapGeneration(GamePane pane){
        switch(mapIndex){
            case 0:{
                //Desert Map
                frontGroundSetup(pane, "Pictures/Frontgrounds/Desert Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Desert Background.png");
            }break;
            
            case 1:{
                //Mountain Map
                frontGroundSetup(pane, "Pictures/Frontgrounds/Mountain Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Mountain Background.png");
            }break;
            
            case 2:{
                //Space Map
                frontGroundSetup(pane, "Pictures/Frontgrounds/Space Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Space Background.png");
            }break;
            
            case 3:{
                //Snow Map
                frontGroundSetup(pane, "Pictures/Frontgrounds/Snow Frontground.png");
                backGroundSetup(pane, "Pictures/Backgrounds/Snow Background.png");
            }break;
        }
        
    }
    
    /**
     *Sets up the tanks that are going to be used in this game
     * @param pane
     * @param currentPlayer
     */
    public void tanksSetup(GamePane pane, int currentPlayer){
        tanksAnimation = new TanksAnimation(mapGeneration, this, numOfPlayers, playerArrayList, currentPlayer, sounds);  
        tanksAnimationArrayUsed = tanksAnimation.getTanksAnimationArrayUsed();
    }
    
    /**
     *Sets up the background for the right map
     * @param pane
     * @param backGroundPath
     */
    public void backGroundSetup(GamePane pane, String backGroundPath){
        //Setting the background of the pane
        BackgroundImage myBI= new BackgroundImage(new Image(backGroundPath, width, height, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
          
        pane.setBackground(new Background(myBI));
    }
    
    /**
     *Sets up the hills of the map generated by the object MapGeneration
     * @param pane
     * @param frontGroundImagePath
     */
    public void frontGroundSetup(GamePane pane, String frontGroundImagePath){
       Image image = new Image(frontGroundImagePath);
       PixelReader pixelReader = image.getPixelReader();
       WritableImage writeImage = new WritableImage(1200, 800);
       PixelWriter writer = writeImage.getPixelWriter();
       //Writing to the image so that the texture for the hill is apllied only underneath it
       for (int i = 0; i < width; i++){
           for(int k = 0; k < height; k++){
               if(k >= mapGeneration.getY(i)){
                   writer.setColor(i, k, pixelReader.getColor(i, k));
               }
               else{
                   writer.setColor(i, k, Color.TRANSPARENT);
               } 
           }
        }
       this.getChildren().add(new ImageView(writeImage));
   }
    
    /**
     *
     * @return
     */
    public HUD getHUD(){
        return tanksAnimation.getHud();
    }

    /**
     *
     * @param tanksAnimation
     */
    public void setTanksAnimation(TanksAnimation tanksAnimation) {
        this.tanksAnimation = tanksAnimation;
    }
    
    /**
     *
     * @return
     */
    public TanksAnimation getTanksAnimation() {
        return tanksAnimation;
    }
    
    /**
     *
     * @param playerArrayList
     */
    public void setPlayerArrayList(ArrayList<Player> playerArrayList){
        this.playerArrayList = playerArrayList;
    }
    
    /**
     *
     * @param player
     */
    public void addPlayer(Player player){
        playerArrayList.add(player);
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    /**
     *
     * @return
     */
    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    /**
     *
     * @return
     */
    public GameLoop getGameLoop() {
        return gameLoop;
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
    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    /**
     *
     * @param numOfPlayers
     */
    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    /**
     *
     * @return
     */
    public int getMapIndex() {
        return mapIndex;
    }

    /**
     *
     * @param mapIndex
     */
    public void setMapIndex(int mapIndex) {
        this.mapIndex = mapIndex;
    }

    /**
     *
     * @return
     */
    public Timeline[] getTanksAnimationArrayUsed() {
        return tanksAnimationArrayUsed;
    }

    /**
     *
     * @param tanksAnimationArrayUsed
     */
    public void setTanksAnimationArrayUsed(Timeline[] tanksAnimationArrayUsed) {
        this.tanksAnimationArrayUsed = tanksAnimationArrayUsed;
    }
    
    
}