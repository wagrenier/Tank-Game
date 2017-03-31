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
       weaponArrayList.add(new Weapon("Texture/weapon.png"));
    }
    
    public Weapon getWeaponFromWeaponManager(int index){
        return weaponArrayList.get(index);
    }
    
    public ArrayList<Weapon> getWeaponArrayList() {
        return weaponArrayList;
    }
    
}
