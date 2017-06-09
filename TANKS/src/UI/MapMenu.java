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

import PlayerSettings.Player;
import Sounds.SoundLib;
import java.util.ArrayList;
import javafx.scene.Scene;
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
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 *
 * @author Cedrik Dubois
 */
public class MapMenu extends Pane {

    private SoundLib sounds;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 950;

    /*
    *
    *For now I'm using the var variable to switch between backgrounds since I only have two.
    *Later on, they will be in an array and it will cycle through that array as players 
    *cycle through the maps.
    *
     */
 /**
  * 
        0 = Desert Map, 
        1 = Mountain Map, 
        2 = Space Map, 
        3 = Snow Map
     */
    private Background[] backgrounds = new Background[4];
    private static int backgroundIndex = 0;

    private ImageView playBtn;
    /**The back button*/
    private ImageView backBtn;
    /**The right button*/
    private ImageView rightBtn;
    /**The mute button*/
    private ImageView muteBtn;

    private Image[] tanks = new Image[4];
    
    
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
    
    /**Image of the USA flag*/
    private Image usaTank = new Image("Texture/Menus/MapMenu/USA.png");
    /**Image of the North Korea flag*/
    private Image northKoreaTank = new Image("Texture/Menus/MapMenu/North Korea.png");
    /**Image of the Canada flag*/
    private Image canadaTank = new Image("Texture/Menus/MapMenu/Canada.png");
    /**Image of the China flag*/
    private Image chinaTank = new Image("Texture/Menus/MapMenu/China.png");

    private Image playBtnImage = new Image("Texture/Menus/MapMenu/Play Button.png");
    private Image playBtnHover = new Image("Texture/Menus/MapMenu/Play Button Hover.png");
    private Image playBtnClicked = new Image("Texture/Menus/MapMenu/Play Button Clicked.png");
    
    /**Array List containing all the players*/
    private ArrayList<Player> players;
    /**Array List containing all the player name*/
    private ArrayList<Text> playerNames = new ArrayList<Text>();
    /**Array List containign all the players' tank*/
    private ArrayList<ImageView> playerTanks = new ArrayList<ImageView>();
    
    /**Name of the map currently being displayed*/
    private Text mapTitle = new Text();
    /** Array of all the map's name*/
    private String[] titleList = new String[4];

    /**
     * Constructor of the object
     * @param sounds
     */
    public MapMenu(SoundLib sounds) {
        this.sounds = sounds;

        this.setMinWidth(WIDTH);
        this.setMaxWidth(WIDTH);
        this.setMinHeight(HEIGHT);
        this.setMaxHeight(HEIGHT);

        setBackgrounds();
        setTankList();
        setTitleList();
        setPlayBtn();
        setBackBtn();
        setRightBtn();
        setMapTitle();
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
     *Sets the scene of the game
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
    /**Sets the elements*/
    private void setTitleList() {
        titleList[1] = "Mountains";
        titleList[2] = "Space";
        titleList[3] = "Artic";
        titleList[0] = "Desert";
    }
/**Sets the elements*/
    private void setMapTitle() {
        this.getChildren().add(mapTitle);
        mapTitle.setText(titleList[backgroundIndex]);
        mapTitle.setTextAlignment(TextAlignment.CENTER);
        mapTitle.setTranslateX(511.5);
        mapTitle.setTranslateY(203.5);
        mapTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 35));

        /*
        mapTitle.setOnMouseDragged(e -> {
            mapTitle.setTranslateX(e.getSceneX());
            mapTitle.setTranslateY(e.getSceneY());
            System.out.println(mapTitle.getTranslateX() + ", " + mapTitle.getTranslateY());
        });
         */
    }

    /**
     *
     */
    public void resetPane() {
        for (int i = 0; i < playerNames.size(); i++) {
            this.getChildren().remove(playerNames.get(i));
            this.getChildren().remove(playerTanks.get(i));
        }

        playerTanks.clear();
        playerNames.clear();
        players.clear();
    }
/**Sets the elements*/
    private void setPlayerTanks() {
        for (int i = 0; i < players.size(); i++) {
            playerTanks.add(new ImageView(tanks[players.get(i).getTeam()]));
        }

        for (int i = 0; i < playerTanks.size(); i++) {
            this.getChildren().add(playerTanks.get(i));
        }

        playerTanks.get(0).setTranslateX(110.0);
        playerTanks.get(0).setTranslateY(-140.0);

        for (int i = 1; i < playerTanks.size(); i++) {
            playerTanks.get(i).setTranslateX(110.0);
            playerTanks.get(i).setTranslateY(playerTanks.get(i - 1).getTranslateY() + 80);
        }

    }
/**Sets the elements*/
    private void setTankList() {
        tanks[0] = northKoreaTank;
        tanks[1] = usaTank;
        tanks[2] = canadaTank;
        tanks[3] = chinaTank;
    }
/**Sets the elements*/
    private void setLeaderboard() {

        for (int i = 0; i < playerNames.size(); i++) {
            this.getChildren().add(playerNames.get(i));
        }

        playerNames.get(0).setTranslateX(400);
        playerNames.get(0).setTranslateY(280);

        for (int i = 1; i < playerNames.size(); i++) {
            playerNames.get(i).setTranslateX(playerNames.get(i - 1).getTranslateX());
            playerNames.get(i).setTranslateY(playerNames.get(i - 1).getTranslateY() + 80);
        }

    }

    /**
     *
     */
    public void setPlayers() {
        for (int i = 0; i < players.size(); i++) {
            playerNames.add(new Text(players.get(i).getUsername()));
        }
        for (int i = 0; i < playerNames.size(); i++) {
            playerNames.get(i).setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        }
        setLeaderboard();
        setPlayerTanks();
    }

    /**
     *
     * @param players
     */
    public void setPlayerList(ArrayList<Player> players) {
        this.players = players;
    }
/**Sets the elements*/
    private void setRightBtn() {
        rightBtn = new ImageView(rightBtnImage);

        this.getChildren().add(rightBtn);

        rightBtn.setTranslateX(151.0);
        rightBtn.setTranslateY(-203.5);

        /*
        rightBtn.setOnMouseDragged(e -> {
            rightBtn.setTranslateX(e.getSceneX());
            rightBtn.setTranslateY(e.getSceneY());
            System.out.println(rightBtn.getTranslateX() + ", " + rightBtn.getTranslateY());
        });
         */
        rightBtn.setOnMouseClicked(e -> {
            if (backgroundIndex == 3) {
                backgroundIndex = 0;
            } else {
                backgroundIndex++;
            }

            this.setBackground(backgrounds[backgroundIndex]);
            mapTitle.setText(titleList[backgroundIndex]);
        });

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
        });
    }
/**Sets the elements*/
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
/**Sets the elements*/
    private void setPlayBtn() {
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

            if (sounds.isSoundPlaying()) {
                sounds.getBtnClicked().seek(Duration.ZERO);
                sounds.getBtnClicked().play();
            }
        });

        /*
        playBtn.setOnMouseReleased(e -> {
            playBtn.setImage(playBtnHover);
            
        });*/
    }
    
    /**
     *
     * @return
     */
    public int getChosenMap() {

        /*
        0 = Desert Map
        1 = Mountain Map
        2 = Space Map
        3 = Snow Map
         */
        return backgroundIndex;
    }
/**Sets the elements*/
    private void setBackgrounds() {
        backgrounds[0] = new Background(new BackgroundImage(new Image("Texture/Menus/MapMenu/Desert Map.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
        backgrounds[1] = new Background(new BackgroundImage(new Image("Texture/Menus/MapMenu/Mountain Map.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
        backgrounds[2] = new Background(new BackgroundImage(new Image("Texture/Menus/MapMenu/Space Map.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
        backgrounds[3] = new Background(new BackgroundImage(new Image("Texture/Menus/MapMenu/Snow Map.png", WIDTH, HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));

        this.setBackground(backgrounds[backgroundIndex]);
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
    public Image getPlayBtnHover() {
        return playBtnHover;
    }
    
    /**
     *
     * @return
     */
    public ImageView getBackBtn() {
        return backBtn;
    }

  
}
