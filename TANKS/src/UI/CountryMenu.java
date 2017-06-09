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
public class CountryMenu extends Pane {
    /**Width of the pane*/
    private static final int WIDTH = 1200;
    /**Height of the pane*/
    private static final int HEIGHT = 950;
    
    /**The number of players in this game*/
    private static int player = 1;
    /**The number of tanks int he game*/
    private static int tankCount = 0;
    
    /**The sounds of the menus*/ 
    private SoundLib sounds;
    
    /**The back button*/
    private ImageView backBtn;
    /**The next button*/
    private ImageView nextBtn;
    /**The left button*/
    private ImageView leftBtn;
    /**The right button*/
    private ImageView rightBtn;
    /**The tank related to this pane*/
    private ImageView tank;
    /**The flag currently being displayed*/
    private ImageView flag;
    /**The mute button*/
    private ImageView muteBtn;
    
    /**The checkBox for the ai, activates the AI*/
    private CheckBox ai = new CheckBox();
    
    /**The array list of all the tanks*/
    private ArrayList<Image> tankList = new ArrayList<>();
    /**The flag list containing all the flags*/
    private ArrayList<Image> flagList = new ArrayList<>();
    
    /**Image of the mute button*/
    private Image muteBtnImage = new Image("Texture/Menus/MainMenu/Mute Button.png");
/**Image of the next button*/
    private Image nextBtnImage = new Image("Texture/Menus/CountryMenu/Next Button.png");
    /**Image of the next hover button*/
    private Image nextBtnHover = new Image("Texture/Menus/CountryMenu/Next Button Hover.png");
    /**Image of the next clicked button*/
    private Image nextBtnClicked = new Image("Texture/Menus/CountryMenu/Next Button Clicked.png");
/**Image of the back button*/
    private Image backBtnImage = new Image("Texture/Menus/PlayerMenu/Back Button.png");
    /**Image of the back hover button*/
    private Image backBtnHover = new Image("Texture/Menus/PlayerMenu/Back Button Hover.png");
    /**Image of the button*/
    private Image backBtnClicked = new Image("Texture/Menus/PlayerMenu/Back Button Clicked.png");
/**Image of the left button*/
    private Image leftBtnImage = new Image("Texture/Menus/PlayerMenu/Left Arrow.png");
    /**Image of the left hover button*/
    private Image leftBtnHover = new Image("Texture/Menus/PlayerMenu/Left Arrow Hover.png");
    /**Image of the left clicked button*/
    private Image leftBtnClicked = new Image("Texture/Menus/PlayerMenu/Left Arrow Clicked.png");
/**Image of the right button*/
    private Image rightBtnImage = new Image("Texture/Menus/PlayerMenu/Right Arrow.png");
    /**Image of the right hover button*/
    private Image rightBtnHover = new Image("Texture/Menus/PlayerMenu/Right Arrow Hover.png");
    /**Image of the right clicked button*/
    private Image rightBtnClicked = new Image("Texture/Menus/PlayerMenu/Right Arrow Clicked.png");
    
    /**Image of north korean flag*/
    private Image northkorea = new Image("Texture/Menus/CountryMenu/North Korea Selection Tank.png");
    /**Image of the USA flag*/
    private Image usa = new Image("Texture/Menus/CountryMenu/USA Selection Tank.png");
    /**Image of the Canada flag*/
    private Image canada = new Image("Texture/Menus/CountryMenu/Canada Selection Tank.png");
    /**Image of the China flag*/
    private Image china = new Image("Texture/Menus/CountryMenu/China Selection Tank.png");
    /**TextField holding the name of the username*/
    private TextField usernameField;

    /**
     * Constructor
     * @param sounds
     */
    public CountryMenu(SoundLib sounds) {
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
    
    /**Sets the variable and location of the mute button*/
    private void setMuteBtn() {
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
     * Sets the scene of the game
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

        scene.getStylesheets().add("classes/checkbox.css");
    }

    /**
     * Resets the Pane 
     */
    public void resetPane() {
        setTankList();
        setFlagList();
        tankCount = 0;
        player = 0;
        tank.setImage(tankList.get(tankCount));
        flag.setImage(flagList.get(tankCount));
    }

    /**
     * Removes a country from the possible selections
     * @param country
     */
    public void removeTeam(int country) {
        tankList.remove(country);
        flagList.remove(country);

    }

    /**
     *
     * @return int
     */
    public int getCountry() {
        if (tankList.get(tankCount).equals(northkorea)) {
            return 0;
        } else if (tankList.get(tankCount).equals(usa)) {
            return 1;
        } else if (tankList.get(tankCount).equals(canada)) {
            return 2;
        } else if (tankList.get(tankCount).equals(china)) {
            return 3;
        } else {
            return -1; //Will cause error if method return -1;
        }
    }

    private void setBackBtn() {
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
            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        backBtn.setOnMouseReleased(e -> {
            backBtn.setImage(backBtnHover);
        });

    }

    private void setUsername() {
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

    private void setLeftBtn() {
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
            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        leftBtn.setOnMouseReleased(e -> {
            leftBtn.setImage(leftBtnHover);
            if (tankCount == 0) {
                tankCount = tankList.size() - 1;
            } else {
                tankCount--;
            }

            tank.setImage(tankList.get(tankCount));
            flag.setImage(flagList.get(tankCount));
        });
    }

    private void setRightBtn() {
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
            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        rightBtn.setOnMouseReleased(e -> {
            rightBtn.setImage(rightBtnHover);
            if (tankCount == tankList.size() - 1) {
                tankCount = 0;
            } else {
                tankCount++;
            }

            tank.setImage(tankList.get(tankCount));
            flag.setImage(flagList.get(tankCount));
            //getCountry();
        });
    }

    private void setFlag() {
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

    private void setTank() {
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

    private void setTankList() {
        tankList.clear();

        tankList.add(northkorea);
        tankList.add(usa);
        tankList.add(canada);
        tankList.add(china);

    }

    private void setFlagList() {
        flagList.clear();

        flagList.add(new Image("Texture/Menus/CountryMenu/North Korea Flag.png"));
        flagList.add(new Image("Texture/Menus/CountryMenu/USA Flag.png"));
        flagList.add(new Image("Texture/Menus/CountryMenu/Canada Flag.png"));
        flagList.add(new Image("Texture/Menus/CountryMenu/China Flag.png"));
    }

    private void setAI() {
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

    /**
     *
     * @return
     */
    public boolean isAI() {
        if (ai.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param var
     */
    public void refreshPane(int var) {
        player = var;
        usernameField.setText("Player " + player);

        if (tankList.size() > 0) {
            tankCount = 0;
            tank.setImage(tankList.get(tankCount));
            flag.setImage(flagList.get(tankCount));
        }

        usernameField.setStyle("-fx-background-color: transparent;");
    }

    private void setNextBtn() {
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
            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        nextBtn.setOnMouseReleased(e -> {
            nextBtn.setImage(nextBtnHover);
        });
    }

    private void setBackground() {
        BackgroundImage myBI = new BackgroundImage(new Image("Texture/Menus/CountryMenu/Background.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        this.setBackground(new Background(myBI));
    }
    
    /**
     *
     * @return
     */
    public ImageView getNextBtn() {
        return nextBtn;
    }

    /**
     *
     * @return
     */
    public ImageView getBackBtn() {
        return backBtn;
    }
    
    /**
     *
     * @return
     */
    public int getTankCount() {
        return tankCount;
    }
    
    /**
     *
     * @return
     */
    public String getPlayerName() {
        return usernameField.getText();
    }

    /**
     *
     * @return
     */
    public static int getPlayer() {
        return player;
    }

    /**
     *
     * @param player
     */
    public static void setPlayer(int player) {
        CountryMenu.player = player;
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
    public ImageView getLeftBtn() {
        return leftBtn;
    }

    /**
     *
     * @param leftBtn
     */
    public void setLeftBtn(ImageView leftBtn) {
        this.leftBtn = leftBtn;
    }

    /**
     *
     * @return
     */
    public ImageView getRightBtn() {
        return rightBtn;
    }

    /**
     *
     * @param rightBtn
     */
    public void setRightBtn(ImageView rightBtn) {
        this.rightBtn = rightBtn;
    }

    /**
     *
     * @return
     */
    public ImageView getTank() {
        return tank;
    }

    /**
     *
     * @param tank
     */
    public void setTank(ImageView tank) {
        this.tank = tank;
    }

    /**
     *
     * @return
     */
    public ImageView getFlag() {
        return flag;
    }

    /**
     *
     * @param flag
     */
    public void setFlag(ImageView flag) {
        this.flag = flag;
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
    public CheckBox getAi() {
        return ai;
    }

    /**
     *
     * @param ai
     */
    public void setAi(CheckBox ai) {
        this.ai = ai;
    }

    /**
     *
     * @return
     */
    public ArrayList<Image> getTankList() {
        return tankList;
    }

    /**
     *
     * @param tankList
     */
    public void setTankList(ArrayList<Image> tankList) {
        this.tankList = tankList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Image> getFlagList() {
        return flagList;
    }

    /**
     *
     * @param flagList
     */
    public void setFlagList(ArrayList<Image> flagList) {
        this.flagList = flagList;
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
    public Image getNextBtnImage() {
        return nextBtnImage;
    }

    /**
     *
     * @param nextBtnImage
     */
    public void setNextBtnImage(Image nextBtnImage) {
        this.nextBtnImage = nextBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getNextBtnHover() {
        return nextBtnHover;
    }

    /**
     *
     * @param nextBtnHover
     */
    public void setNextBtnHover(Image nextBtnHover) {
        this.nextBtnHover = nextBtnHover;
    }

    /**
     *
     * @return
     */
    public Image getNextBtnClicked() {
        return nextBtnClicked;
    }

    /**
     *
     * @param nextBtnClicked
     */
    public void setNextBtnClicked(Image nextBtnClicked) {
        this.nextBtnClicked = nextBtnClicked;
    }

    /**
     *
     * @return
     */
    public Image getBackBtnImage() {
        return backBtnImage;
    }

    /**
     *
     * @param backBtnImage
     */
    public void setBackBtnImage(Image backBtnImage) {
        this.backBtnImage = backBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getBackBtnHover() {
        return backBtnHover;
    }

    /**
     *
     * @param backBtnHover
     */
    public void setBackBtnHover(Image backBtnHover) {
        this.backBtnHover = backBtnHover;
    }

    /**
     *
     * @return
     */
    public Image getBackBtnClicked() {
        return backBtnClicked;
    }

    /**
     *
     * @param backBtnClicked
     */
    public void setBackBtnClicked(Image backBtnClicked) {
        this.backBtnClicked = backBtnClicked;
    }

    /**
     *
     * @return
     */
    public Image getLeftBtnImage() {
        return leftBtnImage;
    }

    /**
     *
     * @param leftBtnImage
     */
    public void setLeftBtnImage(Image leftBtnImage) {
        this.leftBtnImage = leftBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getLeftBtnHover() {
        return leftBtnHover;
    }

    /**
     *
     * @param leftBtnHover
     */
    public void setLeftBtnHover(Image leftBtnHover) {
        this.leftBtnHover = leftBtnHover;
    }

    /**
     *
     * @return
     */
    public Image getLeftBtnClicked() {
        return leftBtnClicked;
    }

    /**
     *
     * @param leftBtnClicked
     */
    public void setLeftBtnClicked(Image leftBtnClicked) {
        this.leftBtnClicked = leftBtnClicked;
    }

    /**
     *
     * @return
     */
    public Image getRightBtnImage() {
        return rightBtnImage;
    }

    /**
     *
     * @param rightBtnImage
     */
    public void setRightBtnImage(Image rightBtnImage) {
        this.rightBtnImage = rightBtnImage;
    }

    /**
     *
     * @return
     */
    public Image getRightBtnHover() {
        return rightBtnHover;
    }

    /**
     *
     * @param rightBtnHover
     */
    public void setRightBtnHover(Image rightBtnHover) {
        this.rightBtnHover = rightBtnHover;
    }

    /**
     *
     * @return
     */
    public Image getRightBtnClicked() {
        return rightBtnClicked;
    }

    /**
     *
     * @param rightBtnClicked
     */
    public void setRightBtnClicked(Image rightBtnClicked) {
        this.rightBtnClicked = rightBtnClicked;
    }

    /**
     *
     * @return
     */
    public Image getNorthkorea() {
        return northkorea;
    }

    /**
     *
     * @param northkorea
     */
    public void setNorthkorea(Image northkorea) {
        this.northkorea = northkorea;
    }

    /**
     *
     * @return
     */
    public Image getUsa() {
        return usa;
    }

    /**
     *
     * @param usa
     */
    public void setUsa(Image usa) {
        this.usa = usa;
    }

    /**
     *
     * @return
     */
    public Image getCanada() {
        return canada;
    }

    /**
     *
     * @param canada
     */
    public void setCanada(Image canada) {
        this.canada = canada;
    }

    /**
     *
     * @return
     */
    public Image getChina() {
        return china;
    }

    /**
     *
     * @param china
     */
    public void setChina(Image china) {
        this.china = china;
    }

    /**
     *
     * @return
     */
    public TextField getUsernameField() {
        return usernameField;
    }

    /**
     *
     * @param usernameField
     */
    public void setUsernameField(TextField usernameField) {
        this.usernameField = usernameField;
    }

    /**
     *
     * @param tankCount
     */
    public static void setTankCount(int tankCount) {
        CountryMenu.tankCount = tankCount;
    }

    /**
     *
     * @param backBtn
     */
    public void setBackBtn(ImageView backBtn) {
        this.backBtn = backBtn;
    }

    /**
     *
     * @param nextBtn
     */
    public void setNextBtn(ImageView nextBtn) {
        this.nextBtn = nextBtn;
    }

    /**
     *
     * @return
     */
    public static int getWIDTH() {
        return WIDTH;
    }

    /**
     *
     * @return
     */
    public static int getHEIGHT() {
        return HEIGHT;
    }
    
    
}
