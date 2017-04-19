/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import Tanks.Tanks;
import Tanks.TanksAnimation;
import javafx.animation.Timeline;
import javafx.application.Platform;

/**
 *
 * @author willi
 */
public class GameLoop extends Thread{
    
    private int indexOfCurrentPlayerTurn = 0;
    Tanks[] tanksArrayUsed;
    TanksAnimation tanksAnimation;
    Timeline[] tanksAnimationArrayUsed;
    
    public GameLoop(TanksAnimation tanksAnimation, Timeline[] tanksAnimationArrayUsed, Tanks[] tanksAraryused){
        this.tanksAnimation = tanksAnimation;
        this.tanksAnimationArrayUsed = tanksAnimationArrayUsed;
        this.tanksArrayUsed = tanksAraryused;
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
    }
    
    private void bestWeapon(){
        //TODO Implement this
    }
    
    private void moveToClosestTank(){
        //TODO Implement this
    }
    
    private double angleToShoot(){
        //TODO Implement this
        
        return 1;
    }
    
    private double distanceToTanks(){
        //TODO Implement this
        
        return 1;
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
    }
    
    @Override
    public void run() {
        
        
            while(moreThanOneTankAlive()){
            
            tanksAnimation.resetSpeed();
            tanksAnimation.setIndexOfCurrentPlayerTurn(indexOfCurrentPlayerTurn);
            
            //For accessing the main javaFX thread without the program crashing
            Platform.runLater(() -> {
                tanksAnimation.updateTurn();
                
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].isIsAI()){
                        aiTurn();
                    }
                    }
            );
            
            playerTurn(indexOfCurrentPlayerTurn);
            
            double initialPosition = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
            double maxPos = initialPosition + 100;
            double minPos = initialPosition - 100;
            while(waitUntilEndOfTurn(indexOfCurrentPlayerTurn)){
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() >= maxPos || tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() < minPos){
                    break;
                }
            }

               
            indexOfCurrentPlayerTurn++;
            if(indexOfCurrentPlayerTurn >= tanksArrayUsed.length)
                indexOfCurrentPlayerTurn = 0;
            
            
            tanksAnimation.setShotFired(false);
            
            if(!moreThanOneTankAlive()){
                break;
            }
        
        }
        
        System.out.println("Game Over");
        this.stop();
            
        
        
    }

    public int getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }
    
    
}
