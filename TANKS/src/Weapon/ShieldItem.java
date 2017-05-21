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
public class ShieldItem extends Item{
    
    /*
    This class is an extension of the Item class, and is for the shields
        -Small shield: protects 25%
        -Medium shield: protects 50%
        -Large shield: protects 75%
    */
    
    
    
    public ShieldItem(){
        
    }
    
    public ShieldItem(String name, int costOfItem, String texturePath, int shieldProtection, String use){
        this.setName(name);
        this.setCostOfItem(costOfItem);
        this.setItemImage(texturePath);
        this.setUse(use);
        this.setValue(shieldProtection);
    }
    
    public void setShieldProtection(int shieldProtection){
        this.setValue(shieldProtection);
    }
    
}
