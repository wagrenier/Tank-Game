/****************************************************************
 *  File: GameLoop.java
 *  Description: This object contains all the logic of the game(ie AI, current turns,...). This object extends Animation Timer because it will be called 60 times per second, acting as an infinite loop.
 *               Plus, Animation Timer is on the JavaFX thread, so it can modify anything extending Node, (ie Pane, Circle,...) 
 *    History:
 *     Date    04/18/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package GamePane;

import Tanks.Tanks;
import Tanks.TanksAnimation;
import Weapon.Item;
import Weapon.Weapon;
import PlayerSettings.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

/**
 *This object contains all the logic of the game(ie AI, current turns,...). This object extends Animation Timer because it will be called 60 times per second, acting as an infinite loop.
 *Plus, Animation Timer is on the JavaFX thread, so it can modify anything extending Node, (ie Pane, Circle,...) 
 * @author willi
 * 
 * 
 * Current known issues: the animation is not stopped when paused if time correctly (Fixed, Updated to AnimationTimer)
 */
public class GameLoop extends AnimationTimer{
    /**If this current loop passage is a new turn*/
    private boolean newTurn = true; // 
    /**If the turn has ended, if true, then newTurn will be set as true*/
    private boolean endTurn = false; 
    /**If the AI has initiated the launch of a weapon with the progress bar*/
    private boolean launchInitiated = false; // 
    /**If the player has forced ended a turn, giving extra money*/
    private boolean forceEndedTurn = false; // 
    /**A number randomly generated to decide in how many frames the AI will press space again to launch the weapon*/
    private int launchWeaponDelay = 0; // 
    /**The number of turns made so far, default value is 1 to prevent a case where the game could end with 0 turn and making the game crash because of a division by 0*/
    private static int numOfTurns = 1; // 
    /**The delay between the first and second space bar enter from the AI*/
    private int launchWeaponDelayCounter = 0;
    /**Index of the current player*/
    private int indexOfCurrentPlayerTurn = 0;
    /**Generates a random index for a weapon*/
    private int randomWeapon;
    /**The gravity of this map*/
    private double gravity; // 
    /**The maximum position of the tank for this turn*/
    private double maxPos; //
    /**The minimum...*/
    private double minPos; //
    /**The initial position of the tank*/
    double initialPosition; // 
    
    /**Sets the tanks used by this current game*/
    private Tanks[] tanksArrayUsed;
    /**Sets the object TanksAnimation for this current game*/
    private TanksAnimation tanksAnimation;
    /**Sets the tanks animation used by this current game*/
    private Timeline[] tanksAnimationArrayUsed;
    /**Sets the players used byu this current game*/
    private Player[] playerArray;
    /**Sets the array list of all available weapons in the game, for the AI*/
    private ArrayList<Weapon> weaponArrayList;
    /**Sets the array list of all available items in the game, for the AI*/
    private ArrayList<Item> itemArrayList;
    /**Sets an arrays for the 10 scores saved in the leaderboards*/
    private int[] score = new int[10];
    /**Sets an arrays for the 10 names saved in the leaderboards*/
    private String[] names = new String[10];
    
    /**
     *Constructor
     * @param tanksAnimation
     * @param tanksAnimationArrayUsed
     * @param tanksAraryused
     * @param indexPlayer
     */
    public GameLoop(TanksAnimation tanksAnimation, Timeline[] tanksAnimationArrayUsed, Tanks[] tanksAraryused, int indexPlayer){
        this.tanksAnimation = tanksAnimation;
        this.tanksAnimationArrayUsed = tanksAnimationArrayUsed;
        this.tanksArrayUsed = tanksAraryused;
        this.gravity = tanksAnimation.getMapGeneration().getGravity();
        this.indexOfCurrentPlayerTurn = indexPlayer;
        this.playerArray = tanksAnimation.getPlayerArray();
        this.weaponArrayList = tanksAnimation.getHud().getWeaponManager().getWeaponArrayList();
        this.itemArrayList = tanksAnimation.getHud().getWeaponManager().getItemArrayList();
    }
    /**Sets the next player turn*/
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
    /**Checks if there are more thank one tanks alive*/
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
    /**Fire the weapon for the AI*/
    private void fireWeapon(Tanks tank){
        //TODO Implement this
        bestWeapon();
        //System.out.println("AI Fire Weapon " + tank.getImagePath());
        
        tanksArrayUsed[indexOfCurrentPlayerTurn].getCannon().setAICannonAngle(angleToShoot(tank));
        //tanksAnimation.weaponSetup(tanksArrayUsed[indexOfCurrentPlayerTurn], Math.random());
        
        tanksAnimation.keyPressedAI(KeyCode.SPACE, 
                tanksArrayUsed[indexOfCurrentPlayerTurn], 
                tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
        
        if(randomWeapon == 7 || randomWeapon == 8){
            
        }
        else{
            //Times 60 because the thread is called 60 times per second
            launchWeaponDelay = (int) ((1 + Math.random() * 4) * (Math.random() * 61));
            //System.out.println(launchWeaponDelay / 60);
            launchInitiated = true;
        }
        
        
    }
    /**Checks what is the best weapon/item for the AI*/
    private void bestWeapon(){
        //TODO Implement this
        //This will be implemented once the weapon store is fully implemented
        ArrayList<Weapon> possibleWeapon = new ArrayList<>();
        
        
        //If AI has less than 50HP, then it buys a restore pack
        if(tanksArrayUsed[indexOfCurrentPlayerTurn].getLifePoint() < 50){
            for(int i = 2; i > -1; i--){
                if(playerArray[indexOfCurrentPlayerTurn].getMoney() >= itemArrayList.get(i).getCostOfItem()){
                    tanksAnimation.getHud().getStoreMenu().buyItemAI(false, i, playerArray[indexOfCurrentPlayerTurn]);
                    tanksAnimation.getHud().useItemAI(i);
                    break;
                }
            }
        }
        
        //Fill the possible temporary array list to check what are the possible weapons/items to buy 
        for(int i = 0; i < weaponArrayList.size(); i++){
            if(playerArray[indexOfCurrentPlayerTurn].getMoney() >= weaponArrayList.get(i).getCostOfWeapon()){
                possibleWeapon.add(weaponArrayList.get(i));
            }
        }
        
        randomWeapon = (int) (Math.random() * possibleWeapon.size());
        tanksAnimation.getHud().getStoreMenu().buyItemAI(true, possibleWeapon.get(randomWeapon).getIndexOfWeapon(), playerArray[indexOfCurrentPlayerTurn]);
        tanksAnimation.getHud().nextWeaponAction();
    }
    /**Moves the AI to the closest tanks*/
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
            tanksAnimation.keyPressedAI(KeyCode.LEFT, 
                    tanksArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
            //System.out.println("AI Move Left");
        }
        else{
            tanksAnimation.keyPressedAI(KeyCode.RIGHT, 
                    tanksArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                    tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]); 
            //System.out.println("AI Moves Right");
        }
    }
    /**Decides at what angle to shoot to hit the closest tank*/
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
            return Math.PI / 2 - Math.atan(((v*v) + sqrt)/(g*x)) + angleMap;
        }
        
        
    }
    
    /*tank is the current player's tank and other tank is another tank in the pane*/
    private double distanceToTanks(Tanks tank, Tanks otherTank){
        //TODO Implement this
        return Math.sqrt(Math.pow((tank.getTranslateX() - otherTank.getTranslateX()), 2) + Math.pow((tank.getTranslateY() - otherTank.getTranslateY()), 2));
    }
    /**Contains the logic for the AI. Private so that only the game itself can decide when the AI plays*/
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
        //tanksAnimation.getPane().setFocusTraversable(false);
        moveToClosestTank();   
    }
    
    /**
     *
     * @return
     */
    public int getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }
    
    /**
     *
     * @return
     */
    public int[] getIndexOfCurrentPlayerTurnArray(){
        int[] turn = new int[1];
        turn[0] = indexOfCurrentPlayerTurn;
        return turn;
    }

    /**
     *
     * @param forceEndedTurn
     */
    public void setForceEndedTurn(boolean forceEndedTurn) {
        this.forceEndedTurn = forceEndedTurn;
    }
    
    /**
     *
     * @param array
     */
    public void resetNumOfTurn(int[] array){
        numOfTurns = array[0];
    }
    
    /**
     *
     * @return
     */
    public int[] obtainNumOfTurns(){
        int[] numOfTurnArray = new int[1];
        numOfTurnArray[0] = numOfTurns;
        return numOfTurnArray;
    }
    
    /**
     *
     */
    public void leaderboard(){
        File file = new File("src/Leaderboard/leaderboard.txt");
        
        
        try {
            Scanner out = new Scanner(file);
            out.useDelimiter(",");
            int highScoreIndex = -1;
            
            for(int i = 0; i < 10; i++){
                names[i] = out.next();
                BigInteger big = new BigInteger(out.next());
                score[i] = big.intValue();
                //System.out.println(score[i] + " " + names[i]);
            }
            
            if(playerArray[indexOfCurrentPlayerTurn].getFinalScore() > score[0]){
                score[0] = playerArray[indexOfCurrentPlayerTurn].getFinalScore();
                names[0] = "\n" + playerArray[indexOfCurrentPlayerTurn].getUsername();
                 for (int i = 1; i < score.length; i++) {
	            for(int j = i ; j > 0 ; j--){
	                if(score[j] > score[j - 1]){
	                    int temp = score[j];
                            String temp2 = names[j];
	                    score[j] = score[j - 1];
                            names[j] = names[j - 1];
	                    score[j - 1] = temp;
                            names[j - 1] = temp2;
	                }
	            }
	        }
                 
                 out.close();
                 //PrintWriter writer = new PrintWriter(file);
                 FileWriter writer = new FileWriter(file);
                 for(int i = 0; i < 10; i++){          
                     writer.write(names[i] + "," + score[i] + ",");
                 }
                 writer.close();
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @return
     */
    public int[] getScores(){
        return this.score;
    }
    
    /**
     *
     * @return
     */
    public String[] getNames(){
        return this.names;
    }
    
    @Override
    public void handle(long now) {  
        
        //The game's loop is located here. THere is no do while or while loops because the Animation Timer acts as one, since this is called 60 times per second
            if(tanksAnimation.getHud().getPauseMenu().isGamePaused() || tanksAnimation.getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0 || tanksAnimation.getRCAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0){
                //if ever the pause/store is open or an AI is currently playing
        }
            //Checks if the current player is alive. If not, it skips to the next player in the array.
            else if(!tanksArrayUsed[indexOfCurrentPlayerTurn].isTankAlive()){
                indexOfCurrentPlayerTurn++;
                   if(indexOfCurrentPlayerTurn >= tanksArrayUsed.length){
                        indexOfCurrentPlayerTurn = 0;
                   }
            }
            else{
                //Checks if a new turn has been initialized
            if(newTurn){
                //tanksAnimation.getHud().nextItemAction();
                
                numOfTurns++;
                tanksAnimation.setTurnPlayed(false);
                leaderboard();
                //System.out.println("New Turn");
                tanksAnimation.getHud().nextItemAction();
                tanksAnimation.getHud().generateNewWindRes();
                tanksAnimation.getHud().resetWeaponIndex();
                tanksAnimation.getHud().resetItemIndex();
                tanksAnimation.resetSpeed();
                playerTurn(indexOfCurrentPlayerTurn);
                tanksAnimation.setIndexOfCurrentPlayerTurn(indexOfCurrentPlayerTurn);
                tanksAnimation.updateTurn();
                initialPosition = tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX();
                
                //Checks if this player is an AI
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].isIsAI()){
                        aiTurn();
                    }
                
                else if(!tanksArrayUsed[indexOfCurrentPlayerTurn].isIsAI()){
                    //The game has nothing to do in case the player is not an AI.
                }
                newTurn = false;
                maxPos = initialPosition + tanksArrayUsed[indexOfCurrentPlayerTurn].getMaxPixelMove();
                minPos = initialPosition - tanksArrayUsed[indexOfCurrentPlayerTurn].getMaxPixelMove();
                tanksAnimation.getHud().nextItemAction();
            }
            
            //Checks if the computer has pressed the space key
            if(launchInitiated){
                //Increases the number of frames that have passed since the AI has pressed space
                launchWeaponDelayCounter++;
                
                if(launchWeaponDelayCounter >= launchWeaponDelay){
                    tanksAnimation.keyPressedAI(KeyCode.SPACE, 
                        tanksArrayUsed[indexOfCurrentPlayerTurn], 
                        tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], 
                        tanksAnimation.getProgressBarAnimationUsed()[indexOfCurrentPlayerTurn], 
                        tanksAnimation.getProgressBarUsed()[indexOfCurrentPlayerTurn]);
                    launchWeaponDelayCounter = 0;
                    launchInitiated = false;
                }
            }
            
            //Checks if the current player its maximum/minimum displacement
                if(tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() >= maxPos || tanksArrayUsed[indexOfCurrentPlayerTurn].getTranslateX() < minPos || tanksAnimation.isShotFired()){
                    endTurn = true;
                }
                
                //Checks if the player has pressed the force end turn key ('e'), or the turn is over
               if(endTurn || forceEndedTurn){
                   if(forceEndedTurn){
                       forceEndedTurn = false;
                       tanksAnimation.getHud().getCurrentPlayerTurn(indexOfCurrentPlayerTurn).addMoney(50);
                   }
                   else{
                       tanksAnimation.getHud().getCurrentPlayerTurn(indexOfCurrentPlayerTurn).addMoney(25);
                   }
                   indexOfCurrentPlayerTurn++;
                   if(indexOfCurrentPlayerTurn >= tanksArrayUsed.length){
                        indexOfCurrentPlayerTurn = 0;
                   }
                    tanksAnimation.setTurnPlayed(false);
                    tanksAnimation.setShotFired(false);
                    endTurn = false;
                    newTurn = true;  
               }
            
            
            if(!moreThanOneTankAlive()){
                //End Game
                //System.out.println("Game Over");
                playerTurn(indexOfCurrentPlayerTurn);
                int finalScore = (int) playerArray[indexOfCurrentPlayerTurn].getMoney() * (numOfTurns / playerArray.length);
                playerArray[indexOfCurrentPlayerTurn].setFinalScore(finalScore);
                leaderboard();
                this.stop();
                //System.exit(1);
            }
            }
    }
    
}