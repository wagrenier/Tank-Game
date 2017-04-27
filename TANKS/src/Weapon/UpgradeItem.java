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
public class UpgradeItem extends Item{
    
    /*
    This is for the engine upgrade as well as the armor upgrade
    
    Example:
        -Everytime armor item is purchased, upgrade life point by 5
        -Everytime engine item is purchases, upgrade moving pixels by 25
    */
    
    
    
    public UpgradeItem(){
        
    }
    
    public UpgradeItem(String name, int costOfItem, String texturePath, int upgradeUnit, String use){
        this.setName(name);
        this.setCostOfItem(costOfItem);
        this.setItemImage(texturePath);
        this.setUse(use);
        this.setValue(upgradeUnit);
    }
    
    public void setUpgradeUnit(int upgradeUnit){
        this.setValue(upgradeUnit);
    }
    
}
