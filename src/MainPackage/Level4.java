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
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import MainPackage.Bricks;


/**
 *
 * @author win 10
 */
public class Level4 extends Level {

    int scale = 0;
int count=0;
    public Level4(User u) {
        setDoubleBuffered(true);
        // Set the focus to the GamePanel for keyboard events to work.
        setFocusable(true);
        // Start up the game
        setBounds(197, 0, 1000, 1000);

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
        this.introduction_messange = new ImageIcon(this.getClass().getResource("level 4 rocket.png")).getImage();
        this.paddle_ship = new ImageIcon(this.getClass().getResource("paddle ship closed.png")).getImage();
        this.paddle_ship_open = new ImageIcon(this.getClass().getResource("x.png")).getImage();

        time();
        draw_paddle();
        draw_ball();
        draw_bricks();
        draw_gifts();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        scale++;
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
        if (level_introduction_message == true) {

            g2d.drawImage(introduction_messange, introduction_message_x, introduction_message_y, 650, 842, this);
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            introduction_message_y -= 10;
            if (introduction_message_y == -1000) {
                level_introduction_message = false;
                level_paddle_start = true;

            }

        } else if (level_paddle_start == true) {

            if (paddle_ship_x < 400) {
                g2d.drawImage(paddle_ship, paddle_ship_x, paddle_ship_y, 300, 100, this);
                paddle_ship_x += 5;
            } else if (paddle_ship_x == 400) {

                g2d.drawImage(paddle_ship, paddle_ship_x, paddle_ship_y, 300, 100, this);
                g2d.drawImage(paddle_ship_open, paddle_ship_x - 15, paddle_ship_y + 90, 350, 850, this);
                g2d.drawImage(Paddle.getImage(), Paddle.getLocation_x(), Paddle.getLocation_y(), Paddle.getSize_x(), Paddle.getSize_y(), this);
                Paddle.setLocation_y(Paddle.getLocation_y() + 5);
                for (int i = 0; i < numberofball; i++) {
                    ball[i].setLocation_x(Paddle.getLocation_x() + 50);
                    ball[i].setLocation_y(Paddle.getLocation_y() - 25);
                    g2d.drawImage(ball[i].getImage(), ball[i].getLocation_x(), ball[i].getLocation_y(), ball[i].getSize_x(), ball[i].getSize_y(), this);

                }

                if (Paddle.getLocation_y() == 900) {
                    paddle_ship_x += 5;

                }

            } else if (paddle_ship_x > 400) {
                g2d.drawImage(paddle_ship, paddle_ship_x, paddle_ship_y, 300, 100, this);
                g2d.drawImage(Paddle.getImage(), Paddle.getLocation_x(), Paddle.getLocation_y(), Paddle.getSize_x(), Paddle.getSize_y(), this);
                for (int i = 0; i < numberofball; i++) {
                    ball[i].setLocation_x(Paddle.getLocation_x() + 50);
                    ball[i].setLocation_y(Paddle.getLocation_y() - 25);
                    g2d.drawImage(ball[i].getImage(), ball[i].getLocation_x(), ball[i].getLocation_y(), ball[i].getSize_x(), ball[i].getSize_y(), this);

                }
                paddle_ship_x += 5;
            }
            if (paddle_ship_x == 1000) {
                this.level_paddle_start = false;
                count=0;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        } else {
            g2d.drawImage(Paddle.getImage(), Paddle.getLocation_x(), Paddle.getLocation_y(), Paddle.getSize_x(), Paddle.getSize_y(), this);

            for (int i = 0; i < numberofball; i++) {
                g2d.drawImage(ball[i].getImage(), ball[i].getLocation_x(), ball[i].getLocation_y(), ball[i].getSize_x(), ball[i].getSize_y(), this);
            }
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (bricks[i][j].isHit() == false && bricks[i][j].getLocation_x() > 0 && bricks[i][j].getLocation_y() > 0) {
                        g2d.drawImage(bricks[i][j].getImage(), bricks[i][j].getLocation_x(), bricks[i][j].getLocation_y(), bricks[i][j].getSize_x(), bricks[i][j].getSize_y(), this);
                        if (scale % 40 == 0) {
                            bricks[i][j].setLocation_y(bricks[i][j].getLocation_y() + 1);
                            if (bricks[i][j].getGift_number() >= 0) {
                                gifts[bricks[i][j].getGift_number()].setLocation_x(bricks[i][j].getLocation_x());
                                gifts[bricks[i][j].getGift_number()].setLocation_y(bricks[i][j].getLocation_y());
                            }
                        }
                    }
                    if (bricks[i][j].getLocation_y() + bricks[i][j].getSize_y() == Paddle.getLocation_y()) {
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
                        g.setColor(Color.red);
                        g.drawString("Game Over", 300, 300);
                        System.out.println("Game Over");
                        break;
                    }
                }
            }
            if (number_of_bricks == 0) {
                g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
                g.setColor(Color.MAGENTA);
                g.drawString("Winner", 300, 300);
                System.out.println("Winnnnnneeeeeeerrrrrrrrrrrrrrrrrrrr");
            }
            for (Gift gift : gifts) {
                if (gift.show == true) {
                    g2d.drawImage(gift.getImage(), gift.getLocation_x(), gift.getLocation_y(), gift.getSize_x(), gift.getSize_y(), this);
                    gift.Move_y();
                }
//                if (gift instanceof shotgun_gift && ((shotgun_gift) gift).isStart() == true) {
//
//                    ((shotgun_gift) gift).movebullet(Paddle, leveltime);
//                    for (int i = 0; i < ((shotgun_gift) gift).getNumber_of_movebullet(); i++) {
//                        if (((shotgun_gift) gift).bullets[i].isRun() == true) {
//                            g2d.drawImage(((shotgun_gift) gift).bullets[i].getImage(), ((shotgun_gift) gift).bullets[i].getLocation_x(), ((shotgun_gift) gift).bullets[i].getLocation_y(), ((shotgun_gift) gift).bullets[i].getSize_x(), ((shotgun_gift) gift).bullets[i].getSize_y(), this);
//                        }
//                    }
//                }
                /*   if (gift instanceof Laser_gift && ((Laser_gift) gift).start == true) {
                    ((Laser_gift) gift).movelaser(Paddle);

                    g2d.drawImage(((Laser_gift) gift).left_laser.getImage(), ((Laser_gift) gift).left_laser.getLocation_x(), ((Laser_gift) gift).left_laser.getLocation_y(), ((Laser_gift) gift).left_laser.getSize_x(), ((Laser_gift) gift).left_laser.getSize_y(), this);
                    g2d.drawImage(((Laser_gift) gift).right_laser.getImage(), ((Laser_gift) gift).right_laser.getLocation_x(), ((Laser_gift) gift).right_laser.getLocation_y(), ((Laser_gift) gift).right_laser.getSize_x(), ((Laser_gift) gift).right_laser.getSize_y(), this);
                }*/

            }
             shotgun_gift.movebullet(Paddle, leveltime);
            for (int i = 0; i < shotgun_gift.number_of_movebullet; i++) {
                if (shotgun_gift.bullets[i].isRun() == true) {

                    g2d.drawImage(shotgun_gift.bullets[i].getImage(), shotgun_gift.bullets[i].getLocation_x(), shotgun_gift.bullets[i].getLocation_y(), shotgun_gift.bullets[i].getSize_x(), shotgun_gift.bullets[i].getSize_y(), this);
                }
            }
            if (Laser_gift.start_time + Laser_gift.duration > leveltime) {
                Laser_gift.movelaser(Paddle);

                g2d.drawImage(Laser_gift.left_laser.getImage(), Laser_gift.left_laser.getLocation_x(), Laser_gift.left_laser.getLocation_y(), Laser_gift.left_laser.getSize_x(), Laser_gift.left_laser.getSize_y(), this);
                g2d.drawImage(Laser_gift.right_laser.getImage(), Laser_gift.right_laser.getLocation_x(), Laser_gift.right_laser.getLocation_y(), Laser_gift.right_laser.getSize_x(), Laser_gift.right_laser.getSize_y(), this);

            }

        }
        //hrerreerererer
        Rectangle[] rectangle = new Rectangle[10];
        
            for (int i = 0; i < 10; i++) {
                rectangle[i] = new Rectangle(0,scale + 34 * i, 1000, 34);
                g.drawRect(0, scale+34*i, 1000, 34);
            }
    }

    @Override
    public void draw_bricks() {
        number_of_bricks = 0;
        bricks = new Bricks[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                bricks[i][j] = new Bricks(0, false, 0, 0, "purple.png");
            }
        }
        ////////////////////////////////////////////////////////L&U//////////////////////////////////////////////////////////////////////////
        int scale_x = 1;
        int scale_y = 1;
        for (int i = 4; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (!bricks[i][j].isHit()) {

                    number_of_bricks++;
                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);

                    if (bricks[i][j].getGift_number() >= 0) {
                        gifts[bricks[i][j].getGift_number()].setLocation_x(scale_x);
                        gifts[bricks[i][j].getGift_number()].setLocation_y(scale_y);
                    }
                }
                scale_x += 100;
            }
            scale_y += 34;
            scale_x = 1;
        }
///////////////////////////////////////////////////////////L&D///////////////////////////////////////////////////////////
        scale_y += 100;
        for (int i = 5; i < 10; i++) {
            for (int j = i; j >= 5; j--) {
                if (!bricks[i][j].isHit()) {

                    number_of_bricks++;
                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);

                    if (bricks[i][j].getGift_number() >= 0) {
                        gifts[bricks[i][j].getGift_number()].setLocation_x(scale_x);
                        gifts[bricks[i][j].getGift_number()].setLocation_y(scale_y);
                    }
                }
                scale_x += 100;
            }
            scale_y += 34;
            scale_x = 1;
        }
        /////////////////////////////////////////////////////R&U//////////////////////////////////////////////////////////////
        scale_x = 500;
        scale_y = 1;
        for (int i = 10; i < 15; i++) {
            for (int k = 10; k < i; k++) {
                scale_x += 100;
            }

            for (int j = i; j < 15; j++) {
                //bricks[i][j].setImage("yellow.png");
                if (!bricks[i][j].isHit()) {

                    number_of_bricks++;
                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);

                    if (bricks[i][j].getGift_number() >= 0) {
                        gifts[bricks[i][j].getGift_number()].setLocation_x(scale_x);
                        gifts[bricks[i][j].getGift_number()].setLocation_y(scale_y);
                    }
                }
                scale_x += 100;
            }
            scale_y += 34;
            scale_x = 500;
        }
//////////////////////////////////////////////////////R&D///////////////////////////////////////////////////////////////////
        scale_y += 100;
        scale_x = 400;
        for (int i = 15; i < 20; i++) {
            for (int k = 15; k < 35 - i; k++) {
                bricks[i][k].setImage("");
                scale_x += 100;
            }

            for (int j = 20; j <= i + 5; j++) {
                //bricks[i][j].setImage("gray.png");
                if (!bricks[i][j].isHit()) {

                    number_of_bricks++;
                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);

                    if (bricks[i][j].getGift_number() >= 0) {
                        gifts[bricks[i][j].getGift_number()].setLocation_x(scale_x);
                        gifts[bricks[i][j].getGift_number()].setLocation_y(scale_y);
                    }
                }
                scale_x += 100;
            }
            scale_y += 34;
            scale_x = 400;
        }
////////////////////////////////////////////////center///////////////////////////////////////////
        scale_x = 350;
        scale_y = 173;
        for (int i = 20; i < 23; i++) {
            for (int j = 0; j < 3; j++) {
                if (!bricks[i][j].isHit()) {

                    number_of_bricks++;
                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);

                    if (bricks[i][j].getGift_number() >= 0) {
                        gifts[bricks[i][j].getGift_number()].setLocation_x(scale_x);
                        gifts[bricks[i][j].getGift_number()].setLocation_y(scale_y);
                    }
                }
                scale_x += 100;
            }
            scale_x = 350;
            scale_y += 34;
        }
        System.out.println("numberOfBricks = " + number_of_bricks);
    }

    @Override
    public void collision() {
        ball_paddle();
        ball_Bricks();
        Gift_paddle();

    }

    @Override
    public void draw_ball() {
        ball = new Ball[3];
        for (int i = 0; i < 3; i++) {
            ball[i] = new Ball(520, 200);
        }
    }

    @Override
    public void draw_gifts() {
        int number_of_gifts = 20;
        gifts = new Gift[number_of_gifts];
        //System.out.println(number_of_gifts);
        for (int i = 0; i < number_of_gifts; i++) {
            int random_gift = (int) (1 + Math.random() * 10);
            while (true) {
                int random_x = (int) ((int) 0 + Math.random() * 29);
                int random_y = (int) ((int) 0 + Math.random() * 29);
                //System.out.println(random_x + "\t" + random_y);
                // System.out.println(bricks[random_x][random_y]);
                if (bricks[random_x][random_y].getGift_number() == -1 && bricks[random_x][random_y].isHit() == false && bricks[random_x][random_y].getLocation_x() > 0 && bricks[random_x][random_y].getLocation_y() > 0) {
                    bricks[random_x][random_y].setGift_number(i);
                    if (random_gift % 11 == 0) {
                        gifts[i] = new Extend_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 11 == 1) {
                        gifts[i] = new Shrink_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 11 == 2) {
                        gifts[i] = new slowball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 11 == 3) {
                        gifts[i] = new fastball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 11 == 4) {
                        gifts[i] = new shotgun_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 11 == 5) {
                        gifts[i] = new splitball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());

                    } else if (random_gift % 11 == 6) {
                        gifts[i] = new Laser_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    }
                    else if(random_gift%11==7){
                    gifts[i]= new fireball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    }
                    else if(random_gift%11==8){
                    gifts[i]= new life_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                            
                    }
                    else if(random_gift%11==9){
                    gifts[i]= new nextlevel_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    }
                    else if(random_gift%11==10){
                    gifts[i]= new Sticyball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    }

                    break;
                }
            }

        }
    }

    @Override
    public void draw_paddle() {
        Paddle = new paddle(490, 250);
        addKeyListener(Paddle.getKeyHandler());
        //addMouseMotionListener(Paddle.getMouseHandler());
    }

    @Override
    public void time() {
        leveltime = System.currentTimeMillis();
        timer = new Timer();
        timer.scheduleAtFixedRate(new Level4.GameLoop(), 1000, 20);
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
                }  else if (gift instanceof shotgun_gift) {
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
                    if (Laser_gift.duration == 0) {
                        Laser_gift.duration = 20000;
                        Laser_gift.start_time = leveltime;
                    } else {
                        Extend_gift.duration += 20000;
                    }
                }
                else if(gift instanceof fireball_gift){
                for(int i=0;i<numberofball;i++){
                ball[i].setImage("fireball.jpg");
                ball[i].setType("fireball");
                ((fireball_gift)gift).start=true;
                }
                }
                else if(gift instanceof life_gift){
                     ((life_gift)gift).start=true;
                     user.setLives(user.getLives()+1);
                }
                else if(gift instanceof  nextlevel_gift)
                {
                ((nextlevel_gift)gift).start=true;
                number_of_bricks=0;
                }
                else if(gift instanceof Sticyball_gift){
                ((Sticyball_gift)gift ).start=true;
                for(int i=0;i<numberofball;i++){
                ball[i].setType("sticyball");
                }
                }
            }
        }
        if (Laser_gift.start_time + Laser_gift.duration > leveltime) {
            boolean leftcollision = false;
            boolean rightcollision = false;
            Rectangle[] rectangle = new Rectangle[10];
            for (int i = 0; i < 10; i++) {
                rectangle[i] = new Rectangle(0,count + 34 * i, 1000, 34);
                if(scale%40==0)
                    count++;
            }
            start_left_laser:
            for (int i = 10; i >= 0; i--) {
                for (int j = 0; j < 30; j++) {
                    for (int k = 0; k < 30; k++) {
                        if (rectangle[i].intersects(bricks[j][k].getRect()) && bricks[j][k].getLocation_x() - 5 <= Laser_gift.left_laser.getLocation_x() && bricks[j][k].getLocation_x() + bricks[j][k].getSize_x() >= Laser_gift.left_laser.getLocation_x() && bricks[j][k].getLocation_x() > 0 && bricks[j][k].isHit() == false) {
                            Laser_gift.left_laser.setLocation_y(rectangle[i].y + 15);;
                            Laser_gift.left_laser.setSize_y(Paddle.getLocation_y() - (rectangle[i].y + 15));
                            leftcollision = true;
                            break start_left_laser;
                        }
                    }
                }

            }
            if (leftcollision == false) {
                Laser_gift.left_laser.setLocation_y(0); 
                Laser_gift.left_laser.setSize_y( Paddle.getLocation_y());
            }
            ///////RHS//////
            start_right_laser:
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 20; j++) {
                    for (int k = 0; k < 20; k++) {
                        if (rectangle[i].intersects(bricks[j][k].getRect()) && bricks[j][k].getLocation_x() - 5 <= Laser_gift.right_laser.getLocation_x() && bricks[j][k].getLocation_x() + bricks[j][k].getSize_x() >= Laser_gift.right_laser.getLocation_x() && bricks[j][k].getLocation_x() > 0 && bricks[j][k].isHit() == false) {
                            Laser_gift.right_laser.setLocation_y(rectangle[i].y + 15);
                            Laser_gift.right_laser.setSize_y(Paddle.getLocation_y() - (rectangle[i].y + 15));
                            rightcollision = true;
                            break start_right_laser;
                        }
                    }
                }

            }
            if (rightcollision == false) {
                Laser_gift.right_laser.setLocation_y(0); ;
                Laser_gift.right_laser.setSize_y( Paddle.getLocation_y());
            }
        } else {
            Laser_gift.duration = 0;
            Laser_gift.start_time = 0;
        }
        for (int i = 0; i < shotgun_gift.number_of_movebullet; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 20; k++) {
                    if (shotgun_gift.bullets[i].getRect().intersects(bricks[j][k].getRect()) && bricks[j][k].isHit() == false && shotgun_gift.bullets[i].run == true) {
                        // here 
                        shotgun_gift.bullets[i].setRun(false);

                        bricks[j][k].setHit(true);
                        if (bricks[j][k].getGift_number() > 0) {
                            gifts[bricks[j][k].getGift_number()].show = true;

                        }
                    }
                }
            }
        }
        for (Gift gift : gifts) {
           if (gift instanceof Extend_gift && gift.start == true) {
                long x = leveltime - ((Extend_gift) gift).start_time;

                if (x >= 20000) {
                    Paddle.reset();
                    gift.start = false;
                }
            } else if (gift instanceof Shrink_gift && gift.start == true) {
                long x = leveltime - ((Shrink_gift) gift).start_time;

                if (x >= 20000) {
                    Paddle.reset();
                    gift.start = false;
                }
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
            
            for (int i = 0; i < 30; i++) {
                boolean is_hited = false;
                for (int j = 0; j < 30; j++) {
                    if (ball[k].getRect().intersects(bricks[i][j].getRect()) && bricks[i][j].isHit() == false) {
                        // here 
                        is_hited = true;
                        bricks[i][j].setNumberofhits(bricks[i][j].getNumberofhits() - 1);
                        if (bricks[i][j].getNumberofhits() <= 3 && bricks[i][j].getNumberofhits() > 0) {
                            bricks[i][j].setImage("broken_purple.png");
                        } else if (bricks[i][j].getNumberofhits() <= 0) {
                            bricks[i][j].setHit(true);
                            number_of_bricks--;
                            System.out.println(number_of_bricks);

                            if (bricks[i][j].getGift_number() > 0) {
                                gifts[bricks[i][j].getGift_number()].show = true;
                            }
                        }
                        //bricks[i][j].setHit(true);
                        if(ball[k].getType()!="fireball"){
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
                        break;

                    }}
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
                int segment = Paddle.getSize_x() / 5;
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
                if(ball[i].getType()=="fireball"){
                    ball[i].setType("normal");
                 ball[i].reset_ball();
                }
                if(ball[i].getType()=="sticyball"){
                ball[i].setMove(false);
                }
            }
        }
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
                    if(ball[i].isMove()==true)
                    ball[i].move();// Update the paddle's position            
                }
            }
            if (number_of_bricks == 0) {
                user.setLevel(5);
                timer.cancel();
                FinalArkanoidIsa.frame2.setVisible(false);
                FinalArkanoidIsa.frame = new JFrame();
                FinalArkanoidIsa.frame = new JFrame("Bonus++;");
                FinalArkanoidIsa.frame.setSize(1200, 1000);
                FinalArkanoidIsa.frame.setLocation(500, 0);
                FinalArkanoidIsa.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                FinalArkanoidIsa.frame.add(new plan(user));
                FinalArkanoidIsa.frame.setVisible(true);
            } else {
                repaint();
            }
        }
    }
}
