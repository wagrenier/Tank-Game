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

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Cedrik Dubois
 */
public class HelpMenu {
    
    /**Checks if the help menu is currently open*/
    private boolean helpOpen = false;
    /**The pane of the game*/
    private Pane pane;
    
    /**Sets the image for this object*/
    private ImageView background = new ImageView(new Image("Texture/Menus/HelpMenu/Background.png"));
    /**Sets the image for this object*/
    private ImageView closeBtn;
    
    /**Sets the image for this object*/
    private Image closeBtnImage = new Image("Texture/Menus/HelpMenu/Close Button.png");
    /**Sets the image for this object*/
    private Image closeBtnHover = new Image("Texture/Menus/HelpMenu/Close Button Hover.png");
    /**Sets the image for this object*/
    private Text helpInformation = new Text();
    
    /**
     *Constructor
     * @param pane
     * @param cursorImg
     */
    public HelpMenu(Pane pane, ImageCursor cursorImg) {
        this.pane = pane;

        setTextArea();
        setCloseBtn(cursorImg);
    }

    /**
     *Constructor
     * @param pane
     */
    public HelpMenu(Pane pane) {
        this.pane = pane;

        setTextArea();
        setCloseBtn();
    }
    
    /**Sets the properties for this elements*/
    private void setCloseBtn(ImageCursor cursorImg) {
        closeBtn = new ImageView(closeBtnImage);

        closeBtn.setTranslateX(555.0);
        closeBtn.setTranslateY(130.0);

        /*
        closeBtn.setOnMouseDragged(e -> {
            closeBtn.setTranslateX(e.getSceneX());
            closeBtn.setTranslateY(e.getSceneY());
            System.out.println(closeBtn.getTranslateX() + ", " + closeBtn.getTranslateY());
        });
         */
        closeBtn.setOnMouseReleased(e -> {
            closeHelpMenu();
        });

        closeBtn.setOnMouseEntered(e -> {
            closeBtn.setImage(closeBtnHover);
            this.pane.setCursor(Cursor.HAND);
        });

        closeBtn.setOnMouseExited(e -> {
            closeBtn.setImage(closeBtnImage);
            this.pane.setCursor(cursorImg);
        });
    }
    
    /**Sets the properties for this elements*/
    private void setCloseBtn() {
        closeBtn = new ImageView(closeBtnImage);

        closeBtn.setTranslateX(555.0);
        closeBtn.setTranslateY(130.0);

        /*
        closeBtn.setOnMouseDragged(e -> {
            closeBtn.setTranslateX(e.getSceneX());
            closeBtn.setTranslateY(e.getSceneY());
            System.out.println(closeBtn.getTranslateX() + ", " + closeBtn.getTranslateY());
        });
         */
        closeBtn.setOnMouseReleased(e -> {
            closeHelpMenu();
        });

        closeBtn.setOnMouseEntered(e -> {
            closeBtn.setImage(closeBtnHover);
        });

        closeBtn.setOnMouseExited(e -> {
            closeBtn.setImage(closeBtnImage);
        });
    }
    
    /**Sets the properties for this elements*/
    private void setTextArea() {
        String information1 = "HELP MENU\n---------\n"
                + "Welcome to the game of TANKS. This game\n"
                + "is in fact very simple, but here is some\n"
                + "information you might need to play it right\n\n"
                + "CONTROLS:\n"
                + "Move Right: right arrow\n"
                + "Move left: left arrow\n"
                + "Aim up: up arrow\n"
                + "Aim down: down arrow\n"
                + "Fire: spacebar\n"
                + "Skip turn: E\n\n"
                + "If you are facing the opposite way you wish to move\n"
                + "you must press twice, once to turn around\n"
                + "and another to start moving.\n\n"
                + "For every turn, you may either choose to\n"
                + "move left or right, fire any selected weapon,\n"
                + "or use and special item.\n\n"
                + "Your turn will end automatically.";

        helpInformation.setText(information1);

        /*
        helpInformation.setOnMouseDragged(e -> {
            helpInformation.setTranslateX(e.getSceneX());
            helpInformation.setTranslateY(e.getSceneY());
            System.out.println(helpInformation.getTranslateX() + ", " + helpInformation.getTranslateY());
        });
         */
        helpInformation.setTranslateX(385.0);
        helpInformation.setTranslateY(180.0);

        helpInformation.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    }

    /**
     *
     */
    public void openHelpMenu() {
        this.pane.getChildren().add(background);
        this.pane.getChildren().add(helpInformation);
        this.pane.getChildren().add(closeBtn);
        helpOpen = true;
    }

    /**
     *
     */
    public void closeHelpMenu() {
        this.pane.getChildren().remove(background);
        this.pane.getChildren().remove(helpInformation);
        this.pane.getChildren().remove(closeBtn);
        helpOpen = false;
    }

    /**
     *
     * @return
     */
    public boolean isHelpOpen() {
        return helpOpen;
    }
}