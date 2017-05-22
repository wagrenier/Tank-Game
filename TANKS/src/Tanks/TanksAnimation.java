/****************************************************************
 *  File: TanksAnimation.java
 *  Description: This controls the input of the user and the game's render. This class contains all the necessary elements to display the game, but lacking the game mechanics(ie turn by turn, AI,...)
 *               The animation of the 4 tanks are stored in this object. Therefore, there should only have one instance of this object in the entire code, as it can easily slow down the code and create memory leaks if not properly managed.
 *    History:
 *     Date    03/20/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
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
    
    /**Width of the pane in pixels*/
    private double width = 1200;
    
    /**Number of players in the game*/
    private int numOfPlayer;
    
    /**The number of mines currently in the game*/
    private int numOfMines = 0;
    
    /**Gravity of the game is not the same for weapon because like that the tanks stick to the ground*/
    private double gravity = 0.05;
    
    /**HUD for the game*/
    private HUD hud;
    
    /**The index of current player turn*/
    private int indexOfCurrentPlayerTurn = 0;
    
    /**Prevents the program from registering more than once the same input (if the user holds the key, the computer will register it as 1 input)*/
    private boolean keyReleased = true; 
    /**Prevents any other input until the end of turn once a weapon as been fired. Note: a mine does not change this variable to true, but all other weapons do.*/
    private boolean shotFired = false; 
    /**If the user has played their turn, prevents other actions.*/
    private boolean turnPlayed = false; 
    
    //Variables for tank 1
    /**Sets the texture for the tank*/
    private String  pathForTextureTankOne = "Texture/Tanks/Canada/Body/Red_Tank_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureFlippedTankOne = "Texture/Tanks/Canada/Body/Red_Tank_Flipped_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureCannonOne = "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100).png";
    
    
    
    //Variables for tank 2
    /**Sets the texture for the tank*/
    private String pathForTextureTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureFlippedTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_Flipped_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureCannonTwo = "Texture/Tanks/China/Cannon/Yellow_Cannon_(100x100).png";
    
    
    //Variables for tanks 3
    /**Sets the texture for the tank*/
    private String pathForTextureTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureFlippedTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_Flipped_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureCannonThree = "Texture/Tanks/NorthKorea/Cannon/Blue_Cannon_(100x100).png";
    
    
    //Variables for tank 4
    /**Sets the texture for the tank*/
    private String pathForTextureTankFour = "Texture/Tanks/USA/Body/Green_Tank_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureFlippedTankFour = "Texture/Tanks/USA/Body/Green_Tank_Flipped_(100x100).png";
    /**Sets the texture for the tank*/
    private String pathForTextureCannonFour = "Texture/Tanks/USA/Cannon/Green_Cannon_(100x100).png";
    
    
    //The tanks
    /**Creates the object tank*/
    private Tanks tanksOne;
    /**Creates the object tank*/
    private Tanks tanksTwo;
    /**Creates the object tank*/
    private Tanks tanksThree;
    /**Creates the object tank*/
    private Tanks tanksFour;
    
    //Variable for the tanks' animation
    /**Creates the animation for the tank*/
    private Timeline animation;
    /**Creates the animation for the tank*/
    private Timeline animation2;
    /**Creates the animation for the tank*/
    private Timeline animation3;
    /**Creates the animation for the tank*/
    private Timeline animation4;
    
    //Variables for the tanks' progress bar animation, which serves as the setter for the initial velocity
    /**Variables for the tanks' progress bar animation, which serves as the setter for the initial velocity*/
    private Timeline progressBarAnimationOne;
    /**Variables for the tanks' progress bar animation, which serves as the setter for the initial velocity*/
    private Timeline progressBarAnimationTwo;
    /**Variables for the tanks' progress bar animation, which serves as the setter for the initial velocity*/
    private Timeline progressBarAnimationThree;
    /**Variables for the tanks' progress bar animation, which serves as the setter for the initial velocity*/
    private Timeline progressBarAnimationFour;
    
    /**Progress Bar of the tanks*/
    private ProgressBar barOne = new ProgressBar(0);
    /**Progress Bar of the tanks*/
    private ProgressBar barTwo = new ProgressBar(0);
    /**Progress Bar of the tanks*/
    private ProgressBar barThree = new ProgressBar(0);
    /**Progress Bar of the tanks*/
    private ProgressBar barFour = new ProgressBar(0);
    
    /**sounds of the game*/
    private SoundLib sounds;
    
    /**Pane of the game*/
    private GamePane pane;
    
    /**This variable generates the map of the game*/
    private MapGeneration mapGeneration;
    
    /**Contains all the available weapons in the game*/
    private WeaponManager weaponManager;
    
    /**Array list of all the players in the game*/
    private ArrayList<Player> playerArrayList;
    
    /**The animation for the rc, there should only be one at a time*/
    private RCAnimation rcAnimation; 
    /**Same as rc but for other weapons*/
    private WeaponAnimation weaponAnimation;
    /**Array containing all the players in the game, program uses this more often instead of the array because it is more optimized than the .get(int index)*/
    private Player[] playerArray; 
    /**The array containing all the tanks in order of their variable's name (ie tanksOne, tanksTwo,...)*/
    private Tanks[] tanksArray = new Tanks[4]; 
    /**The array of the tanks being used in the game. If the player chooses the tank 4, then the tanks 4 will be first in this array.*/
    private Tanks[] tanksArrayUsed; 
    /**Same as tanksArrayUsed, but for the animation*/
    private Timeline[] tanksAnimationArrayUsed; 
    /**Same as tanksArrayUsed, but for the animation of progress bar*/
    private Timeline[] progressBarAnimationUsed = new Timeline[4];
    /**Same as tanksArrayUsed, but for the progress bar*/
    private ProgressBar[] progressBarUsed = new ProgressBar[4]; 
    /**The arrayList of the mines currently in the game*/
    private ArrayList<Weapon> mineLocationArrayList = new ArrayList<>();
    /**An array list containing all the hit detection for mine currently running. Once the mine explodes, its corresponding hit detection is removed from this array list*/
    private ArrayList<HitDetectionMine> mineHitDetectionArrayList = new ArrayList<>(); 
    
    /**Shows the next player Turn*/
    private Text nextPlayerText = new Text();
    /**The animation of the next player*/
    private Timeline nextPlayerAnimation;
    
    /**
     * Constructor
     * @param mapGeneration
     * @param pane
     * @param numOfPlayer
     * @param playerArrayList
     * @param currentPlayer
     * @param sounds
     */
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
    
    /**Sets the next player animation indicator*/
    private void setNextPlayerAnimation(){
        
        nextPlayerText.setTranslateX(472.5);
        nextPlayerText.setTranslateY(66.0);
        nextPlayerText.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        
        
        nextPlayerAnimation = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            this.pane.getChildren().remove(nextPlayerText);
        }));
        nextPlayerAnimation.setCycleCount(1);
        
    }
    
    /**The setup for the progress bar*/
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
    
    /**Plays the animation of the progress bar of the current play once the space has been pressed*/
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
    
    /**Stops the progress bar of the player once the space bar has been repressed*/
    private void progressBarInGameAnimationStop(Tanks tank, Timeline progressBarAnimation, ProgressBar bar){
        progressBarAnimation.stop();
        pane.getChildren().remove(bar);
        weaponSetup(tank, bar.getProgress());
    }
    
    /**Sets the player tanks*/
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
    
    /**Sets the timeline animation for all the tanks*/
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
    
    /**
     * Sets the weapon to launch it and put it into the pane
     * @param tank
     * @param x
     */
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
    
    /**
     * Resets the speed of all the tanks
     */
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
    
    /**
     * Launches the hit detection for a mine
     * @param tank
     * @param weapon
     */
    public void hitDetectionMine(Tanks tank, Weapon weapon){
        HitDetectionMine hitDetectionMine = new HitDetectionMine(tanksOne, tanksTwo, tanksThree, tanksFour, tank, weapon, this, pane, sounds);
        mineHitDetectionArrayList.add(hitDetectionMine);
        hitDetectionMine.start();
    }
    
    /**Launches the hit detection for a RC*/
    private void hitDetectionRC(Tanks tank, Weapon weapon, RCAnimation rcAnimation){
        HitDetectionRC hitDetectionRC = new HitDetectionRC(rcAnimation, tanksOne, tanksTwo, tanksThree, tanksFour, tank, animation, animation2, animation3, animation4, pane, weapon);
        hitDetectionRC.start();
    }
    /**Launches the hit detection for a weapon*/
    private void hitDetection(Tanks tank, Weapon weapon){
        HitDetection hitDetection = new HitDetection(weaponAnimation, hud, tanksOne, tanksTwo, tanksThree, tanksFour, tank, animation, animation2, animation3, animation4, pane, weapon);
        hitDetection.start();
        System.out.println("Hit Something");
    }
    
    /**
     * Updates the status for all the tanks, if they are dead or not
     */
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
    
    /**
     *Checks if there are more than one tank alive. If not, the game ends.
     * @return boolean
     */
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
    
    /**
     * Returns the number of tanks alive
     * @return int
     */
    public int numOfTanksAlive(){
        int numOfTanksAlive = 0;
        
        for(int i = 0; i < tanksArrayUsed.length; i++){
            if(tanksArrayUsed[i].isTankAlive()){
                numOfTanksAlive++;
            }
            
        }
        return numOfTanksAlive;
    }
    
    /**
     * The key pressed by the user 
     * @param x
     * @param tank
     * @param animationTank
     * @param progressBarAnimation
     * @param bar
     */
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
    
    /**
     * The key pressed by the AI
     * @param x
     * @param tank
     * @param animationTank
     * @param progressBarAnimation
     * @param bar
     */
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
    
    /**
     *Updates the HUD to the current player's turn
     */
    public void updateTurn(){
        nextPlayerText.setText(playerArray[indexOfCurrentPlayerTurn].getUsername() + "'s turn!");
        this.pane.getChildren().remove(nextPlayerText);
        this.pane.getChildren().add(nextPlayerText);
        nextPlayerAnimation.play();
        
        hud.setCurrentPlayerName(playerArray[indexOfCurrentPlayerTurn].getUsername());
        hud.setCurrentPlayerTank(tanksArrayUsed[indexOfCurrentPlayerTurn], tanksArrayUsed[indexOfCurrentPlayerTurn].getTeam(), indexOfCurrentPlayerTurn);
    }
    
    /**
     *Checks if a mine has exploded
     */
    public void mineExploded(){
        numOfMines--;
    }
    
    //Methods Related to saving/loading the game

    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param mineLocation
     */
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
    
    /**
     *
     * @return
     */
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
    
    /**
     *
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public int[] obtainTanksHP(){
        int[] tankHP = new int[tanksArrayUsed.length];
        for(int i = 0; i < tankHP.length; i++){
           tankHP[i] = tanksArrayUsed[i].getLifePoint();
        }
        return tankHP;
    }
    
    /**
     *
     * @param tankHP
     */
    public void resetTankHPSave(int[] tankHP){
        for(int i = 0; i < tankHP.length; i++){
            tanksArrayUsed[i].setLifePoint(tankHP[i]);
        }
    }
    
    /**
     *
     * @param tankOrientation
     */
    public void resetTankOrientationSave(boolean[] tankOrientation){
        for(int i = 0; i < tankOrientation.length; i++){
            if(tankOrientation[i])
                tanksArrayUsed[i].flipTexture();
        }
    }
    
    /**
     *
     * @return
     */
    public boolean[] obtainEachTanksDirection(){
        boolean[] tankOrientation = new boolean[tanksArrayUsed.length];
        for(int i = 0; i < tankOrientation.length; i++){
            tankOrientation[i] = tanksArrayUsed[i].isIsImageFlipped();
        }
        return tankOrientation;
    }
    
    /**
     *
     * @param tanksArraySave
     */
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
    
    /**
     *
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public int[] getIndexOfCurrentPlayerTurnArray(){
        int[] turn = new int[1];
        turn[0] = indexOfCurrentPlayerTurn;
        return turn;
    }
    
    /**
     *
     * @return
     */
    public boolean[] obtainWhoIsDead(){
        boolean[] dead = new boolean[tanksArrayUsed.length];
        
        for(int i = 0;i < tanksArrayUsed.length; i++){
            dead[i] = tanksArrayUsed[i].isTankAlive();
        }
        return dead;
    }
    
    /**
     *
     * @param dead
     */
    public void resetWhoIsDead(boolean[] dead){
        for(int i = 0;i < tanksArrayUsed.length; i++){
            tanksArrayUsed[i].setIsTankAlive(dead[i]);
        }
    }
    
    //To reset the maximum number of pixels a tank can travel

    /**
     *
     * @return
     */
    public double[] obtainMaxPixelMoveSave(){
        double[] maxPixMove = new double[tanksArrayUsed.length];
        for(int i = 0; i < tanksArrayUsed.length; i++){
            maxPixMove[i] = tanksArrayUsed[i].getMaxPixelMove();
        }
        return maxPixMove;
    }
    
    /**
     *
     * @param maxPixMove
     */
    public void resetMaxPixelMove(double[] maxPixMove){
        for(int i = 0; i < tanksArrayUsed.length; i++){
            tanksArrayUsed[i].setMaxPixelMove(maxPixMove[i]);
        }
    }
    
    
    //Beginning of setters and getters

    /**
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public double getGravity() {
        return gravity;
    }

    /**
     *
     * @return
     */
    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    /**
     *
     * @param numOfPlayer
     */
    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    /**
     *
     * @return
     */
    public Timeline getAnimation() {
        return animation;
    }

    /**
     *
     * @param animation
     */
    public void setAnimation(Timeline animation) {
        this.animation = animation;
    }

    /**
     *
     * @return
     */
    public Timeline getAnimation2() {
        return animation2;
    }

    /**
     *
     * @param animation2
     */
    public void setAnimation2(Timeline animation2) {
        this.animation2 = animation2;
    }

    /**
     *
     * @return
     */
    public Timeline getAnimation3() {
        return animation3;
    }

    /**
     *
     * @param animation3
     */
    public void setAnimation3(Timeline animation3) {
        this.animation3 = animation3;
    }

    /**
     *
     * @return
     */
    public Timeline getAnimation4() {
        return animation4;
    }

    /**
     *
     * @param animation4
     */
    public void setAnimation4(Timeline animation4) {
        this.animation4 = animation4;
    }

    /**
     *
     * @return
     */
    public GamePane getPane() {
        return pane;
    }

    /**
     *
     * @param pane
     */
    public void setPane(GamePane pane) {
        this.pane = pane;
    }

    /**
     *
     * @return
     */
    public MapGeneration getMapGeneration() {
        return mapGeneration;
    }

    /**
     *
     * @param mapGeneration
     */
    public void setMapGeneration(MapGeneration mapGeneration) {
        this.mapGeneration = mapGeneration;
    }
    
    /**
     *
     * @return
     */
    public Tanks getTanksOne() {
        return tanksOne;
    }

    /**
     *
     * @return
     */
    public Tanks getTanksTwo() {
        return tanksTwo;
    }

    /**
     *
     * @return
     */
    public Tanks getTanksThree() {
        return tanksThree;
    }

    /**
     *
     * @return
     */
    public Tanks getTanksFour() {
        return tanksFour;
    }  

    /**
     *
     * @return
     */
    public HUD getHud() {
        return hud;
    }

    /**
     *
     * @return
     */
    public Timeline getProgressBarAnimationOne() {
        return progressBarAnimationOne;
    }

    /**
     *
     * @return
     */
    public Timeline getProgressBarAnimationTwo() {
        return progressBarAnimationTwo;
    }

    /**
     *
     * @return
     */
    public Timeline getProgressBarAnimationThree() {
        return progressBarAnimationThree;
    }

    /**
     *
     * @return
     */
    public Timeline getProgressBarAnimationFour() {
        return progressBarAnimationFour;
    }

    /**
     *
     * @return
     */
    public WeaponAnimation getWeaponAnimation() {
        return weaponAnimation;
    }

    /**
     *
     * @return
     */
    public Timeline[] getTanksAnimationArrayUsed() {
        return tanksAnimationArrayUsed;
    }

    /**
     *
     * @return
     */
    public Tanks[] getTanksArrayUsed() {
        return tanksArrayUsed;
    }

    /**
     *
     * @return
     */
    public boolean isShotFired() {
        return shotFired;
    }

    /**
     *
     * @param shotFired
     */
    public void setShotFired(boolean shotFired) {
        this.shotFired = shotFired;
    }

    /**
     *
     * @return
     */
    public int getIndexOfCurrentPlayerTurn() {
        return indexOfCurrentPlayerTurn;
    }

    /**
     *
     * @param indexOfCurrentPlayerTurn
     */
    public void setIndexOfCurrentPlayerTurn(int indexOfCurrentPlayerTurn) {
        this.indexOfCurrentPlayerTurn = indexOfCurrentPlayerTurn;
    }

    /**
     *
     * @return
     */
    public ProgressBar[] getProgressBarUsed() {
        return progressBarUsed;
    }

    /**
     *
     * @return
     */
    public Timeline[] getProgressBarAnimationUsed() {
        return progressBarAnimationUsed;
    }

    /**
     *
     * @param turnPlayed
     */
    public void setTurnPlayed(boolean turnPlayed) {
        this.turnPlayed = turnPlayed;
    }
    
    /**
     *
     * @param hud
     */
    public void setHud(HUD hud) {
        this.hud = hud;
    }
    
    /**
     *
     * @return
     */
    public RCAnimation getRCAnimation(){
        return rcAnimation;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Weapon> getMineLocationArrayList() {
        return mineLocationArrayList;
    }

    /**
     *
     * @return
     */
    public ArrayList<HitDetectionMine> getMineHitDetectionArrayList() {
        return mineHitDetectionArrayList;
    }

    /**
     *
     * @return
     */
    public int getNumOfMines() {
        return numOfMines;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    /**
     *
     * @return
     */
    public Player[] getPlayerArray() {
        return playerArray;
    }

    /**
     *
     * @return
     */
    public boolean isKeyReleased() {
        return keyReleased;
    }

    /**
     *
     * @param keyReleased
     */
    public void setKeyReleased(boolean keyReleased) {
        this.keyReleased = keyReleased;
    }

    /**
     *
     * @return
     */
    public ProgressBar getBarOne() {
        return barOne;
    }

    /**
     *
     * @param barOne
     */
    public void setBarOne(ProgressBar barOne) {
        this.barOne = barOne;
    }

    /**
     *
     * @return
     */
    public ProgressBar getBarTwo() {
        return barTwo;
    }

    /**
     *
     * @param barTwo
     */
    public void setBarTwo(ProgressBar barTwo) {
        this.barTwo = barTwo;
    }

    /**
     *
     * @return
     */
    public ProgressBar getBarThree() {
        return barThree;
    }

    /**
     *
     * @param barThree
     */
    public void setBarThree(ProgressBar barThree) {
        this.barThree = barThree;
    }

    /**
     *
     * @return
     */
    public ProgressBar getBarFour() {
        return barFour;
    }

    /**
     *
     * @param barFour
     */
    public void setBarFour(ProgressBar barFour) {
        this.barFour = barFour;
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
    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    /**
     *
     * @param weaponManager
     */
    public void setWeaponManager(WeaponManager weaponManager) {
        this.weaponManager = weaponManager;
    }

    /**
     *
     * @return
     */
    public RCAnimation getRcAnimation() {
        return rcAnimation;
    }

    /**
     *
     * @param rcAnimation
     */
    public void setRcAnimation(RCAnimation rcAnimation) {
        this.rcAnimation = rcAnimation;
    }

    /**
     *
     * @return
     */
    public Tanks[] getTanksArray() {
        return tanksArray;
    }

    /**
     *
     * @param tanksArray
     */
    public void setTanksArray(Tanks[] tanksArray) {
        this.tanksArray = tanksArray;
    }

    /**
     *
     * @return
     */
    public Text getNextPlayerText() {
        return nextPlayerText;
    }

    /**
     *
     * @param nextPlayerText
     */
    public void setNextPlayerText(Text nextPlayerText) {
        this.nextPlayerText = nextPlayerText;
    }

    /**
     *
     * @return
     */
    public Timeline getNextPlayerAnimation() {
        return nextPlayerAnimation;
    }

    /**
     *
     * @param nextPlayerAnimation
     */
    public void setNextPlayerAnimation(Timeline nextPlayerAnimation) {
        this.nextPlayerAnimation = nextPlayerAnimation;
    }
    
     //End of setters and getters
}