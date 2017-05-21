/****************************************************************
 *  File: Item.java
 *  Description: The Item object contains the items in the game that are not see in the GamePane
 *    History:
 *     Date    04/10/2017
 *     ---------- ---------- ----------------------------
 *  Authors  Cedrik Dubois        
 *
 ****************************************************************/
package Weapon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.Serializable;

/**
 *
 * @author Cedrik Dubois
 */
public class Item{
    private int costOfItem;
    private int value;
    private String name;
    private String use;
    private String texturePath;
    private Image itemImage;
    
    
    
    public Item(){
        
    }
    
    public Item(String name, int costOfItem, String texturePath, String use){
        this.name = name;
        this.costOfItem = costOfItem;
        this.texturePath = texturePath;
        this.itemImage = new Image(this.texturePath);
        this.use = use;
        
    }
    
    public void setUse(String use){
        this.use = use;
    }
    
    public String getUse(){
        return this.use;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setCostOfItem(int costOfItem){
        this.costOfItem = costOfItem;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getCostOfItem(){
        return this.costOfItem;
    }
    
    public Image getItemImage(){
        return this.itemImage;
    }
    
    public void setItemImage(String texturePath){
        this.itemImage = new Image(texturePath);
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public int getValue(){
        return this.value;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }
    
    
}
