/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapon;

import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class WeaponManager {
   
    /**
     * This class is used to manage the Objects weapon.
     * This class contains all the weapon used in the game.
     * 
     */
    
    private ArrayList<Weapon> weaponArrayList = new ArrayList<>();
    
    public WeaponManager(){
        setupWeaponManager();
    }
    
    public void setupWeaponManager(){
        //Linear Weapons
        //The weapon will be dropped at the tank's location and it will drive itself towards
       weaponArrayList.add(new Weapon(10, 30, "C4-RC", "Texture/Items/Linear/C4 RC.png"));
       
       //Normal Weapons
       weaponArrayList.add(new Weapon(35, 100, "Atomic", "Texture/Items/Normal/Atomic Bomb.png"));
       weaponArrayList.add(new Weapon(25, 50, "Missile", "Texture/Items/Normal/Missile.png"));
       weaponArrayList.add(new Weapon(5, 25, "Shrapnel", "Texture/Items/Normal/Shrapnel.png"));
       weaponArrayList.add(new Weapon(7, 0, "Normal", "Texture/Items/Normal/Standard Round.png"));
       weaponArrayList.add(new Weapon(15, 75, "Laser", "Texture/Items/Normal/Laser.png"));
       
       //Normal Weapons but with randomized velocity
       weaponArrayList.add(new Weapon(5, 45, "HMG", "Texture/Items/Normal/Burst (Machine Guns)/HMG.png"));
       weaponArrayList.add(new Weapon(5, 45, "LMG", "Texture/Items/Normal/Burst (Machine Guns)/LMG.png"));
       
       //Static Weapons
       //This weapon is dropped at the tank's current location and stays there until an enemy runs over it
       weaponArrayList.add(new Weapon(35, 20, "Mine", "Texture/Items/Static/Mine.png"));
       
       //There is a total of 9 weapons
    }
    
    public Weapon getWeaponFromWeaponManager(int index){
        return weaponArrayList.get(index);
    }
    
    public ArrayList<Weapon> getWeaponArrayList() {
        return weaponArrayList;
    }
    
}
