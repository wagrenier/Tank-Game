/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import Weapon.HitDetection;
import GamePane.GamePane;
import HUD.HUD;
import MapGeneration.MapGeneration;
import Sounds.SoundLib;
import Weapon.HitDetectionMine;
import Weapon.HitDetectionRC;
import Weapon.RCAnimation;
import Weapon.Weapon;
import Weapon.WeaponAnimation;
import Weapon.WeaponManager;
import PlayerSettings.Player;
import java.util.ArrayList;
import javafx.animation.Animation;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.CacheHint;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class TanksAnimation{
    
    //Width of the pane in pixels
    private double width = 1200;
    
    //Number of players in the game
    private int numOfPlayer;
    
    //The number of mines currently in the game
    private int numOfMines = 0;
    
    //Gravity of the game is not the same for weapon because like that the tanks stick to the ground
    private double gravity = 0.05;
    
    //HUD for the game
    private HUD hud;
    
    //The index of current player turn
    private int indexOfCurrentPlayerTurn = 0;
    
    private boolean keyReleased = true;
    private boolean shotFired = false;
    private boolean turnPlayed = false;
    
    //Variables for tank 1
    private String  pathForTextureTankOne = "Texture/Tanks/Canada/Body/Red_Tank_(100x100).png";
    private String pathForTextureFlippedTankOne = "Texture/Tanks/Canada/Body/Red_Tank_Flipped_(100x100).png";
    private String pathForTextureCannonOne = "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100).png";
    
    
    
    //Variables for tank 2
    private String pathForTextureTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_(100x100).png";
    private String pathForTextureFlippedTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_Flipped_(100x100).png";
    private String pathForTextureCannonTwo = "Texture/Tanks/China/Cannon/Yellow_Cannon_(100x100).png";
    
    
    //Variables for tanks 3
    private String pathForTextureTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_(100x100).png";
    private String pathForTextureFlippedTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_Flipped_(100x100).png";
    private String pathForTextureCannonThree = "Texture/Tanks/NorthKorea/Cannon/Blue_Cannon_(100x100).png";
    
    
    //Variables for tank 4
    private String pathForTextureTankFour = "Texture/Tanks/USA/Body/Green_Tank_(100x100).png";
    private String pathForTextureFlippedTankFour = "Texture/Tanks/USA/Body/Green_Tank_Flipped_(100x100).png";
    private String pathForTextureCannonFour = "Texture/Tanks/USA/Cannon/Green_Cannon_(100x100).png";
    
    
    
    private Tanks tanksOne;
    private Tanks tanksTwo;
    private Tanks tanksThree;
    private Tanks tanksFour;
    
    //Variable for the tanks' animation
    private Timeline animation;
    private Timeline animation2;
    private Timeline animation3;
    private Timeline animation4;
    
    //Variables for the tanks' progress bar animation, which serves as the setter for the initial velocity
    private Timeline progressBarAnimationOne;
    private Timeline progressBarAnimationTwo;
    private Timeline progressBarAnimationThree;
    private Timeline progressBarAnimationFour;
    
    //Progress Bar of the tanks
    private ProgressBar barOne = new ProgressBar(0);
    private ProgressBar barTwo = new ProgressBar(0);
    private ProgressBar barThree = new ProgressBar(0);
    private ProgressBar barFour = new ProgressBar(0);
    
    //sounds of the game
    private SoundLib sounds;
    
    //Pane of the game
    private GamePane pane;
    
    //This variable generates the map of the game
    private MapGeneration mapGeneration;
    
    //Contains all the available weapons in the game
    private WeaponManager weaponManager;
    
    private ArrayList<Player> playerArrayList;
    
    private RCAnimation rcAnimation;
    private WeaponAnimation weaponAnimation;
    private Player[] playerArray;
    private Tanks[] tanksArray = new Tanks[4];
    private Tanks[] tanksArrayUsed;
    private Timeline[] tanksAnimationArrayUsed;
    private Timeline[] progressBarAnimationUsed = new Timeline[4];
    private ProgressBar[] progressBarUsed = new ProgressBar[4];
    private ArrayList<Weapon> mineLocationArrayList = new ArrayList<>();
    private ArrayList<HitDetectionMine> mineHitDetectionArrayList = new ArrayList<>();
    
    private Text nextPlayerText = new Text();
    private Timeline nextPlayerAnimation;
    
    public TanksAnimation(MapGeneration mapGeneration, GamePane pane, int numOfPlayer, ArrayList<Player> playerArrayList, int currentPlayer, SoundLib sounds) {
        this.mapGeneration = mapGeneration;
        this.indexOfCurrentPlayerTurn = currentPlayer;
        this.pane = pane;
        this.numOfPlayer = numOfPlayer; 
        this.playerArray = new Player[this.numOfPlayer];
        this.playerArrayList = playerArrayList;
        this.sounds = sounds;
        
        setNextPlayerAnimation();
        
        tanksOne = new Tanks(pathForTextureTankOne, pathForTextureFlippedTankOne, pathForTextureCannonOne, "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100)_Flipped.png", 2);
        tanksTwo = new Tanks(pathForTextureTankTwo, pathForTextureFlippedTankTwo, pathForTextureCannonTwo, "Texture/Tanks/China/Cannon/Yellow_Cannon_Flipped_(100x100).png", 3);
        tanksThree = new Tanks(pathForTextureTankThree, pathForTextureFlippedTankThree, pathForTextureCannonThree, "Texture/Tanks/NorthKorea/Cannon/Blue_Cannon_Flipped_(100x100).png", 0);
        tanksFour = new Tanks(pathForTextureTankFour, pathForTextureFlippedTankFour, pathForTextureCannonFour, "Texture/Tanks/USA/Cannon/Green_Cannon_Flipped_(100x100).png", 1);
        
        progressBarAnimationOne = progressBarInitialSetup(barOne);
        progressBarAnimationTwo = progressBarInitialSetup(barTwo);
        progressBarAnimationThree = progressBarInitialSetup(barThree);
        progressBarAnimationFour = progressBarInitialSetup(barFour);
        
        tanksArray[0] = tanksOne;
        tanksArray[1] = tanksTwo;
        tanksArray[2] = tanksThree;
        tanksArray[3] = tanksFour;
        
        
        tanksArrayUsed = new Tanks[numOfPlayer];
        tanksAnimationArrayUsed = new Timeline[numOfPlayer];
        progressBarAnimationUsed = new Timeline[numOfPlayer];
        progressBarUsed = new ProgressBar[numOfPlayer];
        
        
        animation = animationForTanks(tanksOne, 300, progressBarAnimationOne);
        animation2 = animationForTanks(tanksTwo, 500, progressBarAnimationTwo);
        animation3 = animationForTanks(tanksThree, 700, progressBarAnimationThree);
        animation4 = animationForTanks(tanksFour, 100, progressBarAnimationFour);
        
        for(int i = 0; i < playerArray.length; i++){
            playerArray[i] = playerArrayList.get(i);
            
            switch(playerArray[i].getTeam()){
                case 0:{ 
                    tanksArrayUsed[i] = tanksArray[2];
                    tanksAnimationArrayUsed[i] = animation3;
                    progressBarUsed[i] = barThree;
                    progressBarAnimationUsed[i] = progressBarAnimationThree;
                    if(playerArray[i].isIsAI()){
                        tanksArrayUsed[i].setIsAI(true);
                    }
                }
                break;
                
                case 1: {
                    tanksArrayUsed[i] = tanksArray[3];
                    tanksAnimationArrayUsed[i] = animation4;
                    progressBarUsed[i] = barFour;
                    progressBarAnimationUsed[i] = progressBarAnimationFour;
                    if(playerArray[i].isIsAI()){
                        tanksArrayUsed[i].setIsAI(true);
                    }
                }break;
                
                case 2: {
                    tanksArrayUsed[i] = tanksArray[0];
                    tanksAnimationArrayUsed[i] = animation;
                    progressBarUsed[i] = barOne;
                    progressBarAnimationUsed[i] = progressBarAnimationOne;
                    if(playerArray[i].isIsAI()){
                        tanksArrayUsed[i].setIsAI(true);
                    }
                }break;
                
                case 3: {
                    tanksArrayUsed[i] = tanksArray[1];
                    tanksAnimationArrayUsed[i] = animation2;
                    progressBarUsed[i] = barTwo;
                    progressBarAnimationUsed[i] = progressBarAnimationTwo;
                    if(playerArray[i].isIsAI()){
                        tanksArrayUsed[i].setIsAI(true);
                    }
                }break;
                }
            }
        
        weaponManager = new WeaponManager();
        hud = new HUD(weaponManager, this.pane, sounds);
        
        
        
        setupTanksPlayer();
        
        Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(((int)(Math.random() * 9))).getDamage(), weaponManager.getWeaponFromWeaponManager(((int)(Math.random() * 9))).getTexturePath(), 0);        
        
       weaponAnimation = new WeaponAnimation(weapon, tanksOne, mapGeneration, pane, 1, 1, sounds); //Values are not important as this object wil;l never be used (Initialized to prevent nuillPointerException)
       rcAnimation = new RCAnimation(weapon, tanksOne, mapGeneration, pane, sounds);
       pane.setOnKeyReleased(e -> { 
           keyReleased = true;
       });
        pane.setOnKeyPressed(e -> {
            
            if(keyReleased){
                System.out.println("Key Entered");
                keyPressed(e.getCode(), 
                    tanksArrayUsed[indexOfCurrentPlayerTurn], 
                    tanksAnimationArrayUsed[indexOfCurrentPlayerTurn],
                    progressBarAnimationUsed[indexOfCurrentPlayerTurn], 
                    progressBarUsed[indexOfCurrentPlayerTurn]);
                keyReleased = false;
            }
            }); 
    }
    
    private void setNextPlayerAnimation(){
        
        nextPlayerText.setTranslateX(472.5);
        nextPlayerText.setTranslateY(66.0);
        nextPlayerText.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        
        
        nextPlayerAnimation = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            this.pane.getChildren().remove(nextPlayerText);
        }));
        nextPlayerAnimation.setCycleCount(1);
        
    }
    
    private Timeline progressBarInitialSetup(ProgressBar bar){
        bar.setPrefSize(200, 24);

        Timeline progressBarAnimation = new Timeline(
        new KeyFrame(
                Duration.ZERO,       
                new KeyValue(bar.progressProperty(), 0)
        ),
        new KeyFrame(
                Duration.millis(500), 
                new KeyValue(bar.progressProperty(), 1)
        )
    );
        
        progressBarAnimation.setCycleCount(Timeline.INDEFINITE);
        
        return progressBarAnimation;
    }
    
    private void progressBarInGameAnimationPlay(Tanks tank, Timeline progressBarAnimation, ProgressBar bar){
        if(progressBarAnimation.getStatus().compareTo(RUNNING) == 0){
            progressBarInGameAnimationStop(tank, progressBarAnimation, bar);
        }
        
        else{
            bar.setTranslateX(tank.getTranslateX() - 50);
            bar.setTranslateY(tank.getTranslateY() - 100);
            pane.getChildren().add(bar);
            progressBarAnimation.setAutoReverse(true);
            progressBarAnimation.playFromStart();
        }
       
        
    }
    
    private void progressBarInGameAnimationStop(Tanks tank, Timeline progressBarAnimation, ProgressBar bar){
        progressBarAnimation.stop();
        pane.getChildren().remove(bar);
        weaponSetup(tank, bar.getProgress());
    }
    
    private void setupTanksPlayer(){
        for(int i = 0; i < tanksArrayUsed.length; i++){
            pane.getChildren().add(tanksArrayUsed[i].getCannon());
            pane.getChildren().add(tanksArrayUsed[i]);
            tanksArrayUsed[i].setIsTankAlive(true);
        }
        
        if(tanksOne.isTankAlive()){
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.playFromStart();
        }
        
        if(tanksTwo.isTankAlive()){
            animation2.setCycleCount(Timeline.INDEFINITE);
            animation2.playFromStart();
        }
        
        if(tanksThree.isTankAlive()){
            animation3.setCycleCount(Timeline.INDEFINITE);
            animation3.playFromStart();
        }
        if(tanksFour.isTankAlive()){
            animation4.setCycleCount(Timeline.INDEFINITE);
            animation4.playFromStart();
        }
    }
    
    private Timeline animationForTanks(Tanks tanks, int initialPosition, Timeline progressBarAnimation){
        
        
        tanks.setLayoutY(-18);
        tanks.getCannon().setLayoutY(tanks.getLayoutY());
        
        tanks.setTranslateX(initialPosition);
        tanks.setTranslateY(mapGeneration.getY(initialPosition));
        tanks.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())));
        
        tanks.getCannon().setTranslateX(tanks.getTranslateX());
        tanks.getCannon().setTranslateY(tanks.getCannon().getTranslateY());
        
        tanks.setxSpeed(0);
        tanks.setCache(true);
        tanks.setCacheHint(CacheHint.ROTATE);
        tanks.setCacheHint(CacheHint.SPEED);
        tanks.getCannon().setCacheHint(CacheHint.ROTATE);
        tanks.getCannon().setCacheHint(CacheHint.SPEED);
        
        
        Timeline tankAnimation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            updateTanksStatus();
            
            
            if(progressBarAnimation.getStatus().compareTo(RUNNING) == 0){
            tanks.setxSpeed(0);
            tanks.setySpeed(0);
        }
            
            tanks.setTranslateX((tanks.getTranslateX() + tanks.getxSpeed())); 
            tanks.setTranslateY(mapGeneration.getY(tanks.getTranslateX()));
            tanks.getCannon().setTranslateX(tanks.getTranslateX());
            tanks.getCannon().setTranslateY(tanks.getTranslateY());
            
            tanks.setYTanks(mapGeneration.getY(tanks.getTranslateX()));
            tanks.getCannon().setTranslateX(tanks.getTranslateX());
            
            if(tanks.getxSpeed() < 0){
                tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getCannon().getTranslateX()) + tanks.getCannon().getCanonAngle()));
            }
            
            else if(tanks.getxSpeed() == 0){
                if(tanks.isIsImageFlipped()){
                    tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getCannon().getTranslateX()) + tanks.getCannon().getCanonAngle()));
                }
                else{
                    tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getCannon().getTranslateX()) - tanks.getCannon().getCanonAngle()));   
                }
            }
            
            else if(tanks.getxSpeed() > 0){
                tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getCannon().getTranslateX()) - tanks.getCannon().getCanonAngle()));
            }
            
            
            
            if(tanks.getTranslateX() <= 10 || tanks.getTranslateX() >= 1190){
                tanks.setxSpeed(tanks.getxSpeed() * -1);
                if(tanks.getTranslateX() <= 10){
                    tanks.normalTexture();
                }
                
                else{
                    tanks.flipTexture();
                }
            }
            
            
            tanks.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())));
            
            if (tanks.getTranslateY() < tanks.getYTanks() ){
                tanks.setySpeed(tanks.getySpeed() + gravity);
            }
            else{
                tanks.setySpeed(0);
            }
            if(tanks.getTranslateY() > tanks.getYTanks()){
                tanks.setTranslateY(tanks.getYTanks());
                tanks.getCannon().setTranslateY(tanks.getCannon().getTranslateX());
            } 
            }));
        return tankAnimation;
    }
    
    public void weaponSetup(Tanks tank, double x){
        shotFired = true;
        Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(this.hud.getWeaponIndex()).getDamage(), weaponManager.getWeaponFromWeaponManager(this.hud.getWeaponIndex()).getTexturePath(), 0);        
        //this.hud.updateItemStatus();
        this.hud.updateWeaponStatus();
        if(pane.getMapGeneration().getMapIndex() != 2){
            x *= hud.getWindResistance();
        }
        
        int tankOffsetX = 25;
        int tankOffsetY = 50;
        int cannonOffset = 25;
        //weapon.setLayoutX(-tankOffsetX);
        //weapon.setLayoutY(-tankOffsetY + cannonOffset);
        
        //Special Setup For Mines
        if(this.hud.getWeaponIndex() == 7){
            mineLocationArrayList.add(weapon);
            //weapon.setCenterY(-5);
            //weapon.setLayoutX(-50);
           //weapon.setLayoutY(-30);
            weapon.setTranslateX(tank.getTranslateX());
            weapon.setTranslateY(tank.getTranslateY());
            weapon.setRotate(tank.getRotate());
            //weapon.setCenterY(-5);
            pane.getChildren().add(mineLocationArrayList.get(numOfMines));
            hitDetectionMine(tank, weapon);
            numOfMines++;
        }
        //Special Setup For RC
        else if(this.hud.getWeaponIndex() == 8){
            rcAnimation = new RCAnimation(weapon, tank, mapGeneration, pane, sounds);
            weapon.setLayoutX(0);
            weapon.setLayoutY(0);
            weapon.setCenterY(-10);
            //weapon.setLayoutX(-50);
            //weapon.setLayoutY(-30);
            rcAnimation.launchAnimation();
            hitDetectionRC(tank, weapon, rcAnimation);
        }
        
        else{
            weaponAnimation = new WeaponAnimation(weapon, tank, mapGeneration, pane, x, mapGeneration.getGravity(), sounds);
            weaponAnimation.launchAnimation();
            
            if (sounds.isSoundPlaying()){
            sounds.getTankShot().seek(Duration.ZERO);
            sounds.getTankShot().play();
            }
            
            hitDetection(tank, weapon);
            }
    }
    
    public void resetSpeed(){
        tanksOne.setxSpeed(0);
        tanksOne.setySpeed(0);
        tanksOne.setVelocity(0);
        
        tanksTwo.setxSpeed(0);
        tanksTwo.setySpeed(0);
        tanksTwo.setVelocity(0);
        
        tanksThree.setxSpeed(0);
        tanksThree.setySpeed(0);
        tanksThree.setVelocity(0);
        
        tanksFour.setxSpeed(0);
        tanksFour.setySpeed(0);
        tanksFour.setVelocity(0);
    }
    
    public void hitDetectionMine(Tanks tank, Weapon weapon){
        HitDetectionMine hitDetectionMine = new HitDetectionMine(tanksOne, tanksTwo, tanksThree, tanksFour, tank, weapon, this, pane, sounds);
        mineHitDetectionArrayList.add(hitDetectionMine);
        hitDetectionMine.start();
    }
    
    private void hitDetectionRC(Tanks tank, Weapon weapon, RCAnimation rcAnimation){
        HitDetectionRC hitDetectionRC = new HitDetectionRC(rcAnimation, tanksOne, tanksTwo, tanksThree, tanksFour, tank, animation, animation2, animation3, animation4, pane, weapon);
        hitDetectionRC.start();
    }
    
    private void hitDetection(Tanks tank, Weapon weapon){
        HitDetection hitDetection = new HitDetection(weaponAnimation, hud, tanksOne, tanksTwo, tanksThree, tanksFour, tank, animation, animation2, animation3, animation4, pane, weapon);
        hitDetection.start();
        System.out.println("Hit Something");
    }
    
    public void updateTanksStatus(){
        if(!tanksOne.isTankAlive()){
               animation.stop();
               pane.getChildren().remove(tanksOne);
               pane.getChildren().remove(tanksOne.getCannon());
           }
        if(!tanksTwo.isTankAlive()){
               animation2.stop();
               pane.getChildren().remove(tanksTwo);
               pane.getChildren().remove(tanksTwo.getCannon());
           }
        
        if(!tanksThree.isTankAlive()){
               animation3.stop();
               pane.getChildren().remove(tanksThree);
               pane.getChildren().remove(tanksThree.getCannon());
           }
       
           if(!tanksFour.isTankAlive()){
               animation4.stop();
               pane.getChildren().remove(tanksFour);
               pane.getChildren().remove(tanksFour.getCannon());
           }
    }
    
    public boolean moreThanOneTankAlive(){
        int numOfTanksAlive = 0;
        
        for(int i = 0; i < tanksArrayUsed.length; i++){
            if(tanksArrayUsed[i].isTankAlive()){
                numOfTanksAlive++;
            }
            
            if(numOfTanksAlive == 2){
                
                return true;
            }
            
        }
        return false;
    }
    
    public int numOfTanksAlive(){
        int numOfTanksAlive = 0;
        
        for(int i = 0; i < tanksArrayUsed.length; i++){
            if(tanksArrayUsed[i].isTankAlive()){
                numOfTanksAlive++;
            }
            
        }
        return numOfTanksAlive;
    }
    
    public void keyPressed(KeyCode x, Tanks tank, Timeline animationTank, Timeline progressBarAnimation, ProgressBar bar){
        if(hud.getPauseMenu().isGamePaused() || turnPlayed || shotFired){
            
        }
         
        else{
        switch (x){
            
            /*
            case F:{
                pane.getChildren().clear();
                pane.mapSetupNewMapGeneration(pane);
                pane.paneSetup(pane, 0);
            }break;*/
            
            //Controls for player 1
            case SPACE: {
                if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0 && !tank.isIsAI())
                    if(this.hud.getWeaponIndex() == 7){
                        weaponSetup(tank, 1);
                    }
                    else if(this.hud.getWeaponIndex() == 8){
                        weaponSetup(tank, 1);
                    }
                    else{
                        progressBarInGameAnimationPlay(tank, progressBarAnimation, bar);
                    }
                    
            }break;
                        
                case LEFT: {
                    
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0 && !tank.isIsAI() && !turnPlayed && keyReleased  && rcAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                    if(tank.getVelocity() == 0  && !tank.isIsImageFlipped()){
                            //tank.setxSpeed(tank.getxSpeed() - 0.05);
                            tank.flipTexture();
                            break;
                        }
                    
                    else if(tank.getVelocity() == 0 && tank.isIsImageFlipped()){
                        tank.setVelocity(tank.getVelocity() - 0.05);
                        tank.setVelocities(mapGeneration);
                        turnPlayed = true;
                    }
                    }
                    }break;
                    
                case RIGHT: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0 && !tank.isIsAI() && !turnPlayed && keyReleased  && rcAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                    if(tank.getVelocity() == 0 && tank.isIsImageFlipped()){
                            //tank.setxSpeed(tank.getxSpeed() + 0.05);
                            tank.normalTexture();
                            break;
                        }
                    
                    else if(tank.getVelocity() == 0  && !tank.isIsImageFlipped())
                        tank.setVelocity(tank.getVelocity() + 0.05);
                    tank.setVelocities(mapGeneration);
                        turnPlayed = true;
                    }
                    }break;
                    
                case UP: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0 && !tank.isIsAI()  && rcAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0)
                        tank.getCannon().higherAngle();
                    }break;
                    
                case DOWN: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0 && !tank.isIsAI()  && rcAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0)
                    tank.getCannon().lowerAngle();
                }break;     
                
                //Ends the Turn but gives extra money
                case E: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0 && !tank.isIsAI()  && rcAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                        pane.getGameLoop().setForceEndedTurn(true);
                    }
                }break;
        }
        }
    }
    
    public void keyPressedAI(KeyCode x, Tanks tank, Timeline animationTank, Timeline progressBarAnimation, ProgressBar bar){
        if(hud.getPauseMenu().isGamePaused()){
            
        }
         
        else{
        switch (x){
            
            
            //Controls for player 1
            case SPACE: {
                if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0)
                if(this.hud.getWeaponIndex() == 7){
                        weaponSetup(tank, 1);
                    }
                    else if(this.hud.getWeaponIndex() == 8){
                        weaponSetup(tank, 1);
                    }
                    else{
                        progressBarInGameAnimationPlay(tank, progressBarAnimation, bar);
                    }
            }break;
                    
                    
                case LEFT: {
                    
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                    if(tank.getxSpeed() == 0){
                            tank.setxSpeed(tank.getxSpeed() - 0.05);
                            //tanksOne.setRotate(90);
                            
                            tank.flipTexture();
                            //tank.getCannon().flipTexture();
                            //tanksOne.getCannon().setRotate(180 - Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                        }
                    
                    else if(tank.getxSpeed() > -.05){
                        tank.setxSpeed(tank.getxSpeed() - 0.05);
                        //xspeed -= 0.1;
                    }
                    }
                    }break;
                    
                case RIGHT: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                    if(tank.getxSpeed() == 0){
                            tank.setxSpeed(tank.getxSpeed() + 0.05);
                            tank.normalTexture();
                            //tank.getCannon().normalTexture();
                            //tanksOne.getCannon().setRotate(180 + Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                            //cannon.setRotate(180 - Math.toDegrees(cannon.getCanonAngle()));
                        }
                    
                    else if(tank.getxSpeed() < .05)
                        tank.setxSpeed(tank.getxSpeed() + 0.05);
                        //xspeed += 0.1;
                    }
                    }break;
                    
                case UP: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0)
                        tank.getCannon().higherAngle();
                        //tanksOne.updateSomething();
                    }break;
                    
                case DOWN: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0)
                    tank.getCannon().lowerAngle();
                    //tanksOne.updateSomething();break;
                }break;       
                
                case E: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                        pane.getGameLoop().setForceEndedTurn(true);
                    }
                }
        }

        }
    }
    
    public void updateTurn(){
        nextPlayerText.setText(playerArray[indexOfCurrentPlayerTurn].getUsername() + "'s turn!");
        this.pane.getChildren().remove(nextPlayerText);
        this.pane.getChildren().add(nextPlayerText);
        nextPlayerAnimation.play();
        
        hud.setCurrentPlayerName(playerArray[indexOfCurrentPlayerTurn].getUsername());
        hud.setCurrentPlayerTank(tanksArrayUsed[indexOfCurrentPlayerTurn], tanksArrayUsed[indexOfCurrentPlayerTurn].getTeam(), indexOfCurrentPlayerTurn);
    }
    
    public void mineExploded(){
        numOfMines--;
    }
    
    //Methods Related to saving/loading the game
    public double[][] obtainMinesLocation(){
        if(mineLocationArrayList.isEmpty()){
            return null;//If no mines are in the game
        }
        
        double[][] minesLocation = new double[mineLocationArrayList.size()][3];
        for(int i = 0; i < mineLocationArrayList.size(); i++){
            minesLocation[i][0] = mineHitDetectionArrayList.get(i).getTank().getTeam();
            minesLocation[i][1] = mineLocationArrayList.get(i).getTranslateX();
            minesLocation[i][2] = mineLocationArrayList.get(i).getTranslateY();
        }
        return minesLocation;
    }
    
    public void resetMineLocation(double[][] mineLocation){
        
        for(int i = 0; i < mineLocation.length; i++){
            
            switch((int) mineLocation[i][0]){
                case 0:{ 
                    Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(7).getDamage(), weaponManager.getWeaponFromWeaponManager(7).getTexturePath(), 0);
        
                    mineLocationArrayList.add(weapon);
                    weapon.setTranslateX(mineLocation[i][1]);
                    weapon.setTranslateY(mineLocation[i][2]);
                    weapon.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(mineLocation[i][1])));
                    pane.getChildren().add(mineLocationArrayList.get(numOfMines));
                    hitDetectionMine(tanksThree, weapon);
                    numOfMines++;
                }
                break;
                
                case 1: {
                    Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(7).getDamage(), weaponManager.getWeaponFromWeaponManager(7).getTexturePath(), 0);
        
                    mineLocationArrayList.add(weapon);
                    weapon.setTranslateX(mineLocation[i][1]);
                    weapon.setTranslateY(mineLocation[i][2]);
                    //weapon.setCenterY(-5);
                    weapon.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(mineLocation[i][1])));
                    pane.getChildren().add(mineLocationArrayList.get(numOfMines));
                    hitDetectionMine(tanksFour, weapon);
                    numOfMines++;
                }break;
                
                case 2: {
                    Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(7).getDamage(), weaponManager.getWeaponFromWeaponManager(7).getTexturePath(), 0);
        
                    mineLocationArrayList.add(weapon);
                    weapon.setTranslateX(mineLocation[i][1]);
                    weapon.setTranslateY(mineLocation[i][2]);
                    weapon.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(mineLocation[i][1])));
                    pane.getChildren().add(mineLocationArrayList.get(numOfMines));
                    hitDetectionMine(tanksOne, weapon);
                    numOfMines++;
                }break;
                
                case 3: {
                    Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(7).getDamage(), weaponManager.getWeaponFromWeaponManager(7).getTexturePath(), 0);
        
                    mineLocationArrayList.add(weapon);
                    weapon.setTranslateX(mineLocation[i][1]);
                    weapon.setTranslateY(mineLocation[i][2]);
                    weapon.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(mineLocation[i][1])));
                    pane.getChildren().add(mineLocationArrayList.get(numOfMines));
                    hitDetectionMine(tanksTwo, weapon);
                    numOfMines++;
                }break;
                }
            }
    }
    
    public boolean isPossibleToSave(){
        if(tanksArrayUsed[indexOfCurrentPlayerTurn].getxSpeed() == 0 && progressBarAnimationUsed[indexOfCurrentPlayerTurn].getStatus().compareTo(Animation.Status.STOPPED) == 0 && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
            System.out.println("Possible");
            return true;
        }
        else{
            System.out.println("Not Possible");
            return false;
        }
    }
    
    public String reasonSaveFailed(){
        if(!(tanksArrayUsed[indexOfCurrentPlayerTurn].getxSpeed() == 0)){
            return "Game Cannot Be Saved When The Tanks Are Moving. Please Wait For The End Of The Turn.";
        }
        if(progressBarAnimationUsed[indexOfCurrentPlayerTurn].getStatus().compareTo(Animation.Status.RUNNING) == 0){
            return "Game Cannot Be Saved Because A Weapon Is Being Fire. Please Fire The Weapon And Wait For It To Land.";
        }
        if(weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
           return "Game Cannot Be Saved Because A Weapon Was Fired. Please Wait For The Weapon To Land."; 
        }
        return "Error Finding The Error!";
    }
    
    public int[] obtainTanksHP(){
        int[] tankHP = new int[tanksArrayUsed.length];
        for(int i = 0; i < tankHP.length; i++){
           tankHP[i] = tanksArrayUsed[i].getLifePoint();
        }
        return tankHP;
    }
    
    public void resetTankHPSave(int[] tankHP){
        for(int i = 0; i < tankHP.length; i++){
            tanksArrayUsed[i].setLifePoint(tankHP[i]);
        }
    }
    
    public void resetTankOrientationSave(boolean[] tankOrientation){
        for(int i = 0; i < tankOrientation.length; i++){
            if(tankOrientation[i])
                tanksArrayUsed[i].flipTexture();
        }
    }
    
    public boolean[] obtainEachTanksDirection(){
        boolean[] tankOrientation = new boolean[tanksArrayUsed.length];
        for(int i = 0; i < tankOrientation.length; i++){
            tankOrientation[i] = tanksArrayUsed[i].isIsImageFlipped();
        }
        return tankOrientation;
    }
    
    public void resetTankPositionSave(double[][] tanksArraySave){
        for(int i = 0; i < tanksArraySave.length; i++){
            for(int k = 0; k < 2; k++){
                if(k % 2 == 0)
                    tanksArrayUsed[i].setTranslateX(tanksArraySave[i][k]);
                
                else
                    tanksArrayUsed[i].setTranslateY(tanksArraySave[i][k]);
            }
        }
    }
    
    public double[][] obtainEachTanksTranslation(){
        double[][] tankLocation = new double[tanksArrayUsed.length][2];
        for(int i = 0; i < tankLocation.length; i++){
            for(int k = 0; k < 2; k++){
                if(k % 2 == 0)
                    tankLocation[i][k] = tanksArrayUsed[i].getTranslateX();
                
                else
                    tankLocation[i][k] = tanksArrayUsed[i].getTranslateY();
            }
        }
        return tankLocation;
    }
    
    public int[] getIndexOfCurrentPlayerTurnArray(){
        int[] turn = new int[1];
        turn[0] = indexOfCurrentPlayerTurn;
        return turn;
    }
    
    public boolean[] obtainWhoIsDead(){
        boolean[] dead = new boolean[tanksArrayUsed.length];
        
        for(int i = 0;i < tanksArrayUsed.length; i++){
            dead[i] = tanksArrayUsed[i].isTankAlive();
        }
        return dead;
    }
    
    public void resetWhoIsDead(boolean[] dead){
        for(int i = 0;i < tanksArrayUsed.length; i++){
            tanksArrayUsed[i].setIsTankAlive(dead[i]);
        }
    }
    
    //To reset the maximum number of pixels a tank can travel
    public double[] obtainMaxPixelMoveSave(){
        double[] maxPixMove = new double[tanksArrayUsed.length];
        for(int i = 0; i < tanksArrayUsed.length; i++){
            maxPixMove[i] = tanksArrayUsed[i].getMaxPixelMove();
        }
        return maxPixMove;
    }
    
    public void resetMaxPixelMove(double[] maxPixMove){
        for(int i = 0; i < tanksArrayUsed.length; i++){
            tanksArrayUsed[i].setMaxPixelMove(maxPixMove[i]);
        }
    }
    
    
    //Beginning of setters and getters
    public double getWidth() {
        return width;
    }

    public double getGravity() {
        return gravity;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public Timeline getAnimation() {
        return animation;
    }

    public void setAnimation(Timeline animation) {
        this.animation = animation;
    }

    public Timeline getAnimation2() {
        return animation2;
    }

    public void setAnimation2(Timeline animation2) {
        this.animation2 = animation2;
    }

    public Timeline getAnimation3() {
        return animation3;
    }

    public void setAnimation3(Timeline animation3) {
        this.animation3 = animation3;
    }

    public Timeline getAnimation4() {
        return animation4;
    }

    public void setAnimation4(Timeline animation4) {
        this.animation4 = animation4;
    }

    public GamePane getPane() {
        return pane;
    }

    public void setPane(GamePane pane) {
        this.pane = pane;
    }

    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    public void setMapGeneration(MapGeneration mapGeneration) {
        this.mapGeneration = mapGeneration;
    }
    
    public Tanks getTanksOne() {
        return tanksOne;
    }

    public Tanks getTanksTwo() {
        return tanksTwo;
    }

    public Tanks getTanksThree() {
        return tanksThree;
    }

    public Tanks getTanksFour() {
        return tanksFour;
    }  

    public HUD getHud() {
        return hud;
    }

    public Timeline getProgressBarAnimationOne() {
        return progressBarAnimationOne;
    }

    public Timeline getProgressBarAnimationTwo() {
        return progressBarAnimationTwo;
    }

    public Timeline getProgressBarAnimationThree() {
        return progressBarAnimationThree;
    }

    public Timeline getProgressBarAnimationFour() {
        return progressBarAnimationFour;
    }

    public WeaponAnimation getWeaponAnimation() {
        return weaponAnimation;
    }

    public Timeline[] getTanksAnimationArrayUsed() {
        return tanksAnimationArrayUsed;
    }

    public Tanks[] getTanksArrayUsed() {
        return tanksArrayUsed;
    }

    public boolean isShotFired() {
        return shotFired;
    }

    public void setShotFired(boolean shotFired) {
        this.shotFired = shotFired;
    }

    public int getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }

    public void setIndexOfCurrentPlayerTurn(int indexOfCurrentPlayerTurn) {
        this.indexOfCurrentPlayerTurn = indexOfCurrentPlayerTurn;
    }

    public ProgressBar[] getProgressBarUsed() {
        return progressBarUsed;
    }

    public Timeline[] getProgressBarAnimationUsed() {
        return progressBarAnimationUsed;
    }

    public void setTurnPlayed(boolean turnPlayed) {
        this.turnPlayed = turnPlayed;
    }
    
    public void setHud(HUD hud) {
        this.hud = hud;
    }
    
    public RCAnimation getRCAnimation(){
        return rcAnimation;
    }
    
    public ArrayList<Weapon> getMineLocationArrayList() {
        return mineLocationArrayList;
    }

    public ArrayList<HitDetectionMine> getMineHitDetectionArrayList() {
        return mineHitDetectionArrayList;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    public Player[] getPlayerArray() {
        return playerArray;
    }

    public boolean isKeyReleased() {
        return keyReleased;
    }

    public void setKeyReleased(boolean keyReleased) {
        this.keyReleased = keyReleased;
    }

    public ProgressBar getBarOne() {
        return barOne;
    }

    public void setBarOne(ProgressBar barOne) {
        this.barOne = barOne;
    }

    public ProgressBar getBarTwo() {
        return barTwo;
    }

    public void setBarTwo(ProgressBar barTwo) {
        this.barTwo = barTwo;
    }

    public ProgressBar getBarThree() {
        return barThree;
    }

    public void setBarThree(ProgressBar barThree) {
        this.barThree = barThree;
    }

    public ProgressBar getBarFour() {
        return barFour;
    }

    public void setBarFour(ProgressBar barFour) {
        this.barFour = barFour;
    }

    public SoundLib getSounds() {
        return sounds;
    }

    public void setSounds(SoundLib sounds) {
        this.sounds = sounds;
    }

    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    public void setWeaponManager(WeaponManager weaponManager) {
        this.weaponManager = weaponManager;
    }

    public RCAnimation getRcAnimation() {
        return rcAnimation;
    }

    public void setRcAnimation(RCAnimation rcAnimation) {
        this.rcAnimation = rcAnimation;
    }

    public Tanks[] getTanksArray() {
        return tanksArray;
    }

    public void setTanksArray(Tanks[] tanksArray) {
        this.tanksArray = tanksArray;
    }

    public Text getNextPlayerText() {
        return nextPlayerText;
    }

    public void setNextPlayerText(Text nextPlayerText) {
        this.nextPlayerText = nextPlayerText;
    }

    public Timeline getNextPlayerAnimation() {
        return nextPlayerAnimation;
    }

    public void setNextPlayerAnimation(Timeline nextPlayerAnimation) {
        this.nextPlayerAnimation = nextPlayerAnimation;
    }
    
     //End of setters and getters
}