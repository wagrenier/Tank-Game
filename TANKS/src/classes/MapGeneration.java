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
    /**
     * f(x) = a * sin(K * x) + B
     */
    
    MapGeneration(){
        
    }
    
    /*
    public static double getY(double x, double ratio){
         
        return (100 * Math.sin(x / 400) + 600);
        
        /*  
        double a = 5.464 * Math.pow(10, -9);
        double b = -0.000013308;
        double c = 0.01009;
        double d = -2.5108;
        double f = 647.7;
        
       // int y = (int)((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + (d * x) + f);
        return ((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + (d * x) + f) / ratio;
            
          
          //return (int) (2000 - Math.sin((x / 100) / (2 * Math.PI)) * 1000);

         */
        
    //}
    
    
        public static double getY(double x){
            
          return (100 * Math.sin(x / 400) + 600);
          
          /*
        double a = 5.464 * Math.pow(10, -9);
        double b = -0.000013308;
        double c = 0.01009;
        double d = -2.5108;
        double f = 647.7;
        
       // int y = (int)((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + (d * x) + f);
        return ((a * Math.pow(x, 4)) + (b * Math.pow(x, 3)) + (c * Math.pow(x, 2)) + (d * x) + f);
            
          */
          //return (int) (2000 - Math.sin((x / 100) / (2 * Math.PI)) * 1000);
        
    }
}
