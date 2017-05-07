/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SaveFunction;

import GamePane.GamePane;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author willi
 */
public class SaveFunction {
    //Default Save file: Saves/save.txt
    //Must implement who is dead -> Done
    /**
     * Implement Mines Location:
     * 
     * 1.0 Save the translateX and translateY
     * 2.0 Save the ownership
     * 
     * 
     */
    
    /**
     * Things To Save:
     * 
     * Tanks:
     * MaxPixelMove
     * 
     * GameLoop:
     * InitialPosition
     * 
     */
    public SaveFunction(GamePane gamePane){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Save/save.dat", false));  
            
            objectOutputStream.writeObject(gamePane);
            objectOutputStream.writeObject(gamePane.getTanksAnimation().obtainEachTanksTranslation());
            objectOutputStream.writeObject(gamePane.getTanksAnimation().obtainEachTanksDirection());
            objectOutputStream.writeObject(gamePane.getTanksAnimation().obtainTanksHP());
            objectOutputStream.writeObject(gamePane.getGameLoop().getIndexOfCurrentPlayerTurnArray());
            objectOutputStream.writeObject(gamePane.getTanksAnimation().obtainWhoIsDead());
            objectOutputStream.writeObject(gamePane.getTanksAnimation().obtainMaxPixelMoveSave());
            objectOutputStream.writeObject(gamePane.getGameLoop().obtainNumOfTurns());
            if(gamePane.getTanksAnimation().getNumOfMines() > 0){
                objectOutputStream.writeObject(gamePane.getTanksAnimation().obtainMinesLocation());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SaveFunction.class.getName()).log(Level.SEVERE, null, ex);
            File file = new File("Save/save.dat"); //There in case user Deletes the save files
            //File file2 = new File("Save/saveLocation.dat");
        }
    }
}
