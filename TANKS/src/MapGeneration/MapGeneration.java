/****************************************************************
 *  File: MapGeneration.java
 *  Description: This object is created once to generate a new random map. The hills intensity depends of the map chosen on the map menu screen. Implements the interface Serializable so that it can be saved in a file.
 *    History:
 *     Date    03/01/2017
 *     ---------- ---------- ----------------------------
 *  Authors  William Adam-Grenier        
 *
 ****************************************************************/
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
         **/
    
    double amplitudeOfWave;
    double startingPoint;
    double lengthOfWave;
    double gravity;
    private int mapIndex;
    
    /**
     *
     * @param startingPoint
     * @param amplitudeOfWave
     * @param lengthOfWave
     * @param gravity
     * @param mapIndex
     */
    public MapGeneration(double startingPoint, double amplitudeOfWave, double lengthOfWave, double gravity, int mapIndex){
        this.gravity = gravity;
        this.mapIndex = mapIndex;
        //The lower the curve can go is 0 and the max is 700
        //Thus, the starting point + amplitude < 700 & starting point - amplitude > 0
        //this.startingPoint = (350 + Math.random() * startingPoint);
        
        //this.startingPoint = startingPoint;
        //this.amplitudeOfWave = amplitudeOfWave;
        //this.lengthOfWave = 101;
        this.startingPoint = startingPoint;
        this.amplitudeOfWave = (Math.random() * amplitudeOfWave);
        this.lengthOfWave = 100 + (Math.random() * lengthOfWave);
        //System.out.println("StartingPoint: " + this.startingPoint);
        //System.out.println("Wave amplitude: " + this.amplitudeOfWave);
       // System.out.println("Length of wave: " + this.lengthOfWave);
    }
    
    /**
     *
     * @param x
     * @return
     */
    public double derivativeFunction(double x){
        return ((amplitudeOfWave * Math.cos( x / lengthOfWave)) / lengthOfWave) ;
       //return 0;
    }
    
    /**
     *
     * @param x
     * @return
     */
    public double getY(double x){
          return (amplitudeOfWave * Math.sin(x / lengthOfWave) + startingPoint);
          //return 700;
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
    public int getMapIndex() {
        return mapIndex;
    }

    /**
     *
     * @return
     */
    public double getAmplitudeOfWave() {
        return amplitudeOfWave;
    }

    /**
     *
     * @param amplitudeOfWave
     */
    public void setAmplitudeOfWave(double amplitudeOfWave) {
        this.amplitudeOfWave = amplitudeOfWave;
    }

    /**
     *
     * @return
     */
    public double getStartingPoint() {
        return startingPoint;
    }

    /**
     *
     * @param startingPoint
     */
    public void setStartingPoint(double startingPoint) {
        this.startingPoint = startingPoint;
    }

    /**
     *
     * @return
     */
    public double getLengthOfWave() {
        return lengthOfWave;
    }

    /**
     *
     * @param lengthOfWave
     */
    public void setLengthOfWave(double lengthOfWave) {
        this.lengthOfWave = lengthOfWave;
    }
    
}
