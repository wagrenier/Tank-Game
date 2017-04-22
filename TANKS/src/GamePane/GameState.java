/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePane;

/**
 *
 * @author willi
 */
public abstract class GameState {
    
    abstract protected void execute(GameLoop loop);
    
}
