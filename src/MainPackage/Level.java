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
import java.time.Instant;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;

public abstract class Level extends JPanel {

    Image backgroundimage;
    int background_x1, background_y1;
    int background_x2, background_y2;

    Image introduction_messange;
    int introduction_message_x, introduction_message_y;
    boolean level_introduction_message = true;

    Image paddle_ship;
    Image paddle_ship_open ;
    int paddle_ship_x, paddle_ship_y;
    boolean level_paddle_start = false;
    
      protected long leveltime;
    Image winner;
    int winner_x1, winner_y1;
    protected int numberofball = 1;
    protected int numberoflifes = 3;
  
    protected Timer timer;
    protected int delay;
    protected paddle Paddle;
    protected Gift[] gifts;
    protected Ball[] ball;
    protected Bricks[][] bricks;
    protected environment envir;
protected User user;
int number_of_bricks;
    public abstract void draw_bricks();
    public abstract void draw_ball();
    public abstract void draw_paddle();
    public abstract void draw_gifts();
    public abstract void Gift_paddle();
    public abstract void ball_Bricks();
    public abstract void ball_paddle() ;
   public abstract void time();
    public abstract void collision();

}
