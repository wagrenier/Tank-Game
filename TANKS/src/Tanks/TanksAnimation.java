/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tanks;

import GamePane.GamePane;
import HUD.HUD;
import MapGeneration.MapGeneration;
import Weapon.Weapon;
import Weapon.WeaponAnimation;
import Weapon.WeaponManager;
import classes.Player;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.animation.Animation;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 *
 * @author willi
 */
public class TanksAnimation{
    
    //Width of the pane
    private final double width = 1200;
    
    //HUD for the game
    private HUD hud;
    
    //The index of current player turn
    private int indexOfCurrentPlayerTurn = 0;
    
    
    private boolean shotFired = false;
    
    //Variables for tank 1
    final String  pathForTextureTankOne = "Texture/Tanks/Canada/Body/Red_Tank_(100x100).png";
    final String pathForTextureFlippedTankOne = "Texture/Tanks/Canada/Body/Red_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonOne = "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100).png";
    
    
    
    //Variables for tank 2
    final String pathForTextureTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_(100x100).png";
    final String pathForTextureFlippedTankTwo = "Texture/Tanks/China/Body/Yellow_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonTwo = "Texture/Tanks/China/Cannon/Yellow_Cannon_(100x100).png";
    
    
    //Variables for tanks 3
    final String pathForTextureTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_(100x100).png";
    final String pathForTextureFlippedTankThree = "Texture/Tanks/NorthKorea/Body/Blue_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonThree = "Texture/Tanks/NorthKorea/Cannon/Blue_Cannon_(100x100).png";
    
    
    //Variables for tank 4
    final String pathForTextureTankFour = "Texture/Tanks/USA/Body/Green_Tank_(100x100).png";
    final String pathForTextureFlippedTankFour = "Texture/Tanks/USA/Body/Green_Tank_Flipped_(100x100).png";
    final String pathForTextureCannonFour = "Texture/Tanks/USA/Cannon/Green_Cannon_(100x100).png";
    
    //Gravity of the game is not the same for weapon because like that the tanks stick to the ground
    private double gravity = 0.05;
    
    private int numOfPlayer;
    private final Tanks tanksOne;
    private final Tanks tanksTwo;
    private final Tanks tanksThree;
    private final Tanks tanksFour;
    
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
     ProgressBar barOne = new ProgressBar(0);
     ProgressBar barTwo = new ProgressBar(0);
     ProgressBar barThree = new ProgressBar(0);
     ProgressBar barFour = new ProgressBar(0);
    
    //Pane of the game
    private GamePane pane;
    
    //This variable generates the map of the game
    private MapGeneration mapGeneration;
    
    //Contains all the available weapons in the game
    private WeaponManager weaponManager;
    
    ArrayList<Player> playerArrayList = new ArrayList<>();
    
    WeaponAnimation weaponAnimation;
    private Player[] playerArray;
    private Tanks[] tanksArray = new Tanks[4];
    private Tanks[] tanksArrayUsed;
    private Timeline[] tanksAnimationArrayUsed;
    private Timeline[] progressBarAnimationUsed = new Timeline[4];
    private ProgressBar[] progressBarUsed = new ProgressBar[4];
    
    public TanksAnimation(MapGeneration mapGeneration, GamePane pane, int numOfPlayer, ArrayList<Player> playerArrayList, int currentPlayer) {
        this.mapGeneration = mapGeneration;
        this.indexOfCurrentPlayerTurn = currentPlayer;
        this.pane = pane;
        this.numOfPlayer = numOfPlayer; 
        this.playerArray = new Player[this.numOfPlayer];
        
        tanksOne = new Tanks(pathForTextureTankOne, pathForTextureFlippedTankOne, pathForTextureCannonOne, "Texture/Tanks/Canada/Cannon/Red_Cannon_(100x100)_Flipped.png", 2);
        tanksTwo = new Tanks(pathForTextureTankTwo, pathForTextureFlippedTankTwo, pathForTextureCannonTwo, 3);
        tanksThree = new Tanks(pathForTextureTankThree, pathForTextureFlippedTankThree, pathForTextureCannonThree, 0);
        tanksFour = new Tanks(pathForTextureTankFour, pathForTextureFlippedTankFour, pathForTextureCannonFour, 1);
        
        
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
        
        animation = animationForTanks(tanksOne, 35, 23, 300, progressBarAnimationOne);
        animation2 = animationForTanks(tanksTwo, 35, 18, 500, progressBarAnimationTwo);
        animation3 = animationForTanks(tanksThree, 35, 21, 700, progressBarAnimationThree);
        animation4 = animationForTanks(tanksFour, 35, 18, 100, progressBarAnimationFour);
        
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
        hud = new HUD(weaponManager, this.pane);
        
        
        
        setupTanksPlayer();
        
        Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(((int)(Math.random() * 9))).getDamage(), weaponManager.getWeaponFromWeaponManager(((int)(Math.random() * 9))).getTexturePath());        
        
       weaponAnimation = new WeaponAnimation(weapon, tanksOne, mapGeneration, pane, 1, 1); //Values are not important as this object wil;l never be used (Initialized to prevent nuillPointerException)
        
        pane.setOnKeyPressed(x -> {
                keyPressed(x.getCode(), tanksArrayUsed[indexOfCurrentPlayerTurn], tanksAnimationArrayUsed[indexOfCurrentPlayerTurn], progressBarAnimationUsed[indexOfCurrentPlayerTurn], progressBarUsed[indexOfCurrentPlayerTurn]);
            });
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
        weaponSetup(tank, bar.getProgress());
        progressBarAnimation.stop();
        pane.getChildren().remove(bar);
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
    
    private Timeline animationForTanks(Tanks tanks, int cannonOffset, int tankOffset, int initialPosition, Timeline progressBarAnimation){
        tanks.setCenterY(-tankOffset);
        tanks.setTranslateX(initialPosition);
        tanks.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())));
        tanks.setTranslateY(mapGeneration.getY(initialPosition));
        tanks.getCannon().setTranslateY(tanks.getTranslateY() - cannonOffset);
        tanks.getCannon().setTranslateX(tanks.getTranslateX());
        tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())) + Math.toDegrees(tanks.getCannon().getCanonAngle()));
        
        tanks.setxSpeed(0);
        Timeline animationFun = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            updateTanksStatus();
            
            tanks.setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())));
            
            if(progressBarAnimation.getStatus().compareTo(RUNNING) == 0){
            tanks.setxSpeed(0);
            tanks.setySpeed(0);
        }
            
        tanks.setY(mapGeneration.getY(tanks.getTranslateX()));
            tanks.setTranslateY(tanks.getTranslateY() + tanks.getySpeed());
            tanks.setTranslateX((tanks.getTranslateX() + tanks.getxSpeed())); 
            
            
            if(tanks.getxSpeed() < 0){
                tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())) + Math.toDegrees(tanks.getCannon().getCanonAngle()));
                tanks.getCannon().setTranslateY(tanks.getTranslateY() + tanks.getySpeed() - cannonOffset);
                tanks.getCannon().setTranslateX((tanks.getTranslateX() + tanks.getxSpeed())); 
            }
            
            else if(tanks.getxSpeed() == 0){
                if(tanks.isIsImageFlipped()){
                    tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())) + Math.toDegrees(tanks.getCannon().getCanonAngle()));
                    tanks.getCannon().setTranslateY(tanks.getTranslateY() + tanks.getySpeed() - cannonOffset);
                    tanks.getCannon().setTranslateX((tanks.getTranslateX() + tanks.getxSpeed())); 
                }
                else{
                    tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())) - Math.toDegrees(tanks.getCannon().getCanonAngle()));
                    tanks.getCannon().setTranslateY(tanks.getTranslateY() + tanks.getySpeed() - cannonOffset);
                    tanks.getCannon().setTranslateX((tanks.getTranslateX() + tanks.getxSpeed())); 
                }
            }
            
            else if(tanks.getxSpeed() > 0){
                tanks.getCannon().setRotate(Math.toDegrees(mapGeneration.derivativeFunction(tanks.getTranslateX())) - Math.toDegrees(tanks.getCannon().getCanonAngle()));
                tanks.getCannon().setTranslateY(tanks.getTranslateY() + tanks.getySpeed() - cannonOffset);
                tanks.getCannon().setTranslateX((tanks.getTranslateX() + tanks.getxSpeed())); 
            }
            
            
            
            
            
            if(tanks.getTranslateX()<= 0 || tanks.getTranslateX() >= 1200){
                if(tanks.getTranslateX()<= 0){
                    tanks.normalTexture();
                    tanks.getCannon().normalTexture();
                }
                
                else{
                    tanks.flipTexture();
                    tanks.getCannon().flipTexture();
                }
                tanks.setxSpeed(tanks.getxSpeed() * -1);
            }
            
            
            if (tanks.getTranslateY() < tanks.getY() ){
                tanks.setySpeed(tanks.getySpeed() + gravity);
                //tanks.setySpeed(yspeed);
            }
            else{
                //yspeed = 0;
                tanks.setySpeed(0);
            }
            if(tanks.getTranslateY() > tanks.getY()){
                tanks.setTranslateY(tanks.getY());
            } 
            
            
            }));
        
        return animationFun;
    }
    
    public void weaponSetup(Tanks tank, double x){
        shotFired = true;
        Weapon weapon = new Weapon(weaponManager.getWeaponFromWeaponManager(((int)(Math.random() * 9))).getDamage(), weaponManager.getWeaponFromWeaponManager(((int)(Math.random() * 9))).getTexturePath());        
        
        weaponAnimation = new WeaponAnimation(weapon, tank, mapGeneration, pane, x, mapGeneration.getGravity());
        weaponAnimation.launchAnimation();
        hitDetection(tank, weapon);
       
    }
    
    public void resetSpeed(){
        tanksOne.setxSpeed(0);
        tanksOne.setySpeed(0);
        
        tanksTwo.setxSpeed(0);
        tanksTwo.setySpeed(0);
        
        tanksThree.setxSpeed(0);
        tanksThree.setySpeed(0);
        
        tanksFour.setxSpeed(0);
        tanksFour.setySpeed(0);
    }
    
    private void hitDetection(Tanks tank, Weapon weapon){
        HitDetection hitDetection = new HitDetection(weaponAnimation, hud, tanksOne, tanksTwo, tanksThree, tanksFour, tank, animation, animation2, animation3, animation4, pane, weapon);
        hitDetection.start();
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
        if(hud.getPauseMenu().isGamePaused()){
            
        }
         
        else{
        switch (x){
            
            
            //Controls for player 1
            case SPACE: {
                if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0)
                progressBarInGameAnimationPlay(tank, progressBarAnimation, bar);
            }break;
                    
                    
                case LEFT: {
                    
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                    if(tank.getxSpeed() == 0){
                            tank.setxSpeed(tank.getxSpeed() - 0.1);
                            //tanksOne.setRotate(90);
                            
                            tank.flipTexture();
                            tank.getCannon().flipTexture();
                            //tanksOne.getCannon().setRotate(180 - Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                        }
                    
                    else if(tank.getxSpeed() > -.1){
                        tank.setxSpeed(tank.getxSpeed() - 0.1);
                        //xspeed -= 0.1;
                    }
                    }
                    }break;
                    
                case RIGHT: {
                    if(tank.isTankAlive() && animationTank.getStatus().compareTo(RUNNING) == 0 && !(weaponAnimation == null) && weaponAnimation.getAnimationWeapon().getStatus().compareTo(Animation.Status.STOPPED) == 0){
                    if(tank.getxSpeed() == 0){
                            tank.setxSpeed(tank.getxSpeed() + 0.1);
                            tank.normalTexture();
                            tank.getCannon().normalTexture();
                            //tanksOne.getCannon().setRotate(180 + Math.toDegrees(tanksOne.getCannon().getCanonAngle()));
                            //cannon.setRotate(180 - Math.toDegrees(cannon.getCanonAngle()));
                        }
                    
                    else if(tank.getxSpeed() < .1)
                        tank.setxSpeed(tank.getxSpeed() + 0.1);
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
        }

        }
    }
    
    public void updateTurn(){
        hud.setCurrentPlayerName(playerArray[indexOfCurrentPlayerTurn].getUsername());
        hud.setCurrentPlayerTank(tanksArrayUsed[indexOfCurrentPlayerTurn], tanksArrayUsed[indexOfCurrentPlayerTurn].getTeam());
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

    public void setHud(HUD hud) {
        this.hud = hud;
    }
    //End of setters and getters
    
}