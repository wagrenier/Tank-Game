/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import Tanks.Tanks;
import Tanks.TanksAnimation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

/**
 *
 * @author willi
 * 
 * 
 * Current known issues: the animation is not stopped when paused if time correctly
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
        bestWeapon();
        //angleToShoot()
        Platform.runLater( () ->{
            tanksArrayUsed[indexOfCurrentPlayerTurn].getCannon().setAICannonAngle(angleToShoot());
            tanksAnimation.weaponSetup(tanksArrayUsed[indexOfCurrentPlayerTurn], Math.random());
        });
        
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
            Platform.runLater( () ->{
            tanksAnimation.keyPressed(KeyCode.LEFT, tanksArrayUsed[indexOfCurrentPlayerTurn], tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
        });
            
        }
        else{
            //tanksArrayUsed[indexOfCurrentPlayerTurn].setxSpeed(.1);
            Platform.runLater( () ->{
            tanksAnimation.keyPressed(KeyCode.RIGHT, tanksArrayUsed[indexOfCurrentPlayerTurn], tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
        });
            
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
    public void run() {
        
        
            while(moreThanOneTankAlive()){
            
                
            
            if(tanksAnimation.getHud().getPauseMenu().isGamePaused()){
                
        }
            
            
            else{
                
                
                
            if(!(tanksAnimation.getWeaponAnimation() == null)){
                try {
                    //int v = 0;
                    while(tanksAnimation.getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0){
                        //System.out.println("HEY" + v + " Tank: " + tanksArrayUsed[indexOfCurrentPlayerTurn].getImagePath());
                        //v++;
                    }
                    
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            tanksAnimation.resetSpeed();
            tanksAnimation.setIndexOfCurrentPlayerTurn(indexOfCurrentPlayerTurn);
            
            //For accessing the main javaFX thread without the program crashing
            Platform.runLater(() -> {
                tanksAnimation.updateTurn();
                
                /*
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].isIsAI()){
                        aiTurn();
                    }*/
                    }
            );
            
            if(tanksArrayUsed[indexOfCurrentPlayerTurn].isIsAI()){
                        aiTurn();
            }
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
            
            if(!(tanksAnimation.getWeaponAnimation() == null)){
                try {
                    //int v = 0;
                    while(tanksAnimation.getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0){
                        //System.out.println("HEY" + v + " Tank: " + tanksArrayUsed[indexOfCurrentPlayerTurn].getImagePath());
                        //v++;
                    }
                    
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
        }
        
        System.out.println("Game Over");
        this.stop();
            
        
        
    }
    
}
