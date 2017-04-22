/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

import Tanks.Tanks;
import Tanks.TanksAnimation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 *
 * @author willi
 * 
 * 
 * Current known issues: the animation is not stopped when paused if time correctly
 */
public class GameLoop extends AnimationTimer{
    
    GameState gameState;
    
    private final GraphicsContext graphicsContext;
    
    //The rate at which the game is updated
    public static double executionRate = 0;
    
    
    
    public GameLoop(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }
    
    
    
    @Override
    public void start() {
        
        gameState = new GSGame();
        super.start();
            
        
    }

    @Override
    public void handle(long now) {
        gameState.execute(this);
        executionRate = (int)(((double)(System.nanoTime() - now) / 16666666) * 100);
    }
}
