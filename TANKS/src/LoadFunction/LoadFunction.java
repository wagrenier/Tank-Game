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
 *
 * @author willi
 */
public class LoadFunction {
    
    //Default Save file: Saves/save.txt
    private boolean minePlaced = false;//If ever there are no mines placed on the map
    private boolean successfulLoad = false;//If ever the fil is incompatible with the current build
    private boolean[] direction; // The direction facing each tanks
    private boolean[] dead; //If the tank was dead when saved
    private int[] numOfTurnArray; //The number of turns made in the current game
    private int[] tanksHP; // The hp left for each of the tanks
    private int[] indexOfCurrentPlayerTurn; // The index of the current tank's turn
    private double[][] tanksArray; // The tanks used in the pane, saves their team only, from their the tanks can be recreated
    private double[] maxPixMove; // The maximum pixels that a tank can do
    private double[][] mineLocation; // The lcoation of the mines in the pane
    private GamePane gamePane; // The game pane containing all crucial information about the game
    
    /**
     *
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
