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
public class Extend_gift extends Gift{
public static int  duration  = 0;
protected static long start_time=0;
    public Extend_gift() {
        
    }

    public Extend_gift(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("extend_gift.png");
        this.size_x=45;
        this.size_y=11;
        }
   
    
    
    


}