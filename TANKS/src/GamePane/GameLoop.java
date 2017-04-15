/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import Tanks.Tanks;
import Tanks.TanksAnimation;
import javafx.animation.Timeline;

/**
 *
 * @author willi
 */
public class GameLoop extends Thread{
    
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
            if(i == indexOfCurrentPlayer){
                tanksAnimationArrayUsed[i].play();
            }
            else{
                tanksAnimationArrayUsed[i].pause();
            }
        }
    }
    
    public boolean waitUntilEndOfTurn(int indexOfCurrentPlayerTurn, double initialPosition){
        /*
        System.out.println(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX());
        if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() - initialPosition >= 100){
            return false;
        }
        
        if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() - initialPosition <= 100){
            return false;
        }
        */
        if(tanksAnimation.isShotFired()){
            tanksAnimation.setShotFired(false);
            return false;
        }
        return true;
    }
    
    @Override
    public void run() {
        int indexOfCurrentPlayerTurn = 0;
        
        do{
            
            playerTurn(indexOfCurrentPlayerTurn);
            double initialPosition = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
            while(waitUntilEndOfTurn(indexOfCurrentPlayerTurn, initialPosition));
                
            
            
            
            indexOfCurrentPlayerTurn++;
            
            if(indexOfCurrentPlayerTurn > tanksArrayUsed.length - 1)
                indexOfCurrentPlayerTurn = 0;
            
            if(!tanksArrayUsed[indexOfCurrentPlayerTurn].isTankAlive()){
                while(!tanksArrayUsed[indexOfCurrentPlayerTurn].isTankAlive()){
                    indexOfCurrentPlayerTurn++;
                    
                    if(indexOfCurrentPlayerTurn > tanksArrayUsed.length - 1)
                        indexOfCurrentPlayerTurn = 0;
                }
                
            }
            
            
            tanksAnimation.resetSpeed();
            
            
        }while(tanksAnimation.moreThanOneTankAlive());
        
        System.out.println("Game Over");
    }
    
}
