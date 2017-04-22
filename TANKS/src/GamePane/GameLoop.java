/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import Tanks.Tanks;
import Tanks.TanksAnimation;
import java.util.Timer;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

/**
 *
 * @author willi
 * 
 * 
 * Current known issues: the animation is not stopped when paused if time correctly (Fixed, Updated to AnimationTimer)
 */
public class GameLoop extends AnimationTimer{
    
    private boolean newTurn = true;
    private boolean endTurn = false;
    private boolean launchInitiated = false;
    private int launchWeaponDelay = 0;
    private int launchWeaponDelayCounter = 0;
    private int indexOfCurrentPlayerTurn = 0;
    double gravity;
    double maxPos;
    double minPos;
    double initialPosition;
    Tanks[] tanksArrayUsed;
    TanksAnimation tanksAnimation;
    Timeline[] tanksAnimationArrayUsed;
    
    public GameLoop(TanksAnimation tanksAnimation, Timeline[] tanksAnimationArrayUsed, Tanks[] tanksAraryused){
        this.tanksAnimation = tanksAnimation;
        this.tanksAnimationArrayUsed = tanksAnimationArrayUsed;
        this.tanksArrayUsed = tanksAraryused;
        this.gravity = tanksAnimation.getMapGeneration().getGravity();
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
    
    private void fireWeapon(Tanks tank){
        //TODO Implement this
        bestWeapon();
        System.out.println("AI Fire Weapon " + tank.getImagePath());
        
        tanksArrayUsed[indexOfCurrentPlayerTurn].getCannon().setAICannonAngle(angleToShoot(tank));
        //tanksAnimation.weaponSetup(tanksArrayUsed[indexOfCurrentPlayerTurn], Math.random());
        
        tanksAnimation.keyPressed(KeyCode.SPACE, 
                tanksArrayUsed[indexOfCurrentPlayerTurn], 
                tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
        
        //Times 60 because the thread is called 60 times per second
        launchWeaponDelay = (int) ((1 + Math.random() * 4) * (Math.random() * 61));
        System.out.println(launchWeaponDelay / 60);
        launchInitiated = true;
        
    }
    
    private void bestWeapon(){
        //TODO Implement this
        //This will be implemented once the weapon store is fully implemented
    }
    
    private void moveToClosestTank(){
        //TODO Implement this
        double[] tanksDistance = new double[tanksArrayUsed.length];
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
                    //System.out.println(tanksDistance[indexOfClosestTank]);
                }
            }
            //System.out.println(tanksDistance[i]);
        }
        
         //System.out.println("AI Leaves Nearest Tank");
         
         //Must be able to orient themselves
        if(tanksDistance[indexOfClosestTank] < 250){
            if(tanksArrayUsed[indexOfClosestTank].getTranslateX() < tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX()){
                tanksArrayUsed[indexOfCurrentPlayerTurn].flipTexture();
                tanksArrayUsed[indexOfCurrentPlayerTurn].getCannon().flipTexture();
        }
            else{
                tanksArrayUsed[indexOfCurrentPlayerTurn].normalTexture();
                tanksArrayUsed[indexOfCurrentPlayerTurn].getCannon().normalTexture();
        }
            fireWeapon(tanksArrayUsed[indexOfClosestTank]);
        }
        else if(tanksArrayUsed[indexOfClosestTank].getTranslateX() < tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX()){
            tanksAnimation.keyPressed(KeyCode.LEFT, 
                    tanksArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
            System.out.println("AI Move Left");
        }
        else{
            tanksAnimation.keyPressed(KeyCode.RIGHT, 
                    tanksArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]); 
            System.out.println("AI Moves Right");
        }
    }
    
    private double angleToShoot(Tanks tank){
        //The initial velocity (v) is set to 0.5 at all time and does not use the real value to allow the human to win sometimes.
        double angleMap = tanksAnimation.getMapGeneration().derivativeFunction(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX());
        double x = -(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() - tank.getTranslateX());
        double y = (tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateY() - tank.getTranslateY());
        double v = 0.5; 
        double g = gravity; 
        double sqrt = (v*v*v*v) - (g*(g*(x*x) + 2*y*(v*v)));
        sqrt = Math.sqrt(sqrt);
        
        if(tanksArrayUsed[indexOfCurrentPlayerTurn].getCannon().isIsImageFlipped()){
            return Math.PI / 2 + Math.atan(((v*v) + sqrt)/(g*x)) + angleMap;
        }
        
        else{
            return Math.atan(((v*v) + sqrt)/(g*x)) + angleMap;
        }
        
        
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
        
        /**
         * Currently the AI is boring to watch.
         * It moves once and then just fires until the end of the game
         */
        //System.out.println("AI Analyzing Turn");
        //Removing the user's ability to input
        tanksAnimation.getPane().setFocusTraversable(false);
        moveToClosestTank();   
    }
    
    public int getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }
    
    @Override
    public void handle(long now) {  
            
            if(tanksAnimation.getHud().getPauseMenu().isGamePaused() || tanksAnimation.getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0){
                //System.out.println("Stuck in Pause");
        }
            
            else if(!tanksArrayUsed[indexOfCurrentPlayerTurn].isTankAlive()){
                indexOfCurrentPlayerTurn++;
                   if(indexOfCurrentPlayerTurn >= tanksArrayUsed.length){
                        indexOfCurrentPlayerTurn = 0;
                   }
            }
            else{
            if(newTurn){
                System.out.println("New Turn");
                tanksAnimation.resetSpeed();
                tanksAnimation.setIndexOfCurrentPlayerTurn(indexOfCurrentPlayerTurn);
                tanksAnimation.updateTurn();
                initialPosition = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
                playerTurn(indexOfCurrentPlayerTurn);
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].isIsAI()){
                        aiTurn();
                        //System.out.println("AI Turn");
                        
                    }
                
                else{
                    tanksAnimation.getPane().setFocusTraversable(true);
                }
                newTurn = false;
            }
            
            if(launchInitiated){
                launchWeaponDelayCounter++;
                
                if(launchWeaponDelayCounter == launchWeaponDelay){
                    tanksAnimation.keyPressed(KeyCode.SPACE, 
                        tanksArrayUsed[indexOfCurrentPlayerTurn], 
                        tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                        tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                        tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
                    launchWeaponDelayCounter = 0;
                    launchInitiated = false;
                }
            }
            maxPos = initialPosition + 100;
            minPos = initialPosition - 100;
            
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() >= maxPos || tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() < minPos || tanksAnimation.isShotFired()){
                    endTurn = true;
                }

               if(endTurn){
                   indexOfCurrentPlayerTurn++;
                   if(indexOfCurrentPlayerTurn >= tanksArrayUsed.length){
                        indexOfCurrentPlayerTurn = 0;
                   }
                    tanksAnimation.setShotFired(false);
                    endTurn = false;
                    newTurn = true;
                    
               }
            
            
            if(!moreThanOneTankAlive()){
                //End Game
                System.out.println("Game Over");
                playerTurn(indexOfCurrentPlayerTurn);
                this.stop();
                //System.exit(1);
            }
            }
    }
    
}
