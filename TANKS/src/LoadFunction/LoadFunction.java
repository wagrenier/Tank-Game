/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private boolean[] direction;
    private boolean[] dead;
    private int[] numOfTurnArray;
    private int[] tanksHP;
    private int[] indexOfCurrentPlayerTurn;
    private double[][] tanksArray;
    private double[] maxPixMove;
    private double[][] mineLocation;
    private GamePane gamePane;
    
    
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

    public int[] getNumOfTurnArray() {
        return numOfTurnArray;
    }
    
    public double[][] getMineLocation() {
        return mineLocation;
    }

    public boolean isMinePlaced() {
        return minePlaced;
    }

    public double[] getMaxPixMove() {
        return maxPixMove;
    }

    public boolean[] getDead() {
        return dead;
    }

    public int[] getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }
    
    public int[] getTanksHP() {
        return tanksHP;
    }
    
    public double[][] getTanksArray() {
        return tanksArray;
    }

    public boolean[] getDirection() {
        return direction;
    }
    
    public GamePane getGamePane() {
        return gamePane;
    }

    public void setMinePlaced(boolean minePlaced) {
        this.minePlaced = minePlaced;
    }

    public void setDirection(boolean[] direction) {
        this.direction = direction;
    }

    public void setDead(boolean[] dead) {
        this.dead = dead;
    }

    public void setNumOfTurnArray(int[] numOfTurnArray) {
        this.numOfTurnArray = numOfTurnArray;
    }

    public void setTanksHP(int[] tanksHP) {
        this.tanksHP = tanksHP;
    }

    public void setIndexOfCurrentPlayerTurn(int[] indexOfCurrentPlayerTurn) {
        this.indexOfCurrentPlayerTurn = indexOfCurrentPlayerTurn;
    }

    public void setTanksArray(double[][] tanksArray) {
        this.tanksArray = tanksArray;
    }

    public void setMaxPixMove(double[] maxPixMove) {
        this.maxPixMove = maxPixMove;
    }

    public void setMineLocation(double[][] mineLocation) {
        this.mineLocation = mineLocation;
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public boolean isSuccessfulLoad() {
        return successfulLoad;
    }

    public void setSuccessfulLoad(boolean successfulLoad) {
        this.successfulLoad = successfulLoad;
    }
    
}
