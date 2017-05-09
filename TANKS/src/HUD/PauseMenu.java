/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;

import GamePane.GamePane;
import SaveFunction.SaveFunction;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Cedrik Dubois
 */
public class PauseMenu {

    private GamePane gamePane;

    private Store storeMenu;
    
    private HelpMenu helpMenu;

    private boolean isGamePaused = false;
    private boolean isMenuOpened = false;

    private boolean isProgressBarStopped1 = false;
    private boolean isProgressBarStopped2 = false;
    private boolean isProgressBarStopped3 = false;
    private boolean isProgressBarStopped4 = false;
    private boolean isWeaponAnimationStopped = false;

    private ImageView pauseMenu = new ImageView(new Image("Texture/Menus/PauseMenu/Pause Menu.png"));

    private ImageView resumeBtn;
    private ImageView exitBtn;
    private ImageView saveBtn;
    private ImageView helpBtn;
    
    private Image helpBtnImage = new Image("Texture/Menus/MainMenu/Help Button.png");

    private Image resumeBtnImage = new Image("Texture/Menus/PauseMenu/Resume Button.png");
    private Image resumeBtnHover = new Image("Texture/Menus/PauseMenu/Resume Button Hover.png");
    private Image resumeBtnClicked = new Image("Texture/Menus/PauseMenu/Resume Button Clicked.png");

    private Image saveBtnImage = new Image("Texture/Menus/PauseMenu/Save Button.png");
    private Image saveBtnHover = new Image("Texture/Menus/PauseMenu/Save Button Hover.png");
    private Image saveBtnClicked = new Image("Texture/Menus/PauseMenu/Save Button Clicked.png");

    private Image exitBtnImage = new Image("Texture/Menus/PauseMenu/Exit Button.png");
    private Image exitBtnHover = new Image("Texture/Menus/PauseMenu/Exit Button Hover.png");
    private Image exitBtnClicked = new Image("Texture/Menus/PauseMenu/Exit Button Clicked.png");

    public PauseMenu(GamePane gamePane, Store storeMenu) {
        this.gamePane = gamePane;
        this.storeMenu = storeMenu;
        this.helpMenu = new HelpMenu(gamePane);

        setResumeBtn();
        setSaveBtn();
        setExitBtn();
        setHelpBtn();

    }
    
    private void setHelpBtn(){
       helpBtn = new ImageView(helpBtnImage);
       
       helpBtn.setTranslateX(559.0);
       helpBtn.setTranslateY(562.0);
       
       /*
       helpBtn.setOnMouseDragged(e -> {
            helpBtn.setTranslateX(e.getSceneX());
            helpBtn.setTranslateY(e.getSceneY());
            System.out.println(helpBtn.getTranslateX() + ", " + helpBtn.getTranslateY());
        });
       */
       
       helpBtn.setOnMouseReleased(e -> {
           if (helpMenu.isHelpOpen() == false){
               helpMenu.openHelpMenu();
           }
       });
    }

    private void setExitBtn() {
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

    private void setSaveBtn() {
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
            if (gamePane.getTanksAnimation().isPossibleToSave()) {
                SaveFunction save = new SaveFunction(gamePane);
                System.out.println("Successful Save");
            } else {
                System.out.println("Did not Save");
            }
        });

        saveBtn.setOnMouseReleased(e -> {
            saveBtn.setImage(saveBtnHover);
        });
    }

    private void setResumeBtn() {
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
            resumeBtn.setImage(saveBtnImage);

            if (this.isGamePaused() && storeMenu.isStoreOpened() && this.isMenuOpen() == false) {
                this.openMenuWithoutPause();
            } else if (this.isGamePaused() && storeMenu.isStoreOpened() && this.isMenuOpen()) {
                this.closeMenuWithoutResume();
            } else if (this.isGamePaused() == false && storeMenu.isStoreOpened() == false && this.isMenuOpen() == false) {
                this.pauseGame();
            } else if (this.isGamePaused() && storeMenu.isStoreOpened() == false && this.isMenuOpen() == true) {
                this.resumeGame();
            }

            System.out.println("Game Pause: " + this.isGamePaused());
            System.out.println("Store Open: " + storeMenu.isStoreOpened());
            System.out.println("Pause Menu Open: " + this.isMenuOpen());
        });
    }

    public void resumeGame(int storeResume) {

        isGamePaused = false;

        gamePane.getTanksAnimation().getAnimation().play();
        gamePane.getTanksAnimation().getAnimation2().play();
        gamePane.getTanksAnimation().getAnimation3().play();
        gamePane.getTanksAnimation().getAnimation4().play();

        if (isWeaponAnimationStopped) {
            isWeaponAnimationStopped = false;
            gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().play();
        }

        if (isProgressBarStopped1) {
            isProgressBarStopped1 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationOne().play();
        }

        if (isProgressBarStopped2) {
            isProgressBarStopped2 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationTwo().play();
        }

        if (isProgressBarStopped3) {
            isProgressBarStopped3 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationThree().play();
        }

        if (isProgressBarStopped4) {
            isProgressBarStopped4 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationFour().play();
        }
    }

    public void resumeGame() {

        isGamePaused = false;
        isMenuOpened = false;

        gamePane.getTanksAnimation().getAnimation().play();
        gamePane.getTanksAnimation().getAnimation2().play();
        gamePane.getTanksAnimation().getAnimation3().play();
        gamePane.getTanksAnimation().getAnimation4().play();

        if (isWeaponAnimationStopped) {
            isWeaponAnimationStopped = false;
            gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().play();
        }

        if (isProgressBarStopped1) {
            isProgressBarStopped1 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationOne().play();
        }

        if (isProgressBarStopped2) {
            isProgressBarStopped2 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationTwo().play();
        }

        if (isProgressBarStopped3) {
            isProgressBarStopped3 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationThree().play();
        }

        if (isProgressBarStopped4) {
            isProgressBarStopped4 = false;
            gamePane.getTanksAnimation().getProgressBarAnimationFour().play();
        }

        gamePane.getChildren().removeAll(pauseMenu, resumeBtn, saveBtn, exitBtn, helpBtn);
    }

    public void pauseGame(int storePause) {
        isGamePaused = true;
        gamePane.getTanksAnimation().getAnimation().pause();
        gamePane.getTanksAnimation().getAnimation2().pause();
        gamePane.getTanksAnimation().getAnimation3().pause();
        gamePane.getTanksAnimation().getAnimation4().pause();

        if (gamePane.getTanksAnimation().getWeaponAnimation() == null) {

        } else if (gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(RUNNING) == 0) {
            isWeaponAnimationStopped = true;
            gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationOne().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped1 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationOne().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationTwo().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped2 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationTwo().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationThree().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped3 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationThree().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationFour().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped4 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationFour().pause();
        }
    }

    public void pauseGame() {
        System.out.println("Game Paused");

        isGamePaused = true;
        isMenuOpened = true;

        gamePane.getTanksAnimation().getAnimation().pause();
        gamePane.getTanksAnimation().getAnimation2().pause();
        gamePane.getTanksAnimation().getAnimation3().pause();
        gamePane.getTanksAnimation().getAnimation4().pause();

        if (gamePane.getTanksAnimation().getWeaponAnimation() == null) {

        } else if (gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().getStatus().compareTo(RUNNING) == 0) {
            isWeaponAnimationStopped = true;
            gamePane.getTanksAnimation().getWeaponAnimation().getAnimationWeapon().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationOne().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped1 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationOne().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationTwo().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped2 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationTwo().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationThree().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped3 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationThree().pause();
        }

        if (gamePane.getTanksAnimation().getProgressBarAnimationFour().getStatus().compareTo(RUNNING) == 0) {
            isProgressBarStopped4 = true;
            gamePane.getTanksAnimation().getProgressBarAnimationFour().pause();
        }

        gamePane.getChildren().addAll(pauseMenu, resumeBtn, saveBtn, exitBtn, helpBtn);
    }

    public boolean isGamePaused() {
        return this.isGamePaused;
    }

    public boolean isMenuOpen() {
        return this.isMenuOpened;
    }

    public void openMenuWithoutPause() {
        gamePane.getChildren().addAll(pauseMenu, resumeBtn, saveBtn, exitBtn, helpBtn);

        isMenuOpened = true;
    }

    public void closeMenuWithoutResume() {
        gamePane.getChildren().removeAll(pauseMenu, resumeBtn, saveBtn, exitBtn, helpBtn);

        isMenuOpened = false;
    }

}
