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
public class shotgun_gift extends Gift {

    static int first2 = 0;
    static int second2 = 1;

    public static int number_of_bullet = 0;
    static public int number_of_movebullet = 0;
    static public  Bullet[] bullets;

    public boolean isStart() {
        return start;
    }

    public int getNumber_of_bullet() {
        return number_of_bullet;
    }

    public int getNumber_of_movebullet() {
        return number_of_movebullet;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    

    public shotgun_gift() {

    }

    public shotgun_gift(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("shotgun_gift.png");
        this.size_x = 45;
        this.size_y = 11;

        bullets = new Bullet[1000];
        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet();
        }
        first2 = 0;
        second2 = 1;

    }

    public static void movebullet(paddle Paddle, long time_now) {
        boolean end_run = false;
        int segment = Paddle.getSize_x() / 5;
        int first = Paddle.getLocation_x() + segment;
        int second = Paddle.getLocation_x() + segment * 4;
        long small = first2 * 1000 + start_time;
        long large = second2 * 1000 + start_time;
        //  System.out.println(first2+"      "+ second2+"            "+small+"        "+large+"               "+time_now);
        if (number_of_movebullet < number_of_bullet && small <= time_now && large >= time_now) {
            number_of_movebullet += 2;
            first2 += 1;
            second2 += 1;
        } else {
            end_run = true;

        }

        for (int i = 0; i < number_of_movebullet; i++) {

            if (i == number_of_movebullet - 2 && end_run == false) {

                bullets[i].setLocation_x(first);
                bullets[i].setLocation_y(Paddle.getLocation_y() + 5);
                bullets[i].setRun(true);

            } else if (i == number_of_movebullet - 1 && end_run == false) {
                bullets[i].setLocation_x(second);
                bullets[i].setLocation_y(Paddle.getLocation_y() + 5);
                bullets[i].setRun(true);

            }
            if (bullets[i].getLocation_y() > -50) {
                bullets[i].move_ny();
            } else {
                bullets[i].setRun(false);
            }

        }

    }

}
