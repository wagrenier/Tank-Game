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
    
    /**
     * Index of the teams
     * 
     * 0 = North Korea
     * 1 = United States
     * 2 = Canada
     * 3 = China
     * 
     */
    
    public Player(){
        
    }
    
    public Player(String username, int team){
        this.username = username;
        this.team = team;
    }
    
    public void setName(String name){
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
