/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author william
 */
public class Tanks extends Circle{
    
    ImageView imageView;
    
    Tanks(){
        this.setRadius(50);
        Image texture = new Image("Texture/Tanks/Canada/Body/Red_Tank_(100x100).png");
        imageView = new ImageView(texture);
        this.setCenterY(-25);
        //imageView.setClip(this);
        //imageView.setX(this.getCenterX());
        //imageView.setY(this.getCenterY() + 50);
        this.setFill(new ImagePattern(texture, 0, 0, 1, 1, true));
        //this.setLayoutX(100);
        //this.setLayoutY(100);
        //this.setWidth(100);
        //this.setHeight(100);
        //setFill(Color.BLACK);
        //setStroke(Color.BLACK);
        setTranslateX(50);
        setTranslateY(200);
    }  

    public ImageView getImageView() {
        return imageView;
    }
    
    
}
