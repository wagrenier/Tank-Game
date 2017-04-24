/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import GamePane.GamePane;
import SaveFunction.SaveFunction;
import Tanks.Tanks;
import java.io.Serializable;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Cedrik Dubois
 */
public class PauseMenu implements Serializable{
    
    
    private  transient GamePane gamePane ;
    
    private boolean isGamePaused = false;
    
    private boolean isProgressBarStopped1 = false;
    private boolean isProgressBarStopped2 = false;
    private boolean isProgressBarStopped3 = false;
    private boolean isProgressBarStopped4 = false;
    private boolean isWeaponAnimationStopped = false;
    
    private  transient ImageView pauseMenu = new ImageView(new Image("Texture/Menus/PauseMenu/Pause Menu.png"));
    
    private transient  ImageView resumeBtn;
    private  transient ImageView exitBtn;
    private  transient ImageView saveBtn;
    
    private  transient Image resumeBtnImage = new Image("Texture/Menus/PauseMenu/Resume Button.png");
    private  transient Image resumeBtnHover = new Image("Texture/Menus/PauseMenu/Resume Button Hover.png");
    private transient Image resumeBtnClicked = new Image("Texture/Menus/PauseMenu/Resume Button Clicked.png");
    
    private  transient Image saveBtnImage = new Image("Texture/Menus/PauseMenu/Save Button.png");
    private  transient Image saveBtnHover = new Image("Texture/Menus/PauseMenu/Save Button Hover.png");
    private transient Image saveBtnClicked = new Image("Texture/Menus/PauseMenu/Save Button Clicked.png");
    
    private transient  Image exitBtnImage = new Image("Texture/Menus/PauseMenu/Exit Button.png");
    private transient  Image exitBtnHover = new Image("Texture/Menus/PauseMenu/Exit Button Hover.png");
    private  transient Image exitBtnClicked = new Image("Texture/Menus/PauseMenu/Exit Button Clicked.png");
    
    
    public PauseMenu(GamePane gamePane){
        this.gamePane = gamePane;
        
        
        
        setResumeBtn();
        setSaveBtn();
        setExitBtn();
        
    }
   
    private void setExitBtn(){
        exitBtn = new ImageView(exitBtnImage);
        
        exitBtn.setTranslateX(392.0);
        exitBtn.setTranslateY(427.0);
        
        /*
        exitBtn.setOnMouseDragged(e -> {
            exitBtn.setTranslateX(e.getSceneX());
            exitBtn.setTranslateY(e.getSceneY());
            System.out.println(exitBtn.getTranslateX() + ", " + exitBtn.getTranslateY());
        });
        */
        
        exitBtn.setOnMouseEntered(e -> {
            gamePane.setCursor(Cursor.HAND);
            exitBtn.setImage(exitBtnHover);
        });
        
        exitBtn.setOnMouseExited(e -> {
            gamePane.setCursor(Cursor.DEFAULT);
            exitBtn.setImage(exitBtnImage);
        });
        
        exitBtn.setOnMousePressed(e -> {
            exitBtn.setImage(exitBtnClicked);
        });
        
        exitBtn.setOnMouseReleased(e -> {
            exitBtn.setImage(exitBtnHover);
            System.exit(0);
        });
    }
    private void setSaveBtn(){
        saveBtn = new ImageView(saveBtnImage);
        
        saveBtn.setTranslateX(398.5);
        saveBtn.setTranslateY(277.0);
        
        /*
        saveBtn.setOnMouseDragged(e -> {
            saveBtn.setTranslateX(e.getSceneX());
            saveBtn.setTranslateY(e.getSceneY());
            System.out.println(saveBtn.getTranslateX() + ", " + saveBtn.getTranslateY());
        });
        */
        
        saveBtn.setOnMouseEntered(e -> {
            gamePane.setCursor(Cursor.HAND);
            saveBtn.setImage(saveBtnHover);
        });
        
        saveBtn.setOnMouseExited(e -> {
            gamePane.setCursor(Cursor.DEFAULT);
            saveBtn.setImage(saveBtnImage);
        });
        
        saveBtn.setOnMousePressed(e -> {
            saveBtn.setImage(saveBtnClicked);
            SaveFunction save = new SaveFunction(gamePane);
            Tanks[] array1 = gamePane.getTanksAnimation().getTanksArrayUsed();
            
            for(int i = 0; i < array1.length; i++){
            System.out.println("x: " + array1[i].getTranslateX() + " y: " + array1[i].getTranslateY());
        }
        });
        
        saveBtn.setOnMouseReleased(e -> {
            saveBtn.setImage(saveBtnHover);
        });
    }
    private void setResumeBtn(){
        resumeBtn = new ImageView(resumeBtnImage);
        
        resumeBtn.setTranslateX(402.0);
        resumeBtn.setTranslateY(131.0);
        
        /*
        resumeBtn.setOnMouseDragged(e -> {
            resumeBtn.setTranslateX(e.getSceneX());
            resumeBtn.setTranslateY(e.getSceneY());
            System.out.println(resumeBtn.getTranslateX() + ", " + resumeBtn.getTranslateY());
        });
        */
        
        resumeBtn.setOnMouseEntered(e -> {
            gamePane.setCursor(Cursor.HAND);
            resumeBtn.setImage(resumeBtnHover);
        });
        
        resumeBtn.setOnMouseExited(e -> {
            gamePane.setCursor(Cursor.DEFAULT);
            resumeBtn.setImage(resumeBtnImage);
        });
        
        resumeBtn.setOnMousePressed(e -> {
            resumeBtn.setImage(resumeBtnClicked);
        });
        
        resumeBtn.setOnMouseReleased(e -> {
            resumeBtn.setImage(resumeBtnHover);
            resumeBtn.setImage(saveBtnImage);
            resumeGame();
        });
    }
    public void resumeGame(){
        
        isGamePaused = false;
        
        
        gamePane.getTanksAnimation().getAnimation().play();
        gamePane.getTanksAnimation().getAnimation2().play();
        gamePane.getTanksAnimation().getAnimation3().play();
        gamePane.getTanksAnimation().getAnimation4().play();
        
        if(isWeaponAnimationStopped){
            isWeaponAnimationStopped = false;
            gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().play();
        }
        
        if(isProgressBarStopped1){
            isProgressBarStopped1 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationOne().play();
        }
        
        if(isProgressBarStopped2){
            isProgressBarStopped2 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationTwo().play();
        }
        
        if(isProgressBarStopped3){
            isProgressBarStopped3 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationThree().play();
        }
        
        if(isProgressBarStopped4){
            isProgressBarStopped4 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationFour().play();
        }
        
        gamePane.getChildren().removeAll(pauseMenu, resumeBtn, saveBtn, exitBtn);
    }
    
    public void pauseGame(){
        isGamePaused = true;
        gamePane.getTanksAnimation().getAnimation().pause();
        gamePane.getTanksAnimation().getAnimation2().pause();
        gamePane.getTanksAnimation().getAnimation3().pause();
        gamePane.getTanksAnimation().getAnimation4().pause();
        
        if(gamePane.getTanksAnimation().getWeaponAnimation() == null){
            
        }
        
        else if(gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(RUNNING) == 0){
            isWeaponAnimationStopped = true;
            gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().pause();
        }
        
        if(gamePane.getTanksAnimation().getProgressBarAnimationOne().getStatus().compareTo(RUNNING) == 0){
            isProgressBarStopped1 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationOne().pause();
        }
        
        if(gamePane.getTanksAnimation().getProgressBarAnimationTwo().getStatus().compareTo(RUNNING) == 0){
            isProgressBarStopped2 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationTwo().pause();
        }
        
        if(gamePane.getTanksAnimation().getProgressBarAnimationThree().getStatus().compareTo(RUNNING) == 0){
            isProgressBarStopped3 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationThree().pause();
        }
        
        if(gamePane.getTanksAnimation().getProgressBarAnimationFour().getStatus().compareTo(RUNNING) == 0){
            isProgressBarStopped4 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationFour().pause();
        }
        
        
        gamePane.getChildren().addAll(pauseMenu, resumeBtn, saveBtn, exitBtn);
    }
    
    public boolean isGamePaused(){
        return this.isGamePaused;
    }
    
    
}
