/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

/**
 *
 * @author Cedrik Dubois
 */
public class ShieldItem extends Item{
    
    /*
    This class is an extension of the Item class, and is for the shields
        -Small shield: protects 25%
        -Medium shield: protects 50%
        -Large shield: protects 75%
    */
    
    private int shieldProtection;
    
    public ShieldItem(){
        
    }
    
    public ShieldItem(String name, int costOfItem, String texturePath, int shieldProtection){
        this.setName(name);
        this.setCostOfItem(costOfItem);
        this.setItemImage(texturePath);
        this.shieldProtection = shieldProtection;
    }
    
    public int getShieldProtection(){
        return this.shieldProtection;
    }
    
    public void setShieldProtection(int shieldProtection){
        this.shieldProtection = shieldProtection;
    }
    
}
