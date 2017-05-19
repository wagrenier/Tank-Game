/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import GamePane.GamePane;
import Sounds.SoundLib;
import Weapon.WeaponManager;
import PlayerSettings.Player;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Cedrik Dubois
 */
public class Store {

    private boolean storeOpened = false;

    private GamePane gamePane;
    private WeaponManager weaponManager;
    private SoundLib sounds;
    private HUD hud;

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

    private Text itemCost = new Text("Cost of Item");
    private Text itemValue = new Text("Value of Item");
    private Text itemDesc = new Text("Description");

    //Error Panel
    private ImageView errorPanel = new ImageView(new Image("Texture/Menus/Store/Error Panel.png"));
    private boolean errorPanelOpen = false;

    private Text errorMsg = new Text("Insufficient funds");

    public Store(GamePane gamePane, WeaponManager weaponManager, SoundLib sounds, HUD hud) {
        this.gamePane = gamePane;
        this.weaponManager = weaponManager;
        this.sounds = sounds;
        this.hud = hud;

        storeBackground.setFitWidth(this.gamePane.getMinWidth());
        storeBackground.setFitHeight(this.gamePane.getMinHeight());
    }

    private void throwStoreError(String error) {
        if (sounds.isSoundPlaying()){
                    sounds.getError().seek(Duration.ZERO);
                    sounds.getError().play();
                }
        
        errorPanelOpen = true;
        errorMsg.setText(error);
        this.gamePane.getChildren().removeAll(errorPanel, errorMsg);
        this.gamePane.getChildren().addAll(errorPanel, errorMsg);
    }

    private void removeStoreError() {
        errorPanelOpen = false;

        this.gamePane.getChildren().removeAll(errorPanel, errorMsg);
        errorMsg.setText("");
    }

    private void setErrorPanel() {

        /*
        errorMsg.setOnMouseDragged(e -> {
            errorMsg.setTranslateX(e.getSceneX());
            errorMsg.setTranslateY(e.getSceneY());
            System.out.println(errorMsg.getTranslateX() + ", " + errorMsg.getTranslateY());
        });
        
        errorPanel.setOnMouseDragged(e -> {
            errorPanel.setTranslateX(e.getSceneX());
            errorPanel.setTranslateY(e.getSceneY());
            System.out.println(errorPanel.getTranslateX() + ", " + errorPanel.getTranslateY());
        });
         */
        errorPanel.setTranslateX(716.5);
        errorPanel.setTranslateY(28.0);

        errorMsg.setTranslateX(735.0);
        errorMsg.setTranslateY(88.5);

        errorMsg.setFont(Font.font("Verdana", FontWeight.BOLD, 35));

    }

    private void setInfoPanel() {

        this.gamePane.getChildren().addAll(infoPanel, itemCost, itemValue, itemDesc);

        /*
        weaponShot.setOnMouseDragged(e -> {
            weaponShot.setTranslateX(e.getSceneX());
            weaponShot.setTranslateY(e.getSceneY());
            System.out.println(weaponShot.getTranslateX() + ", " + weaponShot.getTranslateY());
        });
         */
        itemCost.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        itemValue.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        itemDesc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        infoPanel.setTranslateX(487.0);
        infoPanel.setTranslateY(306.5);

        itemCost.setTranslateX(500.0);
        itemCost.setTranslateY(355.0);

        itemValue.setTranslateX(500.0);
        itemValue.setTranslateY(415.0);

        itemDesc.setTranslateX(500);
        itemDesc.setTranslateY(475.0);

    }

    private void addItemInfo(int index) {
        itemCost.setText("Cost: " + weaponManager.getItemFromWeaponManager(index).getCostOfItem() + "$");
        itemValue.setText("Value: " + weaponManager.getItemFromWeaponManager(index).getValue());
        itemDesc.setText(weaponManager.getItemFromWeaponManager(index).getUse());

    }

    private void resetItemInfo() {
        itemCost.setText("Cost of Item");
        itemValue.setText("Value of Item");
        itemDesc.setText("Descrption");
    }

    private void addWeaponInfo(int index) {
        itemCost.setText("Cost: " + weaponManager.getWeaponFromWeaponManager(index).getCostOfWeapon() + "$");
        itemValue.setText("Damage: " + weaponManager.getWeaponFromWeaponManager(index).getDamage());
        itemDesc.setText("Fire: " + weaponManager.getWeaponFromWeaponManager(index).getShotType());

    }

    private void setPlayerInfo(Player player) {
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

    public void openStore(Player player) {//Will later request the player accessing the store
        this.gamePane.getChildren().add(storeBackground);
        this.player = player;

        /*
        this.gamePane.setOnMouseMoved(e -> {
            System.out.println((int)e.getSceneX() + ", " + (int)e.getSceneY());
        });
         */
        setInfoPanel();
        setErrorPanel();

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

    public void closeStore() {
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
        this.gamePane.getChildren().removeAll(infoPanel, itemCost, itemValue, itemDesc);
        this.gamePane.getChildren().remove(errorPanel);
        removeStoreError();

        storeOpened = false;
    }

    public boolean isStoreOpened() {
        return storeOpened;
    }

    private void addAtomic() {
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
            buyButtonAction(true, 3);
        });

        atomicBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(3);
            removeStoreError();
        });
        atomicBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addLaser() {
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
            buyButtonAction(true, 4);
        });

        laserBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(4);
            removeStoreError();
        });
        laserBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addMissile() {
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
            buyButtonAction(true, 1);
        });

        missileBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(1);
            removeStoreError();
        });
        missileBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addShrapnel() {
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
            buyButtonAction(true, 2);
        });

        shrapnelBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(2);
            removeStoreError();
        });
        shrapnelBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addLMG() {
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
            buyButtonAction(true, 6);
        });

        lmgBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(6);
            removeStoreError();
        });
        lmgBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addHMG() {
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
            buyButtonAction(true, 5);
        });

        hmgBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(5);
            removeStoreError();
        });
        hmgBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addMine() {
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
            buyButtonAction(true, 7);
        });

        mineBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(7);
            removeStoreError();
        });
        mineBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addC4RC() {
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
            buyButtonAction(true, 8);
        });

        c4rcBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addWeaponInfo(8);
            removeStoreError();
        });
        c4rcBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addArmor() {
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
            addItemInfo(6);

        });
        
        armorBtn.setOnMouseReleased(e -> {
            buyButtonAction(false, 6);
        });
        
        armorBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();

        });
    }

    private void addEngine() {
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
            addItemInfo(7);

        });
        
        engineBtn.setOnMouseReleased(e -> {
            buyButtonAction(false, 7);
        });
        
        engineBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addSmallRepair() {
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
            buyButtonAction(false, 0);
        });

        repairSmallBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addItemInfo(0);
            removeStoreError();
        });
        repairSmallBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addMediumRepair() {
        this.gamePane.getChildren().add(repairMediumBtn);

        repairMediumBtn.setTranslateX(48);
        repairMediumBtn.setTranslateY(616);

        repairMediumBtn.setOnMouseReleased(e -> {
            buyButtonAction(false, 1);
        });

        repairMediumBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addItemInfo(1);
            removeStoreError();
        });
        repairMediumBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addLargeRepair() {
        this.gamePane.getChildren().add(repairLargeBtn);

        repairLargeBtn.setTranslateX(48);
        repairLargeBtn.setTranslateY(689.5);

        repairLargeBtn.setOnMouseReleased(e -> {
            buyButtonAction(false, 2);
        });

        repairLargeBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addItemInfo(2);
            removeStoreError();
        });
        repairLargeBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addSmallShield() {
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
            buyButtonAction(false, 3);
        });

        shieldSmallBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addItemInfo(3);
            removeStoreError();
        });
        shieldSmallBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }

    private void addMediumShield() {
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
            buyButtonAction(false, 4);
        });

        shieldMediumBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addItemInfo(4);
            removeStoreError();
        });
        shieldMediumBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });

    }

    private void addLargeShield() {
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
            buyButtonAction(false, 5);
        });

        shieldLargeBtn.setOnMouseEntered(e -> {
            this.gamePane.setCursor(Cursor.HAND);
            addItemInfo(5);
            removeStoreError();
        });
        shieldLargeBtn.setOnMouseExited(e -> {
            this.gamePane.setCursor(cursorImg);
            resetItemInfo();
        });
    }
    
    //Made this method public so the AI can buy items too
    private void buyItem(boolean type, int index) {
        /*
        type = true means it is a weapon, must pick from weaponArray
        type = false means it is an item, must pick from itemArray
        index = index of item in relative array in weaponManager
         */

        if (type) {

            if (this.player.removeMoney(weaponManager.getWeaponFromWeaponManager(index).getCostOfWeapon()) == false) {
                if (errorPanelOpen = false) {
                    throwStoreError("Insuficient Funds");
                } else {
                    removeStoreError();
                    throwStoreError("Insuficient Funds");
                }
            } else {
                this.player.addWeapon(index);
                gamePane.getTanksAnimation().getHud().nextWeaponActionVerification();
                
                if (sounds.isSoundPlaying()){
                    sounds.getBought().seek(Duration.ZERO);
                    sounds.getBought().play();
                }
            }
            
        } else {

            if (this.player.removeMoney(weaponManager.getItemFromWeaponManager(index).getCostOfItem()) == false) {
                if (errorPanelOpen = false) {
                    throwStoreError("Insuficient Funds");
                } else {
                    removeStoreError();
                    throwStoreError("Insuficient Funds");
                }
            } else {
                
                if(index == 3 || index == 4 || index == 5){
                    if(index == 3 && gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield() > 90){
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setShield(weaponManager.getItemArrayList().get(index).getValue());
                        gamePane.getTanksAnimation().getHud().nextItemAction();

                        player.setShield(1);
                        hud.updateHealth();
                        
                        
                        if (sounds.isSoundPlaying()){
                    sounds.getBought().seek(Duration.ZERO);
                    sounds.getBought().play();
                }

                    }
                    else if(index == 3){
                        this.player.addMoney(100);
                        
                        throwStoreError("Item Already Bought");
                    }
                    else if(index == 4 && gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield() > 80){
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setShield(weaponManager.getItemArrayList().get(index).getValue());
                        gamePane.getTanksAnimation().getHud().nextItemAction();
                        player.setShield(2);
                        hud.updateHealth();
                        
                        
                        if (sounds.isSoundPlaying()){
                    sounds.getBought().seek(Duration.ZERO);
                    sounds.getBought().play();
                }
                    }
                    else if(index == 4){
                        this.player.addMoney(150);
                        
                        throwStoreError("Item Already Bought");
                    }
                    else if(index == 5 && gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield() > 70){
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setShield(weaponManager.getItemArrayList().get(index).getValue());
                        gamePane.getTanksAnimation().getHud().nextItemAction();
                        player.setShield(3);
                        hud.updateHealth();
                        
                        
                        if (sounds.isSoundPlaying()){
                    sounds.getBought().seek(Duration.ZERO);
                    sounds.getBought().play();
                }
                    }
                    else if(index == 5){
                        this.player.addMoney(200);
                        
                        throwStoreError("Item Already Bought");
                    }
                    System.out.println(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield());
            }
                else if(index == 6){
                    gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setArmour(weaponManager.getItemArrayList().get(index).getValue());
                    gamePane.getTanksAnimation().getHud().nextItemAction();
                    
                    if (sounds.isSoundPlaying()){
                    sounds.getBought().seek(Duration.ZERO);
                    sounds.getBought().play();
                }
                }
            
                else if(index == 7){
                    if(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getMaxPixelMove() > 500){
                        this.player.addMoney(200);
                        
                        if (sounds.isSoundPlaying()){
                            sounds.getError().seek(Duration.ZERO);
                            sounds.getError().play();
                        }
                    }
                    else{
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setMaxPixelMove(50);gamePane.getTanksAnimation().getHud().nextItemAction();
                        
                        if (sounds.isSoundPlaying()){
                    sounds.getBought().seek(Duration.ZERO);
                    sounds.getBought().play();
                }
                    }  
                    System.out.println(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getMaxPixelMove());
            }
                else{
                    this.player.addItem(index);
                    gamePane.getTanksAnimation().getHud().nextItemAction();
                    
                    if (sounds.isSoundPlaying()){
                    sounds.getBought().seek(Duration.ZERO);
                    sounds.getBought().play();
                }   
                }
                
                System.out.println("Bough Item");
            }
        }

    }
    
    public void buyItemAI(boolean type, int index, Player playerAI) {
        /*
        type = true means it is a weapon, must pick from weaponArray
        type = false means it is an item, must pick from itemArray
        index = index of item in relative array in weaponManager
         */

        if (type) {

            if (playerAI.removeMoney(weaponManager.getWeaponFromWeaponManager(index).getCostOfWeapon()) == false) {
                if (errorPanelOpen = false) {
                    throwStoreError("Insuficient Funds");
                } else {
                    removeStoreError();
                    throwStoreError("Insuficient Funds");
                }
            } else {
                playerAI.addWeapon(index);
            }
            System.out.println("Bought Item");
        } else {

            if (playerAI.removeMoney(weaponManager.getItemFromWeaponManager(index).getCostOfItem()) == false) {
                if (errorPanelOpen = false) {
                    throwStoreError("Insuficient Funds");
                } else {
                    removeStoreError();
                    throwStoreError("Insuficient Funds");
                }
            } else {
                if(index == 3 || index == 4 || index == 5){
                    if(index == 3 && gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield() > 90){
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setShield(weaponManager.getItemArrayList().get(index).getValue());
                    }
                    else if(index == 3){
                        playerAI.addMoney(100);
                    }
                    else if(index == 4 && gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield() > 80){
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setShield(weaponManager.getItemArrayList().get(index).getValue());
                    }
                    else if(index == 4){
                        playerAI.addMoney(150);
                    }
                    else if(index == 5 && gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield() > 70){
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setShield(weaponManager.getItemArrayList().get(index).getValue());
                    }
                    else if(index == 5){
                        playerAI.addMoney(200);
                    }
                    System.out.println(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShield());
            }
                
                
            
                else if(index == 7){
                    if(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getMaxPixelMove() > 500){
                        playerAI.addMoney(200);
                    }
                    else{
                        gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setMaxPixelMove(50);
                    }  
                    System.out.println(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getMaxPixelMove());
            }
                else{
                    playerAI.addItem(index);
                    gamePane.getTanksAnimation().getHud().nextItemActionVerification();
                }
                System.out.println("Bough Item");
            }
        }

    }

    private void buyButtonAction(boolean type, int index) {
        buyItem(type, index);
        playerMoney.setText(player.getMoney() + "$");
    }

    private void showCostAndAmount(boolean type, int index) {
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
