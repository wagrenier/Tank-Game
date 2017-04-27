/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.Serializable;

/**
 *
 * @author Cedrik Dubois
 */
public class Item  implements Serializable{
    
    private String name;
    private int costOfItem;
    private String texturePath;
    private Image itemImage;
    private String use;
    private int value;
    
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
}
