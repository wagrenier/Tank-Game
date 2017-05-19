/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Sounds.SoundLib;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
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
import javafx.util.Duration;

/**
 *
 * @author Cedrik Dubois
 */
public class CountryMenu extends Pane{
    
    private SoundLib sounds;
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 950;
    
    private static int player = 1;
    private static int tankCount = 0;
    
    private ImageView backBtn;
    private ImageView nextBtn;
    private ImageView leftBtn;
    private ImageView rightBtn;
    private ImageView tank;
    private ImageView flag;
    private ImageView muteBtn;
    
    private CheckBox ai = new CheckBox();
    
    private ArrayList<Image> tankList = new ArrayList<>();
    private ArrayList<Image> flagList = new ArrayList<>();
    
    private Image muteBtnImage = new Image("Texture/Menus/MainMenu/Mute Button.png");
    
    private Image nextBtnImage = new Image("Texture/Menus/CountryMenu/Next Button.png");
    private Image nextBtnHover = new Image("Texture/Menus/CountryMenu/Next Button Hover.png");
    private Image nextBtnClicked = new Image("Texture/Menus/CountryMenu/Next Button Clicked.png");
    
    private Image backBtnImage = new Image("Texture/Menus/PlayerMenu/Back Button.png");
    private Image backBtnHover = new Image("Texture/Menus/PlayerMenu/Back Button Hover.png");
    private Image backBtnClicked = new Image("Texture/Menus/playerMenu/Back Button Clicked.png");
    
    private Image leftBtnImage = new Image("Texture/Menus/PlayerMenu/Left Arrow.png");
    private Image leftBtnHover = new Image("Texture/Menus/PlayerMenu/left Arrow Hover.png");
    private Image leftBtnClicked = new Image("Texture/Menus/PlayerMenu/Left Arrow Clicked.png");
    
    private Image rightBtnImage = new Image("Texture/Menus/PlayerMenu/Right Arrow.png");
    private Image rightBtnHover = new Image("Texture/Menus/PlayerMenu/Right Arrow Hover.png");
    private Image rightBtnClicked = new Image("Texture/Menus/PlayerMenu/Right Arrow Clicked.png");
    
    Image northkorea = new Image("Texture/Menus/CountryMenu/North Korea Selection Tank.png");
    Image usa = new Image("Texture/Menus/CountryMenu/USA Selection Tank.png");
    Image canada = new Image("Texture/Menus/CountryMenu/Canada Selection Tank.png");
    Image china = new Image("Texture/Menus/CountryMenu/China Selection Tank.png");
    
    
    private TextField usernameField;
    
    public CountryMenu(SoundLib sounds){
        this.sounds = sounds;
        
        this.setMaxHeight(HEIGHT);
        this.setMinHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMinWidth(WIDTH);
        
        setBackground();
        setTankList();
        setFlagList();
        
        setNextBtn();
        setRightBtn();
        setLeftBtn();
        setTank();
        setFlag();
        setUsername();
        setBackBtn();
        setAI();
        setMuteBtn();
    }
    private void setMuteBtn(){
        muteBtn = new ImageView(muteBtnImage);
        
        this.getChildren().add(muteBtn);
        
        muteBtn.setTranslateX(456.0);
        muteBtn.setTranslateY(654.0);
        
        /*
        muteBtn.setOnMouseDragged(e -> {
            muteBtn.setTranslateX(e.getSceneX());
            muteBtn.setTranslateY(e.getSceneY());
            System.out.println(muteBtn.getTranslateX() + ", " + muteBtn.getTranslateY());
        });
        */
        
        muteBtn.setOnMouseReleased(e -> {
            if (sounds.isSoundPlaying()){
                sounds.getBackgroundMusic().pause();
                sounds.setSoundPlaying(false);
            }
            else if (sounds.isSoundPlaying() == false){
                sounds.getBackgroundMusic().play();
                sounds.setSoundPlaying(true);
            }
        });
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
        
        scene.getStylesheets().add("classes/checkbox.css");
    }
    public void resetPane(){
        setTankList();
        setFlagList();
        tankCount = 0;
        player = 0;
        tank.setImage(tankList.get(tankCount));
        flag.setImage(flagList.get(tankCount));
    }
    public void removeTeam(int country){
        tankList.remove(country);
        flagList.remove(country);
        
    }
    public String getPlayerName(){
        return usernameField.getText();
    }
    public int getTankCount(){
        return tankCount;
    }
    public int getCountry(){
        if (tankList.get(tankCount).equals(northkorea))
            return 0;
        else if (tankList.get(tankCount).equals(usa))
            return 1;
        else if (tankList.get(tankCount).equals(canada))
            return 2;
        else if (tankList.get(tankCount).equals(china))
            return 3;
        else
            return -1; //Will cause error if method return -1;
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
            if (sounds.isSoundPlaying()){
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });
        
        backBtn.setOnMouseReleased(e -> {
            backBtn.setImage(backBtnHover);
        });
        
    }
    private void setUsername(){
        usernameField = new TextField("Player " + player);
        usernameField.setStyle("-fx-background-color: transparent;");
        this.getChildren().add(usernameField);
        
        usernameField.setTranslateX(470.5);
        usernameField.setTranslateY(233.5);
        
        /*
        usernameField.setOnMouseDragged(e -> {
            usernameField.setTranslateX(e.getSceneX());
            usernameField.setTranslateY(e.getSceneY());
            System.out.println(usernameField.getTranslateX() + ", " + usernameField.getTranslateY());
        });
        */
        
        usernameField.setPrefColumnCount(10);
        usernameField.setMinHeight(40);
        
        usernameField.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        usernameField.setAlignment(Pos.CENTER);
        usernameField.setFocusTraversable(false);
    }
    private void setLeftBtn(){
        leftBtn = new ImageView(leftBtnImage);
        
        this.getChildren().add(leftBtn);
        
        leftBtn.setTranslateX(-170.0);
        leftBtn.setTranslateY(-18.0);
        
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
        
        leftBtn.setOnMousePressed(e -> {
            leftBtn.setImage(leftBtnClicked);
            if (sounds.isSoundPlaying()){
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });
        
        leftBtn.setOnMouseReleased(e -> {
            leftBtn.setImage(leftBtnHover);
            if (tankCount == 0)
                tankCount = tankList.size() - 1;
            else
                tankCount--;
            
            tank.setImage(tankList.get(tankCount));
            flag.setImage(flagList.get(tankCount));
        });
    }
    private void setRightBtn(){
        rightBtn = new ImageView(rightBtnImage);
        
        this.getChildren().add(rightBtn);
        
        rightBtn.setTranslateX(152.5);
        rightBtn.setTranslateY(3.5);
        
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
        
        rightBtn.setOnMousePressed(e -> {
            rightBtn.setImage(rightBtnClicked);
            if (sounds.isSoundPlaying()){
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });
        
        rightBtn.setOnMouseReleased(e -> {
            rightBtn.setImage(rightBtnHover);
            if (tankCount == tankList.size() - 1)
                tankCount = 0;
            else
                tankCount++;
            
            tank.setImage(tankList.get(tankCount));
            flag.setImage(flagList.get(tankCount));
            //getCountry();
        });
    }
    private void setFlag(){
        flag = new ImageView(flagList.get(tankCount));
        flag.setFitWidth(175);
        flag.setFitHeight(100);
        
        this.getChildren().add(flag);
        
        flag.setTranslateX(505.0);
        flag.setTranslateY(295.5);
        
        /*
        flag.setOnMouseDragged(e -> {
            flag.setTranslateX(e.getSceneX());
            flag.setTranslateY(e.getSceneY());
            System.out.println(flag.getTranslateX() + ", " + flag.getTranslateY());
        });
        */
    }
    private void setTank(){
        tank = new ImageView(tankList.get(tankCount));
        
        this.getChildren().add(tank);
        
        tank.setTranslateX(-24.5);
        tank.setTranslateY(50.5);
        
        /*
        tank.setOnMouseDragged(e -> {
            tank.setTranslateX(e.getSceneX());
            tank.setTranslateY(e.getSceneY());
            System.out.println(tank.getTranslateX() + ", " + tank.getTranslateY());
        });
        */
        
    }
    private void setTankList(){
        tankList.clear();
        
        tankList.add(northkorea);
        tankList.add(usa);
        tankList.add(canada);
        tankList.add(china);
        
    }
    private void setFlagList(){
        flagList.clear();
        
        flagList.add(new Image("Texture/Menus/CountryMenu/North Korea Flag.png"));
        flagList.add(new Image("Texture/Menus/CountryMenu/USA Flag.png"));
        flagList.add(new Image("Texture/Menus/CountryMenu/Canada Flag.png"));
        flagList.add(new Image("Texture/Menus/CountryMenu/China Flag.png"));
    }
    private void setAI(){
        this.getChildren().add(ai);
        
        ai.setTranslateX(632.0);
        ai.setTranslateY(544.5);
        
        /*
        ai.setOnMouseDragged(e -> {
            ai.setTranslateX(e.getSceneX());
            ai.setTranslateY(e.getSceneY());
            System.out.println(ai.getTranslateX() + ", " + ai.getTranslateY());
        });
        */
        
        ai.setStyle("-fx-font-size: 30;");
        
        
    }
    public boolean isAI(){
        if (ai.isSelected())
            return true;
        else
            return false;
    }
    public void refreshPane(int var){
        player = var;
        usernameField.setText("Player " + player);
        
        if (tankList.size() > 0){
            tankCount = 0;
            tank.setImage(tankList.get(tankCount));
            flag.setImage(flagList.get(tankCount));
        }
        
        usernameField.setStyle("-fx-background-color: transparent;");
    }
    private void setNextBtn(){
        nextBtn = new ImageView(nextBtnImage);
        
        this.getChildren().add(nextBtn);
        
        nextBtn.setTranslateX(139.0);
        nextBtn.setTranslateY(168.5);
        
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
            if (sounds.isSoundPlaying()){
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });
        
        nextBtn.setOnMouseReleased(e -> {
            nextBtn.setImage(nextBtnHover);
        });
    }
    public ImageView getNextBtn(){
        return nextBtn;
    }
    public ImageView getBackBtn(){
        return backBtn;
    }
    private void setBackground(){
        BackgroundImage myBI= new BackgroundImage(new Image("Texture/Menus/CountryMenu/Background.png", WIDTH, HEIGHT, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        this.setBackground(new Background(myBI));
    }
    
}