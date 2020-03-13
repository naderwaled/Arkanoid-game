
package MainPackage;
import javax.swing.JFrame;

/**
 *
 * @author nader
 */
public class FinalArkanoidIsa {
static public JFrame frame;
static public  Frame_levels frame2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        frame= new JFrame("Bonus++;");
        frame.setSize(1000,1000);
        frame.setLocation(500, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new start());
         
        frame.setVisible(true);   
        
    }
    
}
