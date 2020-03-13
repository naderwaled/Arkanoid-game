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
public class Laser extends Commons{

        public Laser() {
            super.setImage("laser.png");
        }

        public Laser(int location_x, int location_y) {
            super(location_x, location_y);
            super.setImage("laser.png");
        }
    
    }