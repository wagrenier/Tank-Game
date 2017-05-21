/****************************************************************
 *  File: ShieldItem.java
 *  Description: This is the the shield that serves as a permanent protection to the tanks.
 *    History:
 *     Date    04/12/2017
 *     ---------- ---------- ----------------------------
 *  Authors  Cedrik        
 *
 ****************************************************************/
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

    /**
     *
     */

    
    
    
    public ShieldItem(){
        
    }
    
    /**
     *
     * @param name
     * @param costOfItem
     * @param texturePath
     * @param shieldProtection
     * @param use
     */
    public ShieldItem(String name, int costOfItem, String texturePath, int shieldProtection, String use){
        this.setName(name);
        this.setCostOfItem(costOfItem);
        this.setItemImage(texturePath);
        this.setUse(use);
        this.setValue(shieldProtection);
    }
    
    /**
     *
     * @param shieldProtection
     */
    public void setShieldProtection(int shieldProtection){
        this.setValue(shieldProtection);
    }
    
}
