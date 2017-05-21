/****************************************************************
 *  File: UpgradeItem.java
 *  Description: Items that serve as an upgrade in the game (have limited use).
 *    History:
 *     Date    04/13/2017
 *     ---------- ---------- ----------------------------
 *  Authors  Cedrik Dubois       
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

    /**
     *
     */

    
    
    
    public UpgradeItem(){
        
    }
    
    /**
     *
     * @param name
     * @param costOfItem
     * @param texturePath
     * @param upgradeUnit
     * @param use
     */
    public UpgradeItem(String name, int costOfItem, String texturePath, int upgradeUnit, String use){
        this.setName(name);
        this.setCostOfItem(costOfItem);
        this.setItemImage(texturePath);
        this.setUse(use);
        this.setValue(upgradeUnit);
    }
    
    /**
     *
     * @param upgradeUnit
     */
    public void setUpgradeUnit(int upgradeUnit){
        this.setValue(upgradeUnit);
    }
    
}
