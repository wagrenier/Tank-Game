/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SaveFunction;

import GamePane.GamePane;
import MapGeneration.MapGeneration;
import Tanks.Tanks;
import Tanks.TanksAnimation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author willi
 */
public class SaveFunction {
    //Default Save file: src/Saves/save.txt
    //Must implement who is dead
    public SaveFunction(GamePane gamePane){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Save/save.dat", false));  
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("Save/saveLocation.dat", false)); 
            
            objectOutputStream.writeObject(gamePane);
            objectOutputStream2.writeObject(gamePane.getTanksAnimation().obtainEachTanksTranslation());
            objectOutputStream2.writeObject(gamePane.getTanksAnimation().obtainEachTanksDirection());
            objectOutputStream2.writeObject(gamePane.getTanksAnimation().obtainTanksHP());
            objectOutputStream2.writeObject(gamePane.getGameLoop().getIndexOfCurrentPlayerTurnArray());
        } catch (IOException ex) {
            Logger.getLogger(SaveFunction.class.getName()).log(Level.SEVERE, null, ex);
            File file = new File("Save/save.dat"); //There in case user Deletes the save files
            File file2 = new File("Save/saveLocation.dat");
        }
    }
}
