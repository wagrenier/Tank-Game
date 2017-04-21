/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cedrik Dubois
 */
public class Item {
    
    private String name;
    private int costOfItem;
    private String texturePath;
    private Image itemImage;
    
    public Item(){
        
    }
    
    public Item(String name, int costOfItem, String texturePath){
        this.name = name;
        this.costOfItem = costOfItem;
        this.texturePath = texturePath;
        this.itemImage = new Image(this.texturePath);
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
}
