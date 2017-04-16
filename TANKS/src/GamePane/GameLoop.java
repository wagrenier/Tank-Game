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
        if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() - initialPosition >= 100){
            return false;
        }
        
        if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() - initialPosition <= 100){
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
        int indexOfCurrentPlayerTurn = 0;
        
        while(moreThanOneTankAlive()){
            
            tanksAnimation.resetSpeed();
            //System.out.println(tanksArrayUsed.length + " animation length: " + tanksAnimationArrayUsed.length);
            
            
            
            playerTurn(indexOfCurrentPlayerTurn);
            
            /*
            System.out.println("before 2nd loop");
            for(int i = 0; i < tanksArrayUsed.length; i++){
                  System.out.println("Before: " + "Tank: " + i + " isAlive: " +  tanksArrayUsed[i].isTankAlive());
               }
            //double initialPosition = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
            */
            //double initialPosition = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
            
            while(waitUntilEndOfTurn(indexOfCurrentPlayerTurn));

               
            indexOfCurrentPlayerTurn++;
            if(indexOfCurrentPlayerTurn >= tanksArrayUsed.length)
                indexOfCurrentPlayerTurn = 0;
            
            
            tanksAnimation.setShotFired(false);
            
        
        }
        
        System.out.println("Game Over");
        this.stop();
    }
 
}
