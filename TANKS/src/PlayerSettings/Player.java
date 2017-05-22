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
    /**sets if this player is human or controlled by an AI*/
    private boolean isAI = true;
    /**Sets in what tem this player is*/
    private int team;
    /**The amount of money that the player has*/
    private int money = 500;
    /**The final score of this player, is only set once the game is over*/
    private int finalScore = 0;//The score of the player
    /**The amount of shield available for this player*/
    private int shield = 0;
    /**The weapons at the player's disposal*/
    private int[] weaponInventory = new int[9];
    /**Item inventory available for the player to use*/
    private int[] itemInventory = new int[8];
    /**The username of this player*/
    private String username;

    /**
     * Default no-arg constructor
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
     * Constructor
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
     *Removes a weapon from the weapon array
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
     * Adds a weapon to the weapon array
     * @param index
     */
    public void addWeapon(int index) {
        weaponInventory[index] = weaponInventory[index] + 1;
    }

    /**
     *Removes an item
     * @param index
     */
    public void removeItem(int index) {
        itemInventory[index] = itemInventory[index] - 1;
    }

    /**
     * Adds an item
     * @param index
     */
    public void addItem(int index) {
        itemInventory[index] = itemInventory[index] + 1;
    }

    /**
     *Removes money from the user, returns a boolean to indicate if the player has enough money for the transaction
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
     *Adds money to the player
     * @param amount
     */
    public void addMoney(int amount) {
        this.money = this.money + amount;
    }
    /**Sets the initial weapon owning for the player*/
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
    
    /**Sets the basic item ownership for the player*/
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
