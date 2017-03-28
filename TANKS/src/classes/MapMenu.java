/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 *
 * @author Cedrik Dubois
 */
public class MapMenu extends Pane{
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    
    /*
    *
    *For now I'm using the var variable to switch between backgrounds since I only have two.
    *Later on, they will be in an array and it will cycle through that array as players 
    *cycle through the maps.
    *
    */
    
    private Background mountainBackground;
    private Background desertBackground;
    private BackgroundImage desertImage = new BackgroundImage(new Image("Texture/Menus/MapMenu/Desert Map.png", WIDTH, HEIGHT, false, true), 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    private BackgroundImage mountainImage = new BackgroundImage(new Image("Texture/Menus/MapMenu/Mountain Map.png", WIDTH, HEIGHT, false, true), 
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    private int var = 0;
    
    private ImageView playBtn;
    private ImageView backBtn;
    private ImageView rightBtn;
    
    private Image playBtnImage = new Image("Texture/Menus/MapMenu/Play Button.png");
    private Image playBtnHover = new Image("Texture/Menus/MapMenu/Play Button Hover.png");
    private Image playBtnClicked = new Image("Texture/Menus/MapMenu/Play Button Clicked.png");
    
    private Image backBtnImage = new Image("Texture/Menus/PlayerMenu/Back Button.png");
    private Image backBtnHover = new Image("Texture/Menus/PlayerMenu/Back Button Hover.png");
    private Image backBtnClicked = new Image("Texture/Menus/playerMenu/Back Button Clicked.png");
    
    private Image rightBtnImage = new Image("Texture/Menus/PlayerMenu/Right Arrow.png");
    private Image rightBtnHover = new Image("Texture/Menus/PlayerMenu/Right Arrow Hover.png");
    private Image rightBtnClicked = new Image("Texture/Menus/PlayerMenu/Right Arrow Clicked.png");
    
    public MapMenu(){
        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);
        
        setBackgrounds();
        setPlayBtn();
        setBackBtn();
        setRightBtn();
        
    }
    private void setRightBtn(){
        rightBtn = new ImageView(rightBtnImage);
        
        this.getChildren().add(rightBtn);
        
        rightBtn.setTranslateX(146.0);
        rightBtn.setTranslateY(-203.0);
        
        /*
        rightBtn.setOnMouseDragged(e -> {
            rightBtn.setTranslateX(e.getSceneX());
            rightBtn.setTranslateY(e.getSceneY());
            System.out.println(rightBtn.getTranslateX() + ", " + rightBtn.getTranslateY());
        });
        */
        
        rightBtn.setOnMouseClicked(e -> {
            if (var == 0){
                this.setBackground(desertBackground);
                var = 1;
            }
            else if (var == 1){
                this.setBackground(mountainBackground);
                var = 0;
            }
        });
        
        rightBtn.setOnMouseEntered(e -> {
            rightBtn.setImage(rightBtnHover);
        });
        
        rightBtn.setOnMouseExited(e -> {
            rightBtn.setImage(rightBtnImage);
        });
        
        rightBtn.setOnMousePressed(e -> {
            rightBtn.setImage(rightBtnClicked);
        });
        
        rightBtn.setOnMouseReleased(e -> {
            rightBtn.setImage(rightBtnHover);
        });
    }
    private void setBackBtn(){
        backBtn = new ImageView(backBtnImage);
        
        this.getChildren().add(backBtn);
        
        backBtn.setTranslateX(-162.0);
        backBtn.setTranslateY(-207.5);
        
        /*
        backBtn.setOnMouseDragged(e -> {
            backBtn.setTranslateX(e.getSceneX());
            backBtn.setTranslateY(e.getSceneY());
            System.out.println(backBtn.getTranslateX() + ", " + backBtn.getTranslateY());
        });
        */
        
        backBtn.setOnMouseEntered(e -> {
            backBtn.setImage(backBtnHover);
        });
        
        backBtn.setOnMouseExited(e -> {
            backBtn.setImage(backBtnImage);
        });
        
        backBtn.setOnMousePressed(e -> {
            backBtn.setImage(backBtnClicked);
        });
        
        backBtn.setOnMouseReleased(e -> {
            backBtn.setImage(backBtnHover);
        });
        
    }
    private void setPlayBtn(){
        playBtn = new ImageView(playBtnImage);
        
        this.getChildren().add(playBtn);
        
        playBtn.setTranslateX(10.5);
        playBtn.setTranslateY(176.0);
        
        /*
        playBtn.setOnMouseDragged(e -> {
            playBtn.setTranslateX(e.getSceneX());
            playBtn.setTranslateY(e.getSceneY());
            System.out.println(playBtn.getTranslateX() + ", " + playBtn.getTranslateY());
        });
        */
        
        playBtn.setOnMouseEntered(e -> {
            playBtn.setImage(playBtnHover);
        });
        
        playBtn.setOnMouseExited(e -> {
            playBtn.setImage(playBtnImage);
        });
        
        playBtn.setOnMousePressed(e -> {
            playBtn.setImage(playBtnClicked);
        });
        
        playBtn.setOnMouseReleased(e -> {
            playBtn.setImage(playBtnHover);
        });
    }
    private void setBackgrounds(){
        mountainBackground = new Background(mountainImage);
        desertBackground = new Background(desertImage);
        
        this.setBackground(mountainBackground);
    }
}
