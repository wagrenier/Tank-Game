/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author 1530178
 */
public class MapGeneration {
    
    MapGeneration(){
        
    }
    
    
        public int getY(double x){
        double a = 5.464 * Math.pow(10, -9);
        double b = -0.000013308;
        double c = 0.01009;
        double d = -2.5108;
        double f = 647.7;
        
        int y = (int)((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + (d * x) + f);
        return y;
    }
}
