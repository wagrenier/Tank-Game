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
    private String team;
    
    public Player(){
        
    }
    public Player(String username, String team){
        this.username = username;
        this.team = team;
    }
    
    public void setName(String name){
        this.username = name;
    }
    
    public void setTeam(String team){
        this.team = team;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public String getTeam(){
        return this.team;
    }
    
    public String toString(){
        return this.username + " : " + this.team;
    }
}
