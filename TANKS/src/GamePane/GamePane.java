/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import HUD.HUD;
import Tanks.TanksAnimation;
import MapGeneration.MapGeneration;
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
import javafx.scene.shape.Rectangle;

/**
 *
 * @author MSI
 */
public class GamePane extends Pane implements Serializable{
    
    private int numOfPlayers;
    private double width = 1200;
    private double height = 800;
    private transient TanksAnimation tanksAnimation;
    MapGeneration mapGeneration = new MapGeneration(450, 200, 500, 0.0005);
    private ArrayList<Player> playerArrayList = new ArrayList<>();
    private transient Timeline[] tanksAnimationArrayUsed;
    private transient GameLoop gameLoop;
    
    
    public GamePane(int numOfPlayers, ArrayList<Player> playerArrayList, MapGeneration mapGeneration, int currentPlayer){
        this.mapGeneration = mapGeneration;
        this.playerArrayList = playerArrayList;
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.numOfPlayers = numOfPlayers;
        paneSetup(this, currentPlayer);   
        gameLoop(currentPlayer);
    }
    
    public GamePane(int numOfPlayers, ArrayList<Player> playerArrayList){
        this.playerArrayList = playerArrayList;
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.numOfPlayers = numOfPlayers;
        paneSetup(this, 0);   
        gameLoop(0);
    }
    
    //The game's main loop put in a thread because otherwise the program hangs in here
    public void gameLoop(int currentPlayer){
        gameLoop = new GameLoop(tanksAnimation, tanksAnimationArrayUsed, tanksAnimation.getTanksArrayUsed(), currentPlayer);
        gameLoop.start();
    }
    
    public void paneSetup(Pane pane, int currentPlayer){
        frontGroundSetup(pane);
        backGroundSetup(pane);
        tanksSetup(pane, currentPlayer);
    }
    
    public void tanksSetup(Pane pane, int currentPlayer){
        tanksAnimation = new TanksAnimation(mapGeneration, this, numOfPlayers, playerArrayList, currentPlayer);  
        tanksAnimationArrayUsed = tanksAnimation.getTanksAnimationArrayUsed();
    }
    
    public void backGroundSetup(Pane pane){
        
        BackgroundImage myBI= new BackgroundImage(new Image("Pictures/Backgrounds/Background.png", width, height, false, true),
        
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        pane.setBackground(new Background(myBI));
    }
    
    public void frontGroundSetup(Pane pane){
       Image image = new Image("Texture/Menus/MapMenu/Space Map.png");
       PixelReader pixelReader = image.getPixelReader();
       
       
       WritableImage writeImage = new WritableImage(1200, 800);
       PixelWriter writer = writeImage.getPixelWriter();
       
       Rectangle rect;
       double yLocation = 0;
       
       for (int i = 0; i < width; i++){
           for(int k = 0; k < height; k++){
               if(k >= mapGeneration.getY(i)){
                   writer.setColor(i, k, pixelReader.getColor(i, k));
               }
               else{
                   writer.setColor(i, k, Color.TRANSPARENT);
               }
               
               
               
           }
           /**
            rect = new Rectangle();
            yLocation = mapGeneration.getY(i);
            
            //rect.setStyle();
            rect.setTranslateX(i);
            rect.setHeight(height - yLocation);
            rect.setTranslateY(yLocation);
            
            rect.setWidth(.25);
            
            //rect.setFill(Color.TRANSPARENT);
            //rect.setStroke(Color.GREEN);
            pane.getChildren().add(rect);
            */
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
