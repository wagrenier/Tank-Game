/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import HUD.HUD;
import Tanks.TanksAnimation;
import MapGeneration.MapGeneration;
import Tanks.Tanks;
import Weapon.Weapon;
import Weapon.WeaponAnimation;
import Weapon.WeaponManager;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author MSI
 */
public class GamePane extends Pane{
    
    private double width = 1200;
    private double height = 800;
    private TanksAnimation tanksAnimation;
    MapGeneration mapGeneration = new MapGeneration(450, 100, 500);
    
    public GamePane(){
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        paneSetup(this);
    }
    
    public void paneSetup(Pane pane){
        frontGroundSetup(pane);
        backGroundSetup(pane);
        tanksSetup(pane);
    }
    
    public void tanksSetup(Pane pane){
        tanksAnimation = new TanksAnimation(mapGeneration, pane, 4);  
    }
    
    public void backGroundSetup(Pane pane){
        
        BackgroundImage myBI= new BackgroundImage(new Image("Texture/Menus/MapMenu/Space Map.png", width, height, false, true),
        
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
       
        pane.setBackground(new Background(myBI));
    }
    
    public void frontGroundSetup(Pane pane){
       
       Rectangle rect;
       double yLocation = 0;
       
       for (int i = 0; i < width; i++){
            rect = new Rectangle();
            yLocation = mapGeneration.getY(i);
            
            //rect.setStyle();
            rect.setTranslateX(i);
            rect.setHeight(height - yLocation);
            rect.setTranslateY(yLocation);
            
            rect.setWidth(.25);
            
            //rect.setFill(Color.TRANSPARENT);
            //rect.setStroke(Color.GREEN);
            pane.getChildren().add(rect);
            
        }

   }
    
    public HUD getHUD(){
        return tanksAnimation.getHud();
    }
}
