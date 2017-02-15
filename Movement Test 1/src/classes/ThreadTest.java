/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.scene.layout.Pane;

/**
 *
 * @author William
 */
public class ThreadTest implements Runnable{
    int l = 0;
    Pane pane;
    
    
    ThreadTest(Pane pane, int l){
        this.pane = pane;
        this.l = l;
    }
    @Override
    public void run() {
        
        if(l == 1){
        MovingBall ballTwo = new MovingBall(pane, l);
        //Thread two = new Thread(ballTwo, "Thread Two");
        //two.start();
        }
        
        else if(l == 2){
         MovingBall ballOne = new MovingBall(pane, l);
        //Thread one = new Thread(ballOne, "Thread One");
        
        //one.start();//ballOne.playAnimation();
    }
        
        //pane.getChildren().add(ballOne);
        //pane.getChildren().add(ballTwo);
        
        
      // ballTwo.playAnimation();
    }
    
}
