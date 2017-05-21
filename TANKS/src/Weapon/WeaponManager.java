/****************************************************************
 *  File: WeaponManager.java
 *  Description: An array list containing all the weapons and items in the game. This object should be created only once in the game as it can easily lead to a memory leak.
 *    History:
 *     Date    04/01/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
package Weapon;

import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class WeaponManager{
   
    /**
     * This class is used to manage the Objects weapon.
     * This class contains all the weapon used in the game.
     * 
     */
    
    private ArrayList<Weapon> weaponArrayList = new ArrayList<>();
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    
    /**
     *
     */
    public WeaponManager(){
        setupWeaponManager();
    }
    
    /**
     *
     */
    public void setupWeaponManager(){
        
        
       //Normal Weapons
       weaponArrayList.add(new Weapon(7, 0, "Normal", "Texture/Items/Normal/Standard Round.png", "Projectile", 0));//0
       weaponArrayList.add(new Weapon(25, 50, "Missile", "Texture/Items/Normal/Missile.png", "Projectile", 1));//1
       weaponArrayList.add(new Weapon(5, 25, "Shrapnel", "Texture/Items/Normal/Shrapnel.png", "Projectile", 2));//2
       weaponArrayList.add(new Weapon(35, 100, "Atomic", "Texture/Items/Normal/Atomic Bomb.png", "Projectile", 3));//3
       weaponArrayList.add(new Weapon(15, 75, "Laser", "Texture/Items/Normal/Laser.png", "Projectile", 4));//4
       
       //Normal Weapons but with randomized velocity
       weaponArrayList.add(new Weapon(5, 45, "HMG", "Texture/Items/Normal/Burst (Machine Guns)/HMG.png", "Burst", 5));//5
       weaponArrayList.add(new Weapon(5, 45, "LMG", "Texture/Items/Normal/Burst (Machine Guns)/LMG.png", "Burst", 5));//6
       
       //Static Weapons
       //This weapon is dropped at the tank's current location and stays there until an enemy runs over it
       weaponArrayList.add(new Weapon(35, 20, "Mine", "Texture/Items/Static/Mine.png", "Drop", 7));//7
       
       //Linear Weapons
       //The weapon will be dropped at the tank's location and it will drive itself towards
       weaponArrayList.add(new Weapon(10, 30, "C4-RC", "Texture/Items/Linear/C4 RC.png", "Guided", 8));//8
       
       
       //There is a total of 9 weapons
       
       //Repair kits
       itemArrayList.add(new RepairItem("Repair (S)", 50, "Texture/Items/Special/Small Repair Kit.png", 25, "Heals Tank"));//0
       itemArrayList.add(new RepairItem("Repair (M)", 75, "Texture/Items/Special/Medium Repair Kit.png", 50, "Heals Tank"));//1
       itemArrayList.add(new RepairItem("Repair (L)", 100, "Texture/Items/Special/Large Repair Kit.png", 75, "Heals Tank"));//2
       
       //Shields
       itemArrayList.add(new ShieldItem("Shield (S)", 100, "Texture/Items/Special/Small Shield.png", 90, "Protects Tank"));//3
       itemArrayList.add(new ShieldItem("Shield (M)", 150, "Texture/Items/Special/Medium Shield.png", 80, "Protects Tank"));//4
       itemArrayList.add(new ShieldItem("Shield (L)", 200, "Texture/Items/Special/Large Shield.png", 70, "Protects Tank"));//5
       
       //Upgrades
       itemArrayList.add(new UpgradeItem("Armor", 200, "Texture/Items/Special/Armor Upgrade.png", 100, "Adds Max Health"));//6
       itemArrayList.add(new UpgradeItem("Engine", 200, "Texture/Items/Special/Engine Upgrade.png", 50, "Raise Movement"));//7
       
    }
    
    /**
     *
     * @param index
     * @return
     */
    public Weapon getWeaponFromWeaponManager(int index){
        return weaponArrayList.get(index);
    }
    
    /**
     *
     * @param index
     * @return
     */
    public Item getItemFromWeaponManager(int index){
        return itemArrayList.get(index);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Weapon> getWeaponArrayList() {
        return weaponArrayList;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Item> getItemArrayList(){
        return itemArrayList;
    }

    /**
     *
     * @param weaponArrayList
     */
    public void setWeaponArrayList(ArrayList<Weapon> weaponArrayList) {
        this.weaponArrayList = weaponArrayList;
    }

    /**
     *
     * @param itemArrayList
     */
    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }
    
    
}
