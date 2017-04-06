/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HUD;


import java.util.ArrayList;
import javafx.scene.image.Image;
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
public class HUD extends Pane{
    
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 150;
    
    private Text player = new Text();
    private ArrayList<String> playerNames = new ArrayList<>();
    private static int playerIndex = 0;
    
    
    
    public HUD(){
        this.setMinSize(WIDTH, HEIGHT);
        this.setMaxSize(WIDTH, HEIGHT);
        
        setBackground();
        setPlayer();
    }
    private void setPlayer(){
        //Using player 1 - 4 for now, but these names will be passed by the Player objects
        //coming from the menu classes
        this.getChildren().add(player);
        
        player.setTranslateX(558.5);
        player.setTranslateY(123.5);
        
        /*
        player.setOnMouseDragged(e -> {
            player.setTranslateX(e.getSceneX());
            player.setTranslateY(e.getSceneY());
            System.out.println(player.getTranslateX() + ", " + player.getTranslateY());
        });
        */
        
        
        playerNames.add("Player 1");
        playerNames.add("Player 2");
        playerNames.add("Player 3");
        playerNames.add("Player 4");
        
        player.setFont(Font.font("Verdana", FontWeight.BOLD, 35));
        player.setText(playerNames.get(playerIndex));
    }
    public void nextPlayer(){
        if (playerIndex == 3)
            playerIndex = 0;
        else
            playerIndex++;
        
        player.setText(playerNames.get(playerIndex));
    }
    
    private void setBackground(){
        BackgroundImage myBI= new BackgroundImage(new Image("Texture/Menus/HUD/Background.png", WIDTH, HEIGHT, false, true),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        this.setBackground(new Background(myBI));
    }
}
