package MainPackage;

import javax.swing.JPanel;
import javax.sound.sampled.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import static javax.swing.Spring.width;

public class paddle extends Commons {

    private KeyHandler keyHandler = null; // Instance of the key event handler.
    private MouseHandler mouseHandler = null; // Instance of the mouse event handler.

    public paddle(int location_x, int location_y) {
        super(location_x, location_y);
        super.setImage("normal paddle.png");

    }

    public void move() {

        if (dx < 0 && location_x <= 0) {
            location_x = 0;
        } else if (dx > 0 && location_x >= (1000 - this.size_x - 25)) {
            location_x = 1000 - this.size_x - 25;
        } else {
            location_x += dx;
        }

    }

    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                dx = -10;
            }
            if (key == KeyEvent.VK_RIGHT) {
                dx = 10;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                dx = 0;
            }
            if (key == KeyEvent.VK_RIGHT) {
                dx = 0;
            }
        }
    }

    public KeyHandler getKeyHandler() {
        if (keyHandler == null) {
            keyHandler = new KeyHandler();
        }
        return keyHandler;
    }

    class MouseHandler extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            int mouseX = e.getX();

            setLocation_x(mouseX - (1000 / 5) + 16);

        }

    }

    // Getter method for the key handler
    public MouseHandler getMouseHandler() {
        if (mouseHandler == null) {
            mouseHandler = new MouseHandler();
        }
        return mouseHandler;
    }

    public void reset() {
        setImage("normal paddle.png");
    }

    public void Shrink() {
        //setImage("shrinked paddle.png");
        // getRect();
        this.size_x = 88;
        this.size_y = 22;
    }

    public void Extend() {
     //   setImage("extended paddle.png");
        //   getRect();

      //getLocation_x();
        //getLocation_y();
        this.size_x = 234;
        this.size_y = 22;
    }

}
