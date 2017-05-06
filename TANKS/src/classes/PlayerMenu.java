/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
public class PlayerMenu extends Pane{
    
    private Scene scene;
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 950;
    
    private Text playerOutput;
    private int numberOfPlayers = 2; //Default value is 2 since you cannot play this game alone
    
    private ImageView leftBtn;
    private ImageView rightBtn;
    private ImageView nextBtn;
    private ImageView backBtn;
    
    private Image leftBtnImage = new Image("Texture/Menus/PlayerMenu/Left Arrow.png");
    private Image leftBtnHover = new Image("Texture/Menus/PlayerMenu/left Arrow Hover.png");
    private Image leftBtnClicked = new Image("Texture/Menus/PlayerMenu/Left Arrow Clicked.png");
    
    private Image rightBtnImage = new Image("Texture/Menus/PlayerMenu/Right Arrow.png");
    private Image rightBtnHover = new Image("Texture/Menus/PlayerMenu/Right Arrow Hover.png");
    private Image rightBtnClicked = new Image("Texture/Menus/PlayerMenu/Right Arrow Clicked.png");
    
    private Image nextBtnImage = new Image("Texture/Menus/PlayerMenu/Next Button.png");
    private Image nextBtnHover = new Image("Texture/Menus/PlayerMenu/Next Button Hover.png");
    private Image nextBtnClicked = new Image("Texture/Menus/PlayerMenu/Next Button Clicked.png");
    
    private Image backBtnImage = new Image("Texture/Menus/PlayerMenu/Back Button.png");
    private Image backBtnHover = new Image("Texture/Menus/PlayerMenu/Back Button Hover.png");
    private Image backBtnClicked = new Image("Texture/Menus/playerMenu/Back Button Clicked.png");
    
    
    public PlayerMenu(){
        this.setMaxHeight(HEIGHT);
        this.setMinHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMinWidth(WIDTH);
        
        setBackground();
        
        setText();
        setLeftBtn();
        setRightBtn();
        setNextBtn();
        setBackBtn();
        
        
    }
    public void setScene(Scene scene, ImageView cursor, MouseEvent m){
        this.getChildren().remove(cursor);
        
        this.getChildren().add(cursor);
        cursor.setTranslateX(m.getSceneX());
        cursor.setTranslateY(m.getSceneY());
        
        scene.setOnMouseEntered(e -> {
            this.getChildren().add(cursor);
            cursor.setTranslateX(e.getSceneX());
            cursor.setTranslateY(e.getSceneY());
        });
        
        scene.setOnMouseExited(e -> {
            this.getChildren().remove(cursor);
        });
        
        scene.setOnMouseMoved(e -> {
            cursor.setTranslateX(e.getSceneX());
            cursor.setTranslateY(e.getSceneY());
        });
    }
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }
    public ImageView getNextBtn(){
        return nextBtn;
    }
    public ImageView getBackBtn(){
        return backBtn;
    }
    private void setBackBtn(){
        backBtn = new ImageView(backBtnImage);
        
        this.getChildren().add(backBtn);
        
        backBtn.setTranslateX(-173.0);
        backBtn.setTranslateY(-195.5);
        
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
    private void setNextBtn(){
        nextBtn = new ImageView(nextBtnImage);
        
        this.getChildren().add(nextBtn);
        
        nextBtn.setTranslateX(123.0);
        nextBtn.setTranslateY(134.0);
        
        /*
        nextBtn.setOnMouseDragged(e -> {
            nextBtn.setTranslateX(e.getSceneX());
            nextBtn.setTranslateY(e.getSceneY());
            System.out.println(nextBtn.getTranslateX() + ", " + nextBtn.getTranslateY());
        });
        */
        
        nextBtn.setOnMouseEntered(e -> {
            nextBtn.setImage(nextBtnHover);
        });
        
        nextBtn.setOnMouseExited(e -> {
            nextBtn.setImage(nextBtnImage);
        });
        
        nextBtn.setOnMousePressed(e -> {
            nextBtn.setImage(nextBtnClicked);
        });
        
        nextBtn.setOnMouseReleased(e -> {
            nextBtn.setImage(nextBtnHover);
        });
    }
    private void updatePlayers(boolean var){
        if (var){
            if (numberOfPlayers > 2){
                numberOfPlayers--;
                playerOutput.setText(numberOfPlayers + "");
            }
            else{
                numberOfPlayers = 4;
                playerOutput.setText(numberOfPlayers + "");
            }
        }
        else
        {
            if (numberOfPlayers < 4){
                numberOfPlayers++;
                playerOutput.setText(numberOfPlayers + "");
            }
            else
            {
                numberOfPlayers = 2;
                playerOutput.setText(numberOfPlayers + "");
            }
        }
    }
    private void setLeftBtn(){
        leftBtn = new ImageView(leftBtnImage);
        
        this.getChildren().add(leftBtn);
        
        leftBtn.setTranslateX(-94.5);
        leftBtn.setTranslateY(-37.0);
        
        /*
        leftBtn.setOnMouseDragged(e -> {
            leftBtn.setTranslateX(e.getSceneX());
            leftBtn.setTranslateY(e.getSceneY());
            System.out.println(leftBtn.getTranslateX() + ", " + leftBtn.getTranslateY());
        });
        */
        
        leftBtn.setOnMouseEntered(e -> {
            leftBtn.setImage(leftBtnHover);
        });
        
        leftBtn.setOnMouseExited(e -> {
            leftBtn.setImage(leftBtnImage);
        });
        
        leftBtn.setOnMouseClicked(e -> {
            updatePlayers(true);
        });
        
        leftBtn.setOnMousePressed(e -> {
            leftBtn.setImage(leftBtnClicked);
        });
        
        leftBtn.setOnMouseReleased(e -> {
            leftBtn.setImage(leftBtnHover);
        });
    }
    private void setRightBtn(){
        rightBtn = new ImageView(rightBtnImage);
        
        this.getChildren().add(rightBtn);
        
        rightBtn.setTranslateX(-8.5);
        rightBtn.setTranslateY(-17.0);
        
        /*
        rightBtn.setOnMouseDragged(e -> {
            rightBtn.setTranslateX(e.getSceneX());
            rightBtn.setTranslateY(e.getSceneY());
            System.out.println(rightBtn.getTranslateX() + ", " + rightBtn.getTranslateY());
        });
        */
        
        rightBtn.setOnMouseEntered(e -> {
            rightBtn.setImage(rightBtnHover);
        });
        
        rightBtn.setOnMouseExited(e -> {
            rightBtn.setImage(rightBtnImage);
        });
        
        rightBtn.setOnMouseClicked(e -> {
            updatePlayers(false);
        });
        
        rightBtn.setOnMousePressed(e -> {
            rightBtn.setImage(rightBtnClicked);
        });
        
        rightBtn.setOnMouseReleased(e -> {
            rightBtn.setImage(rightBtnHover);
        });
    }
    private void setText(){
        playerOutput = new Text(numberOfPlayers + "");
        
        this.getChildren().add(playerOutput);
        
        playerOutput.setTranslateX(538.5);
        playerOutput.setTranslateY(309.5);
       
        
        /*
        //Only used for modifying psoition of text
        
        playerOutput.setOnMouseDragged(e -> {
            playerOutput.setTranslateX(e.getSceneX());
            playerOutput.setTranslateY(e.getSceneY());
            System.out.println(playerOutput.getTranslateX() + ", " + playerOutput.getTranslateY());
        });
        */
        
        playerOutput.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
    }
    private void setBackground(){
        BackgroundImage myBI= new BackgroundImage(new Image("Texture/Menus/PlayerMenu/Background.png", WIDTH, HEIGHT, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        this.setBackground(new Background(myBI));
    }
    
}
