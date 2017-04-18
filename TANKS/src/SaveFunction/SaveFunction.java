/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SaveFunction;

import GamePane.GamePane;
import MapGeneration.MapGeneration;
import Tanks.TanksAnimation;
import java.io.ObjectOutputStream;


/**
 *
 * @author willi
 */
public class SaveFunction {
    //Default Save file: src/Saves/save.txt
    ObjectOutputStream objectOutputStream;
    
    public SaveFunction(){
        
    }
    
    public SaveFunction(MapGeneration mapGeneration, GamePane gamePane, TanksAnimation tanksAniamtion){
        
    }
}
