/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;


import Weapon.WeaponManager;
import java.util.ArrayList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
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

/**
 *
 * @author Cedrik Dubois
 */
public class HUD extends Pane{
    
    //Scene gameScene;
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 150;
    
    private Text player = new Text();
    private ArrayList<String> playerNames = new ArrayList<>();
    private static int playerIndex = 0;
    
    WeaponManager weaponManager;
    private int weaponIndex = 0;
    
    //private Text weapon = new Text("Missile");
    //private Text weaponCost = new Text("($0.00)");
    //private ImageView weaponLogo = new ImageView(new Image("Texture/Items/Normal/Missile.png"));
    private Text weapon = new Text();
    private Text weaponCost = new Text();
    private ImageView weaponLogo;
    
    private ImageView weaponBtn;
    private Image weaponBtnImage = new Image("Texture/Menus/HUD/Right Arrow.png");
    private Image weaponBtnClicked = new Image("Texture/Menus/HUD/Right Arrow Clicked.png");
    
    private String gravity = "9.8";//Default value for the moment
    private Text gravityLbl = new Text("Gravity: " + gravity);
    
    private int windResistance = 30;//Default value for the moment
    private Text wind = new Text("Wind Res.: " + windResistance);
    
    private ColoredProgressBar playerHealth = new ColoredProgressBar("green-bar", 1);
    
    
    public HUD(WeaponManager weaponManager){
        //gameScene = scene;
        this.weaponManager = weaponManager;
        this.setMinSize(WIDTH, HEIGHT);
        this.setMaxSize(WIDTH, HEIGHT);
        
        weapon.setText(weaponManager.getWeaponFromWeaponManager(weaponIndex).getWeaponName());
        weaponCost.setText(weaponManager.getWeaponFromWeaponManager(weaponIndex).getCostOfWeapon() + "$");
        weaponLogo = new ImageView(weaponManager.getWeaponFromWeaponManager(weaponIndex).getTexture());
        
        setBackground();
        setPlayer();
        setWeapon();
        setWeaponBtn();
        setGravity();
        setWind();
        setHealth();
    }
    private void setHealth(){
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
         
         playerHealth.setOnMouseClicked(e -> {
             playerHealth.setProgress(playerHealth.getProgress() - 0.05);
             
             
             //Find a better way to optimize this because right now it calls the method everytime after
             //getting lower than 0.3 instead of just once.
             if (playerHealth.getProgress() <= 0.3){
                 playerHealth.setColor("red-bar");
             }
         });
    }
    private void setWind(){
        this.getChildren().add(wind);
        
        wind.setTranslateX(998.0);
        wind.setTranslateY(36.5);
        
        wind.setOnMouseDragged(e -> {
            wind.setTranslateX(e.getSceneX());
            wind.setTranslateY(e.getSceneY());
            System.out.println(wind.getTranslateX() + ", " + wind.getTranslateY());
        });
        
        wind.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    }
    private void setGravity(){
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
    private void setWeaponBtn(){
        weaponBtn = new ImageView(weaponBtnImage);
        this.getChildren().add(weaponBtn);
        
        weaponBtn.setTranslateX(430.0);
        weaponBtn.setTranslateY(12.0);
        
        
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
            this.setCursor(Cursor.DEFAULT);
        });
        
        weaponBtn.setOnMousePressed(e -> {
            weaponBtn.setImage(weaponBtnClicked);
            if(weaponIndex > 0 || weaponIndex < 8)
            weaponIndex++;
            
            else
                weaponIndex = 0;
            
            this.getChildren().remove(weaponLogo);
            weapon.setText(weaponManager.getWeaponFromWeaponManager(weaponIndex).getWeaponName());
        weaponCost.setText(weaponManager.getWeaponFromWeaponManager(weaponIndex).getCostOfWeapon() + "$");
        weaponLogo = new ImageView(weaponManager.getWeaponFromWeaponManager(weaponIndex).getTexture());
         this.getChildren().add(weaponLogo);
        });
        
        weaponBtn.setOnMouseReleased(e -> {
            weaponBtn.setImage(weaponBtnImage);
        });
        
    }
    private void setWeapon(){
        //The list of weapons will be passed on by some method
        this.getChildren().addAll(weapon, weaponLogo, weaponCost);
        
        weapon.setTranslateX(26.0);
        weapon.setTranslateY(48.0);
        
        weaponLogo.setTranslateX(218.5);
        weaponLogo.setTranslateY(-13.5);
        
        weaponCost.setTranslateX(323.0);
        weaponCost.setTranslateY(42.5);
        
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
        
       
       weaponCost.setOnMouseDragged(e -> {
           weaponCost.setTranslateX(e.getSceneX());
           weaponCost.setTranslateY(e.getSceneY());
           System.out.println(weaponCost.getTranslateX() + ", " + weaponCost.getTranslateY());
       });
       */ 
       
        weapon.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        weaponCost.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        weaponLogo.setFitHeight(100);
        weaponLogo.setFitWidth(100);
    }
    private void setPlayer(){
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
        
        
        playerNames.add("Player 1");
        playerNames.add("Player 2");
        playerNames.add("Player 3");
        playerNames.add("Player 4");
        
        player.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        player.setText(playerNames.get(playerIndex));
    }
    public void nextPlayer(){
        if (playerIndex == 3)
            playerIndex = 0;
        else
            playerIndex++;
        
        player.setText(playerNames.get(playerIndex));
    }

    public Text getWeapon() {
        return weapon;
    }

    public void setWeapon(Text weapon) {
        this.weapon = weapon;
    }

    public Text getWeaponCost() {
        return weaponCost;
    }

    public void setWeaponCost(Text weaponCost) {
        this.weaponCost = weaponCost;
    }

    public ImageView getWeaponLogo() {
        return weaponLogo;
    }

    public void setWeaponLogo(ImageView weaponLogo) {
        this.weaponLogo = weaponLogo;
    }
    
    private void setBackground(){
        BackgroundImage myBI= new BackgroundImage(new Image("Texture/Menus/HUD/Background.png", WIDTH, HEIGHT, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        this.setBackground(new Background(myBI));
    }
}
