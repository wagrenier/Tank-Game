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
public class RepairItem extends Item{
    
    /*
    This class is an extension of the Item class, and is for the repair tools
        -Small repair tool: 25%
        -Medium repair tool: 50%
        -Large repair tool: 75%
    */
    
    
    
    public RepairItem(){
        
    }
    
    public RepairItem(String name, int costOfItem, String texturePath, int repairPercentage, String use){
        this.setName(name);
        this.setCostOfItem(costOfItem);
        this.setItemImage(texturePath);
        this.setUse(use);
        this.setValue(repairPercentage);
       
    }
    
    public void setRepairPercentage(int repairPercentage){
        this.setValue(repairPercentage);
    }
    
}
