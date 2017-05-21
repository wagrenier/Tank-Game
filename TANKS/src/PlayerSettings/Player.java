/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
package PlayerSettings;

import java.io.Serializable;

/**
 *
 * @author Cedrik Dubois
 */
public class Player implements Serializable {

    private boolean isAI = true;
    private int team;
    private int money = 500;
    private int finalScore = 0;//The score of the player
    private int shield = 0;
    private int[] weaponInventory = new int[9];
    private int[] itemInventory = new int[8];
    private String username;

    /**
     * Index of the teams
     *
     * 0 = North Korea 1 = United States 2 = Canada 3 = China
     *
     */
    public Player() {
        setWeaponInventory();
        setItemInventory();
    }

    /**
     *
     * @param username
     * @param team
     */
    public Player(String username, int team) {
        if (username.length() > 10) {
            this.username = username.substring(0, 10);
        }
        this.team = team;

        setWeaponInventory();
        setItemInventory();
    }
    
    /**
     *
     * @param index
     */
    public void removeWeapon(int index) {
        if (index == 0) {

        } else {
            if (index < 1) {

            } else {
                weaponInventory[index] = weaponInventory[index] - 1;
            }
        }
    }

    /**
     *
     * @param index
     */
    public void addWeapon(int index) {
        weaponInventory[index] = weaponInventory[index] + 1;
    }

    /**
     *
     * @param index
     */
    public void removeItem(int index) {
        itemInventory[index] = itemInventory[index] - 1;
    }

    /**
     *
     * @param index
     */
    public void addItem(int index) {
        itemInventory[index] = itemInventory[index] + 1;
    }

    /**
     *
     * @param amount
     * @return
     */
    public boolean removeMoney(int amount) {

        if (amount <= this.money) {
            this.money = this.money - amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param amount
     */
    public void addMoney(int amount) {
        this.money = this.money + amount;
    }

    private void setWeaponInventory() {
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

    private void setItemInventory() {
        itemInventory[0] = 0;
        itemInventory[1] = 0;
        itemInventory[2] = 0;
        itemInventory[3] = 0;
        itemInventory[4] = 0;
        itemInventory[5] = 0;
        itemInventory[6] = 0;
        itemInventory[7] = 0;
    }

    /**
     *
     * @return
     */
    public int[] getItemInventory() {
        return this.itemInventory;
    }

    /**
     *
     * @return
     */
    public int[] getWeaponInventory() {
        return this.weaponInventory;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        if (name.length() > 10) {
            this.username = name.substring(0, 10);
        } else {
            this.username = name;
        }

    }

    /**
     *
     * @param team
     */
    public void setTeam(int team) {
        this.team = team;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @return
     */
    public int getTeam() {
        return this.team;
    }

    /**
     *
     * @return
     */
    public boolean isIsAI() {
        return isAI;
    }

    /**
     *
     * @param isAI
     */
    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    /**
     *
     * @return
     */
    public int getMoney() {
        return this.money;
    }

    /**
     *
     * @return
     */
    public int getFinalScore() {
        return finalScore;
    }

    /**
     *
     * @param finalScore
     */
    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    /**
     *
     * @param var
     */
    public void setShield(int var) {
        this.shield = var;

        System.out.println(this.username + " " + this.shield);
    }

    /**
     *
     * @return
     */
    public int getShield() {
        return this.shield;
    }
    
    @Override
    public String toString() {
        return this.username + " : " + this.team;
    }

}
