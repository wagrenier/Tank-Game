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
    
    private int repairPercentage;
    
    public RepairItem(){
        
    }
    
    public RepairItem(String name, int costOfItem, String texturePath, int repairPercentage){
        this.setName(name);
        this.setCostOfItem(costOfItem);
        this.setItemImage(texturePath);
        this.repairPercentage = repairPercentage;
    }
    
    public int getRepairPercentage(){
        return this.repairPercentage;
    }
    
    public void setRepairPercentage(int repairPercentage){
        this.repairPercentage = repairPercentage;
    }
    
}
