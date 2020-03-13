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
public class Commons {
    protected int location_x;
    protected int location_y;
    protected int size_x;
    protected int size_y;
    protected int dx;
    protected int dy;
    protected Image image;

    public Commons() {
        location_x=location_y=0;
        size_x=size_y=0;
        dx=dy=0;
        image=null;
        
    }

    public Commons(int location_x, int location_y) {
        this.location_x = location_x;
        this.location_y = location_y;
         size_x=size_y=0;
        dx=dy=0;
        image=null;
    }

    public int getLocation_x() {
        return location_x;
    }

    public int getLocation_y() {
        return location_y;
    }

    public int getSize_x() {
        return size_x;
    }

    public int getSize_y() {
        return size_y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Image getImage() {
        return image;
    }

    public void setLocation_x(int location_x) {
        this.location_x = location_x;
    }

    public void setLocation_y(int location_y) {
        this.location_y = location_y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    public void setImage(String path){
    this.image=new ImageIcon(this.getClass().getResource(path)).getImage();
    this.size_x=image.getWidth(null);
    this.size_y=image.getHeight(null);
       
    }
    public Rectangle getRect(){
     return new Rectangle(location_x,location_y,size_x,size_y);
    }

    public void setSize_x(int size_x) {
        this.size_x = size_x;
    }

    public void setSize_y(int size_y) {
        this.size_y = size_y;
    }
    
    
    
    
    
    
}
