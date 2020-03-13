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
public class Bullet extends Commons{
   protected boolean run;
 protected  boolean end;

    public Bullet() {
        super.setImage("bullet.png");
        this.size_x=10;
        this.size_y=30;
        this.run=false;
     
    }

    public Bullet(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("bullet.png");
          this.size_x=10;
        this.size_y=30;
          this.run=false;
          end=false;
    
        
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
    public  void move_ny(){
        if(location_y>=-30)
           location_y-=2;
        else 
            location_y=-30;
    }
     public  void move_py(){
        if(location_y<=1000)
           location_y+=2;
        else 
            location_y=1000;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
    
    
    
}
