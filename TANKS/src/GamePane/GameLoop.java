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
    
    public boolean waitUntilEndOfTurn(int indexOfCurrentPlayerTurn){
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
    
    public boolean moreThanOneTankAlive(){
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
    
    @Override
    public void run() {
        
        
            while(moreThanOneTankAlive()){
            
            tanksAnimation.resetSpeed();
            tanksAnimation.setIndexOfCurrentPlayerTurn(indexOfCurrentPlayerTurn);
            
            //For accessing the main javaFX thread without the program crashing
            Platform.runLater(() -> {
                tanksAnimation.updateTurn();
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
