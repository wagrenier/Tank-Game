/****************************************************************
 *  File: LoadFunction.java
 *  Description: Loads the values of a previous game from a binary file, to allow saving object.
 *    History:
 *     Date    04/20/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package LoadFunction;

import GamePane.GamePane;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *Loads the values of a previous game from a binary file, to allow saving object.
 * @author willi
 */
public class LoadFunction {
    
    //Default Save file: Saves/save.txt
    /**If ever there are no mines placed on the map*/
    private boolean minePlaced = false;
    /**If ever the file is incompatible with the current build*/
    private boolean successfulLoad = false;
    /**The direction facing each tanks*/
    private boolean[] direction; 
    /**If the tank was dead when saved*/
    private boolean[] dead;
    /**The number of turns made in the current game*/
    private int[] numOfTurnArray;
    /**The hp left for each of the tanks*/
    private int[] tanksHP; 
    /**The index of the current tank's turn*/
    private int[] indexOfCurrentPlayerTurn; 
    /**The tanks used in the pane, saves their team only, from their the tanks can be recreated*/
    private double[][] tanksArray; 
    /**The maximum pixels that a tank can do*/
    private double[] maxPixMove; 
    /**The location of the mines in the pane*/
    private double[][] mineLocation; 
    /**The game pane containing all crucial information about the game*/
    private GamePane gamePane; 
    
    /**
     *Constructor
     */
    public LoadFunction() {
        
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Save/save.dat"));
            
            gamePane = (GamePane) (objectInputStream.readObject());
            tanksArray = (double[][]) (objectInputStream.readObject());
            direction = (boolean[]) (objectInputStream.readObject());
            tanksHP = (int[]) (objectInputStream.readObject());
            indexOfCurrentPlayerTurn = (int[]) (objectInputStream.readObject());
            dead = (boolean[])(objectInputStream.readObject());
            maxPixMove = (double[])(objectInputStream.readObject());
            numOfTurnArray = (int[]) (objectInputStream.readObject());
            mineLocation = (double[][]) (objectInputStream.readObject());
            minePlaced = true;
        } catch (java.io.EOFException ex3) {
            minePlaced = false;
            successfulLoad = true;
        } catch (IOException ex){
            successfulLoad = false;
        }catch (ClassNotFoundException ex2) {
            successfulLoad = false;
        } 
        
    }

    /**
     *
     * @return
     */
    public int[] getNumOfTurnArray() {
        return numOfTurnArray;
    }
    
    /**
     *
     * @return
     */
    public double[][] getMineLocation() {
        return mineLocation;
    }

    /**
     *
     * @return
     */
    public boolean isMinePlaced() {
        return minePlaced;
    }

    /**
     *
     * @return
     */
    public double[] getMaxPixMove() {
        return maxPixMove;
    }

    /**
     *
     * @return
     */
    public boolean[] getDead() {
        return dead;
    }

    /**
     *
     * @return
     */
    public int[] getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }
    
    /**
     *
     * @return
     */
    public int[] getTanksHP() {
        return tanksHP;
    }
    
    /**
     *
     * @return
     */
    public double[][] getTanksArray() {
        return tanksArray;
    }

    /**
     *
     * @return
     */
    public boolean[] getDirection() {
        return direction;
    }
    
    /**
     *
     * @return
     */
    public GamePane getGamePane() {
        return gamePane;
    }

    /**
     *
     * @param minePlaced
     */
    public void setMinePlaced(boolean minePlaced) {
        this.minePlaced = minePlaced;
    }

    /**
     *
     * @param direction
     */
    public void setDirection(boolean[] direction) {
        this.direction = direction;
    }

    /**
     *
     * @param dead
     */
    public void setDead(boolean[] dead) {
        this.dead = dead;
    }

    /**
     *
     * @param numOfTurnArray
     */
    public void setNumOfTurnArray(int[] numOfTurnArray) {
        this.numOfTurnArray = numOfTurnArray;
    }

    /**
     *
     * @param tanksHP
     */
    public void setTanksHP(int[] tanksHP) {
        this.tanksHP = tanksHP;
    }

    /**
     *
     * @param indexOfCurrentPlayerTurn
     */
    public void setIndexOfCurrentPlayerTurn(int[] indexOfCurrentPlayerTurn) {
        this.indexOfCurrentPlayerTurn = indexOfCurrentPlayerTurn;
    }

    /**
     *
     * @param tanksArray
     */
    public void setTanksArray(double[][] tanksArray) {
        this.tanksArray = tanksArray;
    }

    /**
     *
     * @param maxPixMove
     */
    public void setMaxPixMove(double[] maxPixMove) {
        this.maxPixMove = maxPixMove;
    }

    /**
     *
     * @param mineLocation
     */
    public void setMineLocation(double[][] mineLocation) {
        this.mineLocation = mineLocation;
    }

    /**
     *
     * @param gamePane
     */
    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    /**
     *
     * @return
     */
    public boolean isSuccessfulLoad() {
        return successfulLoad;
    }

    /**
     *
     * @param successfulLoad
     */
    public void setSuccessfulLoad(boolean successfulLoad) {
        this.successfulLoad = successfulLoad;
    }
    
}
