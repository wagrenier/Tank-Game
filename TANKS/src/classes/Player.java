/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Cedrik Dubois
 */
public class Player {
    private String username;
    private int team;
    
    public Player(){
        
    }
    public Player(String username, int team){
        if (username.length() > 10){
            this.username = username.substring(0, 10);
        }
        this.team = team;
    }
    
    public void setName(String name){
        if (name.length() > 10)
            this.username = name.substring(0, 10);
        else
            this.username = name;
        
    }
    
    public void setTeam(int team){
        this.team = team;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public int getTeam(){
        return this.team;
    }
    
    public String toString(){
        return this.username + " : " + this.team;
    }
}
