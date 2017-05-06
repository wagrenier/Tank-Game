/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Weapon.Item;
import Weapon.Weapon;

import java.io.Serializable;


/**
 *
 * @author Cedrik Dubois
 */
public class Player implements Serializable{
    
    private String username;
    private int team;
    private boolean isAI = true;
    private int money = 500;
    
    private int[] weaponInventory = new int[9];
    private int[] itemInventory = new int[8];
    private int finalScore = 0;//The score of the player
    
    /**
     * Index of the teams
     * 
     * 0 = North Korea
     * 1 = United States
     * 2 = Canada
     * 3 = China
     * 
     */
    
    public Player(){
        setWeaponInventory();
        setItemInventory();
    }
    
    public Player(String username, int team){
        if (username.length() > 10){
            this.username = username.substring(0, 10);
        }
        this.team = team;
        
        setWeaponInventory();
        setItemInventory();
    }
    private void setWeaponInventory(){
        weaponInventory[0] = 1;
        weaponInventory[1] = 0;
        weaponInventory[2] = 0;
        weaponInventory[3] = 0;
        weaponInventory[4] = 0;
        weaponInventory[5] = 0;
        weaponInventory[6] = 0;
        weaponInventory[7] = 0;
        weaponInventory[8] = 0;
    }
    
    private void setItemInventory(){
        itemInventory[0] = 0;
        itemInventory[1] = 0;
        itemInventory[2] = 0;
        itemInventory[3] = 0;
        itemInventory[4] = 0;
        itemInventory[5] = 0;
        itemInventory[6] = 0;
        itemInventory[7] = 0;
    }
    
    public int[] getItemInventory(){
        return this.itemInventory;
    }
    
    public int[] getWeaponInventory(){
        return this.weaponInventory;
    }
    
    public void setName(String name){
        if (name.length() > 10)
            this.username = name.substring(0, 10);
        else
            this.username = name;
        
    }
    
    public void setTeam(int team){
        this.team = team;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public int getTeam(){
        return this.team;
    }

    public boolean isIsAI() {
        return isAI;
    }

    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }
    
    public String toString(){
        return this.username + " : " + this.team;
    }
    
    public void removeWeapon(int index){
        if (index == 0){
            
        }
        else {
            if (index < 1){
                
            }
            else {
                weaponInventory[index] = weaponInventory[index] - 1;
            }
        }
    }
    
    public void addWeapon(int index){
        weaponInventory[index] = weaponInventory[index] + 1;
    }
    
    public void removeItem(int index){
        itemInventory[index] = itemInventory[index] - 1;
    }
    
    public void addItem(int index){
        itemInventory[index] = itemInventory[index] + 1;
    }
    
    public boolean removeMoney(int amount){
        
        if (amount <= this.money){
            this.money = this.money - amount;
            return true;
        }
        else{
            return false;
        }
    }
    
    public void addMoney(int amount){
        this.money = this.money + amount;
    }
    
    public int getMoney(){
        return this.money;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
    
}
