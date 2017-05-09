/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import HUD.HUD;
import Tanks.TanksAnimation;
import MapGeneration.MapGeneration;
import Sounds.SoundLib;
import classes.Player;
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
 *
 * @author MSI
 */
public class GamePane extends Pane implements Serializable{
    
    private SoundLib sounds;
    
    private int numOfPlayers;
    private int mapIndex;
    private double width = 1200;
    private double height = 800;
    private transient TanksAnimation tanksAnimation;
    private MapGeneration mapGeneration;
    private ArrayList<Player> playerArrayList = new ArrayList<>();
    private transient Timeline[] tanksAnimationArrayUsed;
    private transient GameLoop gameLoop;
    
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
    public void gameLoop(int currentPlayer){
        gameLoop = new GameLoop(tanksAnimation, tanksAnimationArrayUsed, tanksAnimation.getTanksArrayUsed(), currentPlayer);
        gameLoop.start();
    }
    
    public void paneSetup(GamePane pane, int currentPlayer){
        tanksSetup(pane, currentPlayer);
    }
    
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
    
    public void tanksSetup(GamePane pane, int currentPlayer){
        tanksAnimation = new TanksAnimation(mapGeneration, this, numOfPlayers, playerArrayList, currentPlayer, sounds);  
        tanksAnimationArrayUsed = tanksAnimation.getTanksAnimationArrayUsed();
    }
    
    public void backGroundSetup(GamePane pane, String backGroundPath){
        //Setting the background of the pane
        BackgroundImage myBI= new BackgroundImage(new Image(backGroundPath, width, height, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
          
        pane.setBackground(new Background(myBI));
    }
    
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
    
    public HUD getHUD(){
        return tanksAnimation.getHud();
    }

    public void setTanksAnimation(TanksAnimation tanksAnimation) {
        this.tanksAnimation = tanksAnimation;
    }
    
    public TanksAnimation getTanksAnimation() {
        return tanksAnimation;
    }
    
    public void setPlayerArrayList(ArrayList<Player> playerArrayList){
        this.playerArrayList = playerArrayList;
    }
    
    public void addPlayer(Player player){
        playerArrayList.add(player);
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }
}