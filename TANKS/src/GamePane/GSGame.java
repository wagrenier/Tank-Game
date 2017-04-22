/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import HUD.HUD;
import MapGeneration.MapGeneration;
import Tanks.Tanks;
import Tanks.TanksAnimation;
import classes.Player;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author willi
 */
public class GSGame extends GameState{
    
    private int numOfPlayers;
    private double width = 1200;
    private double height = 800;
    private TanksAnimation tanksAnimation;
    MapGeneration mapGeneration = new MapGeneration(450, 100, 500);
    private ArrayList<Player> playerArrayList = new ArrayList<>();
    private Timeline[] tanksAnimationArrayUsed;
    
    
    
    
    public GSGame(int numOfPlayers, ArrayList<Player> playerArrayList){
        this.playerArrayList = playerArrayList;
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.numOfPlayers = numOfPlayers;
        paneSetup(this);   
        gameLoop();
    }
    
    //The game's main loop put in a thread because otherwise the program hangs in here
    public void gameLoop(){
        //gameLoop = new GameLoop(tanksAnimation, tanksAnimationArrayUsed, tanksAnimation.getTanksArrayUsed());
        //gameLoop.start();
    }
    
    public void paneSetup(Pane pane){
        frontGroundSetup(pane);
        backGroundSetup(pane);
        tanksSetup(pane);
    }
    
    public void tanksSetup(Pane pane){
        tanksAnimation = new TanksAnimation(mapGeneration, this, numOfPlayers, playerArrayList);  
        tanksAnimationArrayUsed = tanksAnimation.getTanksAnimationArrayUsed();
    }
    
    public void backGroundSetup(Pane pane){
        
        BackgroundImage myBI= new BackgroundImage(new Image("Pictures/Backgrounds/Background.png", width, height, false, true),
        
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        pane.setBackground(new Background(myBI));
    }
    
    public void frontGroundSetup(Pane pane){
       
       Rectangle rect;
       double yLocation = 0;
       
       for (int i = 0; i < width; i++){
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
            
        }

   }
    
    public HUD getHUD(){
        return tanksAnimation.getHud();
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
    
    
    private void playerTurn(int indexOfCurrentPlayer){
        for(int i = 0; i < tanksAnimationArrayUsed.length; i++){
            if(i == indexOfCurrentPlayer && tanksArrayUsed[i].isTankAlive()){
                tanksAnimationArrayUsed[i].play();
            }
            else if(tanksArrayUsed[i].isTankAlive()){
                tanksAnimationArrayUsed[i].pause();
            }
        }
    }
    
    private boolean waitUntilEndOfTurn(int indexOfCurrentPlayerTurn){
        /*
        double position = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
        
        if(position - initialPosition >= 100 || position - initialPosition <= 100){
            return false;
        }
        */
        
        if(!tanksArrayUsed[indexOfCurrentPlayerTurn].isTankAlive()){
           return false; 
        }
        
        if(tanksAnimation.isShotFired()){
            return false;
        }
        
        return true;
    }
    
    private boolean moreThanOneTankAlive(){
        int numOfTanksAlive = 0;
        
        for(int i = 0; i < tanksArrayUsed.length; i++){
            if(tanksArrayUsed[i].isTankAlive()){
                numOfTanksAlive++;
            }
            //System.out.println(numOfTanksAlive);
            if(numOfTanksAlive == 2){
                //System.out.println(numOfTanksAlive);
                return true;
            }
            
        }
        return false;
    }
    
    private void fireWeapon(){
        //TODO Implement this
        bestWeapon();
        //angleToShoot()
        
            tanksArrayUsed[indexOfCurrentPlayerTurn].getCannon().setAICannonAngle(angleToShoot());
            tanksAnimation.weaponSetup(tanksArrayUsed[indexOfCurrentPlayerTurn], Math.random());
        
        
        //tanksAnimation.keyPressed(KeyCode.SPACE, tanksArrayUsed[indexOfCurrentPlayerTurn], tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
    }
    
    private void bestWeapon(){
        //TODO Implement this
        //This will be implemented once the weapon store is fully implemented
    }
    
    private void moveToClosestTank(){
        //TODO Implement this
        double[] tanksDistance = new double[tanksAnimation.numOfTanksAlive()];
        int indexOfClosestTank = 0;
        for(int i = 0; i < tanksArrayUsed.length; i++){
            //To prevent the AI from chosing a dead tank
            if(!tanksArrayUsed[i].isTankAlive()){
                tanksDistance[i] = 100000;
            }
            else if(tanksArrayUsed[i] == tanksArrayUsed[indexOfCurrentPlayerTurn]){
                //Setting a value higher than pane so that the program does not return the tank itself as the closest tank
                tanksDistance[i] = 100000;
            }
            else{
                tanksDistance[i] = distanceToTanks(tanksArrayUsed[indexOfCurrentPlayerTurn], tanksArrayUsed[i]);
                if(tanksDistance[indexOfClosestTank] > tanksDistance[i]){
                    indexOfClosestTank = i;
                }
                //System.out.println(tanksDistance[i]);
            }
        }
        if(tanksDistance[indexOfClosestTank] < 500){
            fireWeapon();
        }
        else if(tanksArrayUsed[indexOfClosestTank].getTranslateX() < tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX()){
            //tanksArrayUsed[indexOfCurrentPlayerTurn].setxSpeed(-.1);
            
            tanksAnimation.keyPressed(KeyCode.LEFT, tanksArrayUsed[indexOfCurrentPlayerTurn], tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
        
            
        }
        else{
            //tanksArrayUsed[indexOfCurrentPlayerTurn].setxSpeed(.1);
            
            tanksAnimation.keyPressed(KeyCode.RIGHT, tanksArrayUsed[indexOfCurrentPlayerTurn], tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
        
            
        }
        
        
    }
    
    private double angleToShoot(){
        //TODO Implement this
        
        return 1;
    }
    
    //tank is the current player's tank and other tank is another tank in the pane
    private double distanceToTanks(Tanks tank, Tanks otherTank){
        //TODO Implement this
        return Math.sqrt(Math.pow((tank.getTranslateX() - otherTank.getTranslateX()), 2) + Math.pow((tank.getTranslateY() - otherTank.getTranslateY()), 2));
    }
    
    private void aiTurn(){
        /**
         * 
         * 1.0: The AI detects if someone is nearby by calculating the approx distance to the tanks
         * 1.2: If someone is nearby, the AI moves closer to that tank
         * 2.0: The AI calculates the angle required to hit the tank
         * 2.1: The AI looks for the best weapon to use
         * 2.2: The AI fires the weapon and the turn ends
         * 
         */
        
        moveToClosestTank();
        /*
        if(!(tanksAnimation.getWeaponAnimation() == null))
                while(tanksAnimation.getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0);   */   
    }
    
    public int getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }
    
    
    @Override
    protected void execute(GameLoop loop) {
        
            
                
            
            if(tanksAnimation.getHud().getPauseMenu().isGamePaused()){
                
        }
            
            
            else{
                
                
                
            
            tanksAnimation.resetSpeed();
            tanksAnimation.setIndexOfCurrentPlayerTurn(indexOfCurrentPlayerTurn);
            
            
            
                tanksAnimation.updateTurn();
                
                
                    
            
            
            if(tanksArrayUsed[indexOfCurrentPlayerTurn].isIsAI()){
                        aiTurn();
            }
            playerTurn(indexOfCurrentPlayerTurn);
            
            double initialPosition = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
            double maxPos = initialPosition + 100;
            double minPos = initialPosition - 100;
            //while(waitUntilEndOfTurn(indexOfCurrentPlayerTurn)){
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() >= maxPos || tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() < minPos){
                    //break;
                    //EndTurn
                }
            //}

               
            indexOfCurrentPlayerTurn++;
            if(indexOfCurrentPlayerTurn >= tanksArrayUsed.length)
                indexOfCurrentPlayerTurn = 0;
            
            
            tanksAnimation.setShotFired(false);
            
            if(!moreThanOneTankAlive()){
                System.exit(1);
            }
            
       
            
            }
    }
    
}
