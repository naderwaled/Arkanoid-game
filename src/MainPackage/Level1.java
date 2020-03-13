/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import org.omg.CORBA.BAD_CONTEXT;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author nader
 */
public class Level1 extends Level {

    public Level1(User u) {
        setDoubleBuffered(true);
        // Set thime focus to the GamePanel for keyboard events to work.
        setFocusable(true);
        // Start up the game
        setBounds(197, 0, 1000, 1000);
        FinalArkanoidIsa.frame.add(new Logo_Score());
        background_x1 = background_x1 = 0;
        background_y1 = -1000;
        background_y2 = 0;
        introduction_message_x = 150;
        introduction_message_y = 1000;
        paddle_ship_x = -300;
        paddle_ship_y = 150;
        user = u;
        number_of_bricks = 10;
        initGame();

    }

    void initGame() {
        this.backgroundimage = new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
        this.introduction_messange = new ImageIcon(this.getClass().getResource("introduction.png")).getImage();
        this.paddle_ship = new ImageIcon(this.getClass().getResource("paddle ship closed.png")).getImage();
        this.paddle_ship_open = new ImageIcon(this.getClass().getResource("x.png")).getImage();
        draw_paddle();
        time();
        draw_ball();
        draw_bricks();
        draw_gifts();
        setFocusable(true);
    }

    @Override
    public void collision() {
        ball_paddle();
        ball_Bricks();
        Gift_paddle();

    }

    @Override
    public void draw_bricks() {

        bricks = new Bricks[20][20];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                bricks[i][j] = new Bricks(2, false, 0, 0, "blue.png");
            }
        }
        //////////////////////////
        int scale_y = 175;
        int scale_x = 100;
        for (int i = 0; i < 8; i++) {
            for (int j = i; j < 8; j++) {
                if (!bricks[i][j].isHit()) {
                    if (bricks[i][j].getNumberofhits() == 1) {
                        bricks[i][j].setImage("broken_babyblue.png");
                    }

                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);

                }
                scale_x += 50;
            }
            scale_y += 20;
            scale_x = 100;
        }
        ///////////////////////////////////////////////////////////R.H.S////////////////////////////////////////////////////////////////////
        scale_x = 500;
        scale_y = 175;
        for (int i = 8; i < 16; i++) {
            for (int k = 8; k < i; k++) {
                scale_x += 50;
            }
            for (int j = i; j < 16; j++) {
                if (!bricks[i][j].isHit()) {
                    if (bricks[i][j].getNumberofhits() == 1) {
                        bricks[i][j].setImage("broken_babyblue.png");
                    }

                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);
                    if (bricks[i][j].getGift_number() >= 0) {
                        gifts[bricks[i][j].getGift_number()].setLocation_x(scale_x);
                        gifts[bricks[i][j].getGift_number()].setLocation_y(scale_y);
                    }
                }
                scale_x += 50;
            }
            scale_y += 20;
            scale_x = 500;
        }
    }

    @Override
    public void draw_ball() {
        ball = new Ball[3];
        for (int i = 0; i < 3; i++) {
            ball[i] = new Ball(520, 200);
        }
    }

    @Override
    public void draw_paddle() {
        Paddle = new paddle(490, 250);
        addKeyListener(Paddle.getKeyHandler());
    }

    @Override
    public void Gift_paddle() {
        for (Gift gift : gifts) {
            if (Paddle.getRect().intersects(gift.getRect()) && gift.show == true) {
                gift.show = false;

                if (gift instanceof Shrink_gift) {
                    if (Shrink_gift.start_time == 0) {
                        Shrink_gift.start_time = System.currentTimeMillis();
                    }
                    Shrink_gift.duration += 20000;
                    ((Shrink_gift) gift).start = true;
                    gift.show = false;
                    Paddle.Shrink();
                    Extend_gift.start_time = 0;
                    Extend_gift.duration = 0;
                } else if (gift instanceof Extend_gift) {
                    if (Extend_gift.start_time == 0) {
                        Extend_gift.start_time = System.currentTimeMillis();
                    }
                    Extend_gift.duration += 20000;
                    ((Extend_gift) gift).start = true;
                    gift.show = false;
                    Paddle.Extend();
                    Shrink_gift.start_time = 0;
                    Shrink_gift.duration = 0;
                } else if (gift instanceof slowball_gift) {
                    gift.start_time = System.currentTimeMillis();
                    ((slowball_gift) gift).start = true;
                    for (int i = 0; i < numberofball; i++) {
                        ball[i].setSpeed(4);
                    }

                } else if (gift instanceof fastball_gift) {
                    for (int i = 0; i < numberofball; i++) {
                        ball[i].setSpeed(10);
                    }
                } else if (gift instanceof shotgun_gift) {
                    gift.start_time = System.currentTimeMillis();
                    shotgun_gift.number_of_bullet += 10;
                    ((shotgun_gift) gift).setStart(true);

                } else if (gift instanceof splitball_gift) {
                    if (numberofball < 3) {
                        numberofball = 3;
                        ball[1].setLocation_x(ball[0].getLocation_x());
                        ball[1].setLocation_y(ball[0].getLocation_y());
                        ball[2].setLocation_x(ball[0].getLocation_x());
                        ball[2].setLocation_y(ball[0].getLocation_y());
                        ball[0].setDx(0);
                        ball[0].setDy(-ball[0].speed);
                        ball[1].setDx(ball[1].speed);
                        ball[1].setDy(-ball[1].speed);
                        ball[2].setDx(-ball[1].speed);
                        ball[2].setDy(ball[1].speed);
                    }
                } else if (gift instanceof Laser_gift) {
                    ((Laser_gift) gift).start = true;
                }
            }
        }
         for (Gift gift : gifts) {
           if (gift instanceof Extend_gift && gift.start == true) {
               if(Extend_gift.start_time+Extend_gift.duration<leveltime&&Shrink_gift.duration==00){
               Paddle.reset();
               Extend_gift.start_time=0;
               Extend_gift.duration=0;
               }
                
            } else  if(Shrink_gift.start_time+Shrink_gift.duration<leveltime&&Extend_gift.duration==0){
               Paddle.reset();
               Shrink_gift.start_time=0;
               Shrink_gift.duration=0;
               } else if (gift instanceof slowball_gift && gift.start == true) {
                long x = leveltime - ((slowball_gift) gift).start_time;

                if (x >= 20000) {
                    for (int i = 0; i < numberofball; i++) {
                        ball[i].setSpeed(7);
                    }
                    gift.start = false;
                }
            }
        }
        
        
    }

    @Override
    public void ball_Bricks() {
        for (int k = 0; k < numberofball; k++) {
            for (int i = 0; i < 20; i++) {
                boolean is_hited = false;
                for (int j = 0; j < 20; j++) {
                    if (ball[k].getRect().intersects(bricks[i][j].getRect()) && bricks[i][j].isHit() == false) {
                        // here 
                        is_hited = true;
                        bricks[i][j].setHit(true);
                        int top = ball[k].getLocation_y();
                        int bottom = top + ball[k].getSize_y();
                        int left = ball[k].getLocation_x();
                        int right = left + ball[k].getSize_x();
                        if (bricks[i][j].getRect().contains(top, right + 1)) {
                            int dx = ball[k].getDx();
                            ball[k].setDx(dx < 0 ? dx : -dx);
                        } else if (bricks[i][j].getRect().contains(top, left - 1)) {
                            int dx = ball[k].getDx();
                            ball[k].setDx(dx < 0 ? -dx : dx);
                        }
                        if (bricks[i][j].getRect().contains(left - 1, top)) {
                            int dy = ball[k].getDy();
                            ball[k].setDy(dy < 0 ? -dy : dy);
                        } else if (bricks[i][j].getRect().contains(left, bottom)) {
                            int dy = ball[k].getDy();
                            ball[k].setDy(dy < 0 ? dy : -dy);
                        } else {
                            ball[k].setDx(-ball[k].getDx());
                            ball[k].setDy(-ball[k].getDy());
                        }

                        if (bricks[i][j].getGift_number() > 0) {
                            gifts[bricks[i][j].getGift_number()].show = true;

                        }

                        break;

                    }

                }
                if (is_hited) {
                    break;
                }
            }
        }
    }

    @Override
    public void ball_paddle() {
        for (int i = 0; i < numberofball; i++) {
            if (Paddle.getRect().intersects(ball[i].getRect())) {
                int segment = Paddle.size_x / 5;
                int frist = Paddle.getLocation_x() + segment;
                int second = Paddle.getLocation_x() + segment * 2;
                int third = Paddle.getLocation_x() + segment * 3;
                int fourth = Paddle.getLocation_x() + segment * 4;
                int center = ball[i].getLocation_x() + ball[i].getSize_x() / 2;
                if (center < frist) {
                    ball[i].setDx(-2);
                    ball[i].setDy(-ball[i].getDy());
                } else if (center >= frist && center < second) {
                    ball[i].setDx(-1);
                    ball[i].setDy(-ball[i].getDy());
                } else if (center >= second && center < third) {
                    ball[i].setDx(0);
                    ball[i].setDy(-ball[i].getDy());
                } else if (center >= third && center < fourth) {
                    ball[i].setDx(1);
                    ball[i].setDy(-ball[i].getDy());
                } else if (center > fourth) {
                    ball[i].setDx(2);
                    ball[i].setDy(-ball[i].getDy());
                }
                ball[i].setLocation_y(Paddle.getLocation_y() - ball[i].getSize_y());
            }
        }
    }

    @Override
    public void draw_gifts() {
        int number_of_gifts = (int) (10 + Math.random() * 20);
        gifts = new Gift[number_of_gifts];
        System.out.println(number_of_gifts);
        for (int i = 0; i < number_of_gifts; i++) {
            int random_gift = (int) (1 + Math.random() * 4);
            while (true) {

                int random_x = (int) ((int) 0 + Math.random() * 19);
                int random_y = (int) ((int) 0 + Math.random() * 19);
                if (bricks[random_x][random_y].getGift_number() == -1 && bricks[random_x][random_y].isHit() == false && bricks[random_x][random_y].getLocation_x() > 0 && bricks[random_x][random_y].getLocation_y() > 0) {
                    bricks[random_x][random_y].setGift_number(i);
                    if (random_gift % 5 == 0) {
                        gifts[i] = new Extend_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 5 == 1) {
                        gifts[i] = new Shrink_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 5 == 2) {
                        gifts[i] = new slowball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 5 == 3) {
                        gifts[i] = new fastball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 5 == 4) {
                        gifts[i] =new life_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    }

                    break;
                }
            }

        }
    }

    @Override
    public void time() {
        leveltime = System.currentTimeMillis();

        timer = new Timer();
        timer.scheduleAtFixedRate(new GameLoop(), 1000, 20);
    }

    class GameLoop extends TimerTask {

        /* This is where the game loop logic goes. Here, the objects are
         * updated and then drawn to the panel.
         * Code for updating game objects should be separate from the code for
         * rendering them. That's why, the code for updating the ball and the
         * paddle and collision handling is not included in the paint() method.
         */
        @Override
        public void run() {
            if (level_paddle_start == false && level_introduction_message == false) {
                leveltime = System.currentTimeMillis();
                collision();
                Paddle.move();

               for (int i = 0; i < numberofball; i++) {
                    if (!Game_over()) {
                    ball[i].move();// Update the paddle's position 
                    }
                }
            }
            repaint();

           
            
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundimage, background_x1 % 1000, background_y1 % 1000, 1000, 1000, this);
        g2d.drawImage(backgroundimage, background_x2 % 1000, background_y2 % 1000, 1000, 1000, this);
        background_y1 += 1;

        background_y2 += 1;
        if (background_y1 >= 1000) {
            background_y1 = -1000;
        }
        if (background_y2 >= 1000) {
            background_y2 = -1000;
        }
        System.out.println(ball[0].getLocation_x() + "      " + ball[0].getLocation_y());
        if (level_introduction_message == true) {

            g2d.drawImage(introduction_messange, introduction_message_x, introduction_message_y, 650, 842, this);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            introduction_message_y -= 1;
            if (introduction_message_y == -1000) {
                level_introduction_message = false;
                level_paddle_start = true;

            }

        } else if (level_paddle_start == true) {

            if (paddle_ship_x < 400) {
                g2d.drawImage(paddle_ship, paddle_ship_x, paddle_ship_y, 300, 100, this);
                paddle_ship_x += 1;
            } else if (paddle_ship_x == 400) {

                g2d.drawImage(paddle_ship, paddle_ship_x, paddle_ship_y, 300, 100, this);
                g2d.drawImage(paddle_ship_open, paddle_ship_x - 15, paddle_ship_y + 90, 350, 850, this);
                g2d.drawImage(Paddle.getImage(), Paddle.getLocation_x(), Paddle.getLocation_y(), Paddle.getSize_x(), Paddle.getSize_y(), this);
                Paddle.setLocation_y(Paddle.getLocation_y() + 1);
                for (int i = 0; i < numberofball; i++) {
                    ball[i].setLocation_x(Paddle.getLocation_x() + 50);
                    ball[i].setLocation_y(Paddle.getLocation_y() - 25);
                    g2d.drawImage(ball[i].getImage(), ball[i].getLocation_x(), ball[i].getLocation_y(), ball[i].getSize_x(), ball[i].getSize_y(), this);

                }

                if (Paddle.getLocation_y() == 900) {
                    paddle_ship_x += 1;

                }

            } else if (paddle_ship_x > 400) {
                g2d.drawImage(paddle_ship, paddle_ship_x, paddle_ship_y, 300, 100, this);
                g2d.drawImage(Paddle.getImage(), Paddle.getLocation_x(), Paddle.getLocation_y(), Paddle.getSize_x(), Paddle.getSize_y(), this);
                for (int i = 0; i < numberofball; i++) {
                    ball[i].setLocation_x(Paddle.getLocation_x() + 50);
                    ball[i].setLocation_y(Paddle.getLocation_y() - 25);
                    g2d.drawImage(ball[i].getImage(), ball[i].getLocation_x(), ball[i].getLocation_y(), ball[i].getSize_x(), ball[i].getSize_y(), this);

                }
                paddle_ship_x += 1;
            }
            if (paddle_ship_x == 1000) {
                this.level_paddle_start = false;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        } else if (level_paddle_start==false && !Game_over()){
            g2d.drawImage(Paddle.getImage(), Paddle.getLocation_x(), Paddle.getLocation_y(), Paddle.getSize_x(), Paddle.getSize_y(), this);
            for (int i = 0; i < numberofball; i++) {
                g2d.drawImage(ball[i].getImage(), ball[i].getLocation_x(), ball[i].getLocation_y(), ball[i].getSize_x(), ball[i].getSize_y(), this);
            }
            number_of_bricks = 0;
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (bricks[i][j].isHit() == false && bricks[i][j].getLocation_x() > 0 && bricks[i][j].getLocation_y() > 0) {
                        g2d.drawImage(bricks[i][j].getImage(), bricks[i][j].getLocation_x(), bricks[i][j].getLocation_y(), bricks[i][j].getSize_x(), bricks[i][j].getSize_y(), this);
                        number_of_bricks++;
                    }
                }
            }
            
             if(winner())
            {
 if (number_of_bricks == 0) {
                user.setLevel(2);
                timer.cancel();
                FinalArkanoidIsa.frame2.setVisible(false);
                FinalArkanoidIsa.frame = new JFrame();
                FinalArkanoidIsa.frame = new JFrame("Bonus++;");
                FinalArkanoidIsa.frame.setSize(1200, 1000);
                FinalArkanoidIsa.frame.setLocation(500, 0);
                FinalArkanoidIsa.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                FinalArkanoidIsa.frame.add(new plan(user));
                FinalArkanoidIsa.frame.setVisible(true);
            
 }
            for (Gift gift : gifts) {
                if (gift.show == true) {
                    g2d.drawImage(gift.getImage(), gift.getLocation_x(), gift.getLocation_y(), gift.getSize_x(), gift.getSize_y(), this);
                    gift.Move_y();
                }
           
               

            }
            shotgun_gift.movebullet(Paddle, leveltime);
            for (int i = 0; i < shotgun_gift.number_of_movebullet; i++) {
                if (shotgun_gift.bullets[i].isRun() == true) {

                    g2d.drawImage(shotgun_gift.bullets[i].getImage(), shotgun_gift.bullets[i].getLocation_x(), shotgun_gift.bullets[i].getLocation_y(), shotgun_gift.bullets[i].getSize_x(), shotgun_gift.bullets[i].getSize_y(), this);
                }
            }

        }   else if (level_paddle_start==false && Game_over()) {

            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.CYAN);
            g.drawString("Game Over", 300, 300);
            System.out.println("Game Over");

        }
    }
  public boolean winner() {
        if (number_of_bricks == 0) {
            return true;
        }
        return false;
    }

       public boolean Game_over() {
        for (int i = 0; i < numberofball; i++) {
            if (ball[i].location_y  > Paddle.getLocation_y()) {

                if (numberofball == 1) {
                    if (numberoflifes > 1) {
                        numberoflifes--;
                        System.out.println(numberoflifes);
                    } else if (numberoflifes == 1) {

                       
                        return true;
                    }
                } else if (numberofball > 1) {
                    numberofball--;
                }

            }
        }
      
        return false;
    }
}
