/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import sun.security.util.Length;

/**
 *
 * @author nader
 */
public class Shrink_gift extends Gift {
   protected static long start_time=0;
   public static int  duration  = 0;
    public Shrink_gift() {
    }

    public Shrink_gift(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("shrink_gift.png");
        this.size_x = 45;
        this.size_y = 11;
    }

}
