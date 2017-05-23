/****************************************************************
 *  Header File: BXXXXXXX.h
 *  Description: Generic Business Function Header File
 *    History:
 *     Date    Programmer SAR# - Description
 *     ---------- ---------- ----------------------------
 *  Author 03/15/2006           - Created
 *
 ****************************************************************/
package HUD;

import GamePane.GamePane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Cedrik Dubois
 */
public class Leaderboard {
    /**The pane of the game*/
    private GamePane pane;
    /**Sets the image for this object*/
    private ImageView background = new ImageView(new Image("Texture/Menus/HelpMenu/Background.png"));
    /**Sets the image for this object*/
    private ImageView closeBtn;
    /**Sets the image for this object*/
    private Image closeBtnImage = new Image("Texture/Menus/HelpMenu/Close Button.png");
    /**Sets the image for this object*/
    private Image closeBtnHover = new Image("Texture/Menus/HelpMenu/Close Button Hover.png");
    /**Creates a text where the leaderboard names can be written to*/
    private Text names = new Text("");
    /**Creates a text where the leaderboard scores can be written to*/
    private Text scores = new Text("");
    
    /**Checks if the leaderboard is open*/
    private boolean leaderboardOpen = false;

    /**
     *
     * @param pane
     */
    public Leaderboard(GamePane pane) {
        this.pane = pane;

        setTextArea();
        setCloseBtn();
    }
    /**Updates the leaderboard*/
    private void updateBoard() {
        names.setText("");
        scores.setText("");

        for (int i = 0; i < pane.getGameLoop().getNames().length; i++) {
            names.setText(names.getText() + pane.getGameLoop().getNames()[i] + "\n");
        }

        for (int i = 0; i < pane.getGameLoop().getScores().length; i++) {
            scores.setText(scores.getText() + "\n\n" + pane.getGameLoop().getScores()[i]);
        }
    }
    /**Sets where the text is displayed*/
    private void setTextArea() {

        names.setTranslateX(452.0);
        names.setTranslateY(147.0);

        scores.setTranslateX(664.0);
        scores.setTranslateY(121.0);

        /*
        names.setOnMouseDragged(e -> {
            names.setTranslateX(e.getSceneX());
            names.setTranslateY(e.getSceneY());
            System.out.println(names.getTranslateX() + ", " + names.getTranslateY());
        });
        *
        scores.setOnMouseDragged(e -> {
            scores.setTranslateX(e.getSceneX());
            scores.setTranslateY(e.getSceneY());
            System.out.println(scores.getTranslateX() + ", " + scores.getTranslateY());
        });
         */
        names.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        scores.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    }
    /**Sets the properties for this elements*/
    private void setCloseBtn() {
        closeBtn = new ImageView(closeBtnImage);

        closeBtn.setTranslateX(555.0);
        closeBtn.setTranslateY(339.0);

        /*
        closeBtn.setOnMouseDragged(e -> {
            closeBtn.setTranslateX(e.getSceneX());
            closeBtn.setTranslateY(e.getSceneY());
            System.out.println(closeBtn.getTranslateX() + ", " + closeBtn.getTranslateY());
        });
         */
        closeBtn.setOnMouseReleased(e -> {
            closeLeaderboard();
        });

        closeBtn.setOnMouseEntered(e -> {
            closeBtn.setImage(closeBtnHover);
        });

        closeBtn.setOnMouseExited(e -> {
            closeBtn.setImage(closeBtnImage);
        });
        
        
    }

    /**
     *
     * @return
     */
    public boolean isLeaderboardOpen() {
        return this.leaderboardOpen;
    }

    /**
     *
     */
    public void openLeaderBoard() {
        updateBoard();

        this.pane.getChildren().addAll(background, names, scores, closeBtn);
        this.leaderboardOpen = true;
    }

    /**
     *
     */
    public void closeLeaderboard() {
        this.pane.getChildren().removeAll(background, names, scores, closeBtn);
        this.leaderboardOpen = false;
    }

}