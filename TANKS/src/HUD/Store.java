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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cedrik Dubois
 */
public class Store{
    
    private boolean storeOpened = false;
    
    private GamePane gamePane;
    
    private Player player;//Player accessing the store
    
    private Image buyImg = new Image("Texture/Menus/Store/Buy Button.png");
    
    private transient ImageView storeBackground = new ImageView(new Image("Texture/Menus/Store/Store.png"));

    
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
    
    public Store(GamePane gamePane, WeaponManager weaponManager){
        this.gamePane = gamePane;
        
        
        storeBackground.setFitWidth(this.gamePane.getMinWidth());
        storeBackground.setFitHeight(this.gamePane.getMinHeight());
    }
    
    public void openStore(Player player){//Will later request the player accessing the store
        this.gamePane.getChildren().add(storeBackground);
        this.player = player;
        
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
        `*/
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
    }
    private void addC4RC(){
        this.gamePane.getChildren().add(c4rcBtn);
        
        c4rcBtn.setTranslateX(816.0);
        c4rcBtn.setTranslateY(397.0);
        
        c4rcBtn.setOnMouseDragged(e -> {
            c4rcBtn.setTranslateX(e.getSceneX());
            c4rcBtn.setTranslateY(e.getSceneY());
            System.out.println(c4rcBtn.getTranslateX() + ", " + c4rcBtn.getTranslateY());
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
    }
    
    private void buyItem(boolean type, int index){
        /*
        type = true means it is a weapon, must pick from weaponArray
        type = false means it is an item, must pick from itemArray
        index = index of item in relative array in weaponManager
        */
        
        
        
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
