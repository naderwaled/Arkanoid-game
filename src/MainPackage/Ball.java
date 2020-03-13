/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

/**
 *
 * @author nader
 */
import javax.swing.JPanel;
import javax.sound.sampled.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
public class Ball extends Commons {

    int speed=7;

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
private boolean move;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
String type;
    public void setSpeed(int speed) {
        this.speed = speed;
        dx=dy=this.speed;
    }

    public Ball(int location_x, int location_y) {
        super(location_x, location_y);
        dy =dx=speed;
        super.setImage("ball.png");
        type="normal";
        reset();
        move=true;
    }
     private void reset() {
        location_x = 1000/ 3;
        location_y = 1000/ 2;
    }
     public void reset_ball(){
     this.setImage("ball.png");
     }
    public void move()
    {  
        if(move==true)
        // Bounce ball back when it hits borders.
        location_x += dx;
        location_y += dy;
        
        
        // Reverse the horizontal direction.
        if (location_x <= 0) { 
            location_x = 0;
            dx = -dx; 
        }
        // Reverse the horizontal direction.
        if (location_x >= 1000-this.size_x-15) {
            location_x = 1000-this.size_x-15;
            dx = -dx; 
        }
        // Reverse the vertical direction.
        if (location_y <= 0) {
            location_y = 0;
            dy = -dy; 
        }
         if (location_y >=1000) {
            location_y = 1000;
            dy = -dy; 
        }
       
        //for revese from the ground and that is for me only
      
    }
}
