/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;


import java.util.ArrayList;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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
    
    Scene gameScene;
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 150;
    
    private Text player = new Text();
    private ArrayList<String> playerNames = new ArrayList<>();
    private static int playerIndex = 0;
    
    private Text weapon = new Text("Missile");
    private ImageView weaponLogo = new ImageView(new Image("Texture/Items/Normal/Missile.png"));
    
    private ImageView weaponBtn;
    private Image weaponBtnImage = new Image("Texture/Menus/HUD/Right Arrow.png");
    
    
    public HUD(Scene scene){
        gameScene = scene;
        
        this.setMinSize(WIDTH, HEIGHT);
        this.setMaxSize(WIDTH, HEIGHT);
        
        setBackground();
        setPlayer();
        setWeapon();
        setWeaponBtn();
    }
    private void setWeaponBtn(){
        weaponBtn = new ImageView(weaponBtnImage);
        this.getChildren().add(weaponBtn);
        
        weaponBtn.setTranslateX(322.5);
        weaponBtn.setTranslateY(10.5);
        
        
        /*
        weaponBtn.setOnMouseDragged(e -> {
            weaponBtn.setTranslateX(e.getSceneX());
            weaponBtn.setTranslateY(e.getSceneY());
            System.out.println(weaponBtn.getTranslateX() + ", " + weaponBtn.getTranslateY());
        });
        */
        
        weaponBtn.setOnMouseEntered(e -> {
            gameScene.setCursor(Cursor.HAND);
        });
        
        weaponBtn.setOnMouseExited(e -> {
            gameScene.setCursor(Cursor.DEFAULT);
        });
        
    }
    private void setWeapon(){
        //The list of weapons will be passed on by some method
        this.getChildren().add(weapon);
        this.getChildren().add(weaponLogo);
        
        weapon.setTranslateX(26.0);
        weapon.setTranslateY(48.0);
        
        weaponLogo.setTranslateX(218.5);
        weaponLogo.setTranslateY(-13.5);
        
        /*
        weapon.setOnMouseDragged(e -> {
            weapon.setTranslateX(e.getSceneX());
            weapon.setTranslateY(e.getSceneY());
            System.out.println(weapon.getTranslateX() + ", " + weapon.getTranslateY());
        });
        */
        /*
        weaponLogo.setOnMouseDragged(e -> {
            weaponLogo.setTranslateX(e.getSceneX());
            weaponLogo.setTranslateY(e.getSceneY());
            System.out.println(weaponLogo.getTranslateX() + ", " + weaponLogo.getTranslateY());
        });
        */
        
        weapon.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
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
    
    private void setBackground(){
        BackgroundImage myBI= new BackgroundImage(new Image("Texture/Menus/HUD/Background.png", WIDTH, HEIGHT, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        this.setBackground(new Background(myBI));
    }
}
