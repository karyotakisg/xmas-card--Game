package gr.aueb.xmascard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pacman extends JPanel implements KeyListener {
    private int x=0;
    private int y=0;
    private int dx=0;
    private int dy=0;
    Thread t = new Thread();
    public Pacman() {
        super();
      addKeyListener(this);
      setSize(1000,1000);
      setBackground(Color.BLACK);
      setFocusable(true);
      while(true){
        moveBall();
        repaint();
        try {
            t.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      }
    }
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.yellow);
        g.fillOval(x,y,50,50);
    }
    public void moveBall(){
        x=x+dx;
        y=y+dy;
    }
    
   //controls    
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    dx=-1;
                } else if (key == KeyEvent.VK_RIGHT) {
                    dx = 1;
                } else if (key == KeyEvent.VK_UP) {     
                    dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    dy = 1;
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {  
            dx=0;
            dy=0;

        }
}