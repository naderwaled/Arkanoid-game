/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.time.Instant;

/**
 *
 * @author nader
 */
public class Laser_gift extends Gift{

 public static Laser left_laser;
  public static Laser right_laser;
 public static int  duration;
 protected static long start_time;
 Instant starts = Instant.now();

   

    public Laser_gift() {
    }

    public Laser_gift(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("laser_gift.png");
        this.left_laser=new Laser();
           this.right_laser=new Laser();
        this.start=false;
        this.size_x=45;
        this.size_y=11;
        duration  = 0;
        start_time=0;
    }
    public static void movelaser(paddle Paddle){
     int segment = Paddle.getSize_x() / 5;
        int frist = Paddle.getLocation_x() + segment;
        int second = Paddle.getLocation_x() + segment * 4;
        left_laser.setLocation_x(frist);
        
        
        right_laser.setLocation_x(second);
        
    }
    
    
   
    
}
