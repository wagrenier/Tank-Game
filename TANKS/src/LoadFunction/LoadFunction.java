/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoadFunction;

import GamePane.GamePane;
import Tanks.Tanks;
import Tanks.TanksAnimation;
import classes.MainMenuManagerPane;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willi
 */
public class LoadFunction {
    
    //Default Save file: src/Saves/save.txt
    GamePane gamePane;
    double[][] tanksArray;
    boolean[] direction;
    boolean[] dead;
    int[] tanksHP;
    int[] indexOfCurrentPlayerTurn;
    
    public LoadFunction() {
        
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Save/save.dat"));
            //ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream("Save/saveLocation.dat"));
            
            gamePane = (GamePane) (objectInputStream.readObject());
            tanksArray = (double[][]) (objectInputStream.readObject());
            direction = (boolean[]) (objectInputStream.readObject());
            tanksHP = (int[]) (objectInputStream.readObject());
            indexOfCurrentPlayerTurn = (int[]) (objectInputStream.readObject());
            dead = (boolean[])(objectInputStream.readObject());
        } catch (IOException ex) {
            Logger.getLogger(LoadFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex2) {
            Logger.getLogger(LoadFunction.class.getName()).log(Level.SEVERE, null, ex2);
        } 
        
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
}
