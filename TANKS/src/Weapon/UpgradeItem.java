/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
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
