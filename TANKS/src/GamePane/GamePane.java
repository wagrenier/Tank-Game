/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import Tanks.TanksAnimation;
import MapGeneration.MapGeneration;
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
    private WeaponAnimation weaponAnimation;
    WeaponManager weaponManager;
    
    MapGeneration mapGeneration = new MapGeneration(200, 200, 500);
    
    public GamePane(){
        paneSetup(this);
    }
    
    public void paneSetup(Pane pane){
        frontGroundSetup(pane);
        backGroundSetup(pane);
        tanksSetup(pane);
        weaponSetup(pane);
    }
    
    private void weaponSetup(Pane pane){
        weaponManager = new WeaponManager();
        
        tanksAnimation.getTanksOne().setTranslateY(mapGeneration.getY(tanksAnimation.getTanksOne().getTranslateX()));
        
        weaponAnimation = new WeaponAnimation(weaponManager.getWeaponFromWeaponManager(0), tanksAnimation.getTanksOne(), mapGeneration, pane);
        
        //weaponAnimation.launchAnimation();
    }
    
    public void tanksSetup(Pane pane){
        tanksAnimation = new TanksAnimation(mapGeneration, pane, 4);  
    }
    
    public void backGroundSetup(Pane pane){
        
        BackgroundImage myBI= new BackgroundImage(new Image("Pictures/Backgrounds/Background.png", width, height, false, true),
        
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
    
    public void launchAnimation(){
        weaponAnimation.launchAnimation();
    }

}
