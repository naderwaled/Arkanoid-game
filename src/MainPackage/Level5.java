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

/**
 *
 * @author win 10
 */
public class Level5 extends Level {

    int number_of_bricks;
    int scale = 0;
    Enemy enemy1;
    Enemy enemy2;

    public Level5(User u) {
        setDoubleBuffered(true);
        // Set the focus to the GamePanel for keyboard events to work.
        setFocusable(true);
        // Start up the game
        setBounds(197, 0, 1000, 1000);
        number_of_bricks = 69;
        // f.jLabel11.setText("5");
        background_x1 = background_x1 = 0;
        background_y1 = -1000;
        background_y2 = 0;
        introduction_message_x = 150;
        introduction_message_y = 1000;
        paddle_ship_x = -300;
        paddle_ship_y = 150;
user=u;

        initGame();
        enemy1 = new Enemy(300, 150);
        enemy2 = new Enemy(700, 150);
    }

    @Override
    public void draw_bricks() {

        bricks = new Bricks[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                bricks[i][j] = new Bricks(0, false, 0, 0, "gray.png"); //3amlaha tt5bt mara
            }
        }
//////////////////////////////////////////////////////drawing letter B//////////////////////////////////////////////////////////////////////////////////
        int scale_x = 10;
        int scale_y = 170;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                if ((j == 1 && (i == 0 || i == 1 || i == 2 || i == 5)) || (j == 2 && (i == 0 || i == 1 || i == 2))) {
                    scale_x += 50;
                } else {
                    if (!bricks[i][j].isHit()) {
                        bricks[i][j].setLocation_x(scale_x);
                        bricks[i][j].setLocation_y(scale_y);
                    }
                    scale_x += 50;
                }

            }
            scale_y += 20;
            scale_x = 10;
        }
        ///////////////////////////////////////////////letter O//////////////////////////////////////////////////////
        scale_y = 220;
        scale_x += 155;
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 2 || i == 6) {
                scale_x += 50;
            } else if (i == 1) {
                scale_x -= 25;
                scale_y += 20;
            } else if (i == 3 || i == 5) {
                scale_x -= 75;
                scale_y += 20;
            } else if (i == 4) {
                scale_x += 100;
            } else if (i == 7) {
                scale_x -= 25;
                scale_y += 20;
            }
            if (!bricks[8][i].isHit()) {
                bricks[8][i].setLocation_x(scale_x);
                bricks[8][i].setLocation_y(scale_y);
            }

        }
        /////////////////////////////////////////////////////letter N////////////////////////////////////////////////////////
        scale_x = 320;
        scale_y = 205;
        for (int i = 9; i < 15; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0 || (i == 9 || i == 10) && (j != 0 || j != 2) || j == 2) {
                    bricks[i][j].setLocation_x(scale_x);
                    bricks[i][j].setLocation_y(scale_y);

                }
                scale_x += 50;
            }
            scale_y += 20;
            scale_x = 320;
        }
        ////////////////////////////////////////////////////////letter u////////////////////////////////////////////////////////////////
        scale_x = 480;
        scale_y = 205;
        for (int i = 9; i < 15; i++) {
            for (int j = 3; j < 6; j++) {
                if (j == 3 || ((i == 13 || i == 14) && (j != 3 || j != 5)) || j == 5) {
                    if (!bricks[i][j].isHit()) {
                        bricks[i][j].setLocation_x(scale_x);
                        bricks[i][j].setLocation_y(scale_y);
                    }
                }
                scale_x += 50;
            }
            scale_y += 20;
            scale_x = 480;
        }

        ///////////////////////////////////////////////////letter S//////////////////////////////////////////////////////////////////////////
        scale_y = 225;
        scale_x = 640;
        for (int i = 15; i < 20; i++) {
            for (int j = 0; j < 2; j++) {
                if ((i == 16 && j == 1) || (i == 18 && j == 0)) {
                    scale_x += 50;
                } else {
                    if (!bricks[i][j].isHit()) {
                        bricks[i][j].setLocation_x(scale_x);
                        bricks[i][j].setLocation_y(scale_y);
                    }
                    scale_x += 50;
                }
            }
            scale_y += 20;
            scale_x = 640;
        }
        /////////////////////////////////////////////////////////drawing ++/////////////////////////////////////////////////////////////////////////
        scale_x = 755;
        scale_y = 235;
        for (int i = 3; i < 7; i++) {
            if (i == 3 || i == 7)//|| i==5)
            {
                scale_x += 25;
            } else if (i == 5 || i == 9) {
                scale_x += 50;
            } else if (i == 4 || i == 6 || i == 8 || i == 10) {
                scale_x -= 25;
                scale_y += 20;
            }
            if (!bricks[10][i].isHit()) {
                bricks[19][i].setLocation_x(scale_x);
                bricks[19][i].setLocation_y(scale_y);
            }
        }
        scale_x = 860;
        scale_y = 235;
        for (int i = 7; i < 11; i++) {
            if (i == 7) {
                scale_x += 25;
            } else if (i == 9) {
                scale_x += 50;
            } else if (i == 8 || i == 10) {
                scale_x -= 25;
                scale_y += 20;
            }
            if (!bricks[10][i].isHit()) {
                bricks[19][i].setLocation_x(scale_x);
                bricks[19][i].setLocation_y(scale_y);
            }

        }

    }

    @Override
    public void collision() {
        //collision of ball wit paddle

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
            }
        }
        ////collision of ball with enemy

        //collision of ball with bricks
        for (int k = 0; k < numberofball; k++) {
            for (int i = 0; i < 20; i++) {
                boolean is_hited = false;
                for (int j = 0; j < 20; j++) {
                    if (ball[k].getRect().intersects(bricks[i][j].getRect()) && bricks[i][j].isHit() == false) {
                        // here
                        is_hited = true;

                        if (bricks[i][j].getNumberofhits() <= 3 && bricks[i][j].getNumberofhits() > 0) {
                            bricks[i][j].setNumberofhits(bricks[i][j].getNumberofhits() - 1);
                            bricks[i][j].setImage("broken_gray.png");
                        } else if (bricks[i][j].getNumberofhits() <= 0) {
                            bricks[i][j].setHit(true);
                            number_of_bricks--;
                           // System.out.println(number_of_bricks);
                            // logo.jTextField2.setText((69 - number_of_bricks) + " / 69");
                            if (bricks[i][j].getGift_number() > 0) {
                                gifts[bricks[i][j].getGift_number()].show = true;
                            }
                        }
                        //bricks[i][j].setHit(true);
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
                    } else {
                    }
                }
                if (is_hited) {
                    break;
                }
            }
        }

        for (Gift gift : gifts) {
            if (Paddle.getRect().intersects(gift.getRect()) && gift.show == true) {
                gift.show = false;

                if (gift instanceof Shrink_gift) {
                    gift.start_time = System.currentTimeMillis();
                    ((Shrink_gift) gift).start = true;
                    gift.show = false;
                    Paddle.Shrink();
                } else if (gift instanceof Extend_gift) {
                    gift.start_time = System.currentTimeMillis();
                    ((Extend_gift) gift).start = true;
                    gift.show = false;
                    Paddle.Extend();
                } else if (gift instanceof slowball_gift) {
                    gift.start_time = System.currentTimeMillis();
                    ((slowball_gift) gift).start = true;
                    for (int i = 0; i < numberofball; i++) {
                        ball[i].setSpeed(4);
                    }

                } /*else if (gift instanceof FireBall) {
                 for (int i = 0; i < numberofball; i++) {
                 ball[i].setFired(true);
                 ball[i].setImage("FireBall.png");
                 }
                 } */ else if (gift instanceof fastball_gift) {
                    for (int i = 0; i < numberofball; i++) {
                        ball[i].setSpeed(10);
                    }
                } else if (gift instanceof shotgun_gift) {
                    gift.start_time = System.currentTimeMillis();

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
            if (gift instanceof shotgun_gift) {
                if (((shotgun_gift) gift).isStart() == true) {
                    for (int i = 0; i < ((shotgun_gift) gift).getNumber_of_movebullet(); i++) {
                        for (int j = 0; j < 20; j++) {
                            for (int k = 0; k < 20; k++) {
                                if (((shotgun_gift) gift).bullets[i].getRect().intersects(bricks[j][k].getRect()) && bricks[j][k].isHit() == false) {
                                    // here 
                                    ((shotgun_gift) gift).bullets[i].setRun(false);
                                    bricks[j][k].setNumberofhits(bricks[j][k].getNumberofhits() - 2);
                                    if (bricks[j][k].getNumberofhits() <= 3 && bricks[j][k].getNumberofhits() > 0) {
                                        bricks[j][k].setImage("broken_gray.png");
                                    } else if (bricks[j][k].getNumberofhits() <= 0) {
                                        bricks[j][k].setHit(true);
                                        number_of_bricks--;
                                        System.out.println(number_of_bricks);
                                        if (bricks[j][k].getGift_number() > 0) {
                                            gifts[bricks[j][k].getGift_number()].show = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (gift instanceof Laser_gift && ((Laser_gift) gift).start == true) {

                boolean leftcollision = false;
                boolean rightcollision = false;
                Rectangle[] rectangle = new Rectangle[8];
                for (int i = 0; i < 8; i++) {
                    rectangle[i] = new Rectangle(0, 175 + 20 * i, 1000, 20);
                }
                start_left_laser:
                for (int i = 7; i >= 0; i--) {
                    for (int j = 0; j < 20; j++) {
                        for (int k = 0; k < 20; k++) {
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
                Laser_gift.left_laser.setSize_y( Paddle.getLocation_y());     }
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
            } else if (gift instanceof Extend_gift && gift.start == true) {
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
        //collision of enemy with ball
        if(enemy1.isShow()==true)
        {
            for(int i=0;i<numberofball;i++)
            {
                if(ball[i].getRect().intersects(enemy1.getRect()))
                {
                    enemy1.setNumber_enemy1_hits(enemy1.getNumber_enemy1_hits()-1);
                      System.out.print("Toka"+enemy1.getNumber_enemy1_hits());
                    if(enemy1.getNumber_enemy1_hits()==0)
                        enemy1.setShow(false);
                }
            }
        }
        
             if(enemy2.isShow()==true)
        {
            for(int i=0;i<numberofball;i++)
            {
                if(ball[i].getRect().intersects(enemy2.getRect()))
                {
                    enemy2.setNumber_enemy1_hits(enemy2.getNumber_enemy1_hits()-1);
                    System.out.print("ALAA"+enemy1.getNumber_enemy1_hits());
                    if(enemy2.getNumber_enemy2_hits()==0)
                        enemy2.setShow(false);
                }
            }
        }

    }

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
        addMouseMotionListener(Paddle.getMouseHandler());
    }

    public void time() {
        leveltime = System.currentTimeMillis();
        timer = new Timer();
        timer.scheduleAtFixedRate(new Level5.GameLoop(), 1000, 20);
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

        // System.out.println(ball[0].getLocation_x() + "      " + ball[0].getLocation_y());
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
        } else if (level_paddle_start == false && !Game_over()) {

            g2d.drawImage(Paddle.getImage(), Paddle.getLocation_x(), Paddle.getLocation_y(), Paddle.getSize_x(), Paddle.getSize_y(), this);

            for (int i = 0; i < numberofball; i++) {
                g2d.drawImage(ball[i].getImage(), ball[i].getLocation_x(), ball[i].getLocation_y(), ball[i].getSize_x(), ball[i].getSize_y(), this);
            }
            if (number_of_bricks > 0) {
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (bricks[i][j].isHit() == false && bricks[i][j].getLocation_x() > 0 && bricks[i][j].getLocation_y() > 0) {
                            g2d.drawImage(bricks[i][j].getImage(), bricks[i][j].getLocation_x(), bricks[i][j].getLocation_y(), bricks[i][j].getSize_x(), bricks[i][j].getSize_y(), this);
                        }
                    }
                }
                if (winner()) {
                    //codee
                }
                for (Gift gift : gifts) {
                    if (gift.show == true) {
                        g2d.drawImage(gift.getImage(), gift.getLocation_x(), gift.getLocation_y(), gift.getSize_x(), gift.getSize_y(), this);
                        gift.Move_y();
                    }
                    if (gift instanceof shotgun_gift && ((shotgun_gift) gift).isStart() == true) {

                        ((shotgun_gift) gift).movebullet(Paddle, leveltime);
                        for (int i = 0; i < ((shotgun_gift) gift).getNumber_of_movebullet(); i++) {
                            if (((shotgun_gift) gift).bullets[i].isRun() == true) {
                                g2d.drawImage(((shotgun_gift) gift).bullets[i].getImage(), ((shotgun_gift) gift).bullets[i].getLocation_x(), ((shotgun_gift) gift).bullets[i].getLocation_y(), ((shotgun_gift) gift).bullets[i].getSize_x(), ((shotgun_gift) gift).bullets[i].getSize_y(), this);
                            }
                        }
                    }
                    if (gift instanceof Laser_gift && ((Laser_gift) gift).start == true) {
                        ((Laser_gift) gift).movelaser(Paddle);

                        g2d.drawImage(((Laser_gift) gift).left_laser.getImage(), ((Laser_gift) gift).left_laser.getLocation_x(), ((Laser_gift) gift).left_laser.getLocation_y(), ((Laser_gift) gift).left_laser.getSize_x(), ((Laser_gift) gift).left_laser.getSize_y(), this);
                        g2d.drawImage(((Laser_gift) gift).right_laser.getImage(), ((Laser_gift) gift).right_laser.getLocation_x(), ((Laser_gift) gift).right_laser.getLocation_y(), ((Laser_gift) gift).right_laser.getSize_x(), ((Laser_gift) gift).right_laser.getSize_y(), this);
                    }

                }

            } else {
                if (enemy1.getNumber_enemy1_hits() > 0) {
                    enemy1.setShow(true);
                    g2d.drawImage(enemy1.getImage(), enemy1.getLocation_x(), enemy1.getLocation_y(), enemy1.getSize_x(), enemy1.getSize_y(), this);
                    enemy1.move(leveltime);
                    for (int i = 0; i < enemy1.getNumber_of_movebullet(); i++) {
                        g2d.drawImage(enemy1.bullet[i].getImage(), enemy1.bullet[i].getLocation_x(), enemy1.bullet[i].getLocation_y(), enemy1.bullet[i].getSize_x(), enemy1.bullet[i].getSize_y(), this);
                    }
                }
                if (enemy2.getNumber_enemy1_hits() > 0) {
                     enemy2.setShow(true);
                    g2d.drawImage(enemy2.getImage(), enemy2.getLocation_x(), enemy2.getLocation_y(), enemy2.getSize_x(), enemy2.getSize_y(), this);
                    enemy2.move(leveltime);
                    for (int i = 0; i < enemy2.getNumber_of_movebullet(); i++) {
                        g2d.drawImage(enemy2.bullet[i].getImage(), enemy2.bullet[i].getLocation_x(), enemy2.bullet[i].getLocation_y(), enemy2.bullet[i].getSize_x(), enemy2.bullet[i].getSize_y(), this);
                    }
                }
            }
        } else if (level_paddle_start == false && Game_over()) {

            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.CYAN);
            g.drawString("Game Over", 300, 300);
            System.out.println("Game Over");

        }
    }

    void initGame() {
         this.backgroundimage = new ImageIcon(this.getClass().getResource("back.jpg")).getImage();
        this.introduction_messange = new ImageIcon(this.getClass().getResource("level 5 rocket.png")).getImage();
        this.paddle_ship = new ImageIcon(this.getClass().getResource("paddle ship closed.png")).getImage();
        this.paddle_ship_open = new ImageIcon(this.getClass().getResource("x.png")).getImage();

        time();
        draw_paddle();
        draw_ball();
        draw_bricks();
        //
        draw_gifts();

    }

    
    public boolean winner() {
        if (number_of_bricks == 0) {
            return true;
        }
        return false;
    } ///////////////// EDIT

    public boolean Game_over() {
        for (int i = 0; i < numberofball; i++) {
            if (ball[i].getLocation_y() + ball[i].getSize_y() > Paddle.getLocation_y()) {

                if (numberofball == 1) {
                    if (numberoflifes > 1) {
                        numberoflifes--;
                     user.setLives(user.getLives()-1);


                    } else if (numberoflifes == 1) {
                        return true;

                    }
                } else if (numberofball > 1) {
                    numberofball--;
                    return false;
                }

            } else if (enemy1.getNumber_enemy1_hits() == 0 && enemy2.getNumber_enemy2_hits()== 0) {
                return true;
            }

        }
        return false;

    }

   
  

    @Override
    public void draw_gifts() {
  int number_of_gifts = 29;
        gifts = new Gift[number_of_gifts];
        //System.out.println(number_of_gifts);
        for (int i = 0; i < number_of_gifts; i++) {
            int random_gift = (int) (1 + Math.random() * 7);
            while (true) {
                int random_x = (int) ((int) 0 + Math.random() * 19);
                int random_y = (int) ((int) 0 + Math.random() * 19);
                //System.out.println(random_x + "\t" + random_y);
                // System.out.println(bricks[random_x][random_y]);
                if (bricks[random_x][random_y].getGift_number() == -1 && bricks[random_x][random_y].isHit() == false && bricks[random_x][random_y].getLocation_x() > 0 && bricks[random_x][random_y].getLocation_y() > 0) {
                    bricks[random_x][random_y].setGift_number(i);
                    if (random_gift % 7 == 0) {
                        gifts[i] = new Extend_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 7 == 1) {
                        gifts[i] = new Shrink_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 7 == 2) {
                        gifts[i] = new slowball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 7 == 3) {
                        gifts[i] = new fastball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 7 == 4) {
                        gifts[i] = new shotgun_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 7 == 5) {
                        gifts[i] = new splitball_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    } else if (random_gift % 7 == 6) {
                        gifts[i] = new Laser_gift(bricks[random_x][random_y].getLocation_x(), bricks[random_x][random_y].getLocation_y());
                    }
                    break;
                }
            }

        }    }

    @Override
    public void Gift_paddle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ball_Bricks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ball_paddle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
