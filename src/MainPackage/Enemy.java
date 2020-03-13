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
public class Enemy extends Commons {

    private long start_time;
    private int number_of_bullet;
    private int number_of_movebullet;
    private boolean show = false;
    private int number_enemy1_hits = 10;
    private int number_enemy2_hits = 10;

    public int getNumber_enemy1_hits() {
        return number_enemy1_hits;
    }

    public void setNumber_enemy1_hits(int number_enemy1_hits) {
        this.number_enemy1_hits = number_enemy1_hits;
    }

    public int getNumber_enemy2_hits() {
        return number_enemy2_hits;
    }

    public void setNumber_enemy2_hits(int number_enemy2_hits) {
        this.number_enemy2_hits = number_enemy2_hits;
    }
    

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getStart_time() {
        return start_time;
    }

    public int getNumber_of_bullet() {
        return number_of_bullet;
    }

    public int getNumber_of_movebullet() {
        return number_of_movebullet;
    }

    Bullet bullet[];
    int first;
    int second;

    public Enemy(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("enemy.png");
        bullet = new Bullet[1000];
        number_of_bullet = 1000;
        number_of_movebullet = 0;
        dx = dy = 4;
        this.start_time = start_time;
        first = 0;
        second = 1;
        for (int i = 0; i < 1000; i++) {
            bullet[i] = new Bullet();
        }
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void move(long time) {
        // Bounce ball back when it hits borders.
        location_x += dx;
        location_y += dy;

        // Reverse the horizontal direction.
        if (location_x <= 0) {
            location_x = 0;
            dx = -dx;
        }
        // Reverse the horizontal direction.
        if (location_x >= 1000 - this.size_x - 15) {
            location_x = 1000 - this.size_x - 15;
            dx = -dx;
        }
        // Reverse the vertical direction.
        if (location_y <= 0) {
            location_y = 0;
            dy = -dy;
        }
        if (location_y >= 500) {
            location_y = 500;
            dy = -dy;
        }
        movebullet(time);

        //for revese from the ground and that is for me only
    }

    public void movebullet(long time_now) {
        if (number_of_movebullet == 0) {
            this.start_time = time_now;
        }
        boolean end_run = false;

        long small = first * 1000 + start_time;
        long large = second * 1000 + start_time;
        System.out.println(first + "      " + second + "            " + small + "        " + large + "               " + time_now);
        if (number_of_movebullet < number_of_bullet && small <= time_now && large >= time_now) {
            this.number_of_movebullet += 1;
            first += 1;
            second += 1;
        } else {
            end_run = true;
        }

        for (int i = 0; i < number_of_movebullet; i++) {

            if (i == number_of_movebullet - 1 && end_run == false) {

                bullet[i].setLocation_x((this.location_x + (this.size_x) / 2) - 2);
                bullet[i].setLocation_y(this.location_y + this.size_y);
                bullet[i].setRun(true);

            }
            if (bullet[i].getLocation_y() <= 1000) {
                bullet[i].move_py();
            } else {
                bullet[i].setRun(false);
            }

        }

    }

}
