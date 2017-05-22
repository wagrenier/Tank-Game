/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
package UI;

import HUD.HelpMenu;
import LoadFunction.LoadFunction;
import Sounds.SoundLib;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Cedrik Dubois
 */
public class MainMenu extends Pane {
    /**The sounds of the menus*/
    private SoundLib sounds;

    private HelpMenu helpMenu = new HelpMenu(this);

    /**Width of the pane*/
    private static final int WIDTH = 1200;
    /**Height of the pane*/
    private static final int HEIGHT = 950;
    /**The load function which loads the game from a binary file*/
    private LoadFunction load;
    /**The play button*/
    private ImageView playBtn;
    /**The load button*/
    private ImageView loadBtn;
    /**The mute button*/
    private ImageView muteBtn;
    /**The help button, opens the help menu*/
    private ImageView helpBtn;
    
    /**The help button texture*/
    private Image helpBtnImage = new Image("Texture/Menus/MainMenu/Help Button.png");
    /**The help button texture*/
    private Image helpBtnHover = new Image("Texture/Menus/MainMenu/Help Button Hover.png");
    
    /**The play button texture*/
    private Image playBtnImage = new Image("Texture/Menus/MainMenu/Play Button.png");
    /**The play button texture*/
    private Image playBtnHover = new Image("Texture/Menus/MainMenu/Play Button Hover.png");
    /**The play button texture*/
    private Image playBtnClicked = new Image("Texture/Menus/MainMenu/Play Button Clicked.png");
    
    /**The load button texture*/
    private Image loadBtnImage = new Image("Texture/Menus/MainMenu/Load Button.png");
    /**The load button texture*/
    private Image loadBtnHover = new Image("Texture/Menus/MainMenu/Load Button Hover.png");
    /**The load button texture*/
    private Image loadBtnClicked = new Image("Texture/Menus/MainMenu/Load Button Clicked.png");
    
    /**The mute button texture*/
    private Image muteBtnImage = new Image("Texture/Menus/MainMenu/Mute Button.png");

    /**
     * Constructor
     * @param sounds
     */
    public MainMenu(SoundLib sounds) {
        this.sounds = sounds;

        this.setMaxHeight(HEIGHT);
        this.setMinHeight(HEIGHT);
        this.setMaxWidth(WIDTH);
        this.setMinWidth(WIDTH);
        setBackground();

        setPlayBtn();
        setLoadBtn();
        setMuteBtn();
        setHelpBtn();

        this.requestFocus();

    }

    /**
     * Sets the scene for this pane
     * @param scene
     * @param cursor
     */
    public void setScene(Scene scene, ImageView cursor) {

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

    /**
     * Sets the scene for this pane
     * @param scene
     * @param cursor
     * @param m
     */
    public void setScene(Scene scene, ImageView cursor, MouseEvent m) {
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
    
    /**Sets help the button*/
    private void setHelpBtn() {
        helpBtn = new ImageView(helpBtnImage);

        this.getChildren().add(helpBtn);

        helpBtn.setTranslateX(601.0);
        helpBtn.setTranslateY(759.0);

        /*
        helpBtn.setOnMouseDragged(e -> {
            helpBtn.setTranslateX(e.getSceneX());
            helpBtn.setTranslateY(e.getSceneY());
            System.out.println(helpBtn.getTranslateX() + ", " + helpBtn.getTranslateY());
        });
         */
        helpBtn.setOnMouseReleased(e -> {
            if (helpMenu.isHelpOpen() == false) {
                helpMenu.openHelpMenu();
            }
        });

        helpBtn.setOnMouseEntered(e -> {
            helpBtn.setImage(helpBtnHover);
        });

        helpBtn.setOnMouseExited(e -> {
            helpBtn.setImage(helpBtnImage);
        });
    }
    
    /**Sets the load button*/
    private void setLoadBtn() {
        loadBtn = new ImageView(loadBtnImage);

        this.getChildren().add(loadBtn);

        loadBtn.setTranslateX(-19.0);
        loadBtn.setTranslateY(126.0);

        /*
        This is used just to place the buttons in the pane.
        I'm leaving it here for now in case we decide to do any modifications
        /
        
        loadBtn.setOnMouseDragged(e -> {
            loadBtn.setTranslateX(e.getSceneX());
            loadBtn.setTranslateY(e.getSceneY());
            System.out.println(e.getSceneX() + ", " + e.getSceneY());
        });
         */
        loadBtn.setOnMouseEntered(e -> {
            loadBtn.setImage(loadBtnHover);

        });

        loadBtn.setOnMouseExited(e -> {
            loadBtn.setImage(loadBtnImage);

        });

        loadBtn.setOnMousePressed(e -> {
            loadBtn.setImage(loadBtnClicked);
            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        loadBtn.setOnMouseReleased(e -> {
            loadBtn.setImage(loadBtnHover);
        });
    }
    
    /**Sets the play button*/
    private void setPlayBtn() {

        playBtn = new ImageView(playBtnImage);

        this.getChildren().add(playBtn);

        playBtn.setTranslateX(-6.0);
        playBtn.setTranslateY(-16.0);

        /*
        This is used just to place the buttons in the pane.
        I'm leaving it here for now in case we decide to do any modifications
        /
        
        playBtn.setOnMouseDragged(e -> {
            playBtn.setTranslateX(e.getSceneX());
            playBtn.setTranslateY(e.getSceneY());
            System.out.println(e.getSceneX() + ", " + e.getSceneY());
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
            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });
        playBtn.setOnMouseReleased(e -> {
            playBtn.setImage(playBtnHover);
        });

    }
    
    /**Sets the Background*/
    private void setBackground() {
        BackgroundImage myBI = new BackgroundImage(new Image("Texture/Menus/MainMenu/Background.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));
    }
    
    /**Sets the mute button*/
    private void setMuteBtn() {
        muteBtn = new ImageView(muteBtnImage);

        this.getChildren().add(muteBtn);

        muteBtn.setTranslateX(485.0);
        muteBtn.setTranslateY(772.0);

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
    }

    /**
     *
     * @return
     */
    public ImageView getLoadBtn() {
        return loadBtn;
    }
    
    /**
     *
     * @return
     */
    public ImageView getPlayBtn() {
        return playBtn;
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
    public HelpMenu getHelpMenu() {
        return helpMenu;
    }

    /**
     *
     * @param helpMenu
     */
    public void setHelpMenu(HelpMenu helpMenu) {
        this.helpMenu = helpMenu;
    }

    /**
     *
     * @return
     */
    public LoadFunction getLoad() {
        return load;
    }

    /**
     *
     * @param load
     */
    public void setLoad(LoadFunction load) {
        this.load = load;
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
    public ImageView getHelpBtn() {
        return helpBtn;
    }

    /**
     *
     * @param helpBtn
     */
    public void setHelpBtn(ImageView helpBtn) {
        this.helpBtn = helpBtn;
    }

    /**
     *
     * @return
     */
    public Image getHelpBtnImage() {
        return helpBtnImage;
    }

    /**
     *
     * @param helpBtnImage
     */
    public void setHelpBtnImage(Image helpBtnImage) {
        this.helpBtnImage = helpBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getHelpBtnHover() {
        return helpBtnHover;
    }

    /**
     *
     * @param helpBtnHover
     */
    public void setHelpBtnHover(Image helpBtnHover) {
        this.helpBtnHover = helpBtnHover;
    }

    /**
     *
     * @return
     */
    public Image getPlayBtnImage() {
        return playBtnImage;
    }

    /**
     *
     * @param playBtnImage
     */
    public void setPlayBtnImage(Image playBtnImage) {
        this.playBtnImage = playBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getPlayBtnHover() {
        return playBtnHover;
    }

    /**
     *
     * @param playBtnHover
     */
    public void setPlayBtnHover(Image playBtnHover) {
        this.playBtnHover = playBtnHover;
    }

    /**
     *
     * @return
     */
    public Image getPlayBtnClicked() {
        return playBtnClicked;
    }

    /**
     *
     * @param playBtnClicked
     */
    public void setPlayBtnClicked(Image playBtnClicked) {
        this.playBtnClicked = playBtnClicked;
    }

    /**
     *
     * @return
     */
    public Image getLoadBtnImage() {
        return loadBtnImage;
    }

    /**
     *
     * @param loadBtnImage
     */
    public void setLoadBtnImage(Image loadBtnImage) {
        this.loadBtnImage = loadBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getLoadBtnHover() {
        return loadBtnHover;
    }

    /**
     *
     * @param loadBtnHover
     */
    public void setLoadBtnHover(Image loadBtnHover) {
        this.loadBtnHover = loadBtnHover;
    }

    /**
     *
     * @return
     */
    public Image getLoadBtnClicked() {
        return loadBtnClicked;
    }

    /**
     *
     * @param loadBtnClicked
     */
    public void setLoadBtnClicked(Image loadBtnClicked) {
        this.loadBtnClicked = loadBtnClicked;
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
}
