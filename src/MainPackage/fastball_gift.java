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
public class fastball_gift extends Gift{

    public fastball_gift() {
    }

    public fastball_gift(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("fast_ball_gift.png");
        this.size_x=45;
        this.size_y=11;
    }
    
    
}
