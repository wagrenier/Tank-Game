/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import GamePane.GamePane;
import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cedrik Dubois
 */
public class Store implements Serializable{
    
    private boolean storeOpened = false;
    
    private transient GamePane gamePane;
    
    private transient ImageView storeBackground = new ImageView(new Image("Texture/Menus/Store/Store.png"));
    
    public Store(GamePane gamePane){
        this.gamePane = gamePane;
    }
    
    public void openStore(){//Will later request the player accessing the store
        this.gamePane.getChildren().add(storeBackground);
        storeOpened = true;
    }
    
    public void closeStore(){
        this.gamePane.getChildren().remove(storeBackground);
        storeOpened = false;
    }
    
    public boolean isStoreOpened(){
        return storeOpened;
    }
    
}
