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
public class Gift extends Commons {

    protected boolean show = false;
    protected boolean start = false;
    protected static long start_time=0;

    protected Gift() {
    }

    protected Gift(int location_x, int location_y) {
        super(location_x, location_y);

    }

    protected void Move_y() {

        location_y += 2;

    }

}
