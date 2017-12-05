/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
package HUD;

import PlayerSettings.Player;
import Sounds.SoundLib;
import Tanks.Tanks;
import GamePane.GamePane;
import Weapon.WeaponManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Cedrik Dubois
 */
public class HUD extends Pane {
    
    private int WIDTH = 1200;
    private int HEIGHT = 150;

    private GamePane gamePane;

    private PauseMenu pauseMenu;

    private Store storeMenu;

    private SoundLib sounds;

    private int playerTurn = 0; //Set to zero for the moment but this value will be changed by the player manager during the game
 
    private Text player = new Text();
    private int playerIndex = 0;

    private WeaponManager weaponManager;
    private int weaponIndex = 0;
    private int itemIndex = 0;

    private Text weapon = new Text();
    private Text weaponAmount = new Text();
    private ImageView weaponLogo;

    private ArrayList<Player> playerList;

    private Text item = new Text();
    private Text itemAmount = new Text();
    private ImageView itemLogo;

    private Image emptyLogo = new Image("Texture/Menus/HUD/Not Available Logo.png");

    private ImageView muteBtn;
    private Image muteBtnImage = new Image("Texture/Menus/MainMenu/Mute Button.png");
    
    /**Sets the Image for the button*/
    private ImageView weaponBtn;
    /**Sets the Image for the button*/
    private Image weaponBtnImage = new Image("Texture/Menus/HUD/Right Arrow.png");
    /**Sets the Image for the button*/
    private Image weaponBtnClicked = new Image("Texture/Menus/HUD/Right Arrow Clicked.png");
    
    /**Sets the Image for the button*/
    private ImageView itemBtn;
    /**Sets the Image for the button*/
    private Image itemBtnImage = new Image("Texture/Menus/HUD/Right Arrow.png");
    /**Sets the Image for the button*/
    private Image itemBtnClicked = new Image("Texture/Menus/HUD/Right Arrow Clicked.png");
    
    /**Sets the Image for the button*/
    private ImageView storeBtn;
    /**Sets the Image for the button*/
    private Image storeBtnImage = new Image("Texture/Menus/HUD/Store Button.png");
    /**Sets the Image for the button*/
    private Image storeBtnClicked = new Image("Texture/Menus/HUD/Store Button Clicked.png");
    
    /**Sets the Image for the button*/
    private ImageView pauseBtn;
    /**Sets the Image for the button*/
    private Image pauseBtnImage = new Image("Texture/Menus/HUD/Pause Button.png");
    /**Sets the Image for the button*/
    private Image pauseBtnClicked = new Image("Texture/Menus/HUD/Pause Button Clicked.png");
    
    /**Sets the Image for the button*/
    private ImageView useBtn;
    /**Sets the Image for the button*/
    private Image useBtnImage = new Image("Texture/Menus/HUD/Use Button.png");
    /**Sets the Image for the button*/
    private Image useBtnClicked = new Image("Texture/Menus/HUD/Use BUtton Clicked.png");
    
    /**Sets the string for the gravity*/
    private String gravity;
    /**Sets the Text for the gravity*/
    private Text gravityLbl;
    
    /**Sets the value for the wind*/
    private double windResistance = 0.01;//Default value for the moment
    /**Sets the text for the wind*/
    private Text wind = new Text("Wind Res.: " + windResistance + "%");
    
    /**The progress bar of the player's health*/
    private ColoredProgressBar playerHealth = new ColoredProgressBar("green-bar", 1);
    
    /**The current player's tank that is being displayed in the hud*/
    private ImageView playerTank;

    /**Aray containg the four different countries*/
    private Image[] tanks = new Image[4];
    /**The texture for the tank in the HUD*/
    private Image canadaTank = new Image("Texture/Menus/HUD/Canada Tank.png");
    /**The texture for the tank in the HUD*/
    private Image usaTank = new Image("Texture/Menus/HUD/USA Tank.png");
    /**The texture for the tank in the HUD*/
    private Image northKoreaTank = new Image("Texture/Menus/HUD/North Korea Tank.png");
    /**The texture for the tank in the HUD*/
    private Image chinaTank = new Image("Texture/Menus/HUD/China Tank.png");
    
    /**The texture for the cursor in the Scene*/
    private ImageCursor cursorImg = new ImageCursor(new Image("Texture/Cursor/Cursor.png"));

    /**
     *Constructor
     * @param weaponManager
     * @param gamePane
     * @param sounds
     */
    public HUD(WeaponManager weaponManager, GamePane gamePane, SoundLib sounds) {

        this.sounds = sounds;

        this.setCursor(cursorImg);

        //gameScene = scene;
        this.weaponManager = weaponManager;
        this.setMinSize(WIDTH, HEIGHT);
        this.setMaxSize(WIDTH, HEIGHT);

        this.gamePane = gamePane;
        this.storeMenu = new Store(this.gamePane, this.weaponManager, this.sounds, this);
        this.pauseMenu = new PauseMenu(this.gamePane, this.storeMenu, this.sounds, this.cursorImg);

        //The gravity in the hud is multiplied by 100 to prevent the game from displaying 5e-6
        //Instead, it will just display 0.05
        gravity = "" + gamePane.getMapGeneration().getGravity() * 100;
        gravityLbl = new Text("Gravity: " + gravity);

        playerList = gamePane.getPlayerArrayList();
        setPlayer();

        weapon.setText(weaponManager.getWeaponFromWeaponManager(weaponIndex).getWeaponName());
        weaponAmount.setText(playerList.get(playerIndex).getWeaponInventory()[weaponIndex] + "");
        weaponLogo = new ImageView(weaponManager.getWeaponFromWeaponManager(weaponIndex).getTexture());

        item.setText(weaponManager.getItemFromWeaponManager(itemIndex).getName());
        itemAmount.setText(playerList.get(playerIndex).getItemInventory()[itemIndex] + "");
        itemLogo = new ImageView(weaponManager.getItemFromWeaponManager(itemIndex).getItemImage());

        setTanks();

        setBackground();

        setWeapon();
        setWeaponBtn();

        setItem();
        setItemBtn();

        setGravity();
        setWind();

        setHealth();
        setPlayerTank();

        setStoreBtn();
        setPauseBtn();
        setUseBtn();

        setMuteBtn();

    }
    /**Sets the properties for this elements*/
    private void setMuteBtn() {
        muteBtn = new ImageView(muteBtnImage);

        this.getChildren().add(muteBtn);

        muteBtn.setTranslateX(795.0);
        muteBtn.setTranslateY(87.0);

        /*
        muteBtn.setOnMouseDragged(e -> {
            muteBtn.setTranslateX(e.getSceneX());
            muteBtn.setTranslateY(e.getSceneY());
            System.out.println(muteBtn.getTranslateX() + ", " + muteBtn.getTranslateY());
        });
         */
        muteBtn.setOnMouseReleased(e -> {
            if (sounds.isSoundPlaying()) {
                sounds.getBackgroundMusic().pause();
                sounds.setSoundPlaying(false);
            } else if (sounds.isSoundPlaying() == false) {
                sounds.getBackgroundMusic().play();
                sounds.setSoundPlaying(true);
            }
        });

        muteBtn.setOnMouseEntered(e -> {
            this.setCursor(Cursor.HAND);
        });

        muteBtn.setOnMouseExited(e -> {
            this.setCursor(cursorImg);
        });
    }

    /**
     *Generates a new wind resistance
     */
    public void generateNewWindRes() {
        /**
         * private double windResistance = 30;//Default value for the moment
         * private Text wind = new Text("Wind Res.: " + windResistance);
         */

        windResistance = Math.random();
        if (gamePane.getMapGeneration().getMapIndex() == 2) {
            //Because there is no wind or resistance in space ;)
            windResistance = 0;
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            //Multiplied by 100 to show in %
            wind.setText("Wind Res.: " + df.format(windResistance) + "%");
        } else {
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            //Multiplied by 100 to show in %
            wind.setText("Wind Res.: " + df.format((1 - windResistance) * 100) + "%");
        }

    }
    
    /**Sets the properties for this elements*/
    private void setTanks() {
        tanks[0] = northKoreaTank;
        tanks[1] = usaTank;
        tanks[2] = canadaTank;
        tanks[3] = chinaTank;
    }
    
    /**Sets the properties for this elements*/
    private void setUseBtn() {
        useBtn = new ImageView(useBtnImage);

        this.getChildren().add(useBtn);

        /*
        useBtn.setOnMouseDragged(e -> {
            useBtn.setTranslateX(e.getSceneX());
            useBtn.setTranslateY(e.getSceneY());
            System.out.println(useBtn.getTranslateX() + ", " + useBtn.getTranslateY());
        });
         */
        useBtn.setTranslateX(344.5);
        useBtn.setTranslateY(84.0);

        useBtn.setOnMouseEntered(e -> {
            this.setCursor(Cursor.HAND);
        });

        useBtn.setOnMouseExited(e -> {
            this.setCursor(cursorImg);
        });

        useBtn.setOnMousePressed(e -> {
            useBtn.setImage(useBtnClicked);
        });

        useBtn.setOnMouseReleased(e -> {
            useBtn.setImage(useBtnImage);

            //Do something
            useItem();
        });

    }

    /**
     *
     * @param itemIndexAI
     */
    public void useItemAI(int itemIndexAI) {
        if (isItemInventoryEmpty()) {
            nextItemAction();
        } else {

            if (itemIndexAI == 6) {
                gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setArmour(weaponManager.getItemArrayList().get(itemIndexAI).getValue());
                updateItemStatusAI(itemIndexAI);
                nextItemActionVerification();
            } else if (itemIndexAI == 0 || itemIndexAI == 1 || itemIndexAI == 2) {
                if (gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].restoreLifePoints(weaponManager.getItemArrayList().get(itemIndexAI).getValue())) {
                    updateHealth(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getLifePoint());
                    updateItemStatusAI(itemIndexAI);
                    nextItemActionVerification();
                }
            }

        }
    }

    /**
     *
     */
    public void useItem() {
        if (isItemInventoryEmpty()) {
            nextItemAction();
        } else {

            if (itemIndex == 6) {
                gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].setArmour(weaponManager.getItemArrayList().get(itemIndex).getValue());
                updateItemStatus();
                nextItemActionVerification();
            } else if (itemIndex == 0 || itemIndex == 1 || itemIndex == 2) {
                if (gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].restoreLifePoints(weaponManager.getItemArrayList().get(itemIndex).getValue())) {
                    System.out.println("-------TEST-------");
                    
                    updateHealth(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getLifePoint());
                    updateHealth(gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getShieldType());
                    updateItemStatus();
                    nextItemActionVerification();
                    if (sounds.isSoundPlaying()) {
                        sounds.getRepair().seek(Duration.ZERO);
                        sounds.getRepair().play();
                    }
                }
            }

        }
    }
    
    /**Sets the properties for this elements*/
    private void setItemBtn() {
        itemBtn = new ImageView(itemBtnImage);
        this.getChildren().add(itemBtn);

        itemBtn.setTranslateX(406.0);
        itemBtn.setTranslateY(86.5);

        /*
        itemBtn.setOnMouseDragged(e -> {
            itemBtn.setTranslateX(e.getSceneX());
            itemBtn.setTranslateY(e.getSceneY());
            System.out.println(itemBtn.getTranslateX() + ", " + itemBtn.getTranslateY());
        });
         */
        itemBtn.setOnMouseEntered(e -> {
            this.setCursor(Cursor.HAND);
        });

        itemBtn.setOnMouseExited(e -> {
            this.setCursor(cursorImg);
        });

        itemBtn.setOnMousePressed(e -> {
            itemBtn.setImage(itemBtnClicked);

            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        itemBtn.setOnMouseReleased(e -> {
            itemBtn.setImage(itemBtnImage);
            nextItemAction();
        });
    }

    /**
     *
     */
    public void nextItemActionVerification() {
        if (playerList.get(playerIndex).getItemInventory()[itemIndex] == 0) {
            nextItemAction();
        } else {

        }
    }

    /**
     *
     */
    public void nextWeaponActionVerification() {
        if (playerList.get(playerIndex).getWeaponInventory()[weaponIndex] == 0) {

        } else {
            nextWeaponAction();
        }
    }

    /**
     *
     */
    public void nextItemAction() {
        if (isItemInventoryEmpty() == false) {
            do {
                if (itemIndex < playerList.get(playerIndex).getItemInventory().length - 1) {
                    itemIndex++;
                } else {
                    itemIndex = 0;
                }
            } while (playerList.get(playerIndex).getItemInventory()[itemIndex] == 0);

            item.setText(weaponManager.getItemFromWeaponManager(itemIndex).getName());
            itemAmount.setText(playerList.get(playerIndex).getItemInventory()[itemIndex] + "");
            itemLogo.setImage(weaponManager.getItemFromWeaponManager(itemIndex).getItemImage());
        } else {
            item.setText("Empty");
            itemAmount.setText("");
            itemLogo.setImage(emptyLogo);
        }
    }

    private void setWeaponBtn() {
        weaponBtn = new ImageView(weaponBtnImage);
        this.getChildren().add(weaponBtn);

        weaponBtn.setTranslateX(406.0);
        weaponBtn.setTranslateY(8.5);

        /*
        weaponBtn.setOnMouseDragged(e -> {
            weaponBtn.setTranslateX(e.getSceneX());
            weaponBtn.setTranslateY(e.getSceneY());
            System.out.println(weaponBtn.getTranslateX() + ", " + weaponBtn.getTranslateY());
        });
         */
        weaponBtn.setOnMouseEntered(e -> {
            this.setCursor(Cursor.HAND);
        });

        weaponBtn.setOnMouseExited(e -> {
            this.setCursor(cursorImg);
        });

        weaponBtn.setOnMousePressed(e -> {
            weaponBtn.setImage(weaponBtnClicked);

            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        weaponBtn.setOnMouseReleased(e -> {
            weaponBtn.setImage(weaponBtnImage);
            nextWeaponAction();
        });

    }

    /**
     *
     */
    public void nextWeaponAction() {
        if (gamePane.getTanksAnimation().getProgressBarAnimationUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].getStatus().compareTo(Animation.Status.RUNNING) == 0 || gamePane.getTanksAnimation().getRCAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0 || gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(Animation.Status.RUNNING) == 0) {

        } else {
            do {
                if (weaponIndex < playerList.get(playerIndex).getWeaponInventory().length - 1) {
                    weaponIndex++;
                } else {
                    weaponIndex = 0;
                }
            } while (playerList.get(playerIndex).getWeaponInventory()[weaponIndex] == 0);

            weapon.setText(weaponManager.getWeaponFromWeaponManager(weaponIndex).getWeaponName());
            weaponAmount.setText(playerList.get(playerIndex).getWeaponInventory()[weaponIndex] + "");
            weaponLogo.setImage(weaponManager.getWeaponFromWeaponManager(weaponIndex).getTexture());
        }
    }
    
    /**Sets the properties for this elements*/
    private void setPauseBtn() {
        pauseBtn = new ImageView(pauseBtnImage);
        this.getChildren().add(pauseBtn);

        pauseBtn.setTranslateX(1107.0);
        pauseBtn.setTranslateY(84.0);

        /*
        pauseBtn.setOnMouseDragged(e -> {
            pauseBtn.setTranslateX(e.getSceneX());
            pauseBtn.setTranslateY(e.getSceneY());
            System.out.println(pauseBtn.getTranslateX() + ", " + pauseBtn.getTranslateY());
        });
         */
        pauseBtn.setOnMouseEntered(e -> {
            this.setCursor(Cursor.HAND);
        });

        pauseBtn.setOnMouseExited(e -> {
            this.setCursor(cursorImg);
        });

        pauseBtn.setOnMousePressed(e -> {

            pauseBtn.setImage(pauseBtnClicked);

            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }

        });

        pauseBtn.setOnMouseReleased(e -> {
            pauseBtn.setImage(pauseBtnImage);
           
                if (pauseMenu.isGamePaused() && storeMenu.isStoreOpened() && pauseMenu.isMenuOpen() == false) {
                    pauseMenu.openMenuWithoutPause();
                } else if (pauseMenu.isGamePaused() && storeMenu.isStoreOpened() && pauseMenu.isMenuOpen()) {
                    pauseMenu.closeMenuWithoutResume();
                } else if (pauseMenu.isGamePaused() == false && storeMenu.isStoreOpened() == false && pauseMenu.isMenuOpen() == false) {
                    pauseMenu.pauseGame();
                } else if (pauseMenu.isGamePaused() && storeMenu.isStoreOpened() == false && pauseMenu.isMenuOpen() == true) {
                    pauseMenu.resumeGame();
                }
            

            System.out.println("Game Pause: " + pauseMenu.isGamePaused());
            System.out.println("Store Open: " + storeMenu.isStoreOpened());
            System.out.println("Pause Menu Open: " + pauseMenu.isMenuOpen());
        });

    }
    
    /**Sets the properties for this elements*/
    private void setStoreBtn() {
        storeBtn = new ImageView(storeBtnImage);
        this.getChildren().add(storeBtn);

        storeBtn.setTranslateX(1107.0);
        storeBtn.setTranslateY(34.0);

        /*
        storeBtn.setOnMouseDragged(e -> {
            storeBtn.setTranslateX(e.getSceneX());
            storeBtn.setTranslateY(e.getSceneY());
            System.out.println(storeBtn.getTranslateX() + ", " + storeBtn.getTranslateY());
        });
         */
        storeBtn.setOnMouseEntered(e -> {
            this.setCursor(Cursor.HAND);
        });

        storeBtn.setOnMouseExited(e -> {
            this.setCursor(cursorImg);
        });

        storeBtn.setOnMousePressed(e -> {
            storeBtn.setImage(storeBtnClicked);

            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        storeBtn.setOnMouseReleased(e -> {
            if (gamePane.getTanksAnimation().getTanksArrayUsed()[gamePane.getTanksAnimation().getIndexOfCurrentPlayerTurn()].isIsAI()) {

            } else {
                storeBtn.setImage(storeBtnImage);

                if (storeMenu.isStoreOpened() && pauseMenu.isGamePaused() && pauseMenu.isMenuOpen()) {

                } else if (storeMenu.isStoreOpened() == false && pauseMenu.isGamePaused() == false) {
                    storeMenu.openStore(playerList.get(playerIndex));
                    pauseMenu.pauseGame(1);
                    System.out.println(playerList.get(playerIndex).getUsername());
                } else if (storeMenu.isStoreOpened() == true) {
                    storeMenu.closeStore();
                    pauseMenu.resumeGame(1);
                }
            }

            System.out.println("Game Pause: " + pauseMenu.isGamePaused());
            System.out.println("Store Open: " + storeMenu.isStoreOpened());
            System.out.println("Pause Menu Open: " + pauseMenu.isMenuOpen());
        });

    }
    
    /**Sets the properties for this elements*/
    private void setPlayerTank() {

        playerTank = new ImageView(tanks[playerTurn]);
        this.getChildren().add(playerTank);

        playerTank.setTranslateX(934.5);
        playerTank.setTranslateY(52.5);

        /*
        playerTank.setOnMouseDragged(e -> {
            playerTank.setTranslateX(e.getSceneX());
            playerTank.setTranslateY(e.getSceneY());
            System.out.println(playerTank.getTranslateX() + ", " + playerTank.getTranslateY());
        });
         */
    }
    
    /**Sets the value of the current player's health in the progress bar*/
    private void setHealth() {
        this.getChildren().add(playerHealth);

        playerHealth.setTranslateX(922.5);
        playerHealth.setTranslateY(46.5);

        playerHealth.getStylesheets().add(getClass().getResource("progress.css").toExternalForm());


        /*
         playerHealth.setOnMouseDragged(e -> {
             playerHealth.setTranslateX(e.getSceneX());
             playerHealth.setTranslateY(e.getSceneY());
             System.out.println(playerHealth.getTranslateX() + ", " + playerHealth.getTranslateY());
         });
         */
 /*
         playerHealth.setOnMouseClicked(e -> {
             
             if (playerHealth.getProgress() > 0.05){
             playerHealth.setProgress(playerHealth.getProgress() - (double)0.05);
             
             
             //Find a better way to optimize this because right now it calls the method everytime after
             //getting lower than 0.3 instead of just once.
             if (playerHealth.getProgress() <= 0.3){
                 playerHealth.setColor("red-bar");
             }
             }
             else
                 playerHealth.setProgress(0);
             
             System.out.println(playerHealth.getProgress());
         });*/
    }

    /**
     *
     */
    public void updateHealth(Player player) {
        
        if (player.getShield() == 0) {
            playerHealth.setBorderColor("default-color");
        } else if (player.getShield() == 1) {
            playerHealth.setBorderColor("small-shield");
        } else if (player.getShield() == 2) {
            playerHealth.setBorderColor("medium-shield");
        } else if (player.getShield() == 3) {
            playerHealth.setBorderColor("large-shield");
        }
    }

    /**
     *
     * @param lifePoints
     */
    public void updateHealth(int lifePoints) {
        

        if (lifePoints < 0) {
            playerHealth.setProgress(0);
        } else {
            playerHealth.setProgress((double) lifePoints / 100.0);
        }

        System.out.println(lifePoints);
        if (lifePoints <= 30) {
            
            playerHealth.setColor("red-bar");
        } else if (lifePoints > 30) {
            
            playerHealth.setColor("green-bar");
        }
        

    }
    
    /**
     *
     * @return
     */
    public boolean isItemInventoryEmpty() {
        for (int i = 0; i < playerList.get(playerIndex).getItemInventory().length; i++) {
            if (playerList.get(playerIndex).getItemInventory()[i] > 0) {
                return false;
            }
        }

        return true;
    }
    
    /**Sets the properties for this elements*/
    private void setWind() {
        this.getChildren().add(wind);

        wind.setTranslateX(998.0);
        wind.setTranslateY(36.5);
/*
        wind.setOnMouseDragged(e -> {
            wind.setTranslateX(e.getSceneX());
            wind.setTranslateY(e.getSceneY());
            System.out.println(wind.getTranslateX() + ", " + wind.getTranslateY());
        });
*/
        wind.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    }
    
    /**Sets the properties for this elements*/
    private void setGravity() {
        this.getChildren().add(gravityLbl);

        //gravity = something that will be passed from somewhere. This is where gravity will be initialised
        gravityLbl.setTranslateX(808.5);
        gravityLbl.setTranslateY(36.5);

        /*
        gravityLbl.setOnMouseDragged(e -> {
            gravityLbl.setTranslateX(e.getSceneX());
            gravityLbl.setTranslateY(e.getSceneY());
            System.out.println(gravityLbl.getTranslateX() + ", " + gravityLbl.getTranslateY());
        });
         */
        gravityLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    }
    
    /**Sets the properties for this elements*/
    private void setItem() {
        this.getChildren().addAll(item, itemLogo, itemAmount);

        item.setTranslateX(28.5);
        item.setTranslateY(122.0);

        itemLogo.setTranslateX(239.0);
        itemLogo.setTranslateY(74.0);

        itemAmount.setTranslateX(323.0);
        itemAmount.setTranslateY(119.0);

        /*
        item.setOnMouseDragged(e -> {
            item.setTranslateX(e.getSceneX());
            item.setTranslateY(e.getSceneY());
            System.out.println(item.getTranslateX() + ", " + item.getTranslateY());
        });
        
        itemLogo.setOnMouseDragged(e -> {
            itemLogo.setTranslateX(e.getSceneX());
            itemLogo.setTranslateY(e.getSceneY());
            System.out.println(itemLogo.getTranslateX() + ", " + itemLogo.getTranslateY());
        });
        
        itemAmount.setOnMouseDragged(e -> {
            itemAmount.setTranslateX(e.getSceneX());
            itemAmount.setTranslateY(e.getSceneY());
            System.out.println(itemAmount.getTranslateX() + ", " + itemAmount.getTranslateY());
        });
         */
        item.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        itemAmount.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        itemLogo.setFitHeight(75);
        itemLogo.setFitWidth(75);
    }
    
    /**Sets the properties for this elements*/
    private void setWeapon() {
        //The list of weapons will be passed on by some method
        this.getChildren().addAll(weapon, weaponLogo, weaponAmount);

        weapon.setTranslateX(26.0);
        weapon.setTranslateY(48.0);

        weaponLogo.setTranslateX(218.5);
        weaponLogo.setTranslateY(-13.5);

        weaponAmount.setTranslateX(323.0);
        weaponAmount.setTranslateY(42.5);

        /*
        weapon.setOnMouseDragged(e -> {
            weapon.setTranslateX(e.getSceneX());
            weapon.setTranslateY(e.getSceneY());
            System.out.println(weapon.getTranslateX() + ", " + weapon.getTranslateY());
        });
        
        
        weaponLogo.setOnMouseDragged(e -> {
            weaponLogo.setTranslateX(e.getSceneX());
            weaponLogo.setTranslateY(e.getSceneY());
            System.out.println(weaponLogo.getTranslateX() + ", " + weaponLogo.getTranslateY());
        });
        
       
       weaponAmount.setOnMouseDragged(e -> {
           weaponAmount.setTranslateX(e.getSceneX());
           weaponAmount.setTranslateY(e.getSceneY());
           System.out.println(weaponAmount.getTranslateX() + ", " + weaponAmount.getTranslateY());
       });
         */
        weapon.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        weaponAmount.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        weaponLogo.setFitHeight(100);
        weaponLogo.setFitWidth(100);
    }
    
    /**Sets the properties for this elements*/
    private void setPlayer() {
        //Using player 1 - 4 for now, but these names will be passed by the Player objects
        //coming from the menu classes
        this.getChildren().add(player);

        player.setTranslateX(558.5);
        player.setTranslateY(123.5);

        /*
        player.setOnMouseDragged(e -> {
            player.setTranslateX(e.getSceneX());
            player.setTranslateY(e.getSceneY());
            System.out.println(player.getTranslateX() + ", " + player.getTranslateY());
        });
         */
        player.setFont(Font.font("Verdana", FontWeight.BOLD, 35));

    }    
    
    /**Sets the properties for this elements*/
    private void setBackground() {
        BackgroundImage myBI = new BackgroundImage(new Image("Texture/Menus/HUD/Background.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));
    }
    
    /**
     *
     */
    public void resetWeaponIndex() {
        weaponIndex = 0;

        weapon.setText(weaponManager.getWeaponFromWeaponManager(weaponIndex).getWeaponName());
        weaponAmount.setText(playerList.get(playerIndex).getWeaponInventory()[weaponIndex] + "");
        weaponLogo.setImage(weaponManager.getWeaponFromWeaponManager(weaponIndex).getTexture());
    }

    /**
     *
     */
    public void resetItemIndex() {
        itemIndex = 0;
        System.out.println(isItemInventoryEmpty() + ", " + playerList.get(playerIndex).getUsername());
        if (isItemInventoryEmpty() == false) {
            item.setText(weaponManager.getItemFromWeaponManager(itemIndex).getName());
            itemAmount.setText(playerList.get(playerIndex).getItemInventory()[itemIndex] + "");
            itemLogo.setImage(weaponManager.getItemFromWeaponManager(itemIndex).getItemImage());
        } else {
            item.setText("Empty");
            itemAmount.setText("");
            itemLogo.setImage(emptyLogo);
        }
    }
    
    /**
     *
     * @param tank
     * @param team
     * @param indexCurrentPlayer
     */
    public void setCurrentPlayerTank(Tanks tank, int team, int indexCurrentPlayer) {
        playerIndex = indexCurrentPlayer;
        playerTank.setImage(tanks[team]);

        playerHealth.setProgress((double) ((double) tank.getLifePoint()) / 100);

        if (tank.getLifePoint() <= 30) {
            System.out.println("Less than 30 points");
            playerHealth.setColor("red-bar");
        } else if (tank.getLifePoint() > 30) {
            System.out.println("More than 30 points");
            playerHealth.setColor("green-bar");
        }
        

        if (this.playerList.get(playerIndex).getShield() == 0) {
            System.out.println("Shield 0");
            playerHealth.setBorderColor("default-color");
        } else if (this.playerList.get(playerIndex).getShield() == 1) {
            System.out.println("Shield 1");
            playerHealth.setBorderColor("small-shield");
        } else if (this.playerList.get(playerIndex).getShield() == 2) {
            System.out.println("Shield 2");
            playerHealth.setBorderColor("medium-shield");
        } else if (this.playerList.get(playerIndex).getShield() == 3) {
            System.out.println("Shield 3");
            playerHealth.setBorderColor("large-shield");
        }
    }
    
    //Beginning of Setters and Getters

    /**
     *
     * @return
     */
    
    public Text getWeapon() {
        return weapon;
    }

    /**
     *
     * @param weapon
     */
    public void setWeapon(Text weapon) {
        this.weapon = weapon;
    }

    /**
     *
     * @return
     */
    public Text getWeaponCost() {
        return weaponAmount;
    }

    /**
     *
     * @param weaponCost
     */
    public void setWeaponCost(Text weaponCost) {
        this.weaponAmount = weaponCost;
    }

    /**
     *
     * @return
     */
    public ImageView getWeaponLogo() {
        return weaponLogo;
    }

    /**
     *
     * @param weaponLogo
     */
    public void setWeaponLogo(ImageView weaponLogo) {
        this.weaponLogo = weaponLogo;
    }

    /**
     *
     * @return
     */
    public GamePane getGamePane() {
        return gamePane;
    }

    /**
     *
     * @return
     */
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    /**
     *
     * @return
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     *
     * @return
     */
    public int getWIDTH() {
        return WIDTH;
    }

    /**
     *
     * @return
     */
    public int getHEIGHT() {
        return HEIGHT;
    }

    /**
     *
     * @return
     */
    public Text getPlayer() {
        return player;
    }

    /**
     *
     * @return
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     *
     * @return
     */
    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    /**
     *
     * @return
     */
    public int getWeaponIndex() {
        return weaponIndex;
    }

    /**
     *
     * @return
     */
    public ImageView getWeaponBtn() {
        return weaponBtn;
    }

    /**
     *
     * @return
     */
    public Image getWeaponBtnImage() {
        return weaponBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getWeaponBtnClicked() {
        return weaponBtnClicked;
    }

    /**
     *
     * @return
     */
    public ImageView getStoreBtn() {
        return storeBtn;
    }

    /**
     *
     * @return
     */
    public Image getStoreBtnImage() {
        return storeBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getStoreBtnClicked() {
        return storeBtnClicked;
    }

    /**
     *
     * @return
     */
    public ImageView getPauseBtn() {
        return pauseBtn;
    }

    /**
     *
     * @return
     */
    public Image getPauseBtnImage() {
        return pauseBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getPauseBtnClicked() {
        return pauseBtnClicked;
    }

    /**
     *
     * @return
     */
    public String getGravity() {
        return gravity;
    }

    /**
     *
     * @return
     */
    public Text getGravityLbl() {
        return gravityLbl;
    }

    /**
     *
     * @return
     */
    public double getWindResistance() {
        return windResistance;
    }

    /**
     *
     * @return
     */
    public Text getWind() {
        return wind;
    }

    /**
     *
     * @return
     */
    public ColoredProgressBar getPlayerHealth() {
        return playerHealth;
    }

    /**
     *
     * @return
     */
    public ImageView getPlayerTank() {
        return playerTank;
    }

    /**
     *
     * @return
     */
    public Image[] getTanks() {
        return tanks;
    }

    /**
     *
     * @return
     */
    public Image getCanadaTank() {
        return canadaTank;
    }

    /**
     *
     * @param name
     */
    public void setCurrentPlayerName(String name) {
        player.setText(name);
    }
    
    /**
     *
     */
    public void updateWeaponStatus() {
        playerList.get(playerIndex).removeWeapon(weaponIndex);
    }

    /**
     *
     */
    public void updateItemStatus() {
        playerList.get(playerIndex).removeItem(itemIndex);
    }

    /**
     *
     * @param itemIndexAI
     */
    public void updateItemStatusAI(int itemIndexAI) {
        playerList.get(playerIndex).removeItem(itemIndexAI);
    }

    /**
     *
     * @param index
     * @return
     */
    public Player getCurrentPlayerTurn(int index) {
        return playerList.get(index);
    }

    /**
     *
     * @return
     */
    public ColoredProgressBar getHealth() {
        return this.playerHealth;
    }

    /**
     *
     * @return
     */
    public Store getStoreMenu() {
        return storeMenu;
    }

    /**
     *
     * @return
     */
    public SoundLib getSounds() {
        return sounds;
    }

    /**
     *
     * @param sounds
     */
    public void setSounds(SoundLib sounds) {
        this.sounds = sounds;
    }

    /**
     *
     * @return
     */
    public int getItemIndex() {
        return itemIndex;
    }

    /**
     *
     * @param itemIndex
     */
    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     *
     * @return
     */
    public Text getWeaponAmount() {
        return weaponAmount;
    }

    /**
     *
     * @param weaponAmount
     */
    public void setWeaponAmount(Text weaponAmount) {
        this.weaponAmount = weaponAmount;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     *
     * @param playerList
     */
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     *
     * @return
     */
    public Text getItem() {
        return item;
    }

    /**
     *
     * @param item
     */
    public void setItem(Text item) {
        this.item = item;
    }

    /**
     *
     * @return
     */
    public Text getItemAmount() {
        return itemAmount;
    }

    /**
     *
     * @param itemAmount
     */
    public void setItemAmount(Text itemAmount) {
        this.itemAmount = itemAmount;
    }

    /**
     *
     * @return
     */
    public ImageView getItemLogo() {
        return itemLogo;
    }

    /**
     *
     * @param itemLogo
     */
    public void setItemLogo(ImageView itemLogo) {
        this.itemLogo = itemLogo;
    }

    /**
     *
     * @return
     */
    public Image getEmptyLogo() {
        return emptyLogo;
    }

    /**
     *
     * @param emptyLogo
     */
    public void setEmptyLogo(Image emptyLogo) {
        this.emptyLogo = emptyLogo;
    }

    /**
     *
     * @return
     */
    public ImageView getMuteBtn() {
        return muteBtn;
    }

    /**
     *
     * @param muteBtn
     */
    public void setMuteBtn(ImageView muteBtn) {
        this.muteBtn = muteBtn;
    }

    /**
     *
     * @return
     */
    public Image getMuteBtnImage() {
        return muteBtnImage;
    }

    /**
     *
     * @param muteBtnImage
     */
    public void setMuteBtnImage(Image muteBtnImage) {
        this.muteBtnImage = muteBtnImage;
    }

    /**
     *
     * @return
     */
    public ImageView getItemBtn() {
        return itemBtn;
    }

    /**
     *
     * @param itemBtn
     */
    public void setItemBtn(ImageView itemBtn) {
        this.itemBtn = itemBtn;
    }

    /**
     *
     * @return
     */
    public Image getItemBtnImage() {
        return itemBtnImage;
    }

    /**
     *
     * @param itemBtnImage
     */
    public void setItemBtnImage(Image itemBtnImage) {
        this.itemBtnImage = itemBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getItemBtnClicked() {
        return itemBtnClicked;
    }

    /**
     *
     * @param itemBtnClicked
     */
    public void setItemBtnClicked(Image itemBtnClicked) {
        this.itemBtnClicked = itemBtnClicked;
    }

    /**
     *
     * @return
     */
    public ImageView getUseBtn() {
        return useBtn;
    }

    /**
     *
     * @param useBtn
     */
    public void setUseBtn(ImageView useBtn) {
        this.useBtn = useBtn;
    }

    /**
     *
     * @return
     */
    public Image getUseBtnImage() {
        return useBtnImage;
    }

    /**
     *
     * @param useBtnImage
     */
    public void setUseBtnImage(Image useBtnImage) {
        this.useBtnImage = useBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getUseBtnClicked() {
        return useBtnClicked;
    }

    /**
     *
     * @param useBtnClicked
     */
    public void setUseBtnClicked(Image useBtnClicked) {
        this.useBtnClicked = useBtnClicked;
    }

    /**
     *
     * @return
     */
    public Image getUsaTank() {
        return usaTank;
    }

    /**
     *
     * @param usaTank
     */
    public void setUsaTank(Image usaTank) {
        this.usaTank = usaTank;
    }

    /**
     *
     * @return
     */
    public Image getNorthKoreaTank() {
        return northKoreaTank;
    }

    /**
     *
     * @param northKoreaTank
     */
    public void setNorthKoreaTank(Image northKoreaTank) {
        this.northKoreaTank = northKoreaTank;
    }

    /**
     *
     * @return
     */
    public Image getChinaTank() {
        return chinaTank;
    }

    /**
     *
     * @param chinaTank
     */
    public void setChinaTank(Image chinaTank) {
        this.chinaTank = chinaTank;
    }

    /**
     *
     * @return
     */
    public ImageCursor getCursorImg() {
        return cursorImg;
    }

    /**
     *
     * @param cursorImg
     */
    public void setCursorImg(ImageCursor cursorImg) {
        this.cursorImg = cursorImg;
    }
    
    //End of Setters and Getters
}