/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import GamePane.GamePane;
import Weapon.WeaponManager;
import classes.Player;
import java.io.Serializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Cedrik Dubois
 */
public class Store{
    
    private boolean storeOpened = false;
    
    private GamePane gamePane;
    private WeaponManager weaponManager;
    
    private Player player;//Player accessing the store
    
    private Image buyImg = new Image("Texture/Menus/Store/Buy Button.png");
    
    private transient ImageView storeBackground = new ImageView(new Image("Texture/Menus/Store/Store.png"));
    
    private ImageCursor cursorImg = new ImageCursor(new Image("Texture/Cursor/Cursor.png"));

    
    //All different buttons for every item
    
    //Weapons
    private ImageView atomicBtn = new ImageView(buyImg);
    private ImageView laserBtn = new ImageView(buyImg);
    private ImageView missileBtn = new ImageView(buyImg);
    private ImageView shrapnelBtn = new ImageView(buyImg);
    private ImageView lmgBtn = new ImageView(buyImg);
    private ImageView hmgBtn = new ImageView(buyImg);
    private ImageView mineBtn = new ImageView(buyImg);
    private ImageView c4rcBtn = new ImageView(buyImg);
    
    //Repair tools
    private ImageView repairSmallBtn = new ImageView(buyImg);
    private ImageView repairMediumBtn = new ImageView(buyImg);
    private ImageView repairLargeBtn = new ImageView(buyImg);
    
    //Shields
    private ImageView shieldSmallBtn = new ImageView(buyImg);
    private ImageView shieldMediumBtn = new ImageView(buyImg);
    private ImageView shieldLargeBtn = new ImageView(buyImg);
    
    //Upgrades
    private ImageView armorBtn = new ImageView(buyImg);
    private ImageView engineBtn = new ImageView(buyImg);
    
    //Player Info
    private Text playerName = new Text();
    private Text playerMoney = new Text();
    
    //Information Panel
    private ImageView infoPanel = new ImageView(new Image("Texture/Menus/Store/Info Panel.png"));
    
    private Text weaponCost = new Text();
    private Text weaponDamage = new Text();
    private Text weaponShot = new Text();
    
    private Text itemCost = new Text();
    private Text itemValue = new Text();
    private Text itemUse = new Text();
    
    public Store(GamePane gamePane, WeaponManager weaponManager){
        this.gamePane = gamePane;
        this.weaponManager = weaponManager;
        
        
        storeBackground.setFitWidth(this.gamePane.getMinWidth());
        storeBackground.setFitHeight(this.gamePane.getMinHeight());
    }
    private void setInfoPanel(){
        
        
        /*
        weaponShot.setOnMouseDragged(e -> {
            weaponShot.setTranslateX(e.getSceneX());
            weaponShot.setTranslateY(e.getSceneY());
            System.out.println(weaponShot.getTranslateX() + ", " + weaponShot.getTranslateY());
        });
        */
        weaponCost.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        weaponDamage.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        weaponShot.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        
        itemCost.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        itemValue.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        itemUse.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        
        
        infoPanel.setTranslateX(487.0);
        infoPanel.setTranslateY(306.5);
        
        weaponCost.setTranslateX(500.0);
        weaponCost.setTranslateY(355.0);
        
        itemCost.setTranslateX(500.0);
        itemCost.setTranslateY(355.0);
        
        weaponDamage.setTranslateX(500.0);
        weaponDamage.setTranslateY(415.0);
        
        itemValue.setTranslateX(500.0);
        itemValue.setTranslateY(415.0);
        
        weaponShot.setTranslateX(500);
        weaponShot.setTranslateY(475.0);
        
        itemUse.setTranslateX(500.0);
        itemUse.setTranslateY(475.0);
    }
    private void addItemInfo(int index){
        itemCost.setText("Cost: " + weaponManager.getItemFromWeaponManager(index).getCostOfItem() + "$");
        itemValue.setText("Value: " + weaponManager.getItemFromWeaponManager(index).getValue());
        itemUse.setText(weaponManager.getItemFromWeaponManager(index).getUse());
        
        this.gamePane.getChildren().addAll(infoPanel, itemCost, itemValue, itemUse);
    }
    private void removeItemInfo(){
        this.gamePane.getChildren().removeAll(infoPanel, itemCost, itemValue, itemUse);
    }
    private void addWeaponInfo(int index){
        weaponCost.setText("Cost: " + weaponManager.getWeaponFromWeaponManager(index).getCostOfWeapon() + "$");
        weaponDamage.setText("Damage: " + weaponManager.getWeaponFromWeaponManager(index).getDamage());
        weaponShot.setText("Fire: " + weaponManager.getWeaponFromWeaponManager(index).getShotType());
        
        this.gamePane.getChildren().addAll(infoPanel, weaponCost, weaponDamage, weaponShot);
    }
    private void removeWeaponInfo(){
        this.gamePane.getChildren().removeAll(infoPanel, weaponCost, weaponDamage, weaponShot);
    }
    private void setPlayerInfo(Player player){
        playerName.setText(player.getUsername());
        playerMoney.setText(player.getMoney() + "$");
        
        this.gamePane.getChildren().addAll(playerName, playerMoney);
        
        playerName.setTranslateX(880.0);
        playerName.setTranslateY(574.5);
        
        playerMoney.setTranslateX(880.0);
        playerMoney.setTranslateY(698.0);
        
        /*
        playerName.setOnMouseDragged(e -> {
            playerName.setTranslateX(e.getSceneX());
            playerName.setTranslateY(e.getSceneY());
            System.out.println(playerName.getTranslateX() + ", " + playerName.getTranslateY());
        });
        
        playerMoney.setOnMouseDragged(e -> {
            playerMoney.setTranslateX(e.getSceneX());
            playerMoney.setTranslateY(e.getSceneY());
            System.out.println(playerMoney.getTranslateX() + ", " + playerMoney.getTranslateY());
        });
        */
        playerName.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        playerMoney.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        
    }
    
    public void openStore(Player player){//Will later request the player accessing the store
        this.gamePane.getChildren().add(storeBackground);
        this.player = player;
        
        /*
        this.gamePane.setOnMouseMoved(e -> {
            System.out.println((int)e.getSceneX() + ", " + (int)e.getSceneY());
        });
        */
        
        setInfoPanel();
        
        addAtomic();
        addLaser();
        addMissile();
        addShrapnel();
        addLMG();
        addHMG();
        addMine();
        addC4RC();
        
        addArmor();
        addEngine();
        
        addSmallRepair();
        addMediumRepair();
        addLargeRepair();
        
        addSmallShield();
        addMediumShield();
        addLargeShield();
        
        setPlayerInfo(this.player);
        storeOpened = true;
    }
    
    public void closeStore(){
        this.gamePane.getChildren().remove(storeBackground);
        this.gamePane.getChildren().remove(atomicBtn);
        this.gamePane.getChildren().remove(laserBtn);
        this.gamePane.getChildren().remove(missileBtn);
        this.gamePane.getChildren().remove(shrapnelBtn);
        this.gamePane.getChildren().remove(lmgBtn);
        this.gamePane.getChildren().remove(hmgBtn);
        this.gamePane.getChildren().remove(mineBtn);
        this.gamePane.getChildren().remove(c4rcBtn);
        this.gamePane.getChildren().remove(armorBtn);
        this.gamePane.getChildren().remove(engineBtn);
        this.gamePane.getChildren().remove(playerName);
        this.gamePane.getChildren().remove(playerMoney);
        this.gamePane.getChildren().remove(repairSmallBtn);
        this.gamePane.getChildren().remove(repairMediumBtn);
        this.gamePane.getChildren().remove(repairLargeBtn);
        this.gamePane.getChildren().remove(shieldSmallBtn);
        this.gamePane.getChildren().remove(shieldMediumBtn);
        this.gamePane.getChildren().remove(shieldLargeBtn);
        
        storeOpened = false;
    }
    
    public boolean isStoreOpened(){
        return storeOpened;
    }
    
    private void addAtomic(){
        this.gamePane.getChildren().add(atomicBtn);
        
        atomicBtn.setTranslateX(61.0);
        atomicBtn.setTranslateY(131.0);
        
        /*
        atomicBtn.setOnMouseDragged(e -> {
            atomicBtn.setTranslateX(e.getSceneX());
            atomicBtn.setTranslateY(e.getSceneY());
            System.out.println(atomicBtn.getTranslateX() + ", " + atomicBtn.getTranslateY());
        });
        */
        
        atomicBtn.setOnMouseReleased(e -> {
            buyItem(true, 3);
            System.out.println("Bought atomic bomb");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        atomicBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(3);
        });
        atomicBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeWeaponInfo();
        });
    }
    private void addLaser(){
        this.gamePane.getChildren().add(laserBtn);
        
        laserBtn.setTranslateX(61.0);
        laserBtn.setTranslateY(224.0);
        
        /*
        laserBtn.setOnMouseDragged(e -> {
            laserBtn.setTranslateX(e.getSceneX());
            laserBtn.setTranslateY(e.getSceneY());
            System.out.println(laserBtn.getTranslateX() + ", " + laserBtn.getTranslateY());
        });
        */
        
        laserBtn.setOnMouseReleased(e -> {
            buyItem(true, 4);
            System.out.println("Bought laser");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        laserBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(4);
        });
        laserBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            this.gamePane.getChildren().remove(infoPanel);
            removeWeaponInfo();
        });
    }
    private void addMissile(){
        this.gamePane.getChildren().add(missileBtn);
        
        missileBtn.setTranslateX(61.0);
        missileBtn.setTranslateY(315.0);
        
        /*
        missileBtn.setOnMouseDragged(e -> {
            missileBtn.setTranslateX(e.getSceneX());
            missileBtn.setTranslateY(e.getSceneY());
            System.out.println(missileBtn.getTranslateX() + ", " + missileBtn.getTranslateY());
        });
        */
        
        missileBtn.setOnMouseReleased(e -> {
            buyItem(true, 1);
            System.out.println("Bought missile");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        missileBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(1);
        });
        missileBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeWeaponInfo();
        });
    }
    private void addShrapnel(){
        this.gamePane.getChildren().add(shrapnelBtn);
        
        shrapnelBtn.setTranslateX(61.0);
        shrapnelBtn.setTranslateY(397.0);
        
        /*
        shrapnelBtn.setOnMouseDragged(e -> {
            shrapnelBtn.setTranslateX(e.getSceneX());
            shrapnelBtn.setTranslateY(e.getSceneY());
            System.out.println(shrapnelBtn.getTranslateX() + ", " + shrapnelBtn.getTranslateY());
        });
        */
        
        shrapnelBtn.setOnMouseReleased(e -> {
            buyItem(true, 2);
            System.out.println("Bought shrapnel");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        shrapnelBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(2);
        });
        shrapnelBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeWeaponInfo();
        });
    }
    private void addLMG(){
        this.gamePane.getChildren().add(lmgBtn);
        
        lmgBtn.setTranslateX(485.0);
        lmgBtn.setTranslateY(131.0);
        
        /*
        lmgBtn.setOnMouseDragged(e -> {
            lmgBtn.setTranslateX(e.getSceneX());
            lmgBtn.setTranslateY(e.getSceneY());
            System.out.println(lmgBtn.getTranslateX() + ", " + lmgBtn.getTranslateY());
        });
        */
        
        lmgBtn.setOnMouseReleased(e -> {
            buyItem(true, 6);
            System.out.println("Bought LMG");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        lmgBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(6);
        });
        lmgBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeWeaponInfo();
        });
    }
    private void addHMG(){
        this.gamePane.getChildren().add(hmgBtn);
        
        hmgBtn.setTranslateX(485.0);
        hmgBtn.setTranslateY(224.0);
        
        /*
        hmgBtn.setOnMouseDragged(e -> {
            hmgBtn.setTranslateX(e.getSceneX());
            hmgBtn.setTranslateY(e.getSceneY());
            System.out.println(hmgBtn.getTranslateX() + ", " + hmgBtn.getTranslateY());
        });
        */
        
        hmgBtn.setOnMouseReleased(e -> {
            buyItem(true, 5);
            System.out.println("Bought HMG");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        hmgBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(5);
        });
        hmgBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeWeaponInfo();
        });
    }
    private void addMine(){
        this.gamePane.getChildren().add(mineBtn);
        
        mineBtn.setTranslateX(816.0);
        mineBtn.setTranslateY(315.0);
        
        /*
        mineBtn.setOnMouseDragged(e -> {
            mineBtn.setTranslateX(e.getSceneX());
            mineBtn.setTranslateY(e.getSceneY());
            System.out.println(mineBtn.getTranslateX() + ", " + mineBtn.getTranslateY());
        });
        */
        
        mineBtn.setOnMouseReleased(e -> {
            buyItem(true, 7);
            System.out.println("Bought mine");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        mineBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(7);
        });
        mineBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeWeaponInfo();
        });
    }
    private void addC4RC(){
        this.gamePane.getChildren().add(c4rcBtn);
        
        c4rcBtn.setTranslateX(816.0);
        c4rcBtn.setTranslateY(397.0);
        
        /*
        c4rcBtn.setOnMouseDragged(e -> {
            c4rcBtn.setTranslateX(e.getSceneX());
            c4rcBtn.setTranslateY(e.getSceneY());
            System.out.println(c4rcBtn.getTranslateX() + ", " + c4rcBtn.getTranslateY());
        });
        */
        
        c4rcBtn.setOnMouseReleased(e -> {
            buyItem(true, 8);
            System.out.println("Bought C4RC");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        c4rcBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addWeaponInfo(8);
        });
        c4rcBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeWeaponInfo();
        });
    }
    
    private void addArmor(){
        this.gamePane.getChildren().add(armorBtn);
        
        armorBtn.setTranslateX(816.0);
        armorBtn.setTranslateY(131.0);
        
        /*
        armorBtn.setOnMouseDragged(e -> {
            armorBtn.setTranslateX(e.getSceneX());
            armorBtn.setTranslateY(e.getSceneY());
            System.out.println(armorBtn.getTranslateX() + ", " + armorBtn.getTranslateY());
        });
        */
        
        armorBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           this.gamePane.getChildren().add(infoPanel);
        });
        armorBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            this.gamePane.getChildren().remove(infoPanel);
        });
    }
    private void addEngine(){
        this.gamePane.getChildren().add(engineBtn);
        
        engineBtn.setTranslateX(816.0);
        engineBtn.setTranslateY(224.0);
        
        /*
        engineBtn.setOnMouseDragged(e -> {
            engineBtn.setTranslateX(e.getSceneX());
            engineBtn.setTranslateY(e.getSceneY());
            System.out.println(engineBtn.getTranslateX() + ", " + engineBtn.getTranslateY());
        });
        */
        
        engineBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           this.gamePane.getChildren().add(infoPanel);
        });
        engineBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            this.gamePane.getChildren().remove(infoPanel);
        });
    }
    
    private void addSmallRepair(){
        this.gamePane.getChildren().add(repairSmallBtn);
        
        repairSmallBtn.setTranslateX(48);
        repairSmallBtn.setTranslateY(545);
        
        /*
        repairSmallBtn.setOnMouseDragged(e -> {
            repairSmallBtn.setTranslateX(e.getSceneX());
            repairSmallBtn.setTranslateY(e.getSceneY());
            System.out.println(repairSmallBtn.getTranslateX() + ", " + repairSmallBtn.getTranslateY());
        });
        */
        
        repairSmallBtn.setOnMouseReleased(e -> {
            buyItem(false, 0);
            System.out.println("Bought Small Repair");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        repairSmallBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addItemInfo(0);
        });
        repairSmallBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeItemInfo();
        });
    }
    private void addMediumRepair(){
        this.gamePane.getChildren().add(repairMediumBtn);
        
        repairMediumBtn.setTranslateX(48);
        repairMediumBtn.setTranslateY(616);
        
        repairMediumBtn.setOnMouseReleased(e -> {
            buyItem(false, 1);
            System.out.println("Bought Medium Repair");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        repairMediumBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addItemInfo(1);
        });
        repairMediumBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeItemInfo();
        });
    }
    private void addLargeRepair(){
        this.gamePane.getChildren().add(repairLargeBtn);
        
        repairLargeBtn.setTranslateX(48);
        repairLargeBtn.setTranslateY(689.5);
        
        repairLargeBtn.setOnMouseReleased(e -> {
            buyItem(false, 2);
            System.out.println("Bought Large Repair");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        repairLargeBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addItemInfo(2);
        });
        repairLargeBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeItemInfo();
        });
    }
    private void addSmallShield(){
        this.gamePane.getChildren().add(shieldSmallBtn);
        
        shieldSmallBtn.setTranslateX(450.0);
        shieldSmallBtn.setTranslateY(527.0);
        
        /*
        shieldSmallBtn.setOnMouseDragged(e -> {
            shieldSmallBtn.setTranslateX(e.getSceneX());
            shieldSmallBtn.setTranslateY(e.getSceneY());
            System.out.println(shieldSmallBtn.getTranslateX() + ", " + shieldSmallBtn.getTranslateY());
        });
        */
        
        shieldSmallBtn.setOnMouseReleased(e -> {
            buyItem(false, 3);
            System.out.println("Bought Small Shield");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        shieldSmallBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addItemInfo(3);
        });
        shieldSmallBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeItemInfo();
        });
    }
    private void addMediumShield(){
        this.gamePane.getChildren().add(shieldMediumBtn);
        
        shieldMediumBtn.setTranslateX(450);
        shieldMediumBtn.setTranslateY(604.5);
        
        /*
        shieldMediumBtn.setOnMouseDragged(e -> {
            shieldMediumBtn.setTranslateX(e.getSceneX());
            shieldMediumBtn.setTranslateY(e.getSceneY());
            System.out.println(shieldMediumBtn.getTranslateX() + ", " + shieldMediumBtn.getTranslateY());
        });
        */
        
        shieldMediumBtn.setOnMouseReleased(e -> {
            buyItem(false, 4);
            System.out.println("Bought Medium Shield");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        shieldMediumBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addItemInfo(4);
        });
        shieldMediumBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeItemInfo();
        });
        
    }
    private void addLargeShield(){
        this.gamePane.getChildren().add(shieldLargeBtn);
        
        shieldLargeBtn.setTranslateX(450);
        shieldLargeBtn.setTranslateY(680.5);
        
        /*
        shieldLargeBtn.setOnMouseDragged(e -> {
            shieldLargeBtn.setTranslateX(e.getSceneX());
            shieldLargeBtn.setTranslateY(e.getSceneY());
            System.out.println(shieldLargeBtn.getTranslateX() + ", " + shieldLargeBtn.getTranslateY());
        });
        */
        
        shieldLargeBtn.setOnMouseReleased(e -> {
            buyItem(false, 5);
            System.out.println("Bought Large Shield");
            playerMoney.setText(player.getMoney() + "$");
        });
        
        shieldLargeBtn.setOnMouseEntered(e -> {
           this.gamePane.setCursor(Cursor.HAND);
           addItemInfo(5);
        });
        shieldLargeBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            removeItemInfo();
        });
    }
    
    private void buyItem(boolean type, int index){
        /*
        type = true means it is a weapon, must pick from weaponArray
        type = false means it is an item, must pick from itemArray
        index = index of item in relative array in weaponManager
        */
        
        
        
        if (type){
            this.player.addWeapon(index);
            this.player.removeMoney(weaponManager.getWeaponFromWeaponManager(index).getCostOfWeapon());
        }
        else {
            this.player.addItem(index);
            this.player.removeMoney(weaponManager.getItemFromWeaponManager(index).getCostOfItem());
        }
        
    }
    
    private void showCostAndAmount(boolean type, int index){
        /*
        type = true means it is a weapon, must pick from weaponArray
        type = false means it is an item, must pick from itemArray
        index = index of item in relative array
        
        displays the cost of buying the weapon/item as well as how many
        the player has in his/her inventory when the mouse is hovering over
        the button
        
        */
    }
    
    
}
