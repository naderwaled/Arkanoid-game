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
public class Bricks extends Commons{
    private int numberofhits;
    private boolean hit;
  
    private int gift_number=-1;

    public Bricks(int numberofhits, boolean hit, int location_x, int location_y , String path) {
        super(location_x, location_y);
        super.setImage(path);
        this.numberofhits = numberofhits;
        setHit(hit);        
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public void setNumberofhits(int numberofhits) {
        this.numberofhits = numberofhits;
    }

    public int getNumberofhits() {
        return numberofhits;
    }

    public int getGift_number() {
        return gift_number;
    }

    public void setGift_number(int gift_number) {
        this.gift_number = gift_number;
    }
    

}