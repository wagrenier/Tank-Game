/** **************************************************************
 *  File: Item.java
 *  Description: The Item object contains the items in the game that are not see in the GamePane
 *    History:
 *     Date    04/10/2017
 *     ---------- ---------- ----------------------------
 *  Authors  Cedrik Dubois
 *
 *************************************************************** */
package Weapon;

import javafx.scene.image.Image;

/**
 *
 * The Item object contains the items in the game that are not seen in the
 * GamePane
 */
public class Item {

    /**
     * This variable stores the cost of the Item
     */
    private int costOfItem;
    /**
     * This variable stores the value of this Item
     */
    private int value;
    /**
     * This variable stores the name of the Item
     */
    private String name;
    /**
     * This variable stores the usage of this Item
     */
    private String use;
    /**
     * This variable stores the path of the Item's texture
     */
    private String texturePath;
    /**
     * This variable stores the Image of the Item
     */
    private Image itemImage;

    /**
     * Default no-arg constructor
     */
    public Item() {

    }

    /**
     * Constructor that takes a string, int, string, and string
     *
     * @param name
     * @param use
     * @param costOfItem
     * @param texturePath
     */
    public Item(String name, int costOfItem, String texturePath, String use) {
        this.name = name;
        this.costOfItem = costOfItem;
        this.texturePath = texturePath;
        this.itemImage = new Image(this.texturePath);
        this.use = use;

    }

    /**
     * Sets a new value for the description of the Item
     *
     * @param use
     */
    public void setUse(String use) {
        this.use = use;
    }

    /**
     * Returns the usage of the Item
     *
     * @return
     */
    public String getUse() {
        return this.use;
    }

    /**
     * Sets a name for the Item
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new cost for the Item
     *
     * @param costOfItem
     */
    public void setCostOfItem(int costOfItem) {
        this.costOfItem = costOfItem;
    }

    /**
     * Returns the name of the Item
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the cost of the item
     *
     * @return
     */
    public int getCostOfItem() {
        return this.costOfItem;
    }

    /**
     * Returns the damage of the Item
     *
     * @return
     */
    public Image getItemImage() {
        return this.itemImage;
    }

    /**
     * Sets a new path for the Item's texture
     *
     * @param texturePath
     */
    public void setItemImage(String texturePath) {
        this.itemImage = new Image(texturePath);
    }

    /**
     * Sets a new value for the item
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * The cost of the ite
     *
     * @return m
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the path of the Item's texture
     *
     * @return
     */
    public String getTexturePath() {
        return texturePath;
    }

    /**
     * Sets a new path for the Item's texture
     *
     * @param texturePath
     */
    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

}
