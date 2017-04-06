/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapGeneration;

/**
 *
 * @author 1530178
 */
public class MapGeneration {
    /**
     * 
     * f(x) = a * sin(K * x) + B
     * 
     */
    
    double amplitudeOfWave;
    double startingPoint;
    double lengthOfWave;
    
    public MapGeneration(double startingPoint, double amplitudeOfWave, double lengthOfWave){
        
        //The lower the curve can go is 0 and the max is 700
        //Thus, the starting point + amplitude < 700 & starting point - amplitude > 0
        //this.startingPoint = (350 + Math.random() * startingPoint);
        this.startingPoint = (500);
        //this.amplitudeOfWave = (300);
        //this.lengthOfWave = (150);
        this.amplitudeOfWave = ( Math.random() * amplitudeOfWave);
        this.lengthOfWave = (300 + Math.random() * lengthOfWave);
    }
    
    public double derivativeFunction(double x){
        return ((amplitudeOfWave * Math.cos( x / lengthOfWave)) / lengthOfWave) ;
    }
    
    public double getY(double x){
          return (amplitudeOfWave * Math.sin(x / lengthOfWave) + startingPoint);
          //return 700;
    }
}
