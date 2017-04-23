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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willi
 */
public class LoadFunction {
    
    //Default Save file: src/Saves/save.txt
    GamePane gamePane;
    TanksAnimation tanksAnimation;
    //ObjectInputStream inputTanksAnimation;
    public LoadFunction(MainMenuManagerPane managerPane) {
        
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Save/save.dat"));
            //inputTanksAnimation = new ObjectInputStream(new FileInputStream("Save/saveTanksAnimation.dat"));
            gamePane = (GamePane) (objectInputStream.readObject());
            tanksAnimation = (TanksAnimation) (objectInputStream.readObject());
            gamePane.setTanksAnimation(tanksAnimation);
            //gamePane.paneSetup(gamePane);
            //managerPane.getChildren().clear();
            //gamePane.frontGroundSetup(gamePane);
            //managerPane.getChildren().add(gamePane.getPane());
            objectInputStream.close();
        } catch (NotSerializableException ex1){
            System.out.println("Load Failed");
        } catch (IOException ex) {
            Logger.getLogger(LoadFunction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex2) {
            Logger.getLogger(LoadFunction.class.getName()).log(Level.SEVERE, null, ex2);
        } 
        
    }

    public GamePane getGamePane() {
        return gamePane;
    }
    
    
    
    
}
