/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGeneration;

import java.io.Serializable;

/**
 *
 * @author 1530178
 */
public class MapGeneration implements Serializable{
    /**
     * 
     * f(x) = a * sin(K * x) + B
     * 
     */
    
    double amplitudeOfWave;
    double startingPoint;
    double lengthOfWave;
    double gravity;
    
    public MapGeneration(double startingPoint, double amplitudeOfWave, double lengthOfWave, double gravity){
        this.gravity = gravity;
        //The lower the curve can go is 0 and the max is 700
        //Thus, the starting point + amplitude < 700 & starting point - amplitude > 0
        //this.startingPoint = (350 + Math.random() * startingPoint);
        
        /**
         * Perfect for Desert
         * MapGeneration(500, 100, 500)
         * this.startingPoint = (500);
           this.amplitudeOfWave = ( Math.random() * amplitudeOfWave); //100
           this.lengthOfWave = (300 + Math.random() * lengthOfWave);
         */
        
        /**
         * Perfect for Artic
         * MapGeneration(600, 50, 500)
         * this.startingPoint = (600);
           this.amplitudeOfWave = ( Math.random() * amplitudeOfWave);
           this.lengthOfWave = (300 + Math.random() * lengthOfWave);
         */
        
        /**
         * Perfect for Space
         * Lower Gravity
         * MapGeneration(600, 300, 600)
         * this.startingPoint = (500);
           this.amplitudeOfWave = ( Math.random() * amplitudeOfWave); //300
           this.lengthOfWave = (300 + Math.random() * lengthOfWave); //600
         */
        
        /**
         * Perfect for Mountains
         * MapGeneration(600, 300, 500)
         * this.startingPoint = (600);
           this.amplitudeOfWave = (Math.random() * amplitudeOfWave); //300
           this.lengthOfWave = (300 + Math.random() * lengthOfWave); //500
         */
        this.startingPoint = (600);
        this.amplitudeOfWave = (Math.random() * amplitudeOfWave); //300
        this.lengthOfWave = (300 + Math.random() * lengthOfWave); //500
    }
    
    public double derivativeFunction(double x){
        return ((amplitudeOfWave * Math.cos( x / lengthOfWave)) / lengthOfWave) ;
    }
    
    public double getY(double x){
          return (amplitudeOfWave * Math.sin(x / lengthOfWave) + startingPoint);
          //return 700;
    }

    public double getGravity() {
        return gravity;
    }
    
    
}
